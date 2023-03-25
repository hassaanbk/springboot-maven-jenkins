pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven 'M3'
    }

    stages {
        stage('Checkout') {
                steps {
                    checkout([$class: 'GitSCM', branches: [[name: '*/main']],
                              userRemoteConfigs: [[url: 'https://github.com/hassaanbk/springboot-maven-jenkins.git']]])
                }
        }
        stage('Build') {
            steps {

                // Run Maven on a Unix agent.
                sh "mvn -Dmaven.test.failure.ignore=true -Dmaven.compiler.source=20 -Dmaven.compiler.target=20 clean package"

                // To run Maven on a Windows agent, use
                // bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }

            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }

        }
        stage('Docker Login'){
            steps {
                withCredentials([usernamePassword(credentialsId: 'docker-token', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                                    sh "docker login -u $DOCKER_USER -p $DOCKER_PASS"
                                }
            }
        }
        stage('Build Docker Image'){
            steps{
                sh "docker build -t hassaanbk/maven-spring ."
                sh "docker run -d -p 8080:8084 hassaanbk/maven-spring"
            }
        }
        stage('Push to Docker Hub'){
            steps{
                sh "docker push hassaanbk/maven-spring"
            }
        }
    }
 }
