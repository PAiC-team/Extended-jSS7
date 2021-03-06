
package org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall.primitive;

import java.io.IOException;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.ContinueWithArgumentArgExtension;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.LegOrCallSegment;
import org.restcomm.protocols.ss7.cap.primitives.SequenceBase;

/**
 *
 * @author Povilas Jurna
 *
 */
public class ContinueWithArgumentArgExtensionImpl extends SequenceBase implements ContinueWithArgumentArgExtension {

    private static final String SUPPRESS_D_CSI = "suppressDCSI";
    private static final String SUPPRESS_N_CSI = "suppressNCSI";
    private static final String SUPPRESS_OUTGOING_CALL_BARRING = "suppressOutgoingCallBarring";
    private static final String LEG_OR_CALL_SEGMENT = "legOrCallSegment";

    public static final int _ID_suppressDCSI = 0;
    public static final int _ID_suppressNCSI = 1;
    public static final int _ID_suppressOutgoingCallBarring = 2;
    public static final int _ID_legOrCallSegment = 3;

    private boolean suppressDCSI;
    private boolean suppressNCSI;
    private boolean suppressOutgoingCallBarring;
    private LegOrCallSegment legOrCallSegment;

    public ContinueWithArgumentArgExtensionImpl() {
        super("ContinueWithArgumentArgExtension");
    }

    public ContinueWithArgumentArgExtensionImpl(boolean suppressDCSI, boolean suppressNCSI,
            boolean suppressOutgoingCallBarring, LegOrCallSegment legOrCallSegment) {
        super("ContinueWithArgumentArgExtension");

        this.suppressDCSI = suppressDCSI;
        this.suppressNCSI = suppressNCSI;
        this.suppressOutgoingCallBarring = suppressOutgoingCallBarring;
        this.legOrCallSegment = legOrCallSegment;
    }

    protected void _decode(AsnInputStream asnInputStream, int length) throws CAPParsingComponentException, IOException,
            AsnException {

        this.suppressDCSI = false;
        this.suppressNCSI = false;
        this.suppressOutgoingCallBarring = false;
        this.legOrCallSegment = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {
                case _ID_suppressDCSI:
                    ais.readNull();
                    this.suppressDCSI = true;
                    break;
                case _ID_suppressNCSI:
                    ais.readNull();
                    this.suppressNCSI = true;
                    break;
                case _ID_suppressOutgoingCallBarring:
                    ais.readNull();
                    this.suppressOutgoingCallBarring = true;
                    break;
                case _ID_legOrCallSegment:
                    if (ais.isTagPrimitive())
                        throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".legOrCallSegment: Parameter is primitive",
                                CAPParsingComponentExceptionReason.MistypedParameter);
                    this.legOrCallSegment = new LegOrCallSegmentImpl();
                    AsnInputStream ais2 = ais.readSequenceStream();
                    ais2.readTag();
                    ((LegOrCallSegmentImpl) this.legOrCallSegment).decodeAll(ais2);
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

        try {
            if (suppressDCSI)
                asnOutputStream.writeNull(Tag.CLASS_CONTEXT_SPECIFIC, _ID_suppressDCSI);
            if (suppressNCSI)
                asnOutputStream.writeNull(Tag.CLASS_CONTEXT_SPECIFIC, _ID_suppressNCSI);
            if (suppressOutgoingCallBarring)
                asnOutputStream.writeNull(Tag.CLASS_CONTEXT_SPECIFIC, _ID_suppressOutgoingCallBarring);
            if (legOrCallSegment != null) {
                asnOutputStream.writeTag(Tag.CLASS_CONTEXT_SPECIFIC, false, _ID_legOrCallSegment);
                int pos = asnOutputStream.StartContentDefiniteLength();
                ((LegOrCallSegmentImpl) this.legOrCallSegment).encodeAll(asnOutputStream);
                asnOutputStream.FinalizeContent(pos);
            }

        } catch (IOException e) {
            throw new CAPException("IOException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new CAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (suppressDCSI) {
            sb.append("suppressDCSI, ");
        }
        if (suppressNCSI) {
            sb.append("suppressNCSI, ");
        }
        if (suppressOutgoingCallBarring) {
            sb.append("suppressOutgoingCallBarring, ");
        }
        if (this.legOrCallSegment != null) {
            sb.append("legOrCallSegment=");
            sb.append(this.legOrCallSegment);
            sb.append(",");
        }

        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<ContinueWithArgumentArgExtensionImpl> CALL_SEGMENT_TO_CANCEL_XML = new XMLFormat<ContinueWithArgumentArgExtensionImpl>(
            ContinueWithArgumentArgExtensionImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml,
                ContinueWithArgumentArgExtensionImpl continueWithArgumentArgExtension) throws XMLStreamException {

            Boolean bval = xml.get(SUPPRESS_D_CSI, Boolean.class);
            if (bval != null)
                continueWithArgumentArgExtension.suppressDCSI = bval;
            bval = xml.get(SUPPRESS_N_CSI, Boolean.class);
            if (bval != null)
                continueWithArgumentArgExtension.suppressNCSI = bval;
            bval = xml.get(SUPPRESS_OUTGOING_CALL_BARRING, Boolean.class);
            if (bval != null)
                continueWithArgumentArgExtension.suppressOutgoingCallBarring = bval;
            continueWithArgumentArgExtension.legOrCallSegment = xml
                    .get(LEG_OR_CALL_SEGMENT, LegOrCallSegmentImpl.class);
        }

        @Override
        public void write(ContinueWithArgumentArgExtensionImpl continueWithArgumentArgExtension,
                javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {

            if (continueWithArgumentArgExtension.suppressDCSI) {
                xml.add(((Boolean) continueWithArgumentArgExtension.suppressDCSI), SUPPRESS_D_CSI, Boolean.class);
            }
            if (continueWithArgumentArgExtension.suppressNCSI) {
                xml.add(((Boolean) continueWithArgumentArgExtension.suppressNCSI), SUPPRESS_N_CSI, Boolean.class);
            }
            if (continueWithArgumentArgExtension.suppressOutgoingCallBarring) {
                xml.add(((Boolean) continueWithArgumentArgExtension.suppressOutgoingCallBarring),
                        SUPPRESS_OUTGOING_CALL_BARRING, Boolean.class);
            }
            if (continueWithArgumentArgExtension.legOrCallSegment != null) {
                xml.add(((LegOrCallSegmentImpl) continueWithArgumentArgExtension.legOrCallSegment),
                        LEG_OR_CALL_SEGMENT, LegOrCallSegmentImpl.class);
            }

        }
    };

    @Override
    public boolean getSuppressDCsi() {
        return suppressDCSI;
    }

    @Override
    public boolean getSuppressNCsi() {
        return suppressNCSI;
    }

    @Override
    public boolean getSuppressOutgoingCallBarring() {
        return suppressOutgoingCallBarring;
    }

    @Override
    public LegOrCallSegment getLegOrCallSegment() {
        return legOrCallSegment;
    }

}
