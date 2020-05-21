# Use an official java runtime as a parent image
FROM openjdk:8-jre-alpine

#copy the app to be deployed in the container
ADD target/sample-graphql.jar sample-graphql.jar

#create a file entrypoint-dos.sh and put the project entrypoint.sh content in it
ADD entrypoint.sh entrypoint-dos.sh

#Get rid of windows characters and put the result in a new entrypoint.sh in the container
RUN sed -e 's/\r$//' entrypoint-dos.sh > entrypoint.sh

#set the file as an executable and set zerodash as the owner
RUN chmod 755 entrypoint.sh

ENTRYPOINT ["./entrypoint.sh"]