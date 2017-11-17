FROM openjdk:8-jre-slim

ARG MAVEN_VERSION=3.5.2
ARG USER_HOME_DIR="/root"
ARG SHA=707b1f6e390a65bde4af4cdaf2a24d45fc19a6ded00fff02e91626e3e42ceaff
ARG BASE_URL=https://apache.osuosl.org/maven/maven-3/${MAVEN_VERSION}/binaries

RUN apt-get update && \
    apt-get install -y \
      curl \
  && rm -rf /var/lib/apt/lists/*

RUN mkdir -p /usr/share/maven /usr/share/maven/ref \
  && curl -fsSL -o /tmp/apache-maven.tar.gz ${BASE_URL}/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
  && echo "${SHA}  /tmp/apache-maven.tar.gz" | sha256sum -c - \
  && tar -xzf /tmp/apache-maven.tar.gz -C /usr/share/maven --strip-components=1 \
  && rm -f /tmp/apache-maven.tar.gz \
  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"

COPY mvn-entrypoint.sh /usr/local/bin/mvn-entrypoint.sh
COPY settings-docker.xml /usr/share/maven/ref/

VOLUME "$USER_HOME_DIR/.m2"

ENTRYPOINT ["/usr/local/bin/mvn-entrypoint.sh"]
CMD ["mvn clean package"]

ADD  target/rajesh-ta-framework-1.0-SNAPSHOT-jar-with-dependencies.jar /usr/share/tag/rajesh-ta-framework-1.0-SNAPSHOT-jar-with-dependencies.jar

ADD  src/test/resources/crossover_tests.xml /usr/share/tag/crossover_tests.xml

ENTRYPOINT /usr/bin/java -cp /usr/share/tag/rajesh-ta-framework-1.0-SNAPSHOT-jar-with-dependencies.jar -crossover.hub.url=$SELENIUM_HUB -Dcrossover.browser=$BROWSER -Dcrossover.remote=$REMOTE org.testng.TestNG /usr/share/tag/$MODULE