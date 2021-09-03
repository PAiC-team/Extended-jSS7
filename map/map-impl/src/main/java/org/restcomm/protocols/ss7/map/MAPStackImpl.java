
package org.restcomm.protocols.ss7.map;

import org.restcomm.protocols.ss7.map.api.MAPProvider;
import org.restcomm.protocols.ss7.map.api.MAPStack;
import org.restcomm.protocols.ss7.sccp.SccpProvider;
import org.restcomm.protocols.ss7.tcap.TCAPStackImpl;
import org.restcomm.protocols.ss7.tcap.api.TCAPProvider;
import org.restcomm.protocols.ss7.tcap.api.TCAPStack;

/**
 *
 * @author amit bhayani
 *
 */
public class MAPStackImpl implements MAPStack {

    protected TCAPStack tcapStack;

    protected MAPProviderImpl mapProvider;

    private State state = State.IDLE;

    private final String name;
    private final MAPStackConfigurationManagement mapCfg;

    private String persistDir = null;

    public MAPStackImpl(String name, SccpProvider sccpPprovider, int ssn) {
        this.name = name;
        this.tcapStack = new TCAPStackImpl(name, sccpPprovider, ssn);
        TCAPProvider tcapProvider = tcapStack.getProvider();
        mapProvider = new MAPProviderImpl(name, tcapProvider);

        this.state = State.CONFIGURED;

        this.mapCfg = MAPStackConfigurationManagement.getInstance();
        this.mapCfg.setConfigFileName(this.name);
    }

    public MAPStackImpl(String name, TCAPProvider tcapProvider) {
        this.name = name;
        mapProvider = new MAPProviderImpl(name, tcapProvider);
        this.tcapStack = tcapProvider.getStack();
        this.state = State.CONFIGURED;

        this.mapCfg = MAPStackConfigurationManagement.getInstance();
        this.mapCfg.setConfigFileName(this.name);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public MAPProvider getMAPProvider() {
        return this.mapProvider;
    }

    @Override
    public void start() throws Exception {
        this.mapCfg.setPersistDir(this.persistDir);
        this.mapCfg.load();

        if (state != State.CONFIGURED) {
            throw new IllegalStateException("Stack has not been configured or is already running!");
        }
        if (tcapStack != null) {
            // this is null in junits!
            this.tcapStack.start();
        }
        this.mapProvider.start();

        this.state = State.RUNNING;

    }

    @Override
    public void stop() {
        if (state != State.RUNNING) {
            throw new IllegalStateException("Stack is not running!");
        }
        this.mapProvider.stop();
        if (tcapStack != null) {
            this.tcapStack.stop();
        }

        this.state = State.CONFIGURED;
        mapCfg.store();
    }

    public void setPersistDir(String persistDir) {
        this.persistDir = persistDir;
    }

    // // ///////////////
    // // CONF METHOD //
    // // ///////////////
    // /**
    // * @throws ConfigurationException
    // *
    // */
    // public void configure(Properties props) throws ConfigurationException {
    // if (state != State.IDLE) {
    // throw new
    // IllegalStateException("Stack already been configured or is already running!");
    // }
    // tcapStack.configure(props);
    // TCAPProvider tcapProvider = tcapStack.getProvider();
    // mapProvider = new MAPProviderImpl(tcapProvider);
    // this.state = State.CONFIGURED;
    // }

    private enum State {
        IDLE, CONFIGURED, RUNNING;
    }

    @Override
    public TCAPStack getTCAPStack() {
        return this.tcapStack;
    }

//    public void onCongestionStart(String congName) {
//        this.mapProvider.onCongestionStart(congName);
//    }
//
//    public void onCongestionFinish(String congName) {
//        this.mapProvider.onCongestionFinish(congName);
//    }
}
