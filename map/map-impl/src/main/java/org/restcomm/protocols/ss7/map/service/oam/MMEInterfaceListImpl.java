
package org.restcomm.protocols.ss7.map.service.oam;

import org.restcomm.protocols.ss7.map.api.service.oam.MMEInterfaceList;
import org.restcomm.protocols.ss7.map.primitives.BitStringBase;

/**
*
* @author sergey vetyutnev
*
*/
public class MMEInterfaceListImpl extends BitStringBase implements MMEInterfaceList {

    static final int _ID_s1Mme = 0;
    static final int _ID_s3 = 1;
    static final int _ID_s6a = 2;
    static final int _ID_s10 = 3;
    static final int _ID_s11 = 4;

    public static final String _PrimitiveName = "MMEInterfaceList";

    public MMEInterfaceListImpl() {
        super(5, 8, 5, _PrimitiveName);
    }

    public MMEInterfaceListImpl(boolean s1Mme, boolean s3, boolean s6a, boolean s10, boolean s11) {
        super(5, 8, 5, _PrimitiveName);

        if (s1Mme)
            this.bitString.set(_ID_s1Mme);
        if (s3)
            this.bitString.set(_ID_s3);
        if (s6a)
            this.bitString.set(_ID_s6a);
        if (s10)
            this.bitString.set(_ID_s10);
        if (s11)
            this.bitString.set(_ID_s11);
    }

    @Override
    public boolean getS1Mme() {
        return this.bitString.get(_ID_s1Mme);
    }

    @Override
    public boolean getS3() {
        return this.bitString.get(_ID_s3);
    }

    @Override
    public boolean getS6a() {
        return this.bitString.get(_ID_s6a);
    }

    @Override
    public boolean getS10() {
        return this.bitString.get(_ID_s10);
    }

    @Override
    public boolean getS11() {
        return this.bitString.get(_ID_s11);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.getS1Mme()) {
            sb.append("s1Mme, ");
        }
        if (this.getS3()) {
            sb.append("s3, ");
        }
        if (this.getS6a()) {
            sb.append("s6a, ");
        }
        if (this.getS10()) {
            sb.append("s10, ");
        }
        if (this.getS11()) {
            sb.append("s11, ");
        }

        sb.append("]");
        return sb.toString();
    }

}
