
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import java.io.Serializable;

/**
<code>
GPRSMSClass ::= SEQUENCE {
  mSNetworkCapability      [0] MSNetworkCapability,
  mSRadioAccessCapability  [1] MSRadioAccessCapability OPTIONAL
}
</code>
 *
 * @author amit bhayani
 *
 */
public interface GPRSMSClass extends Serializable {

    MSNetworkCapability getMSNetworkCapability();

    MSRadioAccessCapability getMSRadioAccessCapability();

}
