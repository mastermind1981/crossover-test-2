FROM openjdk:8-jre-slim

ADD  target/rajesh-ta-framework-1.0-SNAPSHOT-jar-with-dependencies.jar /usr/share/tag/rajesh-ta-framework-1.0-SNAPSHOT-jar-with-dependencies.jar

ADD  src/test/resources/crossover_tests.xml /usr/share/tag/crossover_tests.xml

ENTRYPOINT /usr/bin/java -cp /usr/share/tag/rajesh-ta-framework-1.0-SNAPSHOT-jar-with-dependencies.jar -crossover.hub.url=$SELENIUM_HUB -Dcrossover.browser=$BROWSER -Dcrossover.remote=$REMOTE org.testng.TestNG /usr/share/tag/$MODULE