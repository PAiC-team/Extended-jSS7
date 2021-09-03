package org.restcomm.protocols.ss7.map.api.service.lsm;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * AreaDefinition ::= SEQUENCE { areaList [0] AreaList, ...}
 *
 * AreaList ::= SEQUENCE SIZE (1..10) OF Area
 *
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public interface AreaDefinition extends Serializable {

    ArrayList<Area> getAreaList();

}
