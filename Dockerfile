FROM amazoncorretto:11

EXPOSE 8081

COPY ./target/water_order-0.0.1-SNAPSHOT.jar /mnt/ROOT.jar

WORKDIR /mnt
CMD ["java","-jar","/mnt/ROOT.jar"]