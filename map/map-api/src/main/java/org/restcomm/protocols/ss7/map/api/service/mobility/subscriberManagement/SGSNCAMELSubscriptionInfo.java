
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;
import java.util.ArrayList;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
<code>
SGSN-CAMEL-SubscriptionInfo ::= SEQUENCE {
  gprs-CSI            [0] GPRS-CSI OPTIONAL,
  mo-sms-CSI          [1] SMS-CSI OPTIONAL,
  extensionContainer  [2] ExtensionContainer OPTIONAL,
  ...,
  mt-sms-CSI          [3] SMS-CSI OPTIONAL,
  mt-smsCAMELTDP-CriteriaList [4] MT-smsCAMELTDP-CriteriaList OPTIONAL,
  mg-csi              [5] MG-CSI OPTIONAL
}

MT-smsCAMELTDP-CriteriaList ::= SEQUENCE SIZE (1.. 10) OF MT-smsCAMELTDP-Criteria
</code>
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface SGSNCAMELSubscriptionInfo extends Serializable {

    GPRSCSI getGprsCsi();

    SMSCSI getMoSmsCsi();

    MAPExtensionContainer getExtensionContainer();

    SMSCSI getMtSmsCsi();

    ArrayList<MTsmsCAMELTDPCriteria> getMtSmsCamelTdpCriteriaList();

    MGCSI getMgCsi();

}
