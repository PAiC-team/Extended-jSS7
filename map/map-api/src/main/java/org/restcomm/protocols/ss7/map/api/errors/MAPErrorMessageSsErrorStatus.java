
package org.restcomm.protocols.ss7.map.api.errors;

/**
 *
 ss-ErrorStatus ERROR ::= { PARAMETER SS-Status -- optional CODE local:17 }
 *
 * SS-Status ::= OCTET STRING (SIZE (1))
 *
 * -- bits 8765: 0000 (unused) -- bits 4321: Used to convey the "P bit","R bit","A bit" and "Q bit", -- representing
 * supplementary service state information -- as defined in TS 3GPP TS 23.011 [22]
 *
 * -- bit 4: "Q bit" -- bit 3: "P bit" -- bit 2: "R bit" -- bit 1: "A bit"
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface MAPErrorMessageSsErrorStatus extends MAPErrorMessage {

    int getData();

    boolean getQBit();

    boolean getPBit();

    boolean getRBit();

    boolean getABit();

    void setData(int val);

    void setQBit(boolean val);

    void setPBit(boolean val);

    void setRBit(boolean val);

    void setABit(boolean val);

}
