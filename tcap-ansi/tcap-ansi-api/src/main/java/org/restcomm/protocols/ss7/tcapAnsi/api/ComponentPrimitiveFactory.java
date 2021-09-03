
package org.restcomm.protocols.ss7.tcapAnsi.api;

import org.restcomm.protocols.ss7.tcapAnsi.api.asn.comp.ErrorCode;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.comp.Invoke;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.comp.OperationCode;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.comp.Parameter;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.comp.Reject;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.comp.ReturnError;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.comp.ReturnResultLast;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.comp.ReturnResultNotLast;
import org.restcomm.protocols.ss7.tcapAnsi.api.tc.component.InvokeClass;

/**
 *
 * @author amit bhayani
 * @author baranowb
 */
public interface ComponentPrimitiveFactory {

    /**
     * Create a new Invoke. Class of this Invoke will be 1
     *
     * @return return new instance of ({@link Invoke}
     */
    Invoke createTCInvokeRequestNotLast();

    Invoke createTCInvokeRequestLast();

    /**
     * <p>
     * Create a new {@link Invoke}. Set the {@link InvokeClass} as per bellow consideration
     * </p>
     * <ul>
     * <li>Class 1 – Both success and failure are reported.</li>
     * <li>Class 2 – Only failure is reported.</li>
     * <li>Class 3 – Only success is reported.</li>
     * <li>Class 4 – Neither success, nor failure is reported.</li>
     * </ul>
     *
     * @param invokeClass The Class of Operation
     * @return new instance of ({@link Invoke}
     */
    Invoke createTCInvokeRequestNotLast(InvokeClass invokeClass);

    Invoke createTCInvokeRequestLast(InvokeClass invokeClass);

    Reject createTCRejectRequest();

    ReturnResultLast createTCResultLastRequest();

    ReturnResultNotLast createTCResultNotLastRequest();

    ReturnError createTCReturnErrorRequest();

    OperationCode createOperationCode();

    ErrorCode createErrorCode();

    Parameter createParameter();

    Parameter createParameter(int tag, int tagClass, boolean isPrimitive);

}
