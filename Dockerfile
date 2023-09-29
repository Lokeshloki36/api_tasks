From openjdk:11
add target/api_tasks-0.0.1-SNAPSHOT.jar application.jar
ENTRYPOINT [ "java","-jar","/application.jar" ]