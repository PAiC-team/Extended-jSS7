package org.restcomm.protocols.ss7.cap.api.service.sms.primitive;

import java.io.Serializable;

/**
*
<code>
freeFormatData      [0] OCTET STRING (SIZE(1 .. 160))
</code>
*
* @author Lasith Waruna Perera
*
*/
public interface FreeFormatDataSMS extends Serializable {

    byte[] getData();

}

