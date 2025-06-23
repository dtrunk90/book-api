#!/usr/bin/env groovy
pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/dtrunk90/book-api.git'
            }
        }
        stage('Code Analysis') {
            steps {
                sh './mvnw -ntp compile -DskipTests' // Required for SpotBugs. Triggers validate phase which runs Checkstyle and PMD
                sh './mvnw -ntp spotbugs:spotbugs spotbugs:check -DskipTests'
            }
            post {
                always {
                    recordIssues tools: [
                          checkStyle(pattern: 'target/checkstyle-result.xml'),
                          pmdParser(pattern: 'target/pmd.xml'),
                          spotBugs(pattern: 'target/spotbugsXml.xml')
                        ]
                }
            }
        }
        stage('Test') {
            steps {
                sh './mvnw -ntp test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Package') {
            steps {
                sh './mvnw -ntp package -DskipTests'
            }
            post {
                success {
                    archiveArtifacts artifacts: 'target/book-api.jar', fingerprint: true
                }
            }
        }
    }
}
