
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;
import java.util.ArrayList;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 T-CSI ::= SEQUENCE { t-BcsmCamelTDPDataList T-BcsmCamelTDPDataList, extensionContainer ExtensionContainer OPTIONAL, ...,
 * camelCapabilityHandling [0] CamelCapabilityHandling OPTIONAL, notificationToCSE [1] NULL OPTIONAL, csi-Active [2] NULL
 * OPTIONAL} -- notificationToCSE and csi-Active shall not be present when VT-CSI/T-CSI is sent -- to VLR/GMSC. -- They may only
 * be included in ATSI/ATM ack/NSDC message. -- T-CSI shall not be segmented.
 *
 * T-BcsmCamelTDPDataList ::= SEQUENCE SIZE (1..10) OF T-BcsmCamelTDPData --- T-BcsmCamelTDPDataList shall not contain more than
 * one instance of --- T-BcsmCamelTDPData containing the same value for t-BcsmTriggerDetectionPoint. --- For CAMEL Phase 2, this
 * means that only one instance of T-BcsmCamelTDPData is allowed --- with t-BcsmTriggerDetectionPoint being equal to DP12. ---
 * For CAMEL Phase 3, more TDPs are allowed.
 *
 * CamelCapabilityHandling ::= INTEGER(1..16) -- value 1 = CAMEL phase 1, -- value 2 = CAMEL phase 2, -- value 3 = CAMEL Phase
 * 3, -- value 4 = CAMEL phase 4: -- reception of values greater than 4 shall be treated as CAMEL phase 4.
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface TCSI extends Serializable {

    ArrayList<TBcsmCamelTDPData> getTBcsmCamelTDPDataList();

    MAPExtensionContainer getExtensionContainer();

    Integer getCamelCapabilityHandling();

    boolean getNotificationToCSE();

    boolean getCsiActive();

}
