#!/bin/bash

# change to the tmp directory for the installation of the following dependencies
cd /tmp
# see README.md for more details
wget https://www.dialogic.com/files/DSI/developmentpackages/linux/dpklnx.Z
tar --no-same-owner -zxvf dpklnx.Z
mvn install:install-file -DgroupId=com.vendor.dialogic -DartifactId=gctapi -Dversion=6.7.1 -Dpackaging=jar -Dfile=./JAVA/gctApi.jar

wget http://www.datanucleus.org/downloads/maven2/com/sun/jdmk/jmxtools/1.2.1/jmxtools-1.2.1.jar
mvn install:install-file -DgroupId=com.sun.jdmk -DartifactId=jmxtools -Dversion=1.2.1 -Dpackaging=jar -Dfile=jmxtools-1.2.1.jar

