FROM amazoncorretto:22-alpine
ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8081
WORKDIR /app
COPY target/*.jar /app/w2m-challenge.jar
ENTRYPOINT ["java", "-jar", "w2m-challenge.jar"]


