
String artefactBucket = "beteasy-apigw-artefacts"

pipeline {
  agent any
  //options {
    //ansiColor('xterm')
  //}

  parameters {
      booleanParam(name: 'createInfra', defaultValue: false, description: 'Create infrastructure?')
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
              -vvvv Infrastructure/infra.yml
        """
      }
    }

    stage("Maven Build") {
      steps {
        sh "rm -rf *"
        checkout scm
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


