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
bat "docker build -t=boloyong/seleniumJenk ."
echo "creating the test image in docker"

}
}

stage('3 Push the image'){
steps{
bat "docker push boloyong/seleniumJenk"
echo "pushing the test image to dockerHub..."

}
}

}

}

