
package org.restcomm.protocols.ss7.cap.api.dialog;

import java.io.Serializable;

/**
 *
 * @author sergey vetyutnev
 *
 *         CAP-GPRS-ReferenceNumber ::= SEQUENCE { destinationReference [0] Integer4 OPTIONAL, originationReference [1] Integer4
 *         OPTIONAL } -- This parameter is used to identify the relationship between SGSN and the gsmSCF.
 *
 *         Integer4::= INTEGER (0..2147483647)
 */
public interface CAPGprsReferenceNumber extends Serializable {

    Integer getDestinationReference();

    Integer getOriginationReference();

    void setDestinationReference(Integer destinationReference);

    void setOriginationReference(Integer originationReference);
}