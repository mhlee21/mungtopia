# backend/Dockerfile
# 사용한 java 버전에 맞는 값을 입력해주세요.
#FROM openjdk:11-jdk-alpine
FROM adoptopenjdk/openjdk11

# jar 파일 경로는 직접 입력해주세요.
COPY /jenkins/jenkins_home/workspace/mungtopia/backend/build/libs/mungtopia-0.0.1-SNAPSHOT.jar mungtopia.jar

# 배포용 properties 실행 명령어
#ENTRYPOINT ["java","-jar","app.jar","--spring.config.name=application-prod"] 


# 만약 배포용 properties를 사용하지 않는다면
# Default properties 실행 명령어
ENTRYPOINT ["java","-jar","mungtopia.jar"]
