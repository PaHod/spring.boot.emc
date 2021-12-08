pipeline {
    agent any

    stages {

        stage ('Initialize') {
            steps {
//                 bat echo 'PATH' = ${PATH}
//                 bat echo 'M2_HOME' = ${M2_HOME}

                bat 'JAVA -version'
                bat '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
            }
        }

        stage('Example') {
            steps {
                echo "Running ${env.BUILD_ID} on ${env.JENKINS_URL}"
            }
        }
        stage('Checkout codebase'){
            steps{
                echo 'Checkout codebase....'

                checkout scm: [
                    $class: 'GitSCM',
                    branches: [[name: '*/master']],
                    userRemoteConfigs: [[
                        credentialsId: 'pahod-ssh',
                        url: 'git@github.com:PaHod/spring.boot.emc.git'
                    ]]
                ]
                echo '..... finish Checkout'
            }
        }

        stage('Build') {
            steps {
                echo 'Building..'
                bat ".\mvnw build"

                echo '... finish Building'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                bat ".\mvnw test"

                echo '... finish Testing'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'

                echo '... finish Deploying'
            }
        }
    }
}