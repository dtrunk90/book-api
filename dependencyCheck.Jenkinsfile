#!/usr/bin/env groovy
pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/dtrunk90/book-api.git'
            }
        }
        stage('Dependency Check') {
            steps {
                sh './mvnw -ntp dependency-check:check -Dformat=XML'
            }
            post {
                always {
                    dependencyCheckPublisher pattern: 'target/dependency-check-report.xml'
                }
            }
        }
    }
}
