
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 LSAInformationWithdraw ::= CHOICE { allLSAData NULL, lsaIdentityList LSAIdentityList }
 *
 * LSAIdentityList ::= SEQUENCE SIZE (1..20) OF LSAIdentity
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface LSAInformationWithdraw extends Serializable {

    boolean getAllLSAData();

    ArrayList<LSAIdentity> getLSAIdentityList();

}
