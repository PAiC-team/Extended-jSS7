
package org.restcomm.protocols.ss7.cap.api.errors;

/**
 *
 taskRefused ERROR ::= { PARAMETER ENUMERATED { generic (0), unobtainable (1), congestion (2) } CODE errcode-taskRefused } --
 * An entity normally capable of the task requested cannot or chooses not to perform the task at -- this time. This includes
 * error situations like congestion and unobtainable address as used in -- e.g. the connect operation.)
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface CAPErrorMessageTaskRefused extends CAPErrorMessage {

    TaskRefusedParameter getTaskRefusedParameter();

}
