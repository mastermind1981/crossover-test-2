FROM openjdk:8-jre-slim

RUN apt-get update

RUN apt-get install -y openjdk-8-jdk

RUN  apt-get install -y maven

RUN apt-get install -y wget

RUN apt-get install -y libxi6 libgconf-2-4

COPY pom.xml /usr/local/crossover-tests/pom.xml

COPY src /usr/local/crossover-tests/src

WORKDIR  /usr/local/crossover-tests

RUN apt-get install unzip
RUN wget -N http://chromedriver.storage.googleapis.com/2.26/chromedriver_linux64.zip

RUN unzip chromedriver_linux64.zip

RUN chmod 777 chromedriver

RUN apt-get -y install xvfb gtk2-engines-pixbuf
RUN apt-get -y install xfonts-cyrillic xfonts-100dpi xfonts-75dpi xfonts-base xfonts-scalable
RUN apt-get -y install imagemagick x11-apps
RUN Xvfb -ac :99 -screen 0 1280x1024x16 &
RUN export DISPLAY=:99

RUN mvn clean test -Dcrossover.browser=CHROME -Dcrossover.timeout.seconds=50 -Dcrossover.remote=true -Dcrossover.hub.url=selenium-hub  -Dcrossover.web.url=https://www.crossover.com -Dcrossover.fork.count=4 -Dcrossover.suite.xml=src/test/resources/crossover_tests.xml