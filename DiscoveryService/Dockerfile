FROM java:8

ENV JAVA_HOME /usr/lib/jvm/java-8-oracle

ADD target/discovery*.jar app.jar

EXPOSE 8061

ENTRYPOINT ["java","-jar","/app.jar"]