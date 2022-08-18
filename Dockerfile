FROM debian:latest

EXPOSE 81

RUN apt-get update -y
RUN apt-get install default-jdk -y
RUN apt-get install maven -y
RUN mkdir /target
