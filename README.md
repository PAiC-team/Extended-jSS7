



# Extended jSS7

[![Join the chat at https://gitter.im/RestComm/jss7](https://badges.gitter.im/RestComm/jss7.svg)](https://gitter.im/RestComm/jss7?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)
[![FOSSA Status](https://app.fossa.io/api/projects/git%2Bhttps%3A%2F%2Fgithub.com%2FRestComm%2Fjss7.svg?type=shield)](https://app.fossa.io/projects/git%2Bhttps%3A%2F%2Fgithub.com%2FRestComm%2Fjss7?ref=badge_shield)

> The Extended jSS7 is cloned from [RestComm jSS7](https://github.com/RestComm/jss7) which we have made a log of improvements and added new features.
> 
## Introduction

Open Source Java SS7 stack that allows Java apps to communicate with legacy SS7 communications equipment. 

jSS7 provides an open source software solution implementing M3UA, SCCP, TCAP, CAMEL, MAP, ISUP protocols for a dedicated equipment (Dialogic) and also M3UA (SIGTRAN) over IP.

## Build jSS7 

Here is the list of commands you need to run for building Mobicents jSS7 from the source

1. Clone jSS7 Repo : `git clone https://github.com/PAiC-team/Extended-jSS7`
2. Download Dialogic dependencies from Dialogic website. This is required to build the hardware part of jSS7 to have support for Dialogic boards in case you can't use SIGTRAN directly : `wget https://www.dialogic.com/files/DSI/developmentpackages/linux/dpklnx.Z`
3. Unpack the contents of the Dialogic SS7 dependencies : `tar --no-same-owner -zxvf dpklnx.Z`
4. Install the Dialogic SS7 Java Dependency in your local maven repository : `mvn install:install-file -DgroupId=com.vendor.dialogic -DartifactId=gctapi -Dversion=6.7.1 -Dpackaging=jar -Dfile=./JAVA/gctApi.jar`
5. Download Sun JMX tools dependency used by the jSS7 simulator : `wget http://www.datanucleus.org/downloads/maven2/com/sun/jdmk/jmxtools/1.2.1/jmxtools-1.2.1.jar`
6. Install the Sun JMX Tools Dependency in your local maven repository : `mvn install:install-file -DgroupId=com.sun.jdmk -DartifactId=jmxtools -Dversion=1.2.1 -Dpackaging=jar -Dfile=jmxtools-1.2.1.jar`
7. Build jSS7 with maven : `mvn clean install -Dmaven.test.skip=true`
8. Enjoy the best SS7 Open Source Stack out there ;) !

Note: For deploying of binaries into a local JBOSS AS you need to configure a JBOSS_HOME environmental variable to a JBOSS folder and run following mvn commands:

1. `mvn clean install -Pdeploy-module-jboss5 -Dmaven.test.skip=true` (for jboss 5.1 server)
2. `mvn clean install -Pdeploy-module-wildfly -Dmaven.test.skip=true` (for wildfly 10 server)

## Build Docker

## LICENSE
[GNU AFFERO GENERAL PUBLIC LICENSE](./LICENSE)
