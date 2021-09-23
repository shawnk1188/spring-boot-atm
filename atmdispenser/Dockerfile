FROM adoptopenjdk/openjdk11:alpine-jre
COPY target/atmdispenser-0.0.1.jar atmdispenser.jar
ENTRYPOINT ["java","-jar","/atmdispenser.jar","--interfaceOption=rest"]