# Asg Client Project
 
## Setup
 
 Firstly you need to execute the sql below in your mysql server.
 
 ```
 mysql -u root -p < creation_script.sql
 ```
 
 You then need generate the jar, to do this run
 
 `./gradlew build`
 
 Then to execute it run
 
 `java -jar build/libs/asgproject-0.0.1.jar --spring.profiles.active=development,https`
 
 
 
