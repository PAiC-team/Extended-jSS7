
package org.restcomm.protocols.ss7.map.service.oam;

import org.restcomm.protocols.ss7.map.api.service.oam.TraceNETypeList;
import org.restcomm.protocols.ss7.map.primitives.BitStringBase;

/**
*
* @author sergey vetyutnev
*
*/
public class TraceNETypeListImpl extends BitStringBase implements TraceNETypeList {

    static final int _ID_mscS = 0;
    static final int _ID_mgw = 1;
    static final int _ID_sgsn = 2;
    static final int _ID_ggsn = 3;
    static final int _ID_rnc = 4;
    static final int _ID_bmSc = 5;
    static final int _ID_mme = 6;
    static final int _ID_sgw = 7;
    static final int _ID_pgw = 8;
    static final int _ID_eNB = 9;

    public static final String _PrimitiveName = "TraceNETypeList";

    public TraceNETypeListImpl() {
        super(6, 16, 10, _PrimitiveName);
    }

    public TraceNETypeListImpl(boolean mscS, boolean mgw, boolean sgsn, boolean ggsn, boolean rnc, boolean bmSc, boolean mme, boolean sgw, boolean pgw,
            boolean enb) {
        super(6, 16, 10, _PrimitiveName);

        if (mscS)
            this.bitString.set(_ID_mscS);
        if (mgw)
            this.bitString.set(_ID_mgw);
        if (sgsn)
            this.bitString.set(_ID_sgsn);
        if (ggsn)
            this.bitString.set(_ID_ggsn);
        if (rnc)
            this.bitString.set(_ID_rnc);
        if (bmSc)
            this.bitString.set(_ID_bmSc);
        if (mme)
            this.bitString.set(_ID_mme);
        if (sgw)
            this.bitString.set(_ID_sgw);
        if (pgw)
            this.bitString.set(_ID_pgw);
        if (enb)
            this.bitString.set(_ID_eNB);
    }

    @Override
    public boolean getMscS() {
        return this.bitString.get(_ID_mscS);
    }

    @Override
    public boolean getMgw() {
        return this.bitString.get(_ID_mgw);
    }

    @Override
    public boolean getSgsn() {
        return this.bitString.get(_ID_sgsn);
    }

    @Override
    public boolean getGgsn() {
        return this.bitString.get(_ID_ggsn);
    }

    @Override
    public boolean getRnc() {
        return this.bitString.get(_ID_rnc);
    }

    @Override
    public boolean getBmSc() {
        return this.bitString.get(_ID_bmSc);
    }

    @Override
    public boolean getMme() {
        return this.bitString.get(_ID_mme);
    }

    @Override
    public boolean getSgw() {
        return this.bitString.get(_ID_sgw);
    }

    @Override
    public boolean getPgw() {
        return this.bitString.get(_ID_pgw);
    }

    @Override
    public boolean getEnb() {
        return this.bitString.get(_ID_eNB);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.getMscS()) {
            sb.append("mscS, ");
        }
        if (this.getMgw()) {
            sb.append("mgw, ");
        }
        if (this.getSgsn()) {
            sb.append("sgsn, ");
        }
        if (this.getGgsn()) {
            sb.append("ggsn, ");
        }
        if (this.getRnc()) {
            sb.append("rnc, ");
        }
        if (this.getBmSc()) {
            sb.append("bmSc, ");
        }
        if (this.getMme()) {
            sb.append("mme, ");
        }
        if (this.getSgw()) {
            sb.append("sgw, ");
        }
        if (this.getPgw()) {
            sb.append("pgw, ");
        }
        if (this.getEnb()) {
            sb.append("enb, ");
        }

        sb.append("]");
        return sb.toString();
    }

}
