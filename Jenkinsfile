// pipeline{
//     environment{
//             DOCKERHUB = credentials('MURPHY_DOCKER_HUB_CREDS')
//             MYSQL_DATABASE = credentials('MYSQL_DATABASE')
//             MYSQL = credentials('MYSQL')
//             BACKEND_INTERNAL_PORT = credentials('BACKEND_INTERNAL_PORT')
//     }
//     agent any
//     stages{
//         stage("Git Cloning Stage"){
//             steps{
//                 git 'https://github.com/shahJainam961/JAIRU-Backend.git'
//             }
//         }
//         stage("Building Stage"){
//             steps{
//                 sh 'mvn clean -DMYSQL_DATABASE=$MYSQL_DATABASE -DBACKEND_INTERNAL_PORT=$BACKEND_INTERNAL_PORT -DMYSQL_USR=$MYSQL_USR -DMYSQL_PSW=$MYSQL_PSW'
//                 sh 'mvn compile -DMYSQL_DATABASE=$MYSQL_DATABASE -DBACKEND_INTERNAL_PORT=$BACKEND_INTERNAL_PORT -DMYSQL_USR=$MYSQL_USR -DMYSQL_PSW=$MYSQL_PSW'
//                 sh 'mvn package -DMYSQL_DATABASE=$MYSQL_DATABASE -DBACKEND_INTERNAL_PORT=$BACKEND_INTERNAL_PORT -DMYSQL_USR=$MYSQL_USR -DMYSQL_PSW=$MYSQL_PSW'
//             }
//         }
//         stage("Testing Stage"){
//             steps{
//                 sh 'mvn test -DMYSQL_DATABASE=$MYSQL_DATABASE -DBACKEND_INTERNAL_PORT=$BACKEND_INTERNAL_PORT -DMYSQL_USR=$MYSQL_USR -DMYSQL_PSW=$MYSQL_PSW'
//             }
//         }
//         stage("Docker Login"){
//             steps{
//                 sh 'docker login -u $DOCKERHUB_USR -p $DOCKERHUB_PSW'
//             }
//         }
//         stage("Building Docker Image"){
//             steps{
//                 sh 'docker build -t murphy961/jairu-backend:1.0 .'
//             }
//         }
//         stage("Pushing Docker Image on DockerHub"){
//             steps{
//                 sh 'docker push murphy961/jairu-backend:1.0'
//             }
//         }
//
//     }
// }

pipeline{
    environment{
            DOCKERHUB = credentials('DockerHubCred')
            MYSQL_DATABASE = credentials('MYSQL_DATABASE')
            MYSQL = credentials('MYSQL')
            BACKEND_INTERNAL_PORT = credentials('BACKEND_INTERNAL_PORT')
    }
    agent any
    stages{
        stage("Git Cloning Stage"){
            steps{
                git 'https://github.com/ARJUN1220/SPE_backend.git'
            }
        }
        stage("Building Stage"){
            steps{
                sh 'mvn clean -DMYSQL_DATABASE=$MYSQL_DATABASE -DBACKEND_INTERNAL_PORT=$BACKEND_INTERNAL_PORT -DMYSQL_USR=$MYSQL_USR -DMYSQL_PSW=$MYSQL_PSW'
                sh 'mvn compile -DMYSQL_DATABASE=$MYSQL_DATABASE -DBACKEND_INTERNAL_PORT=$BACKEND_INTERNAL_PORT -DMYSQL_USR=$MYSQL_USR -DMYSQL_PSW=$MYSQL_PSW'
                sh 'mvn package -DMYSQL_DATABASE=$MYSQL_DATABASE -DBACKEND_INTERNAL_PORT=$BACKEND_INTERNAL_PORT -DMYSQL_USR=$MYSQL_USR -DMYSQL_PSW=$MYSQL_PSW'
            }
        }

        stage("Verify JAR File") {
            steps {
                sh 'ls -lh target/workwise-0.0.1-SNAPSHOT.jar'
            }
        }

        stage("Testing Stage"){
            steps{
                sh 'mvn test -DMYSQL_DATABASE=$MYSQL_DATABASE -DBACKEND_INTERNAL_PORT=$BACKEND_INTERNAL_PORT -DMYSQL_USR=$MYSQL_USR -DMYSQL_PSW=$MYSQL_PSW'
            }
        }
        stage("Docker Login"){
            steps{
                sh 'docker login -u $DOCKERHUB_USR -p $DOCKERHUB_PSW'
            }
        }
        stage("Building Docker Image"){
            steps{
                sh 'docker build --no-cache -t arjun201/aj-backend:1.0 .'
            }
        }
        stage("Pushing Docker Image on DockerHub"){
            steps{
                sh 'docker push arjun201/aj-backend:1.0'
            }
        }

    }
}