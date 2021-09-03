
package org.restcomm.protocols.ss7.cap.api.service.sms.primitive;

import java.io.Serializable;

import org.restcomm.protocols.ss7.cap.api.primitives.AppendFreeFormatData;

/**
 *
<code>
fCIBCCCAMELsequence1 [0] SEQUENCE {
  freeFormatData        [0] OCTET STRING (SIZE(1 .. 160)),
  appendFreeFormatData  [1] AppendFreeFormatData DEFAULT overwrite
}
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface FCIBCCCAMELsequence1SMS extends Serializable {

    FreeFormatDataSMS getFreeFormatData();

    AppendFreeFormatData getAppendFreeFormatData();
}
