
package org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement;

import org.restcomm.protocols.ss7.map.api.primitives.GSNAddress;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.MobilityMessage;

/**
 *
 MAP V3: updateGprsLocation OPERATION ::= { --Timer m ARGUMENT UpdateGprsLocationArg RESULT UpdateGprsLocationRes ERRORS {
 * systemFailure | unexpectedDataValue | unknownSubscriber | roamingNotAllowed} CODE local:23 }
 *
 *
 * UpdateGprsLocationArg ::= SEQUENCE { imsi IMSI, sgsn-Number ISDN-AddressString, sgsn-Address GSN-Address, extensionContainer
 * ExtensionContainer OPTIONAL, ... , sgsn-Capability [0] SGSN-Capability OPTIONAL, informPreviousNetworkEntity [1] NULL
 * OPTIONAL, ps-LCS-NotSupportedByUE [2] NULL OPTIONAL, v-gmlc-Address [3] GSN-Address OPTIONAL, add-info [4] ADD-Info OPTIONAL,
 * eps-info [5] EPS-Info OPTIONAL, servingNodeTypeIndicator [6] NULL OPTIONAL, skipSubscriberDataUpdate [7] NULL OPTIONAL,
 * usedRAT-Type [8] Used-RAT-Type OPTIONAL, gprsSubscriptionDataNotNeeded [9] NULL OPTIONAL, nodeTypeIndicator [10] NULL
 * OPTIONAL, areaRestricted [11] NULL OPTIONAL, ue-reachableIndicator [12] NULL OPTIONAL, epsSubscriptionDataNotNeeded [13] NULL
 * OPTIONAL, ue-srvcc-Capability [14] UE-SRVCC-Capability OPTIONAL }
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface UpdateGprsLocationRequest extends MobilityMessage {

    IMSI getImsi();

    ISDNAddressString getSgsnNumber();

    GSNAddress getSgsnAddress();

    MAPExtensionContainer getExtensionContainer();

    SGSNCapability getSGSNCapability();

    boolean getInformPreviousNetworkEntity();

    boolean getPsLCSNotSupportedByUE();

    GSNAddress getVGmlcAddress();

    ADDInfo getADDInfo();

    EPSInfo getEPSInfo();

    boolean getServingNodeTypeIndicator();

    boolean getSkipSubscriberDataUpdate();

    UsedRATType getUsedRATType();

    boolean getGprsSubscriptionDataNotNeeded();

    boolean getNodeTypeIndicator();

    boolean getAreaRestricted();

    boolean getUeReachableIndicator();

    boolean getEpsSubscriptionDataNotNeeded();

    UESRVCCCapability getUESRVCCCapability();

}
