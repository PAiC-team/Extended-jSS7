
package org.restcomm.protocols.ss7.sccpext.impl.congestion;

import org.restcomm.protocols.ss7.sccp.NetworkIdState;

/**
*
* @author sergey vetyutnev
*
*/
public class NetworkIdStateImpl implements NetworkIdState {

    private boolean available;
    private int congLevel;
    private boolean affectedByPc;

    public NetworkIdStateImpl(boolean affectedByPc) {
        this.affectedByPc = affectedByPc;
        this.available = true;
    }

    public NetworkIdStateImpl(boolean available, boolean affectedByPc) {
        this.available = available;
        this.affectedByPc = affectedByPc;
    }

    public NetworkIdStateImpl(int congLevel, boolean affectedByPc) {
        this.congLevel = congLevel;
        this.affectedByPc = affectedByPc;
        this.available = true;
    }

    @Override
    public boolean isAvailable() {
        return this.available;
    }

    @Override
    public int getCongLevel() {
        return this.congLevel;
    }

    public boolean isAffectedByPc() {
        return this.affectedByPc;
    }

    public void setAffectedByPc(boolean val) {
        this.affectedByPc = val;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof NetworkIdStateImpl))
            return false;
        NetworkIdStateImpl b = (NetworkIdStateImpl) obj;
        if (this.affectedByPc == b.affectedByPc && this.available == b.available && this.congLevel == b.congLevel) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int res = this.congLevel;
        if (this.affectedByPc)
            res += 100;
        if (this.available)
            res += 1000;
        return res;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("NetworkIdState=[affectedByPc=");
        sb.append(affectedByPc);
        sb.append(", available=");
        sb.append(available);
        sb.append(", congLevel=");
        sb.append(congLevel);
        sb.append("]");

        return sb.toString();
    }
}
