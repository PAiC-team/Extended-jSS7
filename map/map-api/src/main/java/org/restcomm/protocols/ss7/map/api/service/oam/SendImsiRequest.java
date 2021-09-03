
package org.restcomm.protocols.ss7.map.api.service.oam;

import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;

/**
 *
<code>
MAP V2:
sendIMSI OPERATION ::= {
  --Timer m
  ARGUMENT ISDN-AddressString
  RESULT IMSI
  ERRORS { dataMissing | unexpectedDataValue | unknownSubscriber}
  CODE local:58
}

ARGUMENT ISDN-AddressString
<code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface SendImsiRequest extends OamMessage {

    ISDNAddressString getMsisdn();

}
