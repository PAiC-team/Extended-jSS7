
package org.restcomm.protocols.ss7.cap.api.service.gprs.primitive;

import java.io.Serializable;

import org.restcomm.protocols.ss7.cap.api.primitives.AppendFreeFormatData;

/**
 *
 fCIBCCCAMELsequence1 [0] SEQUENCE { freeFormatData [0] OCTET STRING (SIZE(1 .. 160)), pDPID [1] PDPID OPTIONAL,
 * appendFreeFormatData [2] AppendFreeFormatData DEFAULT overwrite, ... }
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface FCIBCCCAMELsequence1Gprs extends Serializable {

    FreeFormatDataGprs getFreeFormatData();

    PDPID getPDPID();

    AppendFreeFormatData getAppendFreeFormatData();

}