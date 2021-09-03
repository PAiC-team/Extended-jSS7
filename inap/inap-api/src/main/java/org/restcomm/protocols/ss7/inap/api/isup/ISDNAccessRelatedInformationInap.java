package org.restcomm.protocols.ss7.inap.api.isup;

import java.io.Serializable;

import org.restcomm.protocols.ss7.inap.api.INAPException;
import org.restcomm.protocols.ss7.isup.message.parameter.accessTransport.AccessTransport;

/**
*
<code>
ISDNAccessRelatedInformation {PARAMETERS-BOUND : bound} ::= OCTET STRING(SIZE(
bound.&minISDNAccessRelatedInformationLength ..
bound.&maxISDNAccessRelatedInformationLength))
-- Indicates the destination user network interface related information.
-- Refer to the ITU-T Recommendation Q.763 Access
-- Transport parameter for encoding.
</code>

*
* @author sergey vetyutnev
*
*/
public interface ISDNAccessRelatedInformationInap extends Serializable {

    byte[] getData();

    AccessTransport getAccessTransport() throws INAPException;

}
