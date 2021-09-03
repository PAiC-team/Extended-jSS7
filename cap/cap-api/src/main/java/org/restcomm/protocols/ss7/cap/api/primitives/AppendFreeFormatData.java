
package org.restcomm.protocols.ss7.cap.api.primitives;

/**
 *
 AppendFreeFormatData ::= ENUMERATED { overwrite (0), append (1) }
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum AppendFreeFormatData {

    overwrite(0), append(1);

    private int code;

    private AppendFreeFormatData(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static AppendFreeFormatData getInstance(int code) {
        switch (code) {
            case 0:
                return AppendFreeFormatData.overwrite;
            case 1:
                return AppendFreeFormatData.append;
            default:
                return null;
        }
    }
}
