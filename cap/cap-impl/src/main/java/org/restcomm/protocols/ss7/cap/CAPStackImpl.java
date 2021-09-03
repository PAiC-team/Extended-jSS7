package org.restcomm.protocols.ss7.cap;

import org.restcomm.protocols.ss7.cap.api.CAPProvider;
import org.restcomm.protocols.ss7.cap.api.CAPStack;
import org.restcomm.protocols.ss7.sccp.SccpProvider;
import org.restcomm.protocols.ss7.tcap.TCAPStackImpl;
import org.restcomm.protocols.ss7.tcap.api.TCAPProvider;
import org.restcomm.protocols.ss7.tcap.api.TCAPStack;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class CAPStackImpl implements CAPStack {

    protected TCAPStack tcapStack;

    protected CAPProviderImpl capProvider;

    private State state = State.IDLE;

    private final String name;

    private CAPStackConfigurationManagement capStackCfgManagement;

    private String persistDir = null;

    public CAPStackImpl(String name, SccpProvider sccpProvider, int ssn) {
        this.name = name;
        this.tcapStack = new TCAPStackImpl(name, sccpProvider, ssn);
        TCAPProvider tcapProvider = tcapStack.getProvider();
        capProvider = new CAPProviderImpl(name, tcapProvider);

        this.state = State.CONFIGURED;

        this.capStackCfgManagement = CAPStackConfigurationManagement.getInstance();
        this.capStackCfgManagement.setConfigFileName(this.name);
    }

    public CAPStackImpl(String name, TCAPProvider tcapProvider) {
        this.name = name;
        capProvider = new CAPProviderImpl(name, tcapProvider);
        this.tcapStack = tcapProvider.getStack();
        this.state = State.CONFIGURED;

        this.capStackCfgManagement = CAPStackConfigurationManagement.getInstance();
        this.capStackCfgManagement.setConfigFileName(this.name);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public CAPProvider getCAPProvider() {
        return this.capProvider;
    }

    @Override
    public void start() throws Exception {
        this.capStackCfgManagement.setPersistDir(this.persistDir);
        this.capStackCfgManagement.load();

        if (state != State.CONFIGURED) {
            throw new IllegalStateException("Stack has not been configured or is already running!");
        }
        if (tcapStack != null) {
            // this is null in junits!
            this.tcapStack.start();
        }
        this.capProvider.start();

        this.state = State.RUNNING;

    }

    @Override
    public void stop() {
        if (state != State.RUNNING) {
            throw new IllegalStateException("Stack is not running!");
        }
        this.capProvider.stop();
        if (tcapStack != null) {
            this.tcapStack.stop();
        }

        this.state = State.CONFIGURED;
        this.capStackCfgManagement.store();
    }

    @Override
    public TCAPStack getTCAPStack() {
        return this.tcapStack;
    }

    public void setPersistDir(String persistDir) {
        this.persistDir = persistDir;
    }

    private enum State {
        IDLE, CONFIGURED, RUNNING;
    }

}
