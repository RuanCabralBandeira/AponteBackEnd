pipeline {
    agent any

    // 1. Instala a ferramenta Docker
    tools {
        dockerTool 'docker-cli'
    }

    // 2. Define as variáveis
    environment {
        APP_NAME = 'aponte-app'
        CONTAINER_NAME = 'aponte-container'
    }

    // 3. Define os estágios (passos)
    stages {

        // Estágio 1: Puxar o código do GitHub
        stage('Git Checkout') {
            steps {

                cleanWs()

                // Puxa a branch 'main'
                git url: 'https://github.com/RuanCabralBandeira/AponteBackEnd.git', branch: 'main'
            }
        }

        // Estágio 2: Construir a Imagem Docker
        stage('Build Docker Image') {
            steps {
                script {
                    def imageTag = "${env.APP_NAME}:${env.BUILD_NUMBER}"
                    sh "docker build -t ${imageTag} ."
                    sh "docker tag ${imageTag} ${env.APP_NAME}:latest"
                }
            }
        }

        // Estágio 3: Fazer o Deploy (Rodar o novo container)
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
