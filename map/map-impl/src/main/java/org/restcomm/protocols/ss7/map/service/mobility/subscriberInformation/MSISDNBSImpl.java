
package org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.MSISDNBS;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtBasicServiceCode;
import org.restcomm.protocols.ss7.map.primitives.ISDNAddressStringImpl;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;
import org.restcomm.protocols.ss7.map.primitives.SequenceBase;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.ExtBasicServiceCodeImpl;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by vsubbotin on 26/05/16.
 */
public class MSISDNBSImpl extends SequenceBase implements MSISDNBS {
    private static final int _TAG_BASIC_SERVICE_LIST = 0;
    private static final int _TAG_EXTENSION_CONTAINER = 1;

    private ISDNAddressString msisdn;
    private ArrayList<ExtBasicServiceCode> basicServiceList;
    private MAPExtensionContainer extensionContainer;

    public MSISDNBSImpl() {
        super("MSISDNBS");
    }

    public MSISDNBSImpl(ISDNAddressString msisdn, ArrayList<ExtBasicServiceCode> basicServiceList,
            MAPExtensionContainer extensionContainer) {
        super("MSISDNBS");
        this.msisdn = msisdn;
        this.basicServiceList = basicServiceList;
        this.extensionContainer = extensionContainer;
    }

    public ISDNAddressString getMsisdn() {
        return this.msisdn;
    }

    public ArrayList<ExtBasicServiceCode> getBasicServiceList() {
        return this.basicServiceList;
    }

    public MAPExtensionContainer getExtensionContainer() {
        return this.extensionContainer;
    }

    @Override
    protected void _decode(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException, IOException, AsnException {
        this.msisdn = null;
        this.basicServiceList = null;
        this.extensionContainer = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();
            switch (ais.getTagClass()) {
                case Tag.CLASS_UNIVERSAL:
                    switch (tag) {
                        case Tag.STRING_OCTET:
                            // decode msisdn
                            if (!ais.isTagPrimitive())
                                throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                        + ": Parameter msisdn is not primitive",
                                        MAPParsingComponentExceptionReason.MistypedParameter);

                            this.msisdn = new ISDNAddressStringImpl();
                            ((ISDNAddressStringImpl) this.msisdn).decodeAll(ais);
                            break;
                        default:
                            ais.advanceElement();
                            break;
                    }
                    break;
                case Tag.CLASS_CONTEXT_SPECIFIC:
                    switch (tag) {
                        case _TAG_BASIC_SERVICE_LIST:
                            if (ais.isTagPrimitive())
                                throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                        + ".basicServiceList: Parameter basicServiceList is primitive",
                                        MAPParsingComponentExceptionReason.MistypedParameter);

                            ExtBasicServiceCode extBasicServiceCode;
                            this.basicServiceList = new ArrayList<ExtBasicServiceCode>();
                            AsnInputStream ais2 = ais.readSequenceStream();
                            while (true) {
                                if (ais2.available() == 0) {
                                    break;
                                }

                                ais2.readTag();

                                extBasicServiceCode = new ExtBasicServiceCodeImpl();
                                ((ExtBasicServiceCodeImpl) extBasicServiceCode).decodeAll(ais2);
                                this.basicServiceList.add(extBasicServiceCode);
                            }

                            if (this.basicServiceList.size() < 1 || this.basicServiceList.size() > 70) {
                                throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                        + ": Parameter basicServiceList size must be from 1 to 10, found: "
                                        + this.basicServiceList.size(), MAPParsingComponentExceptionReason.MistypedParameter);
                            }
                            break;
                        case _TAG_EXTENSION_CONTAINER:
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
                    break;
                default:
                    ais.advanceElement();
                    break;
            }
        }

        if (this.msisdn == null) {
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + "msisdn is mandatory but it is absent",
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        if (this.msisdn == null) {
            throw new MAPException("Error while encoding " + _PrimitiveName
                    + " the mandatory parameter msisdn is not defined");
        }

        ((ISDNAddressStringImpl)this.msisdn).encodeAll(asnOutputStream);

        try {
            if (this.basicServiceList != null) {
                if (this.basicServiceList.size() < 1 || this.basicServiceList.size() > 70) {
                    throw new MAPException("Error while encoding " + _PrimitiveName
                            + " size basicServiceList is out of range (1..70). Actual size: " + this.basicServiceList.size());
                }

                asnOutputStream.writeTag(Tag.CLASS_CONTEXT_SPECIFIC, false, _TAG_BASIC_SERVICE_LIST);
                int pos = asnOutputStream.StartContentDefiniteLength();
                for (ExtBasicServiceCode extBasicServiceCode: this.basicServiceList) {
                    ((ExtBasicServiceCodeImpl)extBasicServiceCode).encodeAll(asnOutputStream);
                }
                asnOutputStream.FinalizeContent(pos);
            }
        } catch (AsnException ae) {
            throw new MAPException("AsnException when encoding " + _PrimitiveName + ": " + ae.getMessage(), ae);
        }

        if (this.extensionContainer != null) {
            ((MAPExtensionContainerImpl)this.extensionContainer).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_EXTENSION_CONTAINER);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.msisdn != null) {
            sb.append("msisdn=");
            sb.append(this.msisdn);
            sb.append(", ");
        }
        if (this.basicServiceList != null) {
            sb.append("basicServiceList=[");
            boolean firstItem = true;
            for (ExtBasicServiceCode extCwFeature: basicServiceList) {
                if (firstItem) {
                    firstItem = false;
                } else {
                    sb.append(", ");
                }
                sb.append(extCwFeature);
            }
            sb.append("], ");
        }
        if (this.extensionContainer != null) {
            sb.append("extensionContainer=");
            sb.append(this.extensionContainer);
            sb.append(", ");
        }

        sb.append("]");
        return sb.toString();
    }
}
