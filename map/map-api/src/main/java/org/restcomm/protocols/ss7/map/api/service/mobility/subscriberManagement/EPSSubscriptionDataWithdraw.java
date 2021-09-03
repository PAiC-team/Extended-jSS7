
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 EPS-SubscriptionDataWithdraw ::= CHOICE { allEPS-Data NULL, contextIdList ContextIdList}
 *
 * ContextIdList ::= SEQUENCE SIZE (1..50) OF ContextId
 *
 * ContextId ::= INTEGER (1..50)
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface EPSSubscriptionDataWithdraw extends Serializable {

    boolean getAllEpsData();

    ArrayList<Integer> getContextIdList();

}
