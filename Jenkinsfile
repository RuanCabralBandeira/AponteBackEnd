pipeline {
    // 1. Em qual agente o Jenkins deve rodar
    agent any


    tools {
        dockerTool 'docker-cli'
    }


    // 2. Define as variáveis que usaremos
    environment {
        APP_NAME = 'aponte-app'
        CONTAINER_NAME = 'aponte-container'
    }

    // 3. Define os estágios (passos) do pipeline
    stages {

        // Estágio 1: Puxar o código do GitHub
        stage('Git Checkout') {
            steps {
                // Puxa a branch 'main' do seu repositório
                git url: 'https://github.com/RuanCabralBandeira/AponteBackEnd.git', branch: 'main'
            }
        }

        // Estágio 2: Construir a Imagem Docker
        stage('Build Docker Image') {
            steps {
                script {
                    def imageTag = "${env.APP_NAME}:${env.BUILD_NUMBER}"

                    // Constrói a imagem usando seu Dockerfile
                    // Isso agora vai funcionar por causa do bloco 'tools'
                    sh "docker build -t ${imageTag} ."

                    // Marca a imagem como 'latest' (a mais recente)
                    sh "docker tag ${imageTag} ${env.APP_NAME}:latest"
                }
            }
        }

        // Estágio 3: Fazer o Deploy (Rodar o novo container)
        stage('Deploy') {
            steps {
                script {
                    // Para e remove o container antigo (se existir)
                    sh "docker stop ${env.CONTAINER_NAME} || true"
                    sh "docker rm ${env.CONTAINER_NAME} || true"

                    echo "Iniciando novo container..."

                    // Puxa as credenciais que você cadastrou no Jenkins
                    withCredentials([
                        string(credentialsId: 'db-password', variable: 'DB_PASSWORD'),
                        string(credentialsId: 'jwt-secret', variable: 'JWT_SECRET')
                    ]) {
                        // Roda o comando 'docker run' injetando as senhas
                        // que o seu application.properties espera
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

    // 4. O que fazer no final
    post {
        success {
            echo "Pipeline concluído com sucesso! Build #${env.BUILD_NUMBER}"
        }
        failure {
            echo "Pipeline falhou."
        }
    }
}