 #this Dockerfile create an image to run directly from the docker-compose.yaml along with the
#hub, and browser images. Require to the runner.sh file:

#from the following image:
FROM bellsoft/liberica-openjdk-alpine:20.0.1

#install Apps curl, jq:
RUN apk add curl jq 
#workspace
WORKDIR /home/selenium-docker

#add the required files:
ADD target/docker-resources ./
#add the file runner.sh to the new image
ADD runner.sh runner.sh

# Fix for windows
RUN dos2unix runner.sh

#enviroment variables
#BROWSER
#HOST
#TEST_SUITE
#THREAD_COUNT

#run the tests:
ENTRYPOINT sh runner.sh
