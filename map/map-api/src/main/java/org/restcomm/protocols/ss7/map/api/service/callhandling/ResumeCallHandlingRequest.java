
package org.restcomm.protocols.ss7.map.api.service.callhandling;

import java.util.ArrayList;

import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.DCSI;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtBasicServiceCode;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.OBcsmCamelTdpCriteria;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.OCSI;

/**
 *
 MAP V3-4:
 *
 * resumeCallHandling OPERATION ::= { --Timer m ARGUMENT ResumeCallHandlingArg RESULT ResumeCallHandlingRes -- optional ERRORS {
 * forwardingFailed | or-NotAllowed | unexpectedDataValue | dataMissing } CODE local:6 }
 *
 * ResumeCallHandlingArg ::= SEQUENCE { callReferenceNumber [0] CallReferenceNumber OPTIONAL, basicServiceGroup [1]
 * Ext-BasicServiceCode OPTIONAL, forwardingData [2] ForwardingData OPTIONAL, imsi [3] IMSI OPTIONAL, cug-CheckInfo [4]
 * CUG-CheckInfo OPTIONAL, o-CSI [5] O-CSI OPTIONAL, extensionContainer [7] ExtensionContainer OPTIONAL, ccbs-Possible [8] NULL
 * OPTIONAL, msisdn [9] ISDN-AddressString OPTIONAL, uu-Data [10] UU-Data OPTIONAL, allInformationSent [11] NULL OPTIONAL, ...,
 * d-csi [12] D-CSI OPTIONAL, o-BcsmCamelTDPCriteriaList [13] O-BcsmCamelTDPCriteriaList OPTIONAL, basicServiceGroup2 [14]
 * Ext-BasicServiceCode OPTIONAL, mtRoamingRetry [15] NULL OPTIONAL }
 *
 * O-BcsmCamelTDPCriteriaList ::= SEQUENCE SIZE (1..10) OF O-BcsmCamelTDP-Criteria
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ResumeCallHandlingRequest extends CallHandlingMessage {

     CallReferenceNumber getCallReferenceNumber();

     ExtBasicServiceCode getBasicServiceGroup();

     ForwardingData getForwardingData();

     IMSI getImsi();

     CUGCheckInfo getCugCheckInfo();

     OCSI getOCsi();

     MAPExtensionContainer getExtensionContainer();

     boolean getCcbsPossible();

     ISDNAddressString getMsisdn();

     UUData getUuData();

     boolean getAllInformationSent();

     DCSI getDCsi();

     ArrayList<OBcsmCamelTdpCriteria> getOBcsmCamelTDPCriteriaList();

     ExtBasicServiceCode getBasicServiceGroup2();

     boolean getMtRoamingRetry();

}
