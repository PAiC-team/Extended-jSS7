
package org.restcomm.protocols.ss7.map.primitives;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class OidContainer {

    private long[] data;

    public OidContainer() {
    }

    public OidContainer(long[] val) {
        this.data = val;
    }

    public void parseSerializedData(String val) throws NumberFormatException {
        if (val == null || val.length() == 0) {
            this.data = new long[0];
            return;
        }

        String[] ss = val.split("\\.");
        this.data = new long[ss.length];
        for (int i1 = 0; i1 < ss.length; i1++) {
            data[i1] = Long.parseLong(ss[i1]);
        }
    }

    public long[] getData() {
        return data;
    }

    public void setData(long[] val) {
        data = val;
    }

    public String getSerializedData() {
        if (this.data == null)
            return "";
        else {
            boolean isFirst = true;
            StringBuilder sb = new StringBuilder();
            for (long l : data) {
                if (isFirst)
                    isFirst = false;
                else
                    sb.append(".");
                sb.append(l);
            }
            return sb.toString();
        }
    }
}
