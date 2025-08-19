FROM eclipse-temurin:17-jdk

WORKDIR /test-server

COPY . .

RUN chmod +x ./gradlew
RUN ./gradlew clean build

RUN echo "=== Generated JAR files ===" && ls -la /test-server/build/libs/

RUN mv /test-server/build/libs/*.jar /test-server/demo.jar

ENTRYPOINT ["java", "-jar", "demo.jar"]
