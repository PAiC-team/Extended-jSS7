
package org.restcomm.protocols.ss7.map.api.service.mobility.authentication;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 QuintupletList ::= SEQUENCE SIZE (1..5) OF AuthenticationQuintuplet
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface QuintupletList extends Serializable {

    ArrayList<AuthenticationQuintuplet> getAuthenticationQuintuplets();

}
