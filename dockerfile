FROM maven:3.8.6
COPY . /src
WORKDIR /src
ENTRYPOINT [ "mvn", "spring-boot:run" ]