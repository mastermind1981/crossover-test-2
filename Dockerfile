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

RUN sudo apt-get -y install xvfb gtk2-engines-pixbuf
RUN sudo apt-get -y install xfonts-cyrillic xfonts-100dpi xfonts-75dpi xfonts-base xfonts-scalable
RUN sudo apt-get -y install imagemagick x11-apps
RUN Xvfb -ac :99 -screen 0 1280x1024x16 &
RUN export DISPLAY=:99

RUN mvn clean test -Dcrossover.browser=CHROME -Dcrossover.gecko.driver=/home/ubuntu/geckodriver -Dcrossover.chrome.driver=/home/ubuntu/chromedriver -Dcrossover.timeout.seconds=50 -Dcrossover.remote=false -Dcrossover.hub.url=http://localhost:4444/wd/hub -Dcrossover.web.url=https://www.crossover.com -Dcrossover.fork.count=4 -Dcrossover.suite.xml=crossover_tests.xml