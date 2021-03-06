
package org.restcomm.protocols.ss7.tools.simulator.level1;

import org.apache.log4j.Level;
import org.restcomm.protocols.ss7.mtp.Mtp3UserPart;
import org.restcomm.protocols.ss7.tools.simulator.Stoppable;
import org.restcomm.protocols.ss7.tools.simulator.management.TesterHostInterface;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class DialogicMan implements DialogicManMBean, Stoppable {

    public static String SOURCE_NAME = "Dialogic";

    private final String name;
    private TesterHostInterface testerHost;
    private DialogicMtp3UserPartProxyInterface dialogic;
    private boolean isStarted = false;

    public DialogicMan() {
        this.name = "???";
    }

    public DialogicMan(String name) {
        this.name = name;
    }

    public void setTesterHost(TesterHostInterface testerHost) {
        this.testerHost = testerHost;
    }

    @Override
    public int getSourceModuleId() {
        return this.testerHost.getConfigurationData().getDialogicConfigurationData().getSourceModuleId();
    }

    @Override
    public void setSourceModuleId(int val) {
        this.testerHost.getConfigurationData().getDialogicConfigurationData().setSourceModuleId(val);
        this.testerHost.markStore();
    }

    @Override
    public int getDestinationModuleId() {
        return this.testerHost.getConfigurationData().getDialogicConfigurationData().getDestinationModuleId();
    }

    @Override
    public void setDestinationModuleId(int val) {
        this.testerHost.getConfigurationData().getDialogicConfigurationData().setDestinationModuleId(val);
        this.testerHost.markStore();
    }

    @Override
    public String getState() {
        StringBuilder sb = new StringBuilder();
        sb.append("IsStarted: ");
        sb.append(isStarted);

        return sb.toString();
    }

    public boolean start() {
        try {
            this.initDialogic(this.getSourceModuleId(), this.getDestinationModuleId());
            this.isStarted = true;
            this.testerHost.sendNotif(SOURCE_NAME, "Dialogic has been started", "", Level.INFO);
            return true;
        } catch (Throwable e) {
            this.testerHost.sendNotif(SOURCE_NAME, "Exception when starting DialogicMan", e, Level.ERROR);
            return false;
        }
    }

    public void stop() {
        try {
            this.isStarted = false;
            this.stopDialogic();
            this.testerHost.sendNotif(SOURCE_NAME, "Dialogic has been stopped", "", Level.INFO);
        } catch (Throwable e) {
            this.testerHost.sendNotif(SOURCE_NAME, "Exception when stopping DialogicMan", e, Level.ERROR);
        }
    }

    @Override
    public void execute() {
    }

    protected DialogicMtp3UserPartProxyInterface createDialogicMtp3UserPart() {
        return null;
    }

    private void initDialogic(int sourceModuleId, int destinationModuleId) throws Exception {
        this.dialogic = createDialogicMtp3UserPart();
        if (this.dialogic == null)
            throw new Exception("Dialogic cards are not supported in this version of SS7 Simulator");

        this.dialogic.setSourceModuleId(sourceModuleId);
        this.dialogic.setDestinationModuleId(destinationModuleId);

        // set 8 threads for delivering messages
        this.dialogic.setDeliveryMessageThreadCount(8);

        this.dialogic.start();
    }

    private void stopDialogic() throws Exception {
        if (this.dialogic != null)
            this.dialogic.stop();
        this.dialogic = null;
    }

    public Mtp3UserPart getMtp3UserPart() {
        return this.dialogic.getMtp3UserPart();
    }

}
