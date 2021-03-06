
package org.restcomm.protocols.ss7.map.service.mobility.authentication;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPMessageType;
import org.restcomm.protocols.ss7.map.api.MAPOperationCode;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.AccessType;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.AuthenticationFailureReportRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.FailureCause;
import org.restcomm.protocols.ss7.map.primitives.IMSIImpl;
import org.restcomm.protocols.ss7.map.primitives.ISDNAddressStringImpl;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;
import org.restcomm.protocols.ss7.map.service.mobility.MobilityMessageImpl;

/**
*
* @author sergey vetyutnev
*
*/
public class AuthenticationFailureReportRequestImpl extends MobilityMessageImpl implements AuthenticationFailureReportRequest {

    protected static final int _TAG_vlrNumber = 0;
    protected static final int _TAG_sgsnNumber = 1;

    public static final String _PrimitiveName = "AuthenticationFailureReportRequest";

    private IMSI imsi;
    private FailureCause failureCause;
    private MAPExtensionContainer extensionContainer;
    private Boolean reAttempt;
    private AccessType accessType;
    private byte[] rand;
    private ISDNAddressString vlrNumber;
    private ISDNAddressString sgsnNumber;

    public AuthenticationFailureReportRequestImpl() {
    }

    public AuthenticationFailureReportRequestImpl(IMSI imsi, FailureCause failureCause, MAPExtensionContainer extensionContainer, Boolean reAttempt,
            AccessType accessType, byte[] rand, ISDNAddressString vlrNumber, ISDNAddressString sgsnNumber) {
        this.imsi = imsi;
        this.failureCause = failureCause;
        this.extensionContainer = extensionContainer;
        this.reAttempt = reAttempt;
        this.accessType = accessType;
        this.rand = rand;
        this.vlrNumber = vlrNumber;
        this.sgsnNumber = sgsnNumber;
    }

    @Override
    public MAPMessageType getMessageType() {
        return MAPMessageType.authenticationFailureReport_Request;
    }

    @Override
    public int getOperationCode() {
        return MAPOperationCode.authenticationFailureReport;
    }

    @Override
    public IMSI getImsi() {
        return imsi;
    }

    @Override
    public FailureCause getFailureCause() {
        return failureCause;
    }

    @Override
    public MAPExtensionContainer getExtensionContainer() {
        return extensionContainer;
    }

    @Override
    public Boolean getReAttempt() {
        return reAttempt;
    }

    @Override
    public AccessType getAccessType() {
        return accessType;
    }

    @Override
    public byte[] getRand() {
        return rand;
    }

    @Override
    public ISDNAddressString getVlrNumber() {
        return vlrNumber;
    }

    @Override
    public ISDNAddressString getSgsnNumber() {
        return sgsnNumber;
    }

    @Override
    public int getTag() throws MAPException {
        return Tag.SEQUENCE;
    }

    @Override
    public int getTagClass() {
        return Tag.CLASS_UNIVERSAL;
    }

    @Override
    public boolean getIsPrimitive() {
        return false;
    }

    @Override
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

    @Override
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

        imsi = null;
        failureCause = null;
        extensionContainer = null;
        reAttempt = null;
        accessType = null;
        rand = null;
        vlrNumber = null;
        sgsnNumber = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);
        int num = 0;
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            switch (num) {
            case 0:
                // imsi
                if (ais.getTagClass() != Tag.CLASS_UNIVERSAL || !ais.isTagPrimitive() || tag != Tag.STRING_OCTET)
                    throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                            + ".imsi: Parameter 0 bad tag or tag class or not primitive", MAPParsingComponentExceptionReason.MistypedParameter);
                this.imsi = new IMSIImpl();
                ((IMSIImpl) this.imsi).decodeAll(ais);
                break;

            case 1:
                // failureCause
                if (ais.getTagClass() != Tag.CLASS_UNIVERSAL || !ais.isTagPrimitive() || tag != Tag.ENUMERATED)
                    throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                            + ".failureCause: Parameter 1 bad tag class or tag or not primitive",
                            MAPParsingComponentExceptionReason.MistypedParameter);
                int vali = (int) ais.readInteger();
                this.failureCause = FailureCause.getInstance(vali);
                break;

            default:
                if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                    switch (tag) {
                    case _TAG_vlrNumber:
                        // vlrNumber
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".vlrNumber: Parameter is not primitive", MAPParsingComponentExceptionReason.MistypedParameter);
                        this.vlrNumber = new ISDNAddressStringImpl();
                        ((ISDNAddressStringImpl) this.vlrNumber).decodeAll(ais);
                        break;
                    case _TAG_sgsnNumber:
                        // sgsnNumber
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".sgsnNumber: Parameter is not primitive", MAPParsingComponentExceptionReason.MistypedParameter);
                        this.sgsnNumber = new ISDNAddressStringImpl();
                        ((ISDNAddressStringImpl) this.sgsnNumber).decodeAll(ais);
                        break;

                    default:
                        ais.advanceElement();
                        break;
                    }
                } else if (ais.getTagClass() == Tag.CLASS_UNIVERSAL) {

                    switch (tag) {
                    case Tag.SEQUENCE:
                        // extensionContainer
                        if (ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + ".extensionContainer: Parameter is primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);
                        this.extensionContainer = new MAPExtensionContainerImpl();
                        ((MAPExtensionContainerImpl) this.extensionContainer).decodeAll(ais);
                        break;
                    case Tag.BOOLEAN:
                        // reAttempt
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + ".reAttempt: Parameter is not primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);
                        this.reAttempt = ais.readBoolean();
                        break;
                    case Tag.ENUMERATED:
                        // accessType
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + ".accessType: Parameter is not primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);
                        vali = (int) ais.readInteger();
                        this.accessType = AccessType.getInstance(vali);
                        break;
                    case Tag.STRING_OCTET:
                        // rand
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + ".rand: Parameter is not primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);
                        this.rand = ais.readOctetString();
                        if (this.rand == null || this.rand.length != 16) {
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + ".rand: Expected size is 16, received size: "
                                    + (this.rand == null ? 0 : this.rand.length), MAPParsingComponentExceptionReason.MistypedParameter);
                        }
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

        if (num < 2)
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + ": Needs at least 2 mandatory parameters, found " + num,
                    MAPParsingComponentExceptionReason.MistypedParameter);
    }

    @Override
    public void encodeAll(AsnOutputStream asnOutputStream) throws MAPException {
        this.encodeAll(asnOutputStream, this.getTagClass(), this.getTag());
    }

    @Override
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

    @Override
    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        try {
            if (this.imsi == null || failureCause == null)
                throw new MAPException("IMSI and failureCause parameters must not be null");

            ((IMSIImpl) this.imsi).encodeAll(asnOutputStream);
            asnOutputStream.writeInteger(Tag.CLASS_UNIVERSAL, Tag.ENUMERATED, failureCause.getCode());

            if (this.extensionContainer != null)
                ((MAPExtensionContainerImpl) this.extensionContainer).encodeAll(asnOutputStream);

            if (reAttempt != null)
                asnOutputStream.writeBoolean(reAttempt);
            if (accessType != null)
                asnOutputStream.writeInteger(Tag.CLASS_UNIVERSAL, Tag.ENUMERATED, accessType.getCode());
            if (this.rand != null) {
                if (this.rand.length != 16)
                    throw new MAPException("rand parameter must have length 16, found: " + this.rand.length);
                asnOutputStream.writeOctetString(Tag.CLASS_UNIVERSAL, Tag.STRING_OCTET, this.rand);
            }

            if (this.vlrNumber != null)
                ((ISDNAddressStringImpl) this.vlrNumber).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_vlrNumber);
            if (this.sgsnNumber != null)
                ((ISDNAddressStringImpl) this.sgsnNumber).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_sgsnNumber);
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

        if (this.imsi != null) {
            sb.append("imsi=");
            sb.append(imsi.toString());
            sb.append(", ");
        }
        if (this.failureCause != null) {
            sb.append("failureCause=");
            sb.append(failureCause.toString());
            sb.append(", ");
        }
        if (this.extensionContainer != null) {
            sb.append("extensionContainer=");
            sb.append(extensionContainer.toString());
            sb.append(", ");
        }
        if (this.reAttempt != null) {
            sb.append("reAttempt=");
            sb.append(reAttempt.toString());
            sb.append(", ");
        }
        if (this.accessType != null) {
            sb.append("accessType=");
            sb.append(accessType.toString());
            sb.append(", ");
        }
        if (this.rand != null) {
            sb.append("rand=[");
            ArrayToString(rand);
            sb.append("], ");
        }
        if (this.vlrNumber != null) {
            sb.append("vlrNumber=");
            sb.append(vlrNumber.toString());
            sb.append(", ");
        }
        if (this.sgsnNumber != null) {
            sb.append("sgsnNumber=");
            sb.append(sgsnNumber.toString());
            sb.append(", ");
        }

        sb.append("]");

        return sb.toString();
    }

    private String ArrayToString(byte[] array) {
        StringBuilder sb = new StringBuilder();
        int i1 = 0;
        for (byte b : array) {
            if (i1 == 0)
                i1 = 1;
            else
                sb.append(", ");
            sb.append(b);
        }
        return sb.toString();
    }

}
