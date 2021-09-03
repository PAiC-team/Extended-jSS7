package org.restcomm.protocols.ss7.map.functional;

import org.restcomm.protocols.ss7.map.MAPProviderImpl;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPServiceBase;
import org.restcomm.protocols.ss7.map.api.dialog.MAPProviderAbortReason;
import org.restcomm.protocols.ss7.map.api.service.supplementary.MAPServiceSupplementary;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerTest;
import org.restcomm.protocols.ss7.tcap.api.TCAPProvider;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCBeginIndication;
import org.restcomm.protocols.ss7.tcap.asn.ApplicationContextName;
import org.restcomm.protocols.ss7.tcap.asn.comp.Component;

/**
 *
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public class MAPProviderImplWrapper extends MAPProviderImpl {

    private int testMode = 0;

    private final MAPServiceSupplementary mapServiceSupplementaryTest = new MAPServiceSupplementaryImplWrapper(this);

    public MAPProviderImplWrapper(TCAPProvider tcapProvider) {
        super("Test", tcapProvider);

        for (MAPServiceBase serv : this.mapServices) {
            if (serv instanceof MAPServiceSupplementary) {
                this.mapServices.remove(serv);
                break;
            }
        }

        this.mapServices.add(this.mapServiceSupplementaryTest);
    }

    public MAPServiceSupplementary getMAPServiceSupplementary() {
        return this.mapServiceSupplementaryTest;
    }

    public void setTestMode(int testMode) {
        this.testMode = testMode;
    }

    public void onTCBegin(TCBeginIndication tcBeginIndication) {
        ApplicationContextName acn = tcBeginIndication.getApplicationContextName();
        Component[] comps = tcBeginIndication.getComponents();

        if (this.testMode == 1) {
            try {
                this.fireTCAbortProvider(tcBeginIndication.getDialog(), MAPProviderAbortReason.invalidPDU,
                        MAPExtensionContainerTest.GetTestExtensionContainer(), false);
            } catch (MAPException e) {
                loger.error("Error while firing TC-U-ABORT. ", e);
            }
            return;
        }

        super.onTCBegin(tcBeginIndication);
    }
}
