FROM java:8

ENV JAVA_HOME /usr/lib/jvm/java-8-oracle

ADD target/book*.jar app.jar

EXPOSE 8098

ENTRYPOINT ["java","-jar","/app.jar"]