
package org.restcomm.protocols.ss7.sccp;

import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;

/**
 *
 * @author Amit Bhayani
 *
 */
public interface Rule {

    int getRuleId();

    String getMask();

    RuleType getRuleType();

    LoadSharingAlgorithm getLoadSharingAlgorithm();

    SccpAddress getPattern();

    int getPrimaryAddressId();

    int getSecondaryAddressId();

    Integer getNewCallingPartyAddressId();

    OriginationType getOriginationType();

    int getNetworkId();

    SccpAddress getPatternCallingAddress();

    boolean matches(SccpAddress address, SccpAddress callingAddress, boolean isMtpOriginated, int msgNetworkId);

    SccpAddress translate(SccpAddress address, SccpAddress ruleAddress);


}
