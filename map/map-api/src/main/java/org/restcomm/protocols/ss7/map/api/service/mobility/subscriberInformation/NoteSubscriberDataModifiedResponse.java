
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.MobilityMessage;

/**
 *
<code>
NoteSubscriberDataModifiedRes ::= SEQUENCE {
   extensionContainer ExtensionContainer OPTIONAL,
   ...
}
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface NoteSubscriberDataModifiedResponse extends MobilityMessage {

    MAPExtensionContainer getExtensionContainer();

}
