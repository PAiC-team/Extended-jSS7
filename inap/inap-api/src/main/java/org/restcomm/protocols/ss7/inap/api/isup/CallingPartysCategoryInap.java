package org.restcomm.protocols.ss7.inap.api.isup;

import java.io.Serializable;

import org.restcomm.protocols.ss7.inap.api.INAPException;
import org.restcomm.protocols.ss7.isup.message.parameter.CallingPartyCategory;

/**
 *
<code>
ISUP CallingPartyCategory wrapper

CallingPartysCategory::= OCTET STRING (SIZE (1))
-- Indicates the type of calling party (e.g. operator, payphone, ordinary subscriber). Refer to
-- ETS 300 356-1 [7] for encoding.
</code>
 *
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface CallingPartysCategoryInap extends Serializable {

    byte[] getData();

    CallingPartyCategory getCallingPartyCategory() throws INAPException;

}
