package org.restcomm.protocols.ss7.tcap.tc.component;

import org.restcomm.protocols.ss7.tcap.TCAPProviderImpl;
import org.restcomm.protocols.ss7.tcap.api.ComponentPrimitiveFactory;
import org.restcomm.protocols.ss7.tcap.api.tc.component.InvokeClass;
import org.restcomm.protocols.ss7.tcap.asn.InvokeImpl;
import org.restcomm.protocols.ss7.tcap.asn.TcapFactory;
import org.restcomm.protocols.ss7.tcap.asn.comp.ErrorCode;
import org.restcomm.protocols.ss7.tcap.asn.comp.Invoke;
import org.restcomm.protocols.ss7.tcap.asn.comp.OperationCode;
import org.restcomm.protocols.ss7.tcap.asn.comp.Parameter;
import org.restcomm.protocols.ss7.tcap.asn.comp.Problem;
import org.restcomm.protocols.ss7.tcap.asn.comp.ProblemType;
import org.restcomm.protocols.ss7.tcap.asn.comp.Reject;
import org.restcomm.protocols.ss7.tcap.asn.comp.ReturnError;
import org.restcomm.protocols.ss7.tcap.asn.comp.ReturnResult;
import org.restcomm.protocols.ss7.tcap.asn.comp.ReturnResultLast;

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
     * @see org.restcomm.protocols.ss7.tcap.api.ComponentPrimitiveFactory#createTCInvokeRequest()
     */
    public Invoke createTCInvokeRequest() {
        InvokeImpl invoke = (InvokeImpl) TcapFactory.createComponentInvoke();
        invoke.setProvider(provider);
        return invoke;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.api.ComponentPrimitiveFactory# createTCInvokeRequest()
     */
    public Invoke createTCInvokeRequest(InvokeClass invokeClass) {
        InvokeImpl invoke = (InvokeImpl) TcapFactory.createComponentInvoke(invokeClass);
        invoke.setProvider(provider);
        return invoke;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.api.ComponentPrimitiveFactory# createTCRejectRequest()
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
     * @see org.restcomm.protocols.ss7.tcap.api.ComponentPrimitiveFactory# createTCResultRequest(boolean)
     */
    public ReturnResultLast createTCResultLastRequest() {
        return TcapFactory.createComponentReturnResultLast();
    }

    public ReturnResult createTCResultRequest() {
        return TcapFactory.createComponentReturnResult();
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
        Parameter parameter = TcapFactory.createParameter();
        parameter.setTag(tag);
        parameter.setTagClass(tagClass);
        parameter.setPrimitive(isPrimitive);
        return parameter;
    }

    public Problem createProblem(ProblemType problemType) {
        return TcapFactory.createProblem(problemType);
    }
}
