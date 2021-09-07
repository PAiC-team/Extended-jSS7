



# Extended jSS7


> The Extended jSS7 is cloned from [RestComm jSS7](https://github.com/RestComm/jss7) which we have made a log of improvements and added new features.
> 

## Introduction

Open Source Extended Java SS7 stack that allows Java apps to communicate with legacy SS7 communications equipment. 

Extended jSS7 provides provides implementation for `MTP2`, `MTP3`, `ISUP`, `SCCP`, `TCAP`, `CAMEL (Phase I, Phase II, Phase III and Phase IV)` and `MAP` protocols for a dedicated equipment. It also has in-built support for `SIGTRAN (M3UA)` over IP and strictly adheres to the standards and specifications defined by the International Telecommunications Union (ITU) and American National Standards Institute (ANSI). The platform offers developers with a flexible API set that hides the lower layer details (legacy SS7 links or SIGTRAN) and therefore makes it simple and easy to develop SS7 applications as well as to migrate your applications from TDM equipments to M3UA. {this-platform}  {this-application}  is based on an easily scalable and configurable load-balancing architecture.

The platform offers developers with a flexible API set that hides the lower layer details (legacy SS7 links or SIGTRAN) and therefore makes it simple and easy to develop SS7 applications as well as to  migrate your applications from TDM equipments to M3UA. {this-platform} {this-application} is based on an easily scalable and configurable load-balancing architecture. 

Extended jSS7 supports TDM hardware offered by major vendors in the market, namely Intel family boards (Dialogic) and  Zaptel/Dahdi (Digium, Sangoma). Though for production we recommend Dialogic boards with MTP2 and MTP3 on-board only.
 

If you intend to use only `M3UA` you can install the Extended jSS7 on any Operating System that supports Java and SCTP protocol. However if you wish to use SS7 cards, the native libaries for these are only  compiled for Linux at the moment. 

Extended jSS7 comes with JSLEE TCAP, MAP, CAP and ISUP Resource Adaptors (RA) that enable developers to build SS7  applications with ease.
Developers only require an understanding of Resource Adaptors and can focus on building applications quickly and efficiently rather than worry about the SS7 stack.
If you wish to use JSLEE Resource Adapters, the Command Line Interface (CLI - Shell Management tool) or the GUI for run-time configuration, then you must have JBoss Application Server installed and running.
However if you do not wish to use the Resource Adaptors or CLI then Extended jSS7 can work as a standalone library. 

The Open Source Software gives you the flexibility to understand the readily available source code and customise the product for your Enterprise needs. 


## Build Extended jSS7 

### Pre-Requisites for Building from Source

* `Git Client` : Instructions for using GIT, including install, can be found at http://git-scm.com/book
* `Maven 3.2.X` : Instructions for using Maven, including install, can be found at http://maven.apache.org/
* `Ant 1.9.X` : Instructions for using Ant, including install, can be found at http://ant.apache.org
* `jmxtools:jar` :  This library is required to build the Simulator source code. The library com.sun.jdmk:jmxtools:jar:1.2.1 must be downloaded manually and placed in your maven repository. Instructions are provided below.


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


## Generate Wildfly Version
To generate a wildfly version of the Extended jSS7 use the following steps

- Download the Restcomm SLEE version 7.2.0-68.76 from wildfy 10.1.0.Final
- Build the Extended jSS7 using the command below 
  ```bash
  cd release
  ant -f build.xml -Drelease.version=8.3.0 -Dsctp.version=2.0.2-12
  ```
- Unzip the Extended jSS7 file and run the command below to install it
  ```sh
  cd Extended-jSS7-8.3.0/ss7-wildfly
  ant -f build.xml -Djboss.home=../../wildfly-10.1.0.Final
  ```
- NOTE: the path `../../wildfly-10.1.0.Final` indicates the path for the Restcomm SLEE downloaded above.


## Build Docker
To build a docker image you can use the script below. The script below only serve as a guide and you can modify by adding or removing per your requirements

```dockerfile
FROM amazoncorretto:8-alpine
RUN apk add net-tools lksctp-tools supervisor lksctp-tools-dev
RUN mkdir -p /opt/paic/jss7
WORKDIR /opt/paic/jss7
COPY Extended-jSS7-8.3.0-wildfly/. .
RUN chmod +x wildfly-10.1.0.Final/bin/standalone.sh
ENTRYPOINT ["sh", "wildfly-10.1.0.Final/bin/standalone.sh"]
CMD ["-b", "0.0.0.0"]
```


## LICENSE
[GNU AFFERO GENERAL PUBLIC LICENSE](./LICENSE)

