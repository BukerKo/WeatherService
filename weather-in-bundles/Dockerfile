FROM ubuntu:18.04

RUN apt-get update && apt-get install -y \
    openjdk-8-jdk \
    maven \
    unzip \
    wget;

ENV JAVA_HOME /usr/lib/jvm/java-1.8.0-openjdk-amd64


RUN wget https://repo.maven.apache.org/maven2/com/savoirtech/aetos/aetos/3.0.8.0/aetos-3.0.8.0.zip
RUN unzip aetos-3.0.8.0.zip -d aetos
RUN rm aetos-3.0.8.0.zip

RUN mkdir weather-in-bundles
COPY . weather-in-bundles
COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

WORKDIR /aetos/aetos-3.0.8.0/deploy
RUN cp /weather-in-bundles/weather-in-bundles-api/target/weather-in-bundles-api-1.0-SNAPSHOT.jar .
RUN cp /weather-in-bundles/weather-in-bundles-cassandra/target/weather-in-bundles-cassandra-1.0-SNAPSHOT.jar .
RUN cp /weather-in-bundles/weather-in-bundles-provider/target/weather-in-bundles-provider-1.0-SNAPSHOT.jar .
RUN cp /weather-in-bundles/feature.xml .

WORKDIR /aetos/aetos-3.0.8.0/bin


EXPOSE 8181 1099 44444 8101
