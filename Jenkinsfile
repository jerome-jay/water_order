def AWS_ACCOUNTID = "458288760036"
def AWS_REGION = "ap-southeast-2"

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
        sh "docker build -t waterorder:${BUILD_NUMBER} ."
        sh "docker tag waterorder:${BUILD_NUMBER} ${AWS_ACCOUNTID}.dkr.ecr.${AWS_REGION}.amazonaws.com/waterorder:${BUILD_NUMBER}"
        sh "\$(aws ecr get-login --no-include-email --region ${AWS_REGION})"
        sh "docker push ${AWS_ACCOUNTID}.dkr.ecr.${AWS_REGION}.amazonaws.com/waterorder:${BUILD_NUMBER}"
      }
    }

    stage("Deploy") {
      steps {
        sh "echo pouet"
      }
    }

  } // stageS
  post {
    always {
        echo 'Cleaning workspace'
    }
  }
}


