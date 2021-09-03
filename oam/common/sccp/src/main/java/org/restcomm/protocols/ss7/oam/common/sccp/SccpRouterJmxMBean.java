
package org.restcomm.protocols.ss7.oam.common.sccp;

import org.restcomm.protocols.ss7.sccp.Router;
import org.restcomm.protocols.ss7.sccpext.router.RouterExt;

/**
 * @author Amit Bhayani
 *
 */
public interface SccpRouterJmxMBean extends Router, RouterExt {

    void addRoutingAddress(int id, int ai, int pc, int ssn, int tt, int np, int nao, String digits) throws Exception;

    void addRule(int id, String ruleType, String algo, String originationType, int ai, int pc, int ssn, int tt, int np, int nao, String digits, String mask,
            int pAddressId, int sAddressId, int newCallingPartyAddressAddressId, int networkId, int callingai,
                 int callingpc, int callingssn, int callingtt, int callingnp,int callingnao, String callingdigits) throws Exception;

    void addSccpLongMessageRule(int id, int firstSpc, int lastSpc, String ruleType) throws Exception;

    void modifySccpLongMessageRule(int id, int firstSpc, int lastSpc, String ruleType) throws Exception;

    void modifySccpRoutingAddress(int id, int ai, int pc, int ssn, int tt, int np, int nao, String digits) throws Exception;

    void modifySccpRule(int id, String ruleType, String algo, String originationType, int ai, int pc, int ssn, int tt, int np, int nao, String digits, String mask,
            int pAddressId, int sAddressId, int newCallingPartyAddressAddressId, int networkId, int callingai,
            int callingpc, int callingssn, int callingtt, int callingnp,int callingnao, String callingdigits) throws Exception;

}
