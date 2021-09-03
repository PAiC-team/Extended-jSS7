
package org.restcomm.protocols.ss7.cap.service.gprs.primitive;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.GPRSQoS;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.GPRSQoSExtension;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.QualityOfService;
import org.restcomm.protocols.ss7.cap.primitives.SequenceBase;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class QualityOfServiceImpl extends SequenceBase implements QualityOfService {

    public static final int _ID_requestedQoS = 0;
    public static final int _ID_subscribedQoS = 1;
    public static final int _ID_negotiatedQoS = 2;
    public static final int _ID_requestedQoSExtension = 3;
    public static final int _ID_subscribedQoSExtension = 4;
    public static final int _ID_negotiatedQoSExtension = 5;

    private GPRSQoS requestedQoS;
    private GPRSQoS subscribedQoS;
    private GPRSQoS negotiatedQoS;
    private GPRSQoSExtension requestedQoSExtension;
    private GPRSQoSExtension subscribedQoSExtension;
    private GPRSQoSExtension negotiatedQoSExtension;

    public QualityOfServiceImpl() {
        super("QualityOfService");
    }

    public QualityOfServiceImpl(GPRSQoS requestedQoS, GPRSQoS subscribedQoS, GPRSQoS negotiatedQoS,
            GPRSQoSExtension requestedQoSExtension, GPRSQoSExtension subscribedQoSExtension,
            GPRSQoSExtension negotiatedQoSExtension) {
        super("QualityOfService");
        this.requestedQoS = requestedQoS;
        this.subscribedQoS = subscribedQoS;
        this.negotiatedQoS = negotiatedQoS;
        this.requestedQoSExtension = requestedQoSExtension;
        this.subscribedQoSExtension = subscribedQoSExtension;
        this.negotiatedQoSExtension = negotiatedQoSExtension;
    }

    @Override
    public GPRSQoS getRequestedQoS() {
        return this.requestedQoS;
    }

    @Override
    public GPRSQoS getSubscribedQoS() {
        return this.subscribedQoS;
    }

    @Override
    public GPRSQoS getNegotiatedQoS() {
        return this.negotiatedQoS;
    }

    @Override
    public GPRSQoSExtension getRequestedQoSExtension() {
        return this.requestedQoSExtension;
    }

    @Override
    public GPRSQoSExtension getSubscribedQoSExtension() {
        return this.subscribedQoSExtension;
    }

    @Override
    public GPRSQoSExtension getNegotiatedQoSExtension() {
        return this.negotiatedQoSExtension;
    }

    @Override
    protected void _decode(AsnInputStream asnInputStream, int length) throws CAPParsingComponentException, IOException, AsnException,
            MAPParsingComponentException {

        this.requestedQoS = null;
        this.subscribedQoS = null;
        this.negotiatedQoS = null;
        this.requestedQoSExtension = null;
        this.subscribedQoSExtension = null;
        this.negotiatedQoSExtension = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {
                    case _ID_requestedQoS:
                        if (ais.isTagPrimitive())
                            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".requestedQoS: Parameter is primitive",
                                    CAPParsingComponentExceptionReason.MistypedParameter);
                        this.requestedQoS = new GPRSQoSImpl();
                        AsnInputStream ais2 = ais.readSequenceStream();
                        ais2.readTag();
                        ((GPRSQoSImpl) this.requestedQoS).decodeAll(ais2);
                        break;
                    case _ID_subscribedQoS:
                        if (ais.isTagPrimitive())
                            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".subscribedQoS: Parameter is primitive",
                                    CAPParsingComponentExceptionReason.MistypedParameter);
                        this.subscribedQoS = new GPRSQoSImpl();
                        AsnInputStream ais3 = ais.readSequenceStream();
                        ais3.readTag();
                        ((GPRSQoSImpl) this.subscribedQoS).decodeAll(ais3);
                        break;
                    case _ID_negotiatedQoS:
                        if (ais.isTagPrimitive())
                            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".negotiatedQoS: Parameter is primitive",
                                    CAPParsingComponentExceptionReason.MistypedParameter);
                        this.negotiatedQoS = new GPRSQoSImpl();
                        AsnInputStream ais4 = ais.readSequenceStream();
                        ais4.readTag();
                        ((GPRSQoSImpl) this.negotiatedQoS).decodeAll(ais4);
                        break;
                    case _ID_requestedQoSExtension:
                        if (ais.isTagPrimitive())
                            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".requestedQoSExtension: Parameter is  primitive",
                                    CAPParsingComponentExceptionReason.MistypedParameter);
                        this.requestedQoSExtension = new GPRSQoSExtensionImpl();
                        ((GPRSQoSExtensionImpl) this.requestedQoSExtension).decodeAll(ais);
                        break;
                    case _ID_subscribedQoSExtension:
                        if (ais.isTagPrimitive())
                            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".subscribedQoSExtension: Parameter is primitive",
                                    CAPParsingComponentExceptionReason.MistypedParameter);
                        this.subscribedQoSExtension = new GPRSQoSExtensionImpl();
                        ((GPRSQoSExtensionImpl) this.subscribedQoSExtension).decodeAll(ais);
                        break;
                    case _ID_negotiatedQoSExtension:
                        if (ais.isTagPrimitive())
                            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".negotiatedQoSExtension: Parameter is primitive",
                                    CAPParsingComponentExceptionReason.MistypedParameter);
                        this.negotiatedQoSExtension = new GPRSQoSExtensionImpl();
                        ((GPRSQoSExtensionImpl) this.negotiatedQoSExtension).decodeAll(ais);
                        break;
                    default:
                        ais.advanceElement();
                        break;
                }
            } else {
                ais.advanceElement();
            }
        }

    }

    @Override
    public void encodeData(AsnOutputStream asnOutputStream) throws CAPException {

        if (this.requestedQoS != null) {
            try {
                asnOutputStream.writeTag(Tag.CLASS_CONTEXT_SPECIFIC, false, _ID_requestedQoS);
                int pos = asnOutputStream.StartContentDefiniteLength();
                ((GPRSQoSImpl) this.requestedQoS).encodeAll(asnOutputStream);
                asnOutputStream.FinalizeContent(pos);
            } catch (AsnException e) {
                throw new CAPException("AsnException while encoding " + _PrimitiveName + " parameter requestedQoS");
            }
        }

        if (this.subscribedQoS != null) {
            try {
                asnOutputStream.writeTag(Tag.CLASS_CONTEXT_SPECIFIC, false, _ID_subscribedQoS);
                int pos = asnOutputStream.StartContentDefiniteLength();
                ((GPRSQoSImpl) this.subscribedQoS).encodeAll(asnOutputStream);
                asnOutputStream.FinalizeContent(pos);
            } catch (AsnException e) {
                throw new CAPException("AsnException while encoding " + _PrimitiveName + " parameter subscribedQoS");
            }
        }

        if (this.negotiatedQoS != null) {
            try {
                asnOutputStream.writeTag(Tag.CLASS_CONTEXT_SPECIFIC, false, _ID_negotiatedQoS);
                int pos = asnOutputStream.StartContentDefiniteLength();
                ((GPRSQoSImpl) this.negotiatedQoS).encodeAll(asnOutputStream);
                asnOutputStream.FinalizeContent(pos);
            } catch (AsnException e) {
                throw new CAPException("AsnException while encoding " + _PrimitiveName + " parameter negotiatedQoS");
            }
        }

        if (this.requestedQoSExtension != null)
            ((GPRSQoSExtensionImpl) this.requestedQoSExtension).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC,
                    _ID_requestedQoSExtension);

        if (this.subscribedQoSExtension != null)
            ((GPRSQoSExtensionImpl) this.subscribedQoSExtension).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC,
                    _ID_subscribedQoSExtension);

        if (this.negotiatedQoSExtension != null)
            ((GPRSQoSExtensionImpl) this.negotiatedQoSExtension).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC,
                    _ID_negotiatedQoSExtension);

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName + " [");

        if (this.requestedQoS != null) {
            sb.append("requestedQoS=");
            sb.append(this.requestedQoS.toString());
            sb.append(", ");
        }

        if (this.subscribedQoS != null) {
            sb.append("subscribedQoS=");
            sb.append(this.subscribedQoS.toString());
            sb.append(", ");
        }

        if (this.negotiatedQoS != null) {
            sb.append("negotiatedQoS=");
            sb.append(this.negotiatedQoS.toString());
            sb.append(", ");
        }

        if (this.requestedQoSExtension != null) {
            sb.append("requestedQoSExtension=");
            sb.append(this.requestedQoSExtension.toString());
            sb.append(", ");
        }

        if (this.subscribedQoSExtension != null) {
            sb.append("subscribedQoSExtension=");
            sb.append(this.subscribedQoSExtension.toString());
            sb.append(", ");
        }

        if (this.negotiatedQoSExtension != null) {
            sb.append("negotiatedQoSExtension=");
            sb.append(this.negotiatedQoSExtension.toString());
        }

        sb.append("]");

        return sb.toString();
    }

}
