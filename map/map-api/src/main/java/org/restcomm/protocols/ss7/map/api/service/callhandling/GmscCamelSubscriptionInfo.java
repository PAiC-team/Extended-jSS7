
package org.restcomm.protocols.ss7.map.api.service.callhandling;

import java.io.Serializable;
import java.util.ArrayList;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.DCSI;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.OBcsmCamelTdpCriteria;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.OCSI;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.TBcsmCamelTdpCriteria;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.TCSI;

/**
 *
 GmscCamelSubscriptionInfo ::= SEQUENCE { t-CSI [0] T-CSI OPTIONAL, o-CSI [1] O-CSI OPTIONAL, extensionContainer [2]
 * ExtensionContainer OPTIONAL, ..., o-BcsmCamelTDP-CriteriaList [3] O-BcsmCamelTDPCriteriaList OPTIONAL,
 * t-BCSM-CAMEL-TDP-CriteriaList [4] T-BCSM-CAMEL-TDP-CriteriaList OPTIONAL, d-csi [5] D-CSI OPTIONAL}
 *
 * O-BcsmCamelTDPCriteriaList ::= SEQUENCE SIZE (1..10) OF O-BcsmCamelTDP-Criteria
 *
 * T-BCSM-CAMEL-TDP-CriteriaList ::= SEQUENCE SIZE (1..10) OF T-BCSM-CAMEL-TDP-Criteria
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface GmscCamelSubscriptionInfo extends Serializable {

     TCSI getTCsi();

     OCSI getOCsi();

     MAPExtensionContainer getMAPExtensionContainer();

     ArrayList<OBcsmCamelTdpCriteria> getOBcsmCamelTdpCriteriaList();

     ArrayList<TBcsmCamelTdpCriteria> getTBcsmCamelTdpCriteriaList();

     DCSI getDCsi();

}
