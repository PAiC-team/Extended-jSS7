
package org.restcomm.protocols.ss7.tcapAnsi.tc.component;

import org.restcomm.protocols.ss7.tcapAnsi.TCAPProviderImpl;
import org.restcomm.protocols.ss7.tcapAnsi.api.ComponentPrimitiveFactory;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.comp.ErrorCode;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.comp.Invoke;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.comp.OperationCode;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.comp.Parameter;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.comp.Reject;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.comp.ReturnError;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.comp.ReturnResultLast;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.comp.ReturnResultNotLast;
import org.restcomm.protocols.ss7.tcapAnsi.api.tc.component.InvokeClass;
import org.restcomm.protocols.ss7.tcapAnsi.asn.InvokeImpl;
import org.restcomm.protocols.ss7.tcapAnsi.asn.TcapFactory;

/**
 * @author baranowb
 *
 */
public class ComponentPrimitiveFactoryImpl implements ComponentPrimitiveFactory {

    private TCAPProviderImpl provider;

    public ComponentPrimitiveFactoryImpl(TCAPProviderImpl tcaProviderImpl) {
        this.provider = tcaProviderImpl;
    }

    /*
     * (non-Javadoc)
     *
     * @seeorg.restcomm.protocols.ss7.tcap.api.ComponentPrimitiveFactory#createTCInvokeRequest()
     */
    public Invoke createTCInvokeRequestNotLast() {

        InvokeImpl t = (InvokeImpl) TcapFactory.createComponentInvoke();
        t.setNotLast(true);
        t.setProvider(provider);
        return t;
    }

    public Invoke createTCInvokeRequestLast() {

        InvokeImpl t = (InvokeImpl) TcapFactory.createComponentInvoke();
        t.setNotLast(false);
        t.setProvider(provider);
        return t;
    }

    /*
     * (non-Javadoc)
     *
     * @seeorg.restcomm.protocols.ss7.tcap.api.ComponentPrimitiveFactory# createTCInvokeRequest()
     */
    public Invoke createTCInvokeRequestNotLast(InvokeClass invokeClass) {

        InvokeImpl t = (InvokeImpl) TcapFactory.createComponentInvoke(invokeClass);
        t.setNotLast(true);
        t.setProvider(provider);
        return t;
    }

    public Invoke createTCInvokeRequestLast(InvokeClass invokeClass) {

        InvokeImpl t = (InvokeImpl) TcapFactory.createComponentInvoke(invokeClass);
        t.setNotLast(false);
        t.setProvider(provider);
        return t;
    }

    /*
     * (non-Javadoc)
     *
     * @seeorg.restcomm.protocols.ss7.tcap.api.ComponentPrimitiveFactory# createTCRejectRequest()
     */
    public Reject createTCRejectRequest() {

        return TcapFactory.createComponentReject();
    }

    public ReturnError createTCReturnErrorRequest() {

        return TcapFactory.createComponentReturnError();
    }

    /*
     * (non-Javadoc)
     *
     * @seeorg.restcomm.protocols.ss7.tcap.api.ComponentPrimitiveFactory# createTCResultRequest(boolean)
     */
    public ReturnResultLast createTCResultLastRequest() {

        return TcapFactory.createComponentReturnResultLast();

    }

    public ReturnResultNotLast createTCResultNotLastRequest() {

        return TcapFactory.createComponentReturnResultNotLast();
    }

    public OperationCode createOperationCode() {
        return TcapFactory.createOperationCode();
    }

    public ErrorCode createErrorCode() {
        return TcapFactory.createErrorCode();
    }

    public Parameter createParameter() {
        return TcapFactory.createParameter();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.api.ComponentPrimitiveFactory#createParameter(int, int, boolean)
     */
    public Parameter createParameter(int tag, int tagClass, boolean isPrimitive) {
        Parameter p = TcapFactory.createParameter();
        p.setTag(tag);
        p.setTagClass(tagClass);
        p.setPrimitive(isPrimitive);
        return p;
    }
}
