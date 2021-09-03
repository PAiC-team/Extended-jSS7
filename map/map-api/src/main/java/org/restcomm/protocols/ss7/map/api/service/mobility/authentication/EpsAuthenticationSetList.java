
package org.restcomm.protocols.ss7.map.api.service.mobility.authentication;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 EPS-AuthenticationSetList ::= SEQUENCE SIZE (1..5) OF EPC-AV
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface EpsAuthenticationSetList extends Serializable {

    ArrayList<EpcAv> getEpcAv();

}
