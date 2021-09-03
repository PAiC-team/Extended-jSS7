
package org.restcomm.protocols.ss7.map.api.service.oam;

/**
*
<code>
6, 5 - BSS Record Type
  0 - Basic
  1 - Handover
  2 - Radio
  3 - No BSS Trace
</code>
*
* @author sergey vetyutnev
*
*/
public enum BssRecordType {

    Basic(0), Handover(1), Radio(2), NoBssTrace(3);

    private int code;

    private BssRecordType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static BssRecordType getInstance(int code) {
        switch (code) {
        case 0:
            return BssRecordType.Basic;
        case 1:
            return BssRecordType.Handover;
        case 2:
            return BssRecordType.Radio;
        case 3:
            return BssRecordType.NoBssTrace;
        default:
            return null;
        }
    }

}
