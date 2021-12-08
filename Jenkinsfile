pipeline {
    agent any
    tools {
//         jdk 'zulu17'
        maven 'mvn_384'
    }
    stages {


        stage ('Initialize') {
            steps {
                sh '''
                    env | grep -e PATH -e JAVA_HOME
                    which java
                    java -version

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
                        credentialsId: 'PaHod_GitHub',
                        url: 'git@github.com:PaHod/spring.boot.emc.git'
                    ]]
                ]
                echo '..... finish Checkout'
            }
        }

        stage('Build') {
            steps {
                echo 'Building..'

                sh "mvn verify"

                echo '... finish Building'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
//                 sh.\mvnw test"

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