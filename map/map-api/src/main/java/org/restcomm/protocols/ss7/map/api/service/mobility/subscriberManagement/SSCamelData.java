
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;
import java.util.ArrayList;

import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSCode;

/**
 *
 SS-CamelData ::= SEQUENCE { ss-EventList SS-EventList, gsmSCF-Address ISDN-AddressString, extensionContainer [0]
 * ExtensionContainer OPTIONAL, ...}
 *
 * SS-EventList ::= SEQUENCE SIZE (1..10) OF SS-Code -- Actions for the following SS-Code values are defined in CAMEL Phase 3:
 * -- ect SS-Code ::= '00110001'B -- multiPTY SS-Code ::= '01010001'B -- cd SS-Code ::= '00100100'B -- ccbs SS-Code ::=
 * '01000100'B -- all other SS codes shall be ignored -- When SS-CSI is sent to the VLR, it shall not contain a marking for
 * ccbs. -- If the VLR receives SS-CSI containing a marking for ccbs, the VLR shall discard the -- ccbs marking in SS-CSI.
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface SSCamelData extends Serializable {

    ArrayList<SSCode> getSsEventList();

    ISDNAddressString getGsmSCFAddress();

    MAPExtensionContainer getExtensionContainer();

}
