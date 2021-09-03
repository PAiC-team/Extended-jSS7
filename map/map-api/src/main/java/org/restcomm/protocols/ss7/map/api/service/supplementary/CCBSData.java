
package org.restcomm.protocols.ss7.map.api.service.supplementary;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.ExternalSignalInfo;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;

/**
 *
 CCBS-Data ::= SEQUENCE { ccbs-Feature [0] CCBS-Feature, translatedB-Number [1] ISDN-AddressString, serviceIndicator [2]
 * ServiceIndicator OPTIONAL, callInfo [3] ExternalSignalInfo, networkSignalInfo [4] ExternalSignalInfo, ...}
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface CCBSData extends Serializable {

    CCBSFeature getCcbsFeature();

    ISDNAddressString getTranslatedBNumber();

    ServiceIndicator getServiceIndicator();

    ExternalSignalInfo getCallInfo();

    ExternalSignalInfo getNetworkSignalInfo();

}
