
package org.restcomm.protocols.ss7.sccpext.router;

import java.util.Map;

import org.restcomm.protocols.ss7.sccp.LoadSharingAlgorithm;
import org.restcomm.protocols.ss7.sccp.OriginationType;
import org.restcomm.protocols.ss7.sccp.Rule;
import org.restcomm.protocols.ss7.sccp.RuleType;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;

/**
*
* @author Amit Bhayani
* @author sergey vetyutnev
*
*/
public interface RouterExt {

    void addRoutingAddress(int id, SccpAddress routingAddress) throws Exception;

    void removeRoutingAddress(int id) throws Exception;

    void modifyRoutingAddress(int routingAddressId, SccpAddress routingAddress) throws Exception;

    void modifyRoutingAddress(int routingAddressId, Integer ai, Integer pc, Integer ssn, Integer tt, Integer npValue,
            Integer naiValue, String digits) throws Exception;

    Map<Integer, SccpAddress> getRoutingAddresses();

    SccpAddress getRoutingAddress(int id);

    void addRule(int id, RuleType ruleType, LoadSharingAlgorithm algo, OriginationType originationType, SccpAddress pattern,
            String mask, int pAddressId, int sAddressId, Integer newCallingPartyAddressAddressId, int networkId,
            SccpAddress patternCallingAddress) throws Exception;

    void modifyRule(int id, RuleType ruleType, LoadSharingAlgorithm algo, OriginationType originationType, SccpAddress pattern,
            String mask, int pAddressId, int sAddressId, Integer newCallingPartyAddressAddressId, int networkId,
            SccpAddress patternCallingAddress) throws Exception;

    void modifyRule(int id, RuleType ruleType, LoadSharingAlgorithm algo, OriginationType originationType, SccpAddress pattern,
            String mask, Integer pAddressId, Integer sAddressId, Integer newCallingPartyAddressAddressId, Integer networkId,
            SccpAddress patternCallingAddress) throws Exception;

    Rule getRule(int id);

    void removeRule(int id) throws Exception;

    Map<Integer, Rule> getRules();

}
