package org.restcomm.protocols.ss7.tcapAnsi.api.tc.dialog;

/**
 * @author baranowb
 *
 */
public enum TRPseudoState {

    Idle,

    InitialReceived, InitialSent,

    Active,
    // additional state to mark removal
    Expunged;
}
