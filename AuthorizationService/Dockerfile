FROM java:8

ENV JAVA_HOME /usr/lib/jvm/java-8-oracle

ADD target/auth*.jar app.jar

EXPOSE 8099

ENTRYPOINT ["java","-jar","/app.jar"]