
package org.restcomm.protocols.ss7.map.api.service.oam;

/**
*
<code>
4, 3 - MSC Record Type
  0 - Basic
  1 - Detailed (Optional)
  2 - Spare
  3 - No MSC Trace
</code>
*
* @author sergey vetyutnev
*
*/
public enum MscRecordType {

    Basic(0), Detailed(1), Spare(2), NoMscTrace(3);

    private int code;

    private MscRecordType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static MscRecordType getInstance(int code) {
        switch (code) {
        case 0:
            return MscRecordType.Basic;
        case 1:
            return MscRecordType.Detailed;
        case 2:
            return MscRecordType.Spare;
        case 3:
            return MscRecordType.NoMscTrace;
        default:
            return null;
        }
    }

}
