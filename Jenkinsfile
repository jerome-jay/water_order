def AWS_ACCOUNTID = "458288760036"
def AWS_REGION = "ap-southeast-2"
def application = "water-order"

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
    stage("Create Infrastructure") { // this stack creates the ECS cluster - this should be in its own jenkins job
      when {
        expression {
          params.createInfra == true
        }
      }
      steps {
        sh  """ansible-playbook \\
              -vvvv Infrastructure/playbook_infra.yml
        """
      }
    }

    stage("Create ECR") {
      when {
        expression {
          params.createInfra == true
        }
      }
      steps {
        sh  """ansible-playbook \\
              -vvvv Infrastructure/playbook_ecr.yml
              COUNTIMAGES=`aws ecr list-images --repository-name ${application} --region ${AWS_REGION} | jq '.imageIds | length'`
              if [[ $COUNTIMAGES -eq 0 ]];
              then
                echo "no images"
                exit 2
              fi
        """
      }
      
    }

    stage("Create ECS Service") {
      when {
        expression {
          params.createInfra == true
        }
      }
      steps {
        sh  """ansible-playbook \\
              -vvvv Infrastructure/playbook_ecs_service.yml
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
      steps {
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
    }

    stage("Deploy") {
      steps {
        sh("${WORKSPACE}/ecs-deploy/ecs-deploy -c uat -n ${application} -i \"${AWS_ACCOUNTID}.dkr.ecr.${AWS_REGION}.amazonaws.com/${application}:${BUILD_NUMBER}\" --region ap-southeast-2")
        sh("aws ecs wait services-stable --cluster uat --services ${application}")
      }
    }

    stage('Tag image as latest') {
      steps {
        sh("docker tag  ${AWS_ACCOUNTID}.dkr.ecr.${AWS_REGION}.amazonaws.com/${application}:${BUILD_NUMBER} ${AWS_ACCOUNTID}.dkr.ecr.${AWS_REGION}.amazonaws.com/${dockerRepoImage}:latest")
        sh("docker push ${AWS_ACCOUNTID}.dkr.ecr.${AWS_REGION}.amazonaws.com/${application}:latest")
      }
    }

  } // stageS
  post {
    always {
        echo 'Cleaning workspace'
    }
  }
}


