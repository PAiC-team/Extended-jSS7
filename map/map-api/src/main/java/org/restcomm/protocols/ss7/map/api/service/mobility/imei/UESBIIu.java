
package org.restcomm.protocols.ss7.map.api.service.mobility.imei;

import java.io.Serializable;

/**
 *
 UESBI-Iu ::= SEQUENCE { uesbi-IuA [0] UESBI-IuA OPTIONAL, uesbi-IuB [1] UESBI-IuB OPTIONAL, ...}
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface UESBIIu extends Serializable {

    UESBIIuA getUESBI_IuA();

    UESBIIuB getUESBI_IuB();

}
