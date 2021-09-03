package org.restcomm.protocols.ss7.map.api.service.lsm;

import java.io.Serializable;

/**
 * Area ::= SEQUENCE { areaType [0] AreaType, areaIdentification [1] AreaIdentification, ...}
 *
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public interface Area extends Serializable {

    AreaType getAreaType();

    AreaIdentification getAreaIdentification();

}
