FROM eclipse-temurin:17-jdk-jammy

ARG IMAGES=images
ARG JAR_FILE=target/*.jar
ARG DB_FILE=./kahvikauppakanta.mv.db

COPY ${IMAGES} /images
COPY ${JAR_FILE} kahvikauppa-0.0.1.jar
COPY ${DB_FILE} kahvikauppakanta.mv.db

EXPOSE 8080

ENTRYPOINT ["java","-jar","/kahvikauppa-0.0.1.jar"]

