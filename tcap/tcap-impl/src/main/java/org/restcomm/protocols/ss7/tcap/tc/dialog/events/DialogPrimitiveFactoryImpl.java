package org.restcomm.protocols.ss7.tcap.tc.dialog.events;

import org.restcomm.protocols.ss7.tcap.api.ComponentPrimitiveFactory;
import org.restcomm.protocols.ss7.tcap.api.DialogPrimitiveFactory;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCBeginIndication;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCBeginRequest;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCContinueIndication;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCContinueRequest;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCEndIndication;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCEndRequest;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCPAbortIndication;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCUniIndication;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCUniRequest;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCUserAbortIndication;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCUserAbortRequest;
import org.restcomm.protocols.ss7.tcap.asn.ApplicationContextName;
import org.restcomm.protocols.ss7.tcap.asn.TcapFactory;
import org.restcomm.protocols.ss7.tcap.asn.UserInformation;
import org.restcomm.protocols.ss7.tcap.tc.component.ComponentPrimitiveFactoryImpl;

/**
 * @author baranowb
 * @author amit bhayani
 *
 */
public class DialogPrimitiveFactoryImpl implements DialogPrimitiveFactory {

    private ComponentPrimitiveFactoryImpl componentPrimitiveFactory;

    public DialogPrimitiveFactoryImpl(ComponentPrimitiveFactory componentPrimitiveFactory) {
        this.componentPrimitiveFactory = (ComponentPrimitiveFactoryImpl) componentPrimitiveFactory;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.api.tc.dialog.DialogPrimitiveFactory
     * #createBegin(org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog)
     */
    public TCBeginRequest createBegin(Dialog tcapDialog) {
        if (tcapDialog == null) {
            throw new NullPointerException("TCAP Dialog is null");
        }
        TCBeginRequestImpl tcapBeginRequest = new TCBeginRequestImpl();
        tcapBeginRequest.setDialog(tcapDialog);
        tcapBeginRequest.setDestinationAddress(tcapDialog.getRemoteAddress());
        tcapBeginRequest.setOriginatingAddress(tcapDialog.getLocalAddress());
        return tcapBeginRequest;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.api.tc.dialog.DialogPrimitiveFactory
     * #createContinue(org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog)
     */
    public TCContinueRequest createContinue(Dialog tcapDialog) {
        if (tcapDialog == null) {
            throw new NullPointerException("TCAP Dialog is null");
        }
        TCContinueRequestImpl tcapContinueRequest = new TCContinueRequestImpl();
        tcapContinueRequest.setDialog(tcapDialog);
        tcapContinueRequest.setOriginatingAddress(tcapDialog.getLocalAddress());

        return tcapContinueRequest;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.api.tc.dialog.DialogPrimitiveFactory
     * #createEnd(org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog)
     */
    public TCEndRequest createEnd(Dialog tcapDialog) {
        if (tcapDialog == null) {
            throw new NullPointerException("TCAP Dialog is null");
        }
        TCEndRequestImpl tcapEndRequest = new TCEndRequestImpl();
        tcapEndRequest.setDialog(tcapDialog);
        tcapEndRequest.setOriginatingAddress(tcapDialog.getLocalAddress());
        // FIXME: add dialog portion fill
        return tcapEndRequest;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.api.DialogPrimitiveFactory#createUAbort
     * (org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog)
     */
    public TCUserAbortRequest createUAbort(Dialog tcapDialog) {
        if (tcapDialog == null) {
            throw new NullPointerException("TCAP Dialog is null");
        }
        TCUserAbortRequestImpl tcapUserAbortRequest = new TCUserAbortRequestImpl();
        tcapUserAbortRequest.setDialog(tcapDialog);
        tcapUserAbortRequest.setOriginatingAddress(tcapDialog.getLocalAddress());
        // FIXME: add dialog portion fill
        return tcapUserAbortRequest;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.api.tc.dialog.DialogPrimitiveFactory
     * #createUni(org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog)
     */
    public TCUniRequest createUni(Dialog tcapDialog) {
        if (tcapDialog == null) {
            throw new NullPointerException("TCAP Dialog is null");
        }
        TCUniRequestImpl tcapUniRequest = new TCUniRequestImpl();
        tcapUniRequest.setDialog(tcapDialog);
        tcapUniRequest.setDestinationAddress(tcapDialog.getRemoteAddress());
        tcapUniRequest.setOriginatingAddress(tcapDialog.getLocalAddress());
        return tcapUniRequest;
    }

    public TCBeginIndication createBeginIndication(Dialog tcapDialog) {

        if (tcapDialog == null) {
            throw new NullPointerException("TCAP Dialog is null");
        }
        TCBeginIndicationImpl tcapBeginIndication = new TCBeginIndicationImpl();
        tcapBeginIndication.setDialog(tcapDialog);
        return tcapBeginIndication;
    }

    public TCContinueIndication createContinueIndication(Dialog tcapDialog) {
        if (tcapDialog == null) {
            throw new NullPointerException("TCAP Dialog is null");
        }
        TCContinueIndicationImpl tcapContinueIndication = new TCContinueIndicationImpl();
        tcapContinueIndication.setDialog(tcapDialog);
        return tcapContinueIndication;
    }

    public TCEndIndication createEndIndication(Dialog tcapDialog) {
        if (tcapDialog == null) {
            throw new NullPointerException("TCAP Dialog is null");
        }
        TCEndIndicationImpl tcapEndIndication = new TCEndIndicationImpl();
        tcapEndIndication.setDialog(tcapDialog);
        return tcapEndIndication;
    }

    public TCUserAbortIndication createUAbortIndication(Dialog tcapDialog) {
        if (tcapDialog == null) {
            throw new NullPointerException("TCAP Dialog is null");
        }
        TCUserAbortIndicationImpl tcapUserAbortIndication = new TCUserAbortIndicationImpl();
        tcapUserAbortIndication.setDialog(tcapDialog);
        return tcapUserAbortIndication;
    }

    public TCPAbortIndication createPAbortIndication(Dialog tcapDialog) {
        if (tcapDialog == null) {
            throw new NullPointerException("TCAP Dialog is null");
        }
        TCPAbortIndicationImpl tcapProviderAbortIndication = new TCPAbortIndicationImpl();
        tcapProviderAbortIndication.setDialog(tcapDialog);
        return tcapProviderAbortIndication;
    }

    public TCUniIndication createUniIndication(Dialog tcapDialog) {
        if (tcapDialog == null) {
            throw new NullPointerException("TCAP Dialog is null");
        }
        TCUniIndicationImpl tcapUniIndication = new TCUniIndicationImpl();
        tcapUniIndication.setDialog(tcapDialog);
        return tcapUniIndication;
    }

    // Additional for APDU

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.api.tc.dialog.DialogPrimitiveFactory #createApplicationContextName()
     */
    public ApplicationContextName createApplicationContextName(long[] oid) {
        return TcapFactory.createApplicationContextName(oid);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.api.tc.dialog.DialogPrimitiveFactory #createUserInformation()
     */
    public UserInformation createUserInformation() {
        return TcapFactory.createUserInformation();
    }

}
