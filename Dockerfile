FROM openjdk:8-jre-slim

ADD  target/rajesh-ta-framework-1.0-SNAPSHOT-jar-with-dependencies.jar /usr/share/tag/rajesh-ta-framework-1.0-SNAPSHOT-jar-with-dependencies.jar

ENTRYPOINT ["/usr/bin/java","-cp","/usr/share/tag/rajesh-ta-framework-1.0-SNAPSHOT-jar-with-dependencies.jar","org.testng.TestNG","-testclass","com.crossover.rajesh_ta_framework.tests.CrossoverTests"]