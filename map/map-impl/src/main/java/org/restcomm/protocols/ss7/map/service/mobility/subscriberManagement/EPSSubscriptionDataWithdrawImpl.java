
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
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.EPSSubscriptionDataWithdraw;
import org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive;

/**
*
* @author sergey vetyutnev
*
*/
public class EPSSubscriptionDataWithdrawImpl implements EPSSubscriptionDataWithdraw, MAPAsnPrimitive {

    public static final String _PrimitiveName = "EPSSubscriptionDataWithdraw";

    private boolean allEpsData;
    private ArrayList<Integer> contextIdList;

    public EPSSubscriptionDataWithdrawImpl() {
    }

    public EPSSubscriptionDataWithdrawImpl(boolean allEpsData) {
        this.allEpsData = allEpsData;
    }

    public EPSSubscriptionDataWithdrawImpl(ArrayList<Integer> contextIdList) {
        this.contextIdList = contextIdList;
    }


    @Override
    public boolean getAllEpsData() {
        return allEpsData;
    }

    @Override
    public ArrayList<Integer> getContextIdList() {
        return contextIdList;
    }


    @Override
    public int getTag() throws MAPException {
        if (allEpsData)
            return Tag.NULL;
        else if (contextIdList != null)
            return Tag.SEQUENCE;

        throw new MAPException("Error encoding " + _PrimitiveName + ": no choices are selected");
    }

    @Override
    public int getTagClass() {
        return Tag.CLASS_UNIVERSAL;
    }

    @Override
    public boolean getIsPrimitive() {
        if (allEpsData)
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

        this.allEpsData = false;
        this.contextIdList = null;

        if (asnInputStream.getTagClass() == Tag.CLASS_UNIVERSAL) {
            switch (asnInputStream.getTag()) {
            case Tag.NULL:
                if (!asnInputStream.isTagPrimitive())
                    throw new MAPParsingComponentException("Error while decoding allGPRSData choice: Parameter is not primitive",
                            MAPParsingComponentExceptionReason.MistypedParameter);
                asnInputStream.readNullData(length);
                this.allEpsData = true;
                break;

            case Tag.SEQUENCE:
                if (asnInputStream.isTagPrimitive())
                    throw new MAPParsingComponentException("Error while decoding contextIdList choice: Parameter is primitive",
                            MAPParsingComponentExceptionReason.MistypedParameter);

                AsnInputStream ais2 = asnInputStream.readSequenceStreamData(length);
                this.contextIdList = new ArrayList<Integer>();
                while (true) {
                    if (ais2.available() == 0)
                        break;

                    int tag2 = ais2.readTag();
                    if (tag2 != Tag.INTEGER || ais2.getTagClass() != Tag.CLASS_UNIVERSAL || !ais2.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ": bad contextIdList element tag or tagClass or is not primitive ", MAPParsingComponentExceptionReason.MistypedParameter);

                    Integer i1 = (int)ais2.readInteger();
                    this.contextIdList.add(i1);
                }
                if (this.contextIdList.size() < 1 || this.contextIdList.size() > 50) {
                    throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                            + ": Parameter contextIdList size must be from 1 to 50, found: " + this.contextIdList.size(),
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

        try {
            if (this.allEpsData == false && this.contextIdList == null)
                throw new MAPException("Error while encoding the " + _PrimitiveName + ": no choice is defined");
            if (this.allEpsData == true && this.contextIdList != null)
                throw new MAPException("Error while encoding the " + _PrimitiveName + ": both choice is defined");
            if (this.contextIdList != null && (this.contextIdList.size() < 1 || this.contextIdList.size() > 50))
                throw new MAPException("Error while encoding the " + _PrimitiveName + "Parameter contextIdList size must be from 1 to 50, found: "
                        + this.contextIdList.size());

            if (this.allEpsData) {
                asnOutputStream.writeNullData();
            } else {
                try {
                    for (Integer I1 : this.contextIdList) {
                        if (I1 == null)
                            throw new MAPException("Error while encoding the " + _PrimitiveName + ": contextIdList must not contain null Integer value");
                        asnOutputStream.writeInteger(I1);
                    }
                } catch (AsnException e) {
                    throw new MAPException("AsnException when encoding " + _PrimitiveName + ".contextIdList: " + e.getMessage(), e);
                }
            }

        } catch (IOException e) {
            throw new MAPException("IOException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.allEpsData) {
            sb.append("allEpsData, ");
        }
        if (this.contextIdList != null) {
            sb.append("contextIdList=[");
            for (Integer i1 : this.contextIdList) {
                sb.append(i1);
                sb.append(", ");
            }
            sb.append("], ");
        }

        sb.append("]");

        return sb.toString();
    }

}
