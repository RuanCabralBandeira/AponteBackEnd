pipeline {

    agent any


    environment {
        APP_NAME = 'aponte-app'
        CONTAINER_NAME = 'aponte-container'
    }


    stages {


        stage('Git Checkout') {
            steps {

                git url: 'https://github.com/RuanCabralBandeira/AponteBackEnd.git', branch: 'main'
            }
        }


        stage('Build Docker Image') {
            steps {
                script {
                    def imageTag = "${env.APP_NAME}:${env.BUILD_NUMBER}"


                    sh "docker build -t ${imageTag} ."


                    sh "docker tag ${imageTag} ${env.APP_NAME}:latest"
                }
            }
        }


        stage('Deploy') {
            steps {
                script {

                    sh "docker stop ${env.CONTAINER_NAME} || true"
                    sh "docker rm ${env.CONTAINER_NAME} || true"

                    echo "Iniciando novo container..."


                    withCredentials([
                        string(credentialsId: 'db-password', variable: 'DB_PASSWORD'),
                        string(credentialsId: 'jwt-secret', variable: 'JWT_SECRET')
                    ]) {

                        sh """
                        docker run -d -p 8080:8080 \
                            -e DB_PASSWORD=${DB_PASSWORD} \
                            -e JWT_SECRET=${JWT_SECRET} \
                            --name ${env.CONTAINER_NAME} \
                            ${env.APP_NAME}:latest
                        """
                    }
                }
            }
        }
    }


    post {
        success {
            echo "Pipeline concluído com sucesso! Build #${env.BUILD_NUMBER}"
        }
        failure {
            echo "Pipeline falhou."
        }
    }
}