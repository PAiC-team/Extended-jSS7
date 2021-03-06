
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.BitSetStrictLength;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.OfferedCamel4CSIs;
import org.restcomm.protocols.ss7.map.primitives.BitStringBase;

/**
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public class OfferedCamel4CSIsImpl extends BitStringBase implements OfferedCamel4CSIs {

    private static final int _ID_o_csi = 0;
    private static final int _ID_d_csi = 1;
    private static final int _ID_vt_csi = 2;
    private static final int _ID_t_csi = 3;
    private static final int _ID_mt_sms_csi = 4;
    private static final int _ID_mg_csi = 5;
    private static final int _ID_psi_enhancements = 6;

    private boolean badDecoded;

    public OfferedCamel4CSIsImpl() {
        super(7, 16, 7, "OfferedCamel4CSIs");
    }

    public OfferedCamel4CSIsImpl(boolean oCsi, boolean dCsi, boolean vtCsi, boolean tCsi, boolean mtSMSCsi, boolean mgCsi,
            boolean psiEnhancements) {
        super(7, 16, 7, "OfferedCamel4CSIs");

        if (oCsi)
            this.bitString.set(_ID_o_csi);
        if (dCsi)
            this.bitString.set(_ID_d_csi);
        if (vtCsi)
            this.bitString.set(_ID_vt_csi);
        if (tCsi)
            this.bitString.set(_ID_t_csi);
        if (mtSMSCsi)
            this.bitString.set(_ID_mt_sms_csi);
        if (mgCsi)
            this.bitString.set(_ID_mg_csi);
        if (psiEnhancements)
            this.bitString.set(_ID_psi_enhancements);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.service.subscriberManagement.OfferedCamel4CSIs#getOCsi()
     */
    public boolean getOCsi() {
        return this.bitString.get(_ID_o_csi);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.service.subscriberManagement.OfferedCamel4CSIs#getDCsi()
     */
    public boolean getDCsi() {
        return this.bitString.get(_ID_d_csi);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.service.subscriberManagement.OfferedCamel4CSIs#getVtCsi()
     */
    public boolean getVtCsi() {
        return this.bitString.get(_ID_vt_csi);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.service.subscriberManagement.OfferedCamel4CSIs#getTCsi()
     */
    public boolean getTCsi() {
        return this.bitString.get(_ID_t_csi);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.service.subscriberManagement.OfferedCamel4CSIs#getMtSmsCsi()
     */
    public boolean getMtSmsCsi() {
        return this.bitString.get(_ID_mt_sms_csi);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.service.subscriberManagement.OfferedCamel4CSIs#getMgCsi()
     */
    public boolean getMgCsi() {
        return this.bitString.get(_ID_mg_csi);
    }

    public boolean isBadDecoded() {
        return badDecoded;
    }

    @Override
    protected void _decode(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException, IOException, AsnException {
        if (length == 1) {
            // special case for 1-byte encoding style
            // we mark this primitive as bad decoded
            this.bitString = new BitSetStrictLength(0);
            this.badDecoded = true;
            int dataByte = asnInputStream.read();
        } else {
            super._decode(asnInputStream, length);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.service.subscriberManagement.OfferedCamel4CSIs#getPsiEnhancements()
     */
    public boolean getPsiEnhancements() {
        return this.bitString.get(_ID_psi_enhancements);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("OfferedCamel4CSIs [");

        if (getOCsi())
            sb.append("o_csi, ");
        if (getDCsi())
            sb.append("d_csi, ");
        if (getVtCsi())
            sb.append("vt_csi, ");
        if (getTCsi())
            sb.append("t_csi, ");
        if (getMtSmsCsi())
            sb.append("mt_sms_csi, ");
        if (getMgCsi())
            sb.append("mg_csi, ");
        if (getPsiEnhancements())
            sb.append("psi_enhancements, ");

        sb.append("]");

        return sb.toString();
    }
}
