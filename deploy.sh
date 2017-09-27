#!/usr/bin/env bash

./gradlew -Dfile.encoding=UTF-8 clean build

scp build/libs/enterprise-wechat-3rd-provider.war davenkin@123.57.137.49:./software/apache-tomcat-8.5.15/webapps/enterprise-wechat-3rd-provider.war




