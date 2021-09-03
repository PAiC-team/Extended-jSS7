
package org.restcomm.protocols.ss7.tools.simulator.management;

import javax.management.NotificationEmitter;

import org.apache.log4j.Level;
import org.restcomm.protocols.ss7.tools.simulator.common.ConfigurationData;
import org.restcomm.protocols.ss7.tools.simulator.level1.DialogicMan;
import org.restcomm.protocols.ss7.tools.simulator.level1.M3uaMan;
import org.restcomm.protocols.ss7.tools.simulator.level2.SccpMan;
import org.restcomm.protocols.ss7.tools.simulator.level3.CapMan;
import org.restcomm.protocols.ss7.tools.simulator.level3.MapMan;
import org.restcomm.protocols.ss7.tools.simulator.tests.ati.TestAtiClientMan;
import org.restcomm.protocols.ss7.tools.simulator.tests.ati.TestAtiServerMan;
import org.restcomm.protocols.ss7.tools.simulator.tests.cap.TestCapScfMan;
import org.restcomm.protocols.ss7.tools.simulator.tests.cap.TestCapSsfMan;
import org.restcomm.protocols.ss7.tools.simulator.tests.checkimei.TestCheckImeiClientMan;
import org.restcomm.protocols.ss7.tools.simulator.tests.checkimei.TestCheckImeiServerMan;
import org.restcomm.protocols.ss7.tools.simulator.tests.psi.TestPsiServerMan;
import org.restcomm.protocols.ss7.tools.simulator.tests.sms.TestSmsClientMan;
import org.restcomm.protocols.ss7.tools.simulator.tests.sms.TestSmsServerMan;
import org.restcomm.protocols.ss7.tools.simulator.tests.ussd.TestUssdClientMan;
import org.restcomm.protocols.ss7.tools.simulator.tests.ussd.TestUssdServerMan;
import org.restcomm.protocols.ss7.tools.simulator.tests.lcs.TestLcsClientMan;
import org.restcomm.protocols.ss7.tools.simulator.tests.lcs.TestLcsServerMan;

/**
*
* @author sergey vetyutnev
*
*/
public interface TesterHostInterface extends TesterHostMBean, NotificationEmitter {

    String SIMULATOR_HOME_VAR = "SIMULATOR_HOME";

    M3uaMan getM3uaMan();

    DialogicMan getDialogicMan();

    SccpMan getSccpMan();

    MapMan getMapMan();

    CapMan getCapMan();

    TestUssdClientMan getTestUssdClientMan();

    TestUssdServerMan getTestUssdServerMan();

    TestSmsClientMan getTestSmsClientMan();

    TestSmsServerMan getTestSmsServerMan();

    TestCapSsfMan getTestCapSsfMan();

    TestCapScfMan getTestCapScfMan();

    TestAtiClientMan getTestAtiClientMan();

    TestAtiServerMan getTestAtiServerMan();

    TestCheckImeiClientMan getTestCheckImeiClientMan();

    TestCheckImeiServerMan getTestCheckImeiServerMan();

    TestLcsClientMan getTestLcsClientMan();

    TestLcsServerMan getTestLcsServerMan();

    TestPsiServerMan getTestPsiServerMan();

    boolean isNeedQuit();

    void checkStore();

    void execute();

    void sendNotif(String source, String msg, Throwable e, Level logLevel);

    void sendNotif(String source, String msg, String userData, Level logLevel);

    ConfigurationData getConfigurationData();

    void markStore();

    String getPersistDir();

}
