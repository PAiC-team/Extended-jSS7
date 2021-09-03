
package org.restcomm.protocols.ss7.map.api.service.lsm;

/**
 *
 SLR-Arg-PCS-Extensions ::= SEQUENCE { ..., na-ESRK-Request [0] NULL OPTIONAL }
 *
 * @author sergey vetyutnev
 *
 */
public interface SLRArgPCSExtensions {

    boolean getNaEsrkRequest();

}
