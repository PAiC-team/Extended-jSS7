package org.restcomm.protocols.ss7.tcap.api;

import org.restcomm.protocols.ss7.tcap.api.tc.component.InvokeClass;
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
    Invoke createTCInvokeRequest();

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
    Invoke createTCInvokeRequest(InvokeClass invokeClass);

    Reject createTCRejectRequest();

    ReturnResultLast createTCResultLastRequest();

    ReturnResult createTCResultRequest();

    ReturnError createTCReturnErrorRequest();

    OperationCode createOperationCode();

    ErrorCode createErrorCode();

    Parameter createParameter();

    Parameter createParameter(int tag, int tagClass, boolean isPrimitive);

    Problem createProblem(ProblemType problemType);
}
