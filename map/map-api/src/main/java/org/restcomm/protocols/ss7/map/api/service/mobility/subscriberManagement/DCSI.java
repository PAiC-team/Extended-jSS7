
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;
import java.util.ArrayList;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 D-CSI ::= SEQUENCE { dp-AnalysedInfoCriteriaList [0] DP-AnalysedInfoCriteriaList OPTIONAL, camelCapabilityHandling [1]
 * CamelCapabilityHandling OPTIONAL, extensionContainer [2] ExtensionContainer OPTIONAL, notificationToCSE [3] NULL OPTIONAL,
 * csi-Active [4] NULL OPTIONAL, ...} -- notificationToCSE and csi-Active shall not be present when D-CSI is sent to VLR/GMSC.
 * -- They may only be included in ATSI/ATM ack/NSDC message. -- DP-AnalysedInfoCriteria and camelCapabilityHandling shall be
 * present in -- the D-CSI sequence. -- If D-CSI is segmented, then the first segment shall contain dp-AnalysedInfoCriteriaList
 * -- and camelCapabilityHandling. Subsequent segments shall not contain -- camelCapabilityHandling, but may contain
 * dp-AnalysedInfoCriteriaList.
 *
 * DP-AnalysedInfoCriteriaList ::= SEQUENCE SIZE (1..10) OF DP-AnalysedInfoCriterium
 *
 * CamelCapabilityHandling ::= INTEGER(1..16) -- value 1 = CAMEL phase 1, -- value 2 = CAMEL phase 2, -- value 3 = CAMEL Phase
 * 3, -- value 4 = CAMEL phase 4: -- reception of values greater than 4 shall be treated as CAMEL phase 4.
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface DCSI extends Serializable {

    ArrayList<DPAnalysedInfoCriterium> getDPAnalysedInfoCriteriaList();

    Integer getCamelCapabilityHandling();

    MAPExtensionContainer getExtensionContainer();

    boolean getNotificationToCSE();

    boolean getCsiActive();

}
