package org.restcomm.protocols.ss7.map.functional;

import org.apache.log4j.Logger;
import org.restcomm.protocols.ss7.map.api.MAPParameterFactory;
import org.restcomm.protocols.ss7.map.api.MAPProvider;
import org.restcomm.protocols.ss7.map.api.MAPStack;
import org.restcomm.protocols.ss7.map.api.errors.MAPErrorMessageFactory;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;

/**
 *
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public class Server extends EventTestHarness {

    private static Logger logger = Logger.getLogger(Server.class);

    private MAPFunctionalTest runningTestCase;
    private SccpAddress thisAddress;
    private SccpAddress remoteAddress;

    private MAPStack mapStack;
    protected MAPProvider mapProvider;

    protected MAPParameterFactory mapParameterFactory;
    protected MAPErrorMessageFactory mapErrorMessageFactory;

    Server(MAPStack mapStack, MAPFunctionalTest runningTestCase, SccpAddress thisAddress, SccpAddress remoteAddress) {
        super(logger);
        this.mapStack = mapStack;
        this.runningTestCase = runningTestCase;
        this.thisAddress = thisAddress;
        this.remoteAddress = remoteAddress;
        this.mapProvider = this.mapStack.getMAPProvider();

        this.mapParameterFactory = this.mapProvider.getMAPParameterFactory();
        this.mapErrorMessageFactory = this.mapProvider.getMAPErrorMessageFactory();

        this.mapProvider.addMAPDialogListener(this);
        this.mapProvider.getMAPServiceSupplementary().addMAPServiceListener(this);
        this.mapProvider.getMAPServiceSms().addMAPServiceListener(this);
        this.mapProvider.getMAPServiceMobility().addMAPServiceListener(this);
        this.mapProvider.getMAPServiceLsm().addMAPServiceListener(this);
        this.mapProvider.getMAPServiceCallHandling().addMAPServiceListener(this);
        this.mapProvider.getMAPServiceOam().addMAPServiceListener(this);
        this.mapProvider.getMAPServicePdpContextActivation().addMAPServiceListener(this);

        this.mapProvider.getMAPServiceSupplementary().activate();
        this.mapProvider.getMAPServiceSms().activate();
        this.mapProvider.getMAPServiceMobility().activate();
        this.mapProvider.getMAPServiceLsm().activate();
        this.mapProvider.getMAPServiceCallHandling().activate();
        this.mapProvider.getMAPServiceOam().activate();
        this.mapProvider.getMAPServicePdpContextActivation().activate();
    }

    public void debug(String message) {
        this.logger.debug(message);
    }

    public void error(String message, Exception e) {
        this.logger.error(message, e);
    }

}
