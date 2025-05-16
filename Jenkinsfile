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
bat "docker build -t=boloyon/selenium2 ."
echo "creating the test image..."

}
}

stage('3 Push the image'){
steps{
bat "docker push boloyon/selenium2"
echo "pushing the test image to docker hub..."

}
}

}

