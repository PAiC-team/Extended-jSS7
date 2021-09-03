
package org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.CallWaitingData;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.ExtCwFeature;
import org.restcomm.protocols.ss7.map.primitives.SequenceBase;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by vsubbotin on 26/05/16.
 */
public class CallWaitingDataImpl extends SequenceBase implements CallWaitingData {

    private static final int _TAG_CW_FEATURE_LIST = 1;
    private static final int _TAG_NOTIFICATION_TO_CSE = 2;

    private ArrayList<ExtCwFeature> cwFeatureList;
    private boolean notificationToCSE;

    public CallWaitingDataImpl() {
        super("CallWaitingData");
    }

    public CallWaitingDataImpl(ArrayList<ExtCwFeature> cwFeatureList, boolean notificationToCSE) {
        super("CallWaitingData");
        this.cwFeatureList = cwFeatureList;
        this.notificationToCSE = notificationToCSE;
    }

    public ArrayList<ExtCwFeature> getCwFeatureList() {
        return this.cwFeatureList;
    }

    public boolean getNotificationToCSE() {
        return this.notificationToCSE;
    }

    @Override
    protected void _decode(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException, IOException, AsnException {
        this.cwFeatureList = null;
        this.notificationToCSE = false;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);
        while (true) {
            if (ais.available() == 0) {
                break;
            }

            int tag = ais.readTag();
            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {
                    case _TAG_CW_FEATURE_LIST:
                        if (ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter cwFeatureList is primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);

                        ExtCwFeature extCwFeature;
                        this.cwFeatureList = new ArrayList<ExtCwFeature>();
                        AsnInputStream ais2 = ais.readSequenceStream();
                        while (true) {
                            if (ais2.available() == 0) {
                                break;
                            }

                            if (ais2.readTag() != Tag.SEQUENCE || ais2.getTagClass() != Tag.CLASS_UNIVERSAL || ais2.isTagPrimitive())
                                throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                        + ".extCwFeature: Parameter extCwFeature is primitive",
                                        MAPParsingComponentExceptionReason.MistypedParameter);

                            extCwFeature = new ExtCwFeatureImpl();
                            ((ExtCwFeatureImpl)extCwFeature).decodeAll(ais2);
                            this.cwFeatureList.add(extCwFeature);
                        }

                        if (this.cwFeatureList.size() < 1 || this.cwFeatureList.size() > 32) {
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter cwFeatureList size must be from 1 to 10, found: "
                                    + this.cwFeatureList.size(), MAPParsingComponentExceptionReason.MistypedParameter);
                        }
                        break;
                    case _TAG_NOTIFICATION_TO_CSE:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter notificationToCSE is not primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);

                        ais.readNull();
                        this.notificationToCSE = Boolean.TRUE;
                        break;
                    default:
                        ais.advanceElement();
                        break;
                }
            } else {
                ais.advanceElement();
            }
        }

        if (this.cwFeatureList == null) {
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + "cwFeatureList is mandatory but it is absent",
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        if (this.cwFeatureList == null) {
            throw new MAPException("Error while encoding " + _PrimitiveName
                    + " the mandatory parameter cwFeatureList is not defined");
        }

        if (this.cwFeatureList.size() < 1 || this.cwFeatureList.size() > 32) {
            throw new MAPException("Error while encoding " + _PrimitiveName
                    + " size cwFeatureList is out of range (1..10). Actual size: "
                    + this.cwFeatureList.size());
        }

        try {
            asnOutputStream.writeTag(Tag.CLASS_CONTEXT_SPECIFIC, false, _TAG_CW_FEATURE_LIST);
            int pos = asnOutputStream.StartContentDefiniteLength();
            for (ExtCwFeature extCwFeature: this.cwFeatureList) {
                ((ExtCwFeatureImpl)extCwFeature).encodeAll(asnOutputStream);
            }
            asnOutputStream.FinalizeContent(pos);

            if (this.notificationToCSE) {
                asnOutputStream.writeNull(Tag.CLASS_CONTEXT_SPECIFIC, _TAG_NOTIFICATION_TO_CSE);
            }
        } catch (IOException e) {
            throw new MAPException("IOException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        } catch (AsnException ae) {
            throw new MAPException("AsnException when encoding " + _PrimitiveName + ": " + ae.getMessage(), ae);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.cwFeatureList != null) {
            sb.append("cwFeatureList=[");
            boolean firstItem = true;
            for (ExtCwFeature extCwFeature: cwFeatureList) {
                if (firstItem) {
                    firstItem = false;
                } else {
                    sb.append(", ");
                }
                sb.append(extCwFeature);
            }
            sb.append("], ");
        }
        if (this.notificationToCSE) {
            sb.append("isNotificationToCSE, ");
        }

        sb.append("]");
        return sb.toString();
    }
}
