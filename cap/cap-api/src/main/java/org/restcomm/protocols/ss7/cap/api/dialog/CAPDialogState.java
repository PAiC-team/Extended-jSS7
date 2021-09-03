
package org.restcomm.protocols.ss7.cap.api.dialog;

/**
 * @author amit bhayani
 *
 */
public enum CAPDialogState {

    Idle,

    InitialReceived, InitialSent,

    Active,

    // additional state to mark removal
    Expunged;
}
