
package org.restcomm.protocols.ss7.tcap;

import org.restcomm.protocols.ss7.sccp.parameter.ParameterFactory;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.tcap.api.TCAPException;
import org.restcomm.protocols.ss7.tcap.api.TCAPSendException;
import org.restcomm.protocols.ss7.tcap.api.TCAPStack;
import org.restcomm.protocols.ss7.tcap.api.tc.component.InvokeClass;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCBeginIndication;
import org.restcomm.protocols.ss7.tcap.asn.comp.Component;
import org.restcomm.protocols.ss7.tcap.asn.comp.ComponentType;
import org.restcomm.protocols.ss7.tcap.asn.comp.Invoke;
import org.restcomm.protocols.ss7.tcap.asn.comp.OperationCode;
import org.restcomm.protocols.ss7.tcap.asn.comp.ReturnResultLast;

/**
 * @author baranowb
 *
 */
public class Server extends EventTestHarness {

    protected Component[] components;

    /**
     * @param stack
     * @param thisAddress
     * @param remoteAddress
     */
    public Server(final TCAPStack stack, final ParameterFactory parameterFactory, final SccpAddress thisAddress, final SccpAddress remoteAddress) {
        super(stack, parameterFactory, thisAddress, remoteAddress);
    }

    @Override
    public void onTCBegin(TCBeginIndication ind) {
        // TODO Auto-generated method stub
        super.onTCBegin(ind);
        this.components = ind.getComponents();
    }

    @Override
    public void sendContinue() throws TCAPSendException, TCAPException {

        Component[] comps = components;
        if (comps == null || comps.length != 2) {
            throw new TCAPSendException("Bad comps!");
        }
        Component c = comps[0];
        if (c.getType() != ComponentType.Invoke) {
            throw new TCAPSendException("Bad type: " + c.getType());
        }
        // lets kill this Invoke - sending ReturnResultLast
        Invoke invoke = (Invoke) c;
        ReturnResultLast rrlast = this.tcapProvider.getComponentPrimitiveFactory().createTCResultLastRequest();
        rrlast.setInvokeId(invoke.getInvokeId());
        // we need not set operationCode here because of no Parameter is sent and ReturnResultLast will not carry
        // ReturnResultLast value
        // rrlast.setOperationCode(invoke.getOperationCode());
        super.dialog.sendComponent(rrlast);

        c = comps[1];
        if (c.getType() != ComponentType.Invoke) {
            throw new TCAPSendException("Bad type: " + c.getType());
        }

        // lets kill this Invoke - sending Invoke with linkedId
        invoke = (Invoke) c;
        Invoke invoke2 = this.tcapProvider.getComponentPrimitiveFactory().createTCInvokeRequest(InvokeClass.Class1);
        invoke2.setInvokeId(this.dialog.getNewInvokeId());
        invoke2.setLinkedId(invoke.getInvokeId());
        OperationCode oc = this.tcapProvider.getComponentPrimitiveFactory().createOperationCode();
        oc.setLocalOperationCode(new Long(14));
        invoke2.setOperationCode(oc);
        // no parameter
        this.dialog.sendComponent(invoke2);

        super.sendContinue();
    }

    public void sendContinue2() throws TCAPSendException, TCAPException {
        super.sendContinue();
    }

    public void releaseDialog() {
        if (this.dialog != null)
            this.dialog.release();
        this.dialog = null;
    }
}
