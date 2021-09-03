package org.restcomm.protocols.ss7.map.api.service.lsm;

import java.io.Serializable;

/**
 *
 * Deferredmt-lrData ::= SEQUENCE { deferredLocationEventType DeferredLocationEventType, terminationCause [0] TerminationCause
 * OPTIONAL, lcsLocationInfo [1] LCSLocationInfo OPTIONAL, ...} -- lcsLocationInfo may be included only if a terminationCause is
 * present -- indicating mt-lrRestart.
 *
 * @author amit bhayani
 *
 */
public interface DeferredmtlrData extends Serializable {

    DeferredLocationEventType getDeferredLocationEventType();

    TerminationCause getTerminationCause();

    LCSLocationInfo getLCSLocationInfo();

}
