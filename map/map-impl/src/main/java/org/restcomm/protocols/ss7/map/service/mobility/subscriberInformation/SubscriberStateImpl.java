
package org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation;

import java.io.IOException;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.NotReachableReason;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.SubscriberState;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.SubscriberStateChoice;
import org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class SubscriberStateImpl implements SubscriberState, MAPAsnPrimitive {

    public static final int _ID_assumedIdle = 0;
    public static final int _ID_camelBusy = 1;
    public static final int _ID_notProvidedFromVLR = 2;

    private static final String SUBSCRIBER_STATE_CHOICE = "subscriberStateChoice";
    private static final String NOT_REACHABLE_REASON = "notReachableReason";

    private static final String DEFAULT_STRING_VALUE = null;

    public static final String _PrimitiveName = "SubscriberState";

    private SubscriberStateChoice subscriberStateChoice;
    private NotReachableReason notReachableReason;

    public SubscriberStateImpl() {
    }

    public SubscriberStateImpl(SubscriberStateChoice subscriberStateChoice, NotReachableReason notReachableReason) {
        setData(subscriberStateChoice, notReachableReason);
    }

    public void setData(SubscriberStateChoice subscriberStateChoice, NotReachableReason notReachableReason) {
        this.subscriberStateChoice = subscriberStateChoice;
        this.notReachableReason = notReachableReason;
    }

    public SubscriberStateChoice getSubscriberStateChoice() {
        return subscriberStateChoice;
    }

    public NotReachableReason getNotReachableReason() {
        return notReachableReason;
    }

    public int getTag() throws MAPException {

        if (this.subscriberStateChoice == null)
            throw new MAPException("Error encoding " + _PrimitiveName + ": No subscriberStateChoice value");

        switch (this.subscriberStateChoice) {
            case assumedIdle:
                return _ID_assumedIdle;
            case camelBusy:
                return _ID_camelBusy;
            case netDetNotReachable:
                return Tag.ENUMERATED;
            case notProvidedFromVLR:
                return _ID_notProvidedFromVLR;
        }

        throw new MAPException("Error encoding " + _PrimitiveName + ": Bad subscriberStateChoice value");
    }

    public int getTagClass() {
        if (this.subscriberStateChoice != null && this.subscriberStateChoice == SubscriberStateChoice.netDetNotReachable)
            return Tag.CLASS_UNIVERSAL;
        else
            return Tag.CLASS_CONTEXT_SPECIFIC;
    }

    public boolean getIsPrimitive() {
        return true;
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
        this.subscriberStateChoice = null;
        this.notReachableReason = null;

        int tag = asnInputStream.getTag();

        switch (asnInputStream.getTagClass()) {
            case Tag.CLASS_UNIVERSAL:
                if (tag == Tag.ENUMERATED) {
                    this.subscriberStateChoice = SubscriberStateChoice.netDetNotReachable;
                    int i1 = (int) asnInputStream.readIntegerData(length);
                    this.notReachableReason = NotReachableReason.getInstance(i1);
                } else {
                    throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                            + ": bad choice tag for universal tag class: " + tag,
                            MAPParsingComponentExceptionReason.MistypedParameter);
                }
                break;
            case Tag.CLASS_CONTEXT_SPECIFIC:
                switch (tag) {
                    case _ID_assumedIdle:
                        this.subscriberStateChoice = SubscriberStateChoice.assumedIdle;
                        break;
                    case _ID_camelBusy:
                        this.subscriberStateChoice = SubscriberStateChoice.camelBusy;
                        break;
                    case _ID_notProvidedFromVLR:
                        this.subscriberStateChoice = SubscriberStateChoice.notProvidedFromVLR;
                        break;
                    default:
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ": bad choice tag for contextSpecific tag class: " + tag,
                                MAPParsingComponentExceptionReason.MistypedParameter);
                }
                asnInputStream.readNullData(length);
                break;
            default:
                throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + ": bad choice tagClass: "
                        + asnInputStream.getTagClass(), MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    public void encodeAll(AsnOutputStream asnOutputStream) throws MAPException {
        this.encodeAll(asnOutputStream, this.getTagClass(), this.getTag());
    }

    public void encodeAll(AsnOutputStream asnOutputStream, int tagClass, int tag) throws MAPException {
        try {
            asnOutputStream.writeTag(tagClass, true, tag);
            int pos = asnOutputStream.StartContentDefiniteLength();
            this.encodeData(asnOutputStream);
            asnOutputStream.FinalizeContent(pos);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        if (this.subscriberStateChoice == null)
            throw new MAPException("subscriberStateChoice must not be null");
        if (this.subscriberStateChoice == SubscriberStateChoice.netDetNotReachable) {
            if (this.notReachableReason == null)
                throw new MAPException("notReachableReason must not be null when subscriberStateChoice is netDetNotReachable");

            try {
                asnOutputStream.writeIntegerData(this.notReachableReason.getCode());
            } catch (IOException e) {
                throw new MAPException("IOException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
            }
        } else {
            asnOutputStream.writeNullData();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SubscriberState [");

        if (this.subscriberStateChoice != null) {
            sb.append("subscriberStateChoice=");
            sb.append(this.subscriberStateChoice);
        }
        if (this.notReachableReason != null) {
            sb.append(", notReachableReason=");
            sb.append(this.notReachableReason);
        }

        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<SubscriberStateImpl> SUBSCRIBER_STATE_XML = new XMLFormat<SubscriberStateImpl>(
            SubscriberStateImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, SubscriberStateImpl subscriberState)
                throws XMLStreamException {
            String choice = xml.getAttribute(SUBSCRIBER_STATE_CHOICE, DEFAULT_STRING_VALUE);
            SubscriberStateChoice subscriberStateChoice = null;
            if (choice != null) {
                subscriberStateChoice = Enum.valueOf(SubscriberStateChoice.class, choice);
            }
            String nrr = xml.get(NOT_REACHABLE_REASON, String.class);
            NotReachableReason notReachableReason = null;
            if (nrr != null) {
                notReachableReason = Enum.valueOf(NotReachableReason.class, nrr);
            }

            subscriberState.setData(subscriberStateChoice, notReachableReason);
        }

        @Override
        public void write(SubscriberStateImpl subscriberState, javolution.xml.XMLFormat.OutputElement xml)
                throws XMLStreamException {
            if (subscriberState.subscriberStateChoice != null)
                xml.setAttribute(SUBSCRIBER_STATE_CHOICE, subscriberState.subscriberStateChoice.toString());
            if (subscriberState.notReachableReason != null)
                xml.add(subscriberState.notReachableReason.toString(), NOT_REACHABLE_REASON, String.class);
        }
    };
}
