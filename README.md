## Irembo User Account Management System
<p>============================================</p>

### How to deploy  the system manually
<p>-----------------------------------------------------------------------------------</p>

- Clone project to your local machine
- Install maven dependencies by either using your IDE or ```mvn install ``` command if it is the first time you are running the project
- Install maven dependencies by either using your IDE or ```mvn clean package -DskipTests ``` command
- Run the project by either using your IDE or ```mvn spring-boot:run ``` command

### How to deploy  the system using docker
<p>-----------------------------------------------------------------------------------</p>

- Clone project to your local machine
- Install maven dependencies by either using your IDE or ```mvn install ``` command if it is the first time you are running the project
- Install maven dependencies by either using your IDE or ```mvn clean package -DskipTests ``` command
- Build docker image by running this command ``` docker build -t {name:version} .``` e.g ``` docker build -t irembo:1.0 .```
- Run docker image by running this command ```docker run -p 9060:9060 {name-of-the-image}``` e.g ```docker run -p 9060:9060 irembo:1.0```
- After running all of this command, application will be up and running on port 9060

After application is up and running you can also run Kafka and Zookeeper using docker-compose file by running this command ```docker-compose -f kafka-docker-compose.yml up```