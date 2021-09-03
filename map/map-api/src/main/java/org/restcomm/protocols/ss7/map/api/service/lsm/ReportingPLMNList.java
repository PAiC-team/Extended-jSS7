
package org.restcomm.protocols.ss7.map.api.service.lsm;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 ReportingPLMNList::= SEQUENCE { plmn-ListPrioritized [0] NULL OPTIONAL, plmn-List [1] PLMNList, ...}
 *
 * PLMNList::= SEQUENCE SIZE (1..20) OF ReportingPLMN
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ReportingPLMNList extends Serializable {

    boolean getPlmnListPrioritized();

    ArrayList<ReportingPLMN> getPlmnList();

}
