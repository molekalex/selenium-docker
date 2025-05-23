pipeline{

agent any

stages{
stage('1 prepare Jars'){
steps{
bat "mvn clean package -DskipTests"
echo "building the last version of files from the IDLE(intelliJ)"
}
}

stage('2 Build the image'){
steps{
bat "docker build -t=boloyong/selenium-jenk ."
echo "creating the test image in docker"

}
}

stage('3 Push the image'){
environment{
DOCKER_HUB = credentials('dockerHub')
}
steps{
//bat 'docker login -u %DOCKER_HUB_USR% -p %DOCKER_HUB_PSW%'
bat 'echo %DOCKER_HUB_PSW% | docker login -u %DOCKER_HUB_USR% --password-stdin'
bat "docker push boloyong/selenium-jenk"
echo "pushing the test image to dockerHub..."

}
}

}

post {
always{
bat "docker logout"
}
}

}

