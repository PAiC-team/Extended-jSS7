
package org.restcomm.protocols.ss7.map.service.oam;

import org.restcomm.protocols.ss7.map.api.service.oam.PGWInterfaceList;
import org.restcomm.protocols.ss7.map.primitives.BitStringBase;

/**
*
* @author sergey vetyutnev
*
*/
public class PGWInterfaceListImpl extends BitStringBase implements PGWInterfaceList {

    static final int _ID_s2a = 0;
    static final int _ID_s2b = 1;
    static final int _ID_s2c = 2;
    static final int _ID_s5 = 3;
    static final int _ID_s6b = 4;
    static final int _ID_gx = 5;
    static final int _ID_s8b = 6;
    static final int _ID_sgi = 7;

    public static final String _PrimitiveName = "PGWInterfaceList";

    public PGWInterfaceListImpl() {
        super(8, 16, 8, _PrimitiveName);
    }

    public PGWInterfaceListImpl(boolean s2a, boolean s2b, boolean s2c, boolean s5, boolean s6b, boolean gx, boolean s8b, boolean sgi) {
        super(8, 16, 8, _PrimitiveName);

        if (s2a)
            this.bitString.set(_ID_s2a);
        if (s2b)
            this.bitString.set(_ID_s2b);
        if (s2c)
            this.bitString.set(_ID_s2c);
        if (s5)
            this.bitString.set(_ID_s5);
        if (s6b)
            this.bitString.set(_ID_s6b);
        if (gx)
            this.bitString.set(_ID_gx);
        if (s8b)
            this.bitString.set(_ID_s8b);
        if (sgi)
            this.bitString.set(_ID_sgi);
    }

    @Override
    public boolean getS2a() {
        return this.bitString.get(_ID_s2a);
    }

    @Override
    public boolean getS2b() {
        return this.bitString.get(_ID_s2b);
    }

    @Override
    public boolean getS2c() {
        return this.bitString.get(_ID_s2c);
    }

    @Override
    public boolean getS5() {
        return this.bitString.get(_ID_s5);
    }

    @Override
    public boolean getS6b() {
        return this.bitString.get(_ID_s6b);
    }

    @Override
    public boolean getGx() {
        return this.bitString.get(_ID_gx);
    }

    @Override
    public boolean getS8b() {
        return this.bitString.get(_ID_s8b);
    }

    @Override
    public boolean getSgi() {
        return this.bitString.get(_ID_sgi);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.getS2a()) {
            sb.append("s2a, ");
        }
        if (this.getS2b()) {
            sb.append("s2b, ");
        }
        if (this.getS2c()) {
            sb.append("s2c, ");
        }
        if (this.getS5()) {
            sb.append("s5, ");
        }
        if (this.getS6b()) {
            sb.append("s6b, ");
        }
        if (this.getGx()) {
            sb.append("gx, ");
        }
        if (this.getS8b()) {
            sb.append("s8b, ");
        }
        if (this.getSgi()) {
            sb.append("sgi, ");
        }

        sb.append("]");
        return sb.toString();
    }

}
