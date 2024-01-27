pipeline {
    agent any
    environment {
        MAVEN_OPTS = "--illegal-access=warn --add-opens=java.base/java.lang=ALL-UNNAMED"
    }
    stages {
        stage('Run REST tests') {
            steps {
                sh 'mvn clean verify -Dgpg.skip'
            }
        }
    }
    post {
        always {
            junit 'target/surefire-reports/TEST-*.xml'
            recordCoverage(tools: [[parser: 'JACOCO']])
            cleanWs()
        }
    }
}