def AWS_ACCOUNTID = "458288760036"
def AWS_REGION = "ap-southeast-2"
def application = "waterorder"

pipeline {
  agent any
  tools { 
        maven 'Maven 3.6.3' 
        jdk 'CORRETTO_11' 
  }
  //options {
    //ansiColor('xterm')
  //}

  parameters {
      booleanParam(name: 'createInfra', defaultValue: true, description: 'Create infrastructure?')
  }

  stages {
    stage("Create Infrastructure") {
      when {
        expression {
          params.createInfra == true
        }
      }
      steps {
        sh  """ansible-playbook \\
              -vvvv Infrastructure/playbook.yml
        """
      }
    }

    stage("Maven Build") {
      steps {
        sh "mvn -DskipTests=true clean package"
      }
    }

    stage("Build Docker") {
      steps {
        sh "docker build -t ${application}:${BUILD_NUMBER} ."
        sh "docker tag ${application}:${BUILD_NUMBER} ${AWS_ACCOUNTID}.dkr.ecr.${AWS_REGION}.amazonaws.com/${application}:${BUILD_NUMBER}"
        sh "\$(aws ecr get-login --no-include-email --region ${AWS_REGION})"
        sh "docker push ${AWS_ACCOUNTID}.dkr.ecr.${AWS_REGION}.amazonaws.com/${application}:${BUILD_NUMBER}"
      }
    }

    stage('Get deploy code') {
        checkout([$class: 'GitSCM',
            branches: [[name: '*/master']],
            doGenerateSubmoduleConfigurations: false,
            extensions: [
                [$class: 'RelativeTargetDirectory', relativeTargetDir: "${WORKSPACE}/ecs-deploy"],
                [$class: 'CloneOption', noTags: true, reference: '', shallow: true, timeout: 2]
            ],
            submoduleCfg: [],
            userRemoteConfigs: [[url: "https://github.com/silinternational/ecs-deploy.git"]]
        ])
    }
    stage("Deploy") {
      steps {
        sh("${WORKSPACE}/ecs-deploy/ecs-deploy -c uat -n ${application} -i \"${AWS_ACCOUNTID}.dkr.ecr.${AWS_REGION}.amazonaws.com/${application}:${BUILD_NUMBER}\"")
        sh("aws ecs wait services-stable --cluster uat --services ${application}")
      }
    }

  } // stageS
  post {
    always {
        echo 'Cleaning workspace'
    }
  }
}


