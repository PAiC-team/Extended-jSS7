package org.restcomm.protocols.ss7.inap.api.isup;

import java.io.Serializable;

import org.restcomm.protocols.ss7.inap.api.INAPException;
import org.restcomm.protocols.ss7.isup.message.parameter.GenericNumber;

/**
*
<code>
GenericNumber {PARAMETERS-BOUND : bound} ::= OCTET STRING (SIZE(
bound.&minGenericNumberLength..bound.&maxGenericNumberLength))
-- Refer to ITU-T Recommendation Q.763 Generic Number for encoding.
</code>
*
* @author sergey vetyutnev
*
*/
public interface GenericNumberInap extends Serializable {

    byte[] getData();

    GenericNumber getGenericNumber() throws INAPException;

}
