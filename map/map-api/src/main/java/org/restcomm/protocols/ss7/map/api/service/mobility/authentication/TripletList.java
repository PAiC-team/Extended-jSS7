
package org.restcomm.protocols.ss7.map.api.service.mobility.authentication;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 TripletList ::= SEQUENCE SIZE (1..5) OF AuthenticationTriplet
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface TripletList extends Serializable {

    ArrayList<AuthenticationTriplet> getAuthenticationTriplets();

}
