FROM amazoncorretto:8-alpine

# maintainer
MAINTAINER James Amo - james.amo@paicbd.com

# install dependencies
RUN apk add net-tools lksctp-tools supervisor lksctp-tools-dev

# create and set workspace
RUN mkdir -p /opt/paic/jss7
WORKDIR /opt/paic/jss7

# the version number will be changed during the CI/CD build
COPY Extended-jSS7-8.2.6-243-wildfly/. .

RUN chmod +x wildfly-10.1.0.Final/bin/standalone.sh

# run application
ENTRYPOINT ["/bin/"]
CMD ["standalone.sh","-b 0.0.0.0"]