
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.CUGInfo;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.EMLPPInfo;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtCallBarInfo;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtForwInfo;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtSSData;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtSSInfo;
import org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive;

/**
 * @author daniel bichara
 * @author sergey vetyutnev
 *
 */
public class ExtSSInfoImpl implements ExtSSInfo, MAPAsnPrimitive {

    public static final String _PrimitiveName = "ExtSSInfo";

    protected static final int _TAG_forwardingInfo = 0;
    protected static final int _TAG_callBarringInfo = 1;
    protected static final int _TAG_cugInfo = 2;
    protected static final int _TAG_ssData = 3;
    protected static final int _TAG_emlppInfo = 4;

    private ExtForwInfo forwardingInfo = null;
    private ExtCallBarInfo callBarringInfo = null;
    private CUGInfo cugInfo = null;
    private ExtSSData ssData = null;
    private EMLPPInfo emlppInfo = null;

    public ExtSSInfoImpl() {

    }

    public ExtSSInfoImpl(ExtForwInfo forwardingInfo) {

        this.forwardingInfo = forwardingInfo;
    }

    public ExtSSInfoImpl(ExtCallBarInfo callBarringInfo) {

        this.callBarringInfo = callBarringInfo;
    }

    public ExtSSInfoImpl(CUGInfo cugInfo) {

        this.cugInfo = cugInfo;
    }

    public ExtSSInfoImpl(ExtSSData ssData) {

        this.ssData = ssData;
    }

    public ExtSSInfoImpl(EMLPPInfo emlppInfo) {

        this.emlppInfo = emlppInfo;
    }

    public ExtForwInfo getForwardingInfo() {
        return this.forwardingInfo;
    }

    public ExtCallBarInfo getCallBarringInfo() {
        return this.callBarringInfo;
    }

    public CUGInfo getCugInfo() {
        return this.cugInfo;
    }

    public ExtSSData getSsData() {
        return this.ssData;
    }

    public EMLPPInfo getEmlppInfo() {
        return this.emlppInfo;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#getTag()
     */
    public int getTag() throws MAPException {
        if (forwardingInfo != null) {
            return _TAG_forwardingInfo;
        } else if (callBarringInfo != null) {
            return _TAG_callBarringInfo;
        } else if (cugInfo != null) {
            return _TAG_cugInfo;
        } else if (ssData != null) {
            return _TAG_ssData;
        } else if (emlppInfo != null) {
            return _TAG_emlppInfo;
        } else {
            throw new MAPException("No of choices are supplied");
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#getTagClass()
     */
    public int getTagClass() {
        return Tag.CLASS_CONTEXT_SPECIFIC;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#getIsPrimitive ()
     */
    public boolean getIsPrimitive() {
        return false;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#decodeAll(org.mobicents.protocols.asn.AsnInputStream)
     */
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

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#decodeData(org.mobicents.protocols.asn.AsnInputStream,
     * int)
     */
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
        this.forwardingInfo = null;
        this.callBarringInfo = null;
        this.cugInfo = null;
        this.ssData = null;
        this.emlppInfo = null;

        if (asnInputStream.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC || asnInputStream.isTagPrimitive())
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": bad tag class or is primitive: TagClass=" + asnInputStream.getTagClass(),
                    MAPParsingComponentExceptionReason.MistypedParameter);

        switch (asnInputStream.getTag()) {
            case _TAG_forwardingInfo:
                this.forwardingInfo = new ExtForwInfoImpl();
                ((ExtForwInfoImpl) this.forwardingInfo).decodeData(asnInputStream, length);
                break;
            case _TAG_callBarringInfo:
                this.callBarringInfo = new ExtCallBarInfoImpl();
                ((ExtCallBarInfoImpl) this.callBarringInfo).decodeData(asnInputStream, length);
                break;
            case _TAG_cugInfo:
                this.cugInfo = new CUGInfoImpl();
                ((CUGInfoImpl) this.cugInfo).decodeData(asnInputStream, length);
                break;
            case _TAG_ssData:
                this.ssData = new ExtSSDataImpl();
                ((ExtSSDataImpl) this.ssData).decodeData(asnInputStream, length);
                break;
            case _TAG_emlppInfo:
                this.emlppInfo = new EMLPPInfoImpl();
                ((EMLPPInfoImpl) this.emlppInfo).decodeData(asnInputStream, length);
                break;

            default:
                throw new MAPParsingComponentException("Error while " + _PrimitiveName + ": bad tag: " + asnInputStream.getTag(),
                        MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#encodeAll( org.mobicents.protocols.asn.AsnOutputStream)
     */
    public void encodeAll(AsnOutputStream asnOutputStream) throws MAPException {
        this.encodeAll(asnOutputStream, this.getTagClass(), this.getTag());
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#encodeAll( org.mobicents.protocols.asn.AsnOutputStream,
     * int, int)
     */
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

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#encodeData (org.mobicents.protocols.asn.AsnOutputStream)
     */
    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {

        int cnt = 0;
        if (this.forwardingInfo != null)
            cnt++;
        if (this.callBarringInfo != null)
            cnt++;
        if (this.cugInfo != null)
            cnt++;
        if (this.ssData != null)
            cnt++;
        if (this.emlppInfo != null)
            cnt++;

        if (cnt != 1)
            throw new MAPException("Error while encoding " + _PrimitiveName + ": one and only one choice is required.");

        if (this.forwardingInfo != null) {
            ((ExtForwInfoImpl) this.forwardingInfo).encodeData(asnOutputStream);
            return;
        }

        if (this.callBarringInfo != null) {
            ((ExtCallBarInfoImpl) this.callBarringInfo).encodeData(asnOutputStream);
            return;
        }

        if (this.cugInfo != null) {
            ((CUGInfoImpl) this.cugInfo).encodeData(asnOutputStream);
            return;
        }

        if (this.ssData != null) {
            ((ExtSSDataImpl) this.ssData).encodeData(asnOutputStream);
            return;
        }

        if (this.emlppInfo != null) {
            ((EMLPPInfoImpl) this.emlppInfo).encodeData(asnOutputStream);
            return;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName + " [");

        if (this.forwardingInfo != null) {
            sb.append("forwardingInfo=");
            sb.append(this.forwardingInfo.toString());
            sb.append(", ");
        }

        if (this.callBarringInfo != null) {
            sb.append("callBarringInfo=");
            sb.append(this.callBarringInfo.toString());
            sb.append(", ");
        }

        if (this.cugInfo != null) {
            sb.append("cugInfo=");
            sb.append(this.cugInfo.toString());
            sb.append(", ");
        }

        if (this.ssData != null) {
            sb.append("ssData=");
            sb.append(this.ssData.toString());
            sb.append(", ");
        }

        if (this.emlppInfo != null) {
            sb.append("emlppInfo=");
            sb.append(this.emlppInfo.toString());
        }

        sb.append("]");

        return sb.toString();
    }

}
