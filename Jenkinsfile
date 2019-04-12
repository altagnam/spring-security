pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /root/.m2:/root/.m2'
        }
    }
    options {
        skipStagesAfterUnstable()
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
       stage ('Promotion') {
       	steps {
  			input 'Deploy to Production?'
  			}
		}
        stage('Deliver') { 
            steps {
                sh './jenkins/deliver.sh' 
            }
        }
        stage('Docker') { 
            steps {
                sh 'docker build -t rafael.altagnam/spring-security .'
                sh 'docker run -p 2000:8080 rafael.altagnam/spring-security'
            }
        }
    }
}