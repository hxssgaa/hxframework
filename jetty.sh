#!/usr/bin/env bash
export MAVEN_OPTS="-XX:MaxPermSize=1024M -Xmn1024m -Xms2048m -Xmx2048m -Xdebug -Xnoagent -Xrunjdwp:transport=dt_socket,address=4040,server=y,suspend=n $MAVEN_OPTS"
mvn clean compile -Dmaven.test.skip=true
mvn install -Dmaven.test.skip=true
cd framework-web
mvn jetty:run -Dmaven.test.skip=true
