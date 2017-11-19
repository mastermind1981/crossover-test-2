FROM openjdk:8-jre-slim

FROM maven:3.5.2-jdk-7-slim

CMD ["mvn clean package"]

ENTRYPOINT mvn clean test -Dcrossover.hub.url=$SELENIUM_HUB -Dcrossover.browser=$BROWSER -Dcrossover.remote=$REMOTE -Dcrossover.suite.xml=$MODULE