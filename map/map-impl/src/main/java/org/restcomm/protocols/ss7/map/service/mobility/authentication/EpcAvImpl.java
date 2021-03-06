
package org.restcomm.protocols.ss7.map.service.mobility.authentication;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.EpcAv;
import org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class EpcAvImpl implements EpcAv, MAPAsnPrimitive {

    public static final String _PrimitiveName = "EpcAv";

    private byte[] rand;
    private byte[] xres;
    private byte[] autn;
    private byte[] kasme;
    private MAPExtensionContainer extensionContainer;

    public EpcAvImpl() {
    }

    public EpcAvImpl(byte[] rand, byte[] xres, byte[] autn, byte[] kasme, MAPExtensionContainer extensionContainer) {
        this.rand = rand;
        this.xres = xres;
        this.autn = autn;
        this.kasme = kasme;
        this.extensionContainer = extensionContainer;
    }

    public byte[] getRand() {
        return rand;
    }

    public byte[] getXres() {
        return xres;
    }

    public byte[] getAutn() {
        return autn;
    }

    public byte[] getKasme() {
        return kasme;
    }

    public MAPExtensionContainer getExtensionContainer() {
        return extensionContainer;
    }

    public int getTag() throws MAPException {
        return Tag.SEQUENCE;
    }

    public int getTagClass() {
        return Tag.CLASS_UNIVERSAL;
    }

    public boolean getIsPrimitive() {
        return false;
    }

    public void decodeAll(AsnInputStream asnInputStream) throws MAPParsingComponentException {
        try {
            int length = asnInputStream.readLength();
            this._decode(asnInputStream, length);
        } catch (IOException e) {
            throw new MAPParsingComponentException("IOException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new MAPParsingComponentException("AsnException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    public void decodeData(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException {
        try {
            this._decode(asnInputStream, length);
        } catch (IOException e) {
            throw new MAPParsingComponentException("IOException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new MAPParsingComponentException("AsnException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    private void _decode(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException, IOException, AsnException {
        this.rand = null;
        this.xres = null;
        this.autn = null;
        this.kasme = null;
        this.extensionContainer = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);
        int num = 0;
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            switch (num) {
                case 0:
                    // rand
                    if (ais.getTagClass() != Tag.CLASS_UNIVERSAL || !ais.isTagPrimitive() || tag != Tag.STRING_OCTET)
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".rand: Parameter 0 bad tag or tag class or is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.rand = ais.readOctetString();
                    if (this.rand.length != 16)
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".rand: Bad field length: 16 is needed, found: " + this.rand.length,
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    break;

                case 1:
                    // xres
                    if (ais.getTagClass() != Tag.CLASS_UNIVERSAL || !ais.isTagPrimitive() || tag != Tag.STRING_OCTET)
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".sres: Parameter 1 bad tag or tag class or is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.xres = ais.readOctetString();
                    if (this.xres.length < 4 || this.xres.length > 16)
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".xres: Bad field length: from 4 to 16 is needed, found: " + this.xres.length,
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    break;

                case 2:
                    // autn
                    if (ais.getTagClass() != Tag.CLASS_UNIVERSAL || !ais.isTagPrimitive() || tag != Tag.STRING_OCTET)
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".autn: Parameter 2 bad tag or tag class or is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.autn = ais.readOctetString();
                    if (this.autn.length != 16)
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".autn: Bad field length: 16 is needed, found: " + this.autn.length,
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    break;

                case 3:
                    // kasme
                    if (ais.getTagClass() != Tag.CLASS_UNIVERSAL || !ais.isTagPrimitive() || tag != Tag.STRING_OCTET)
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".kasme: Parameter 2 bad tag or tag class or is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.kasme = ais.readOctetString();
                    if (this.kasme.length != 32)
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".kasme: Bad field length: 32 is needed, found: " + this.kasme.length,
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    break;

                default:
                    if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                        ais.advanceElement();
                    } else if (ais.getTagClass() == Tag.CLASS_UNIVERSAL) {

                        switch (tag) {
                            case Tag.SEQUENCE:
                                // extensionContainer
                                if (ais.isTagPrimitive())
                                    throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                            + ".extensionContainer: Parameter extensionContainer is primitive",
                                            MAPParsingComponentExceptionReason.MistypedParameter);
                                this.extensionContainer = new MAPExtensionContainerImpl();
                                ((MAPExtensionContainerImpl) this.extensionContainer).decodeAll(ais);
                                break;

                            default:
                                ais.advanceElement();
                                break;
                        }
                    } else {

                        ais.advanceElement();
                    }
                    break;
            }

            num++;
        }

        if (num < 4)
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": Needs at least 4 mandatory parameters, found " + num,
                    MAPParsingComponentExceptionReason.MistypedParameter);
    }

    public void encodeAll(AsnOutputStream asnOutputStream) throws MAPException {
        this.encodeAll(asnOutputStream, this.getTagClass(), this.getTag());
    }

    public void encodeAll(AsnOutputStream asnOutputStream, int tagClass, int tag) throws MAPException {
        try {
            asnOutputStream.writeTag(tagClass, this.getIsPrimitive(), tag);
            int pos = asnOutputStream.StartContentDefiniteLength();
            this.encodeData(asnOutputStream);
            asnOutputStream.FinalizeContent(pos);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        if (this.rand == null || this.xres == null || this.autn == null || this.kasme == null) {
            throw new MAPException("rand, xres, autn and kasme fields must not be null");
        }
        if (this.rand.length != 16)
            throw new MAPException("Wrong rand field length: must be 16, found " + this.rand.length);
        if (this.xres.length < 4 || this.xres.length > 16)
            throw new MAPException("Wrong xres field length: must be from 4 to 16, found " + this.xres.length);
        if (this.autn.length != 16)
            throw new MAPException("Wrong autn field length: must be 16, found " + this.autn.length);
        if (this.kasme.length != 32)
            throw new MAPException("Wrong kasme field length: must be 32, found " + this.kasme.length);

        try {
            asnOutputStream.writeOctetString(this.rand);
            asnOutputStream.writeOctetString(this.xres);
            asnOutputStream.writeOctetString(this.autn);
            asnOutputStream.writeOctetString(this.kasme);

            if (this.extensionContainer != null)
                ((MAPExtensionContainerImpl) this.extensionContainer).encodeAll(asnOutputStream);
        } catch (IOException e) {
            throw new MAPException("IOException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.rand != null) {
            sb.append("rand=[");
            sb.append(printDataArr(this.rand));
            sb.append("], ");
        }
        if (this.xres != null) {
            sb.append("xres=[");
            sb.append(printDataArr(this.xres));
            sb.append("], ");
        }
        if (this.autn != null) {
            sb.append("autn=[");
            sb.append(printDataArr(this.autn));
            sb.append("]");
        }
        if (this.kasme != null) {
            sb.append("kasme=[");
            sb.append(printDataArr(this.kasme));
            sb.append("]");
        }
        if (this.extensionContainer != null) {
            sb.append("extensionContainer=[");
            sb.append(this.extensionContainer);
            sb.append("]");
        }

        sb.append("]");

        return sb.toString();
    }

    private String printDataArr(byte[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int b : arr) {
            sb.append(b);
            sb.append(", ");
        }

        return sb.toString();
    }
}
