FROM java:8

ENV JAVA_HOME /usr/lib/jvm/java-8-oracle

ADD target/gateway*.jar app.jar

EXPOSE 8060

ENTRYPOINT ["java","-jar","/app.jar"]