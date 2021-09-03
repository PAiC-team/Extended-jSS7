
package org.restcomm.protocols.ss7.cap.api.service.sms.primitive;

import java.io.Serializable;

/**
 *
 RPCause ::= OCTET STRING (SIZE (1)) -- RP cause according to 3GPP TS 24.011 [10] or 3GPP TS 29.002 [11]. -- GsmSCF shall send
 * this cause in the ReleaseSMS operation. -- For a MO-SMS service, the MSC or SGSN shall send the RP Cause to the originating
 * MS. -- It shall be used to overwrite the RP-Cause element in the RP-ERROR RPDU. -- For a MT-SMS service, the MSC or SGSN
 * shall send the RP Cause to the sending SMS-GMSC. -- It shall be used to overwrite the RP-Cause element in the RP-ERROR RPDU.
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface RPCause extends Serializable {

    int getData();

}