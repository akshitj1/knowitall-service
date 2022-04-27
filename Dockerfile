# syntax=docker/dockerfile:experimental
FROM gradle:7.4-jdk11
WORKDIR /app

# copy build config
COPY build.gradle settings.gradle ./

# copy source code
COPY src ./src

# build
# https://www.nvisia.com/insights/optimizing-builds-with-buildkit
RUN --mount=type=cache,id=gradle,target=/home/gradle/.gradle gradle build --no-daemon

#CMD gradle bootRun.
CMD java -jar /app/build/libs/knowitall-service-1.0-SNAPSHOT.jar
