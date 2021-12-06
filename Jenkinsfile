pipeline {
    agent any

    stages {
        stage('Checkout codebase'){
            steps{
                clearWs()
                checkout scm: [
                    $class: 'GitSCM',
                    branches: [[name: '*/main']],
                    userRemoteConfigs: [[
                        credentialsId: 'github-ssh-key',
                        url: 'git@github.com:mnorm88/junit-automation.git'
                    ]]
                ]
            }
        }

        stage('Build') {
            steps {
                echo 'Building..'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'

//                 bat ".\mvnw test"
            }

            post {
                always {
//                     junit ''
                }
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}