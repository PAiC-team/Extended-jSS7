
package org.restcomm.protocols.ss7.map.service.mobility.faultRecovery;

import java.io.IOException;
import java.util.ArrayList;

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
import org.restcomm.protocols.ss7.map.api.primitives.NetworkResource;
import org.restcomm.protocols.ss7.map.api.service.mobility.faultRecovery.ResetRequest;
import org.restcomm.protocols.ss7.map.primitives.IMSIImpl;
import org.restcomm.protocols.ss7.map.primitives.ISDNAddressStringImpl;
import org.restcomm.protocols.ss7.map.service.mobility.MobilityMessageImpl;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class ResetRequestImpl extends MobilityMessageImpl implements ResetRequest {

    public static final String _PrimitiveName = "ResetRequest";

    private NetworkResource networkResource;
    private ISDNAddressString hlrNumber;
    private ArrayList<IMSI> hlrList;

    private long mapProtocolVersion;

    public ResetRequestImpl(long mapProtocolVersion) {
        this.mapProtocolVersion = mapProtocolVersion;
    }

    public ResetRequestImpl(NetworkResource networkResource, ISDNAddressString hlrNumber, ArrayList<IMSI> hlrList, long mapProtocolVersion) {
        this.networkResource = networkResource;
        this.hlrNumber = hlrNumber;
        this.hlrList = hlrList;

        this.mapProtocolVersion = mapProtocolVersion;
    }

    public long getMapProtocolVersion() {
        return this.mapProtocolVersion;
    }

    @Override
    public MAPMessageType getMessageType() {
        return MAPMessageType.reset_Request;
    }

    @Override
    public int getOperationCode() {
        return MAPOperationCode.reset;
    }

    @Override
    public NetworkResource getNetworkResource() {
        return networkResource;
    }

    @Override
    public ISDNAddressString getHlrNumber() {
        return hlrNumber;
    }

    @Override
    public ArrayList<IMSI> getHlrList() {
        return hlrList;
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
        this.networkResource = null;
        this.hlrNumber = null;
        this.hlrList = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);
        int num = 0;
        while (true) {
            if (ais.available() == 0) {
                break;
            }

            int tag = ais.readTag();

            switch (ais.getTagClass()) {
            case Tag.CLASS_UNIVERSAL:
                switch (tag) {
                case Tag.ENUMERATED:
                    if (!ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + ".networkResource: is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    int i1 = (int) ais.readInteger();
                    this.networkResource = NetworkResource.getInstance(i1);
                    break;
                case Tag.STRING_OCTET:
                    if (!ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + ".hlrNumber: is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.hlrNumber = new ISDNAddressStringImpl();
                    ((ISDNAddressStringImpl) this.hlrNumber).decodeAll(ais);
                    break;
                case Tag.SEQUENCE:
                    if (ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + ".hlrList: Parameter is primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);

                    AsnInputStream ais2 = ais.readSequenceStream();
                    this.hlrList = new ArrayList<IMSI>();
                    while (true) {
                        if (ais2.available() == 0)
                            break;

                        int tag2 = ais2.readTag();
                        if (tag2 != Tag.STRING_OCTET || ais2.getTagClass() != Tag.CLASS_UNIVERSAL || !ais2.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": bad hlrList element tag or tagClass or is not primitive ", MAPParsingComponentExceptionReason.MistypedParameter);

                        IMSI imsi = new IMSIImpl();
                        ((IMSIImpl) imsi).decodeAll(ais2);
                        this.hlrList.add(imsi);
                    }
                    if (this.hlrList.size() < 1 || this.hlrList.size() > 50) {
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ": Parameter hlrList size must be from 1 to 50, found: " + this.hlrList.size(),
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    }
                    break;

                default:
                    ais.advanceElement();
                    break;
                }
                break;

            default:
                ais.advanceElement();
                break;
            }

            num++;
        }

        if (this.hlrNumber == null)
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + ": hlrNumber is mandatory but absent absent",
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
            int posk = asnOutputStream.StartContentDefiniteLength();
            this.encodeData(asnOutputStream);
            asnOutputStream.FinalizeContent(posk);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        if (this.mapProtocolVersion == 1 && this.networkResource == null)
            throw new MAPException("For MAP version 1 networkResource must be present but it is empty");

        if (this.hlrNumber == null)
            throw new MAPException("hlrNumber must not be null");


        if (this.hlrList != null && (this.hlrList.size() < 1 || this.hlrList.size() > 50))
            throw new MAPException("hlrList size must be from 1 to 50, found: " + this.hlrList.size());

        if (this.mapProtocolVersion == 1) {
            try {
                asnOutputStream.writeInteger(Tag.CLASS_UNIVERSAL, Tag.ENUMERATED, this.networkResource.getCode());
            } catch (IOException e) {
                throw new MAPException("IOException while encoding " + _PrimitiveName + " parameter hlrList", e);
            } catch (AsnException e) {
                throw new MAPException("IOException while encoding " + _PrimitiveName + " parameter hlrList", e);
            }
        }

        ((ISDNAddressStringImpl) this.hlrNumber).encodeAll(asnOutputStream);

        if (this.hlrList != null) {
            try {
                asnOutputStream.writeTag(Tag.CLASS_UNIVERSAL, false, Tag.SEQUENCE);
                int pos = asnOutputStream.StartContentDefiniteLength();
                for (IMSI imsi : this.hlrList) {
                    ((IMSIImpl) imsi).encodeAll(asnOutputStream);
                }
                asnOutputStream.FinalizeContent(pos);
            } catch (AsnException e) {
                throw new MAPException("AsnException when encoding " + _PrimitiveName + " parameter hlrList: " + e.getMessage(), e);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.networkResource != null) {
            sb.append("networkResource=");
            sb.append(this.networkResource);
            sb.append(", ");
        }

        if (this.hlrNumber != null) {
            sb.append("hlrNumber=");
            sb.append(this.hlrNumber.toString());
            sb.append(", ");
        }

        if (this.hlrList != null) {
            sb.append("hlrList=[");
            boolean firstItem = true;
            for (IMSI imsi : this.hlrList) {
                if (firstItem)
                    firstItem = false;
                else
                    sb.append(", ");
                sb.append(imsi.toString());
            }
            sb.append("], ");
        }

        sb.append("mapProtocolVersion=");
        sb.append(this.mapProtocolVersion);

        sb.append("]");

        return sb.toString();
    }

}

