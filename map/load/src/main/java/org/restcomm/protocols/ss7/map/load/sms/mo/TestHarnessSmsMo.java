
package org.restcomm.protocols.ss7.map.load.sms.mo;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.SimpleLayout;
import org.restcomm.protocols.ss7.indicator.RoutingIndicator;
import org.restcomm.protocols.ss7.m3ua.impl.parameter.ParameterFactoryImpl;
import org.restcomm.protocols.ss7.map.api.MAPDialogListener;
import org.restcomm.protocols.ss7.map.api.service.sms.MAPServiceSms;
import org.restcomm.protocols.ss7.map.api.service.sms.MAPServiceSmsListener;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author abhayani
 * @modified <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public abstract class TestHarnessSmsMo implements MAPDialogListener, MAPServiceSmsListener, MAPServiceSms {

    private static final Logger logger = Logger.getLogger("map.test");

    protected static final String CREATED_DIALOGS = "CreatedScenario";
    protected static final String SUCCESSFUL_DIALOGS = "CompletedScenario";
    protected static final String ERROR_DIALOGS = "FailedScenario";

    protected static final String LOG_FILE_NAME = "log.file.name";
    protected static String logFileName = "maplog.txt";

    protected static int NDIALOGS = 1440000;

    protected static int MAXCONCURRENTDIALOGS = 400;

    // MTP Details
    protected static int CLIENT_SPC = 1;
    protected static int SERVER_SPC = 2;
    protected static int NETWORK_INDICATOR = 2;
    protected static int SERVICE_INDICATOR = 3; // SCCP
    protected static int SSN = 8;

    // M3UA details
    // protected final String CLIENT_IP = "172.31.96.40";
    protected static String CLIENT_IP = "127.0.0.1";
    protected static int CLIENT_PORT = 2345;

    // protected final String SERVER_IP = "172.31.96.41";
    protected static String SERVER_IP = "127.0.0.1";
    protected static int SERVER_PORT = 3434;

    protected static int ROUTING_CONTEXT = 100;

    protected static int DELIVERY_TRANSFER_MESSAGE_THREAD_COUNT = Runtime.getRuntime().availableProcessors() * 2;
    protected static int SENDING_MESSAGE_THREAD_COUNT = Runtime.getRuntime().availableProcessors() * 2;

    protected static int RAMP_UP_PERIOD = -100;

    protected final String SERVER_ASSOCIATION_NAME = "serverAssociation";
    protected final String CLIENT_ASSOCIATION_NAME = "clientAssociation";

    protected final String SERVER_NAME = "testserver";

    // TCAP Details
    protected static final int MAX_DIALOGS = 500000;

    protected static String SCCP_CLIENT_ADDRESS = null;
    protected static String SCCP_SERVER_ADDRESS = null;

    protected static RoutingIndicator ROUTING_INDICATOR = RoutingIndicator.ROUTING_BASED_ON_DPC_AND_SSN;

    protected final ParameterFactoryImpl factory = new ParameterFactoryImpl();

    protected static int TEST_START_DELAY = 20000;
    protected static int TEST_END_DELAY = 3000;
    protected static int PRINT_WRITER_PERIOD = 2000;

    protected TestHarnessSmsMo() {
        init();
    }

    public void init() {
        try {
            Properties tckProperties = new Properties();

            InputStream inStreamLog4j = TestHarnessSmsMo.class.getResourceAsStream("/log4j.properties");

            System.out.println("Input Stream = " + inStreamLog4j);

            Properties propertiesLog4j = new Properties();
            try {
                propertiesLog4j.load(inStreamLog4j);
                PropertyConfigurator.configure(propertiesLog4j);
            } catch (Exception e) {
                e.printStackTrace();
                BasicConfigurator.configure();
            }

            logger.debug("log4j configured");

            String lf = System.getProperties().getProperty(LOG_FILE_NAME);
            if (lf != null) {
                logFileName = lf;
            }

            // If already created a print writer then just use it.
            try {
                logger.addAppender(new FileAppender(new SimpleLayout(), logFileName));
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }

    }
}
