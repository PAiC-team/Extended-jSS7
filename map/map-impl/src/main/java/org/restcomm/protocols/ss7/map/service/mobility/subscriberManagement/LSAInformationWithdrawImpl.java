
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import java.io.IOException;
import java.util.ArrayList;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.LSAIdentity;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.LSAInformationWithdraw;
import org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive;

/**
*
* @author sergey vetyutnev
*
*/
public class LSAInformationWithdrawImpl implements LSAInformationWithdraw, MAPAsnPrimitive {

    public static final String _PrimitiveName = "LSAInformationWithdraw";

    private boolean allLSAData;
    private ArrayList<LSAIdentity> lsaIdentityList;

    public LSAInformationWithdrawImpl() {
    }

    public LSAInformationWithdrawImpl(boolean allLSAData) {
        this.allLSAData = allLSAData;
    }

    public LSAInformationWithdrawImpl(ArrayList<LSAIdentity> lsaIdentityList) {
        this.lsaIdentityList = lsaIdentityList;
    }


    @Override
    public boolean getAllLSAData() {
        return this.allLSAData;
    }

    @Override
    public ArrayList<LSAIdentity> getLSAIdentityList() {
        return this.lsaIdentityList;
    }


    @Override
    public int getTag() throws MAPException {
        if (allLSAData)
            return Tag.NULL;
        else if (lsaIdentityList != null)
            return Tag.SEQUENCE;

        throw new MAPException("Error encoding " + _PrimitiveName + ": no choices are selected");
    }

    @Override
    public int getTagClass() {
        return Tag.CLASS_UNIVERSAL;
    }

    @Override
    public boolean getIsPrimitive() {
        if (allLSAData)
            return true;
        else
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

        this.allLSAData = false;
        this.lsaIdentityList = null;

        if (asnInputStream.getTagClass() == Tag.CLASS_UNIVERSAL) {
            switch (asnInputStream.getTag()) {
            case Tag.NULL:
                if (!asnInputStream.isTagPrimitive())
                    throw new MAPParsingComponentException("Error while decoding allLSAData choice: Parameter is not primitive",
                            MAPParsingComponentExceptionReason.MistypedParameter);
                asnInputStream.readNullData(length);
                this.allLSAData = true;
                break;

            case Tag.SEQUENCE:
                if (asnInputStream.isTagPrimitive())
                    throw new MAPParsingComponentException("Error while decoding lsaIdentityList choice: Parameter is primitive",
                            MAPParsingComponentExceptionReason.MistypedParameter);

                AsnInputStream ais2 = asnInputStream.readSequenceStreamData(length);
                this.lsaIdentityList = new ArrayList<LSAIdentity>();
                while (true) {
                    if (ais2.available() == 0)
                        break;

                    int tag2 = ais2.readTag();
                    if (tag2 != Tag.STRING_OCTET || ais2.getTagClass() != Tag.CLASS_UNIVERSAL || !ais2.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ": bad contextIdList element tag or tagClass or is not primitive ", MAPParsingComponentExceptionReason.MistypedParameter);

                    LSAIdentity lsaIdentity = new LSAIdentityImpl();
                    ((LSAIdentityImpl) lsaIdentity).decodeAll(ais2);
                    this.lsaIdentityList.add(lsaIdentity);
                }
                if (this.lsaIdentityList.size() < 1 || this.lsaIdentityList.size() > 20) {
                    throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                            + ": Parameter contextIdList size must be from 1 to 20, found: " + this.lsaIdentityList.size(),
                            MAPParsingComponentExceptionReason.MistypedParameter);
                }
                break;

            default:
                throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + ": bad tag for Universal TagClass: " + asnInputStream.getTag(),
                        MAPParsingComponentExceptionReason.MistypedParameter);
            }
        } else {
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + ": bad tagClass: "
                    + asnInputStream.getTagClass(), MAPParsingComponentExceptionReason.MistypedParameter);
        }
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

        if (this.allLSAData == false && this.lsaIdentityList == null)
            throw new MAPException("Error while encoding the " + _PrimitiveName + ": no choice is defined");
        if (this.allLSAData == true && this.lsaIdentityList != null)
            throw new MAPException("Error while encoding the " + _PrimitiveName + ": both choice is defined");
        if (this.lsaIdentityList != null && (this.lsaIdentityList.size() < 1 || this.lsaIdentityList.size() > 20))
            throw new MAPException("Error while encoding the " + _PrimitiveName + "Parameter lsaIdentityList size must be from 1 to 20, found: "
                    + this.lsaIdentityList.size());

        if (this.allLSAData) {
            asnOutputStream.writeNullData();
        } else {
            for (LSAIdentity lsaId : this.lsaIdentityList) {
                if (lsaId == null)
                    throw new MAPException("Error while encoding the " + _PrimitiveName + ": lsaIdentityList must not contain null lsaId value");
                ((LSAIdentityImpl) lsaId).encodeAll(asnOutputStream);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.allLSAData) {
            sb.append("allLSAData, ");
        }
        if (this.lsaIdentityList != null) {
            sb.append("lsaIdentityList=[");
            for (LSAIdentity lsaId : this.lsaIdentityList) {
                sb.append(lsaId);
                sb.append(", ");
            }
            sb.append("], ");
        }

        sb.append("]");

        return sb.toString();
    }
}
