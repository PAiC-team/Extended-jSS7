
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 GPRSSubscriptionDataWithdraw ::= CHOICE { allGPRSData NULL, contextIdList ContextIdList}
 *
 * ContextIdList ::= SEQUENCE SIZE (1..50) OF ContextId
 *
 * ContextId ::= INTEGER (1..50)
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface GPRSSubscriptionDataWithdraw extends Serializable {

    boolean getAllGPRSData();

    ArrayList<Integer> getContextIdList();

}
