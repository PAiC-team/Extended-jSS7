package org.restcomm.protocols.ss7.cap.functional;

import org.mobicents.protocols.asn.AsnException;
import org.restcomm.protocols.ss7.cap.CAPProviderImpl;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPServiceBase;
import org.restcomm.protocols.ss7.cap.api.dialog.CAPGprsReferenceNumber;
import org.restcomm.protocols.ss7.map.api.service.supplementary.MAPServiceSupplementary;
import org.restcomm.protocols.ss7.tcap.api.TCAPProvider;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCBeginIndication;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCBeginRequest;
import org.restcomm.protocols.ss7.tcap.asn.ApplicationContextName;

/**
 *
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public class CAPProviderImplWrapper extends CAPProviderImpl {

    private int testMode = 0;

    // private final MAPServiceSupplementary mapServiceSupplementaryTest = new MAPServiceSupplementaryImplWrapper(this);

    public CAPProviderImplWrapper(TCAPProvider tcapProvider) {
        super("Test", tcapProvider);

        for (CAPServiceBase serv : this.capServices) {
            if (serv instanceof MAPServiceSupplementary) {
                this.capServices.remove(serv);
                break;
            }
        }

        // this.capServices.add(this.mapServiceSupplementaryTest);
    }

    // public MAPServiceSupplementary getMAPServiceSupplementary() {
    // return this.mapServiceSupplementaryTest;
    // }

    public void setTestMode(int testMode) {
        this.testMode = testMode;
    }

    public TCBeginRequest encodeTCBegin(Dialog tcapDialog, ApplicationContextName acn,
            CAPGprsReferenceNumber gprsReferenceNumber) throws CAPException {
        return super.encodeTCBegin(tcapDialog, acn, gprsReferenceNumber);
    }

    public void onTCBegin(TCBeginIndication tcBeginIndication) {
        if (this.testMode == 1) {
            try {
                byte[] data = tcBeginIndication.getUserInformation().getEncodeType();
                data[0] = 0;
            } catch (AsnException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        super.onTCBegin(tcBeginIndication);
    }
}
