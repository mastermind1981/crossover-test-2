FROM openjdk:8-jre-slim

RUN sudo apt-get update

RUN  sudo apt-get install -y maven

COPY pom.xml /usr/local/crossover-tests/pom.xml

COPY src /usr/local/crossover-tests/src

WORKDIR  /usr/local/crossover-tests

RUN sudo apt-get install unzip
RUN wget -N http://chromedriver.storage.googleapis.com/2.26/chromedriver_linux64.zip

RUN unzip chromedriver_linux64.zip

RUN chmod 777 chromedriver

RUN mvn clean test -Dcrossover.browser=CHROME -Dcrossover.gecko.driver=/home/ubuntu/geckodriver -Dcrossover.chrome.driver=/home/ubuntu/chromedriver -Dcrossover.timeout.seconds=50 -Dcrossover.remote=false -Dcrossover.hub.url=http://localhost:4444/wd/hub -Dcrossover.web.url=https://www.crossover.com -Dcrossover.fork.count=4 -Dcrossover.suite.xml=crossover_tests.xml