
package org.restcomm.protocols.ss7.cap.service.gprs.primitive;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.VolumeIfTariffSwitch;
import org.restcomm.protocols.ss7.cap.primitives.SequenceBase;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class VolumeIfTariffSwitchImpl extends SequenceBase implements VolumeIfTariffSwitch {

    public static final int _ID_volumeSinceLastTariffSwitch = 0;
    public static final int _ID_volumeTariffSwitchInterval = 1;

    public static final int _ID_VolumeIfTariffSwitch = 1;

    private long volumeSinceLastTariffSwitch;
    private Long volumeTariffSwitchInterval;

    public VolumeIfTariffSwitchImpl() {
        super("VolumeIfTariffSwitch");
    }

    public VolumeIfTariffSwitchImpl(long volumeSinceLastTariffSwitch, Long volumeTariffSwitchInterval) {
        super("VolumeIfTariffSwitch");
        this.volumeSinceLastTariffSwitch = volumeSinceLastTariffSwitch;
        this.volumeTariffSwitchInterval = volumeTariffSwitchInterval;
    }

    public long getVolumeSinceLastTariffSwitch() {
        return this.volumeSinceLastTariffSwitch;
    }

    public Long getVolumeTariffSwitchInterval() {
        return this.volumeTariffSwitchInterval;
    }

    @Override
    public int getTag() throws CAPException {
        return _ID_VolumeIfTariffSwitch;
    }

    @Override
    public int getTagClass() {
        return Tag.CLASS_CONTEXT_SPECIFIC;
    }

    @Override
    protected void _decode(AsnInputStream asnInputStream, int length) throws CAPParsingComponentException, IOException, AsnException {

        boolean isVolumeSinceLastTariffSwitchIncluded = false;

        this.volumeSinceLastTariffSwitch = -1L;
        this.volumeTariffSwitchInterval = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {
                    case _ID_volumeSinceLastTariffSwitch:
                        if (!ais.isTagPrimitive())
                            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".volumeSinceLastTariffSwitch: Parameter is primitive",
                                    CAPParsingComponentExceptionReason.MistypedParameter);
                        this.volumeSinceLastTariffSwitch = ais.readInteger();
                        isVolumeSinceLastTariffSwitchIncluded = true;
                        break;
                    case _ID_volumeTariffSwitchInterval:
                        if (!ais.isTagPrimitive())
                            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".volumeTariffSwitchInterval: Parameter is primitive",
                                    CAPParsingComponentExceptionReason.MistypedParameter);
                        this.volumeTariffSwitchInterval = ais.readInteger();
                        break;
                    default:
                        ais.advanceElement();
                        break;
                }
            } else {
                ais.advanceElement();
            }
        }

        if (!isVolumeSinceLastTariffSwitchIncluded)
            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": volumeSinceLastTariffSwitch is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);

    }

    @Override
    public void encodeData(AsnOutputStream asnOutputStream) throws CAPException {
        try {
            asnOutputStream.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _ID_volumeSinceLastTariffSwitch, this.volumeSinceLastTariffSwitch);

            if (this.volumeTariffSwitchInterval != null)
                asnOutputStream.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _ID_volumeTariffSwitchInterval,
                        this.volumeTariffSwitchInterval.longValue());

        } catch (IOException e) {
            throw new CAPException("IOException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new CAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName + " [");

        sb.append("volumeSinceLastTariffSwitch=");
        sb.append(this.volumeSinceLastTariffSwitch);
        sb.append(", ");

        if (this.volumeTariffSwitchInterval != null) {
            sb.append("volumeTariffSwitchInterval=");
            sb.append(this.volumeTariffSwitchInterval);
        }

        sb.append("]");

        return sb.toString();
    }

}
