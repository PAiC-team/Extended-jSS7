package org.restcomm.protocols.ss7.sccp;

/**
 *
 * @author sergey vetyutnev
 */
public enum RemoteSccpStatus {
    /**
     * Indicates status available.
     */
    AVAILABLE,
    /**
     * Indicates status is unavailable - reason unknown.
     */
    UNAVAILABLE, UNEQUIPPED, INACCESSIBLE, CONGESTED;
}
