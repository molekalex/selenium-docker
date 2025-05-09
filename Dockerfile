#no entrypoint, to execute the test from here is neccesary to run the container in interactive mode and 
#execute the "java -cp..."

#from the following image:
FROM bellsoft/liberica-openjdk-alpine:20.0.1

#workspace
WORKDIR /home/selenium-docker

#add the required files:
ADD target/docker-resources ./

#command to mount volume from terminal, for pasting reports:
#${PWD}/result:/home/selenium-docker/test-output boloyon/selenium
#command to run container within running the tests:
#docker run -e BROWSER=edge -e HUB_HOST=192.168.1.38 -e TEST_SUITE=vendor-portal-json.xml -e THREAD_COUNT=3 boloyon/selenium