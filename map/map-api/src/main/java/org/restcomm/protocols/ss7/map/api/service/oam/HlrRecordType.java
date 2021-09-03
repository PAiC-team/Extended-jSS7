
package org.restcomm.protocols.ss7.map.api.service.oam;

/**
*
<code>
4, 3 - HLR Record Type
  0 - Basic
  1 - Detailed
  2 - Spare
  3 - No HLR Trace
</code>
*
* @author sergey vetyutnev
*
*/
public enum HlrRecordType {

    Basic(0), Detailed(1), Spare(2), NoHlrTrace(3);

    private int code;

    private HlrRecordType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static HlrRecordType getInstance(int code) {
        switch (code) {
        case 0:
            return HlrRecordType.Basic;
        case 1:
            return HlrRecordType.Detailed;
        case 2:
            return HlrRecordType.Spare;
        case 3:
            return HlrRecordType.NoHlrTrace;
        default:
            return null;
        }
    }

}
