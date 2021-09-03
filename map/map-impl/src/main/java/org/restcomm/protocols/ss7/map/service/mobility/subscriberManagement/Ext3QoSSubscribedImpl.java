
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.Ext3QoSSubscribed;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtQoSSubscribed_BitRateExtended;
import org.restcomm.protocols.ss7.map.primitives.OctetStringBase;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class Ext3QoSSubscribedImpl extends OctetStringBase implements Ext3QoSSubscribed {

    private static final String MAX_BIT_RATE_FOR_UPLINK_EXT = "maximumBitRateForUplinkExtended";
    private static final String GUARANTEED_BIT_RATE_FOR_UPLINK_EXT = "guaranteedBitRateForUplinkExtended";

    private static final int DEFAULT_INT_VALUE = 0;

    public Ext3QoSSubscribedImpl() {
        super(1, 2, "Ext3QoSSubscribed");
    }

    public Ext3QoSSubscribedImpl(byte[] data) {
        super(1, 2, "Ext3QoSSubscribed", data);
    }

    public Ext3QoSSubscribedImpl(ExtQoSSubscribed_BitRateExtended maximumBitRateForUplinkExtended,
            ExtQoSSubscribed_BitRateExtended guaranteedBitRateForUplinkExtended) {
        super(1, 2, "Ext3QoSSubscribed");

        this.setData(maximumBitRateForUplinkExtended, guaranteedBitRateForUplinkExtended);
    }

    protected void setData(ExtQoSSubscribed_BitRateExtended maximumBitRateForUplinkExtended, ExtQoSSubscribed_BitRateExtended guaranteedBitRateForUplinkExtended) {
        this.data = new byte[2];

        this.data[0] = (byte) (maximumBitRateForUplinkExtended != null ? maximumBitRateForUplinkExtended.getSourceData() : 0);
        this.data[1] = (byte) (guaranteedBitRateForUplinkExtended != null ? guaranteedBitRateForUplinkExtended.getSourceData() : 0);
    }

    public byte[] getData() {
        return data;
    }

    @Override
    public ExtQoSSubscribed_BitRateExtended getMaximumBitRateForUplinkExtended() {
        if (this.data == null || this.data.length < 1)
            return null;

        return new ExtQoSSubscribed_BitRateExtendedImpl(this.data[0] & 0xFF, true);
    }

    @Override
    public ExtQoSSubscribed_BitRateExtended getGuaranteedBitRateForUplinkExtended() {
        if (this.data == null || this.data.length < 2)
            return null;

        return new ExtQoSSubscribed_BitRateExtendedImpl(this.data[1] & 0xFF, true);
    }

    @Override
    public String toString() {
        if (this.data != null && this.data.length >= 1) {
            ExtQoSSubscribed_BitRateExtended maximumBitRateForUplinkExtended = getMaximumBitRateForUplinkExtended();
            ExtQoSSubscribed_BitRateExtended guaranteedBitRateForUplinkExtended = getGuaranteedBitRateForUplinkExtended();

            StringBuilder sb = new StringBuilder();
            sb.append(_PrimitiveName);
            sb.append(" [");

            if (maximumBitRateForUplinkExtended != null) {
                sb.append("maximumBitRateForUplinkExtended=");
                sb.append(maximumBitRateForUplinkExtended);
                sb.append(", ");
            }
            if (guaranteedBitRateForUplinkExtended != null) {
                sb.append("guaranteedBitRateForUplinkExtended=");
                sb.append(guaranteedBitRateForUplinkExtended);
                sb.append(", ");
            }
            sb.append("]");

            return sb.toString();
        } else {
            return super.toString();
        }
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<Ext3QoSSubscribedImpl> EXT3_QOS_SUBSCRIBED_XML = new XMLFormat<Ext3QoSSubscribedImpl>(Ext3QoSSubscribedImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, Ext3QoSSubscribedImpl qos3Subscribed) throws XMLStreamException {
            ExtQoSSubscribed_BitRateExtended maximumBitRateForUplinkExtended = new ExtQoSSubscribed_BitRateExtendedImpl(xml.getAttribute(MAX_BIT_RATE_FOR_UPLINK_EXT, DEFAULT_INT_VALUE), false);
            ExtQoSSubscribed_BitRateExtended guaranteedBitRateForUplinkExtended = new ExtQoSSubscribed_BitRateExtendedImpl(xml.getAttribute(GUARANTEED_BIT_RATE_FOR_UPLINK_EXT, DEFAULT_INT_VALUE), false);
            qos3Subscribed.setData(maximumBitRateForUplinkExtended, guaranteedBitRateForUplinkExtended);
        }

        @Override
        public void write(Ext3QoSSubscribedImpl qos3Subscribed, javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            if (qos3Subscribed.data != null) {
                if (qos3Subscribed.getMaximumBitRateForUplinkExtended() != null)
                    xml.setAttribute(MAX_BIT_RATE_FOR_UPLINK_EXT, qos3Subscribed.getMaximumBitRateForUplinkExtended().getBitRate());
                if (qos3Subscribed.getGuaranteedBitRateForUplinkExtended() != null)
                    xml.setAttribute(GUARANTEED_BIT_RATE_FOR_UPLINK_EXT, qos3Subscribed.getGuaranteedBitRateForUplinkExtended().getBitRate());
            }
        }
    };
}
