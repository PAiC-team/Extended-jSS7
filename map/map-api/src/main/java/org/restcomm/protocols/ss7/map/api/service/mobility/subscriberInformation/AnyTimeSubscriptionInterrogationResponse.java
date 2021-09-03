
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import java.util.ArrayList;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.MobilityMessage;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.CSGSubscriptionData;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.OfferedCamel4CSIs;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.SupportedCamelPhases;

/**
 *
<code>
AnyTimeSubscriptionInterrogationRes ::= SEQUENCE {
  callForwardingData         [1] CallForwardingData OPTIONAL,
  callBarringData            [2] CallBarringData OPTIONAL,
  odb-Info                   [3] ODB-Info OPTIONAL,
  camel-SubscriptionInfo     [4] CAMEL-SubscriptionInfo OPTIONAL,
  supportedVLR-CAMEL-Phases  [5] SupportedCamelPhases OPTIONAL,
  supportedSGSN-CAMEL-Phases [6] SupportedCamelPhases OPTIONAL,
  extensionContainer         [7] ExtensionContainer OPTIONAL,
  ... ,
  offeredCamel4CSIsInVLR     [8] OfferedCamel4CSIs OPTIONAL,
  offeredCamel4CSIsInSGSN    [9] OfferedCamel4CSIs OPTIONAL,
  msisdn-BS-List             [10] MSISDN-BS-List OPTIONAL,
  csg-SubscriptionDataList   [11] CSG-SubscriptionDataList OPTIONAL,
  cw-Data                    [12] CallWaitingData OPTIONAL,
  ch-Data                    [13] CallHoldData OPTIONAL,
  clip-Data                  [14] ClipData OPTIONAL,
  clir-Data                  [15] ClirData OPTIONAL,
  ect-data                   [16] EctData OPTIONAL
}

MSISDN-BS-List ::= SEQUENCE SIZE (1..50) OF MSISDN-BS

CSG-SubscriptionDataList ::= SEQUENCE SIZE (1..50) OF CSG-SubscriptionData
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface AnyTimeSubscriptionInterrogationResponse extends MobilityMessage {

    CallForwardingData getCallForwardingData();

    CallBarringData getCallBarringData();

    ODBInfo getOdbInfo();

    CAMELSubscriptionInfo getCamelSubscriptionInfo();

    SupportedCamelPhases getsupportedVlrCamelPhases();

    SupportedCamelPhases getsupportedSgsnCamelPhases();

    MAPExtensionContainer getExtensionContainer();

    OfferedCamel4CSIs getOfferedCamel4CSIsInVlr();

    OfferedCamel4CSIs getOfferedCamel4CSIsInSgsn();

    ArrayList<MSISDNBS> getMsisdnBsList();

    ArrayList<CSGSubscriptionData> getCsgSubscriptionDataList();

    CallWaitingData getCwData();

    CallHoldData getChData();

    ClipData getClipData();

    ClirData getClirData();

    EctData getEctData();

}
