
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive;

/**
 *
<code>
ConnectedNumberTreatmentInd ::= ENUMERATED {
  noINImpact (0),
  presentationRestricted (1),
  presentCalledINNumber (2),
  presentCallINNumberRestricted (3)
}
-- This parameter is used to suppress or to display the connected number.
</code>
 *
 * @author sergey vetyutnev
 *
 */
public enum ConnectedNumberTreatmentInd {

    noINImpact(0), presentationRestricted(1), presentCalledINNumber(2), presentCallINNumberRestricted(3);

    private int code;

    private ConnectedNumberTreatmentInd(int code) {
        this.code = code;
    }

    public static ConnectedNumberTreatmentInd getInstance(int code) {
        switch (code) {
            case 0:
                return ConnectedNumberTreatmentInd.noINImpact;
            case 1:
                return ConnectedNumberTreatmentInd.presentationRestricted;
            case 2:
                return ConnectedNumberTreatmentInd.presentCalledINNumber;
            case 3:
                return ConnectedNumberTreatmentInd.presentCallINNumberRestricted;
            default:
                return null;
        }
    }

    public int getCode() {
        return this.code;
    }
}
