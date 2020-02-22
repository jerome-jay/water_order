
String artefactBucket = "beteasy-apigw-artefacts"

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


