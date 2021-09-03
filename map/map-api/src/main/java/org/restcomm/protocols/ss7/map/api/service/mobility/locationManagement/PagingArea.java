
package org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 PagingArea ::= SEQUENCE SIZE (1..5) OF LocationArea
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface PagingArea extends Serializable {

    ArrayList<LocationArea> getLocationAreas();

}
