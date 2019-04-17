pipeline {
    agent none
    options {
        skipStagesAfterUnstable()
    }
    stages {
        stage('Build') {
	        agent{
	          docker {
	            image 'maven:3-alpine'
	            args '-v /root/.m2:/root/.m2'
	        }
        }
	        steps {
	            sh 'mvn clean package'
	        }
        }
        stage('Test') {
        	agent{
	          docker {
	            image 'maven:3-alpine'
	            args '-v /root/.m2:/root/.m2'
	       		}
	       	}
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Deliver') { 
        	agent any
            steps {
               sh './jenkins/deliver.sh' 
            }
        }
    }
}