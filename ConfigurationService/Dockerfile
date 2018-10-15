FROM java:8

ENV JAVA_HOME /usr/lib/jvm/java-8-oracle

ADD target/config*.jar app.jar

EXPOSE 8088

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom -Djava.net.preferIPv4Stack=true","-jar","/app.jar"]