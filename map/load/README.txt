This is simple load test for MAP.

To run test follow the steps

1) Modify the properties "test.client.numOfDialogs" and "test.client.concurrentDialogs" to suit your test in the build.xml file. The first parameter is number of complete MAP calls the test should make and second parameter is number of concurrent MAP Dialogs the test should maintain. If concurrent MAP Dialog goes above threshold limit, test will throttle the creation of more Dialog till concurrent MAP Dialog again comes down below 50% of threshold specified.
2) Modify the value of "packageprefix" property in the build.xml file, the possible values are: "org.restcomm.protocols.ss7.map.load.sms.mo", "org.restcomm.protocols.ss7.map.load.sms.mt" or "org.restcomm.protocols.ss7.map.load.ussd"
2) Clean the previous execution logs/folders by calling "mvn/ant clean"
3) Build load test and assemble by calling "ant -f <build file name> assemble"
4) Start server by calling "ant -f <build file name> server"
5) Start client by calling "ant -f <build file name> client"


For USSD load test: <build file name> = ussd_build.xml
For MO-SMS load test: <build file name> = mo_sms_build.xml
For MT-SMS load test: <build file name> = mt_sms_build.xml