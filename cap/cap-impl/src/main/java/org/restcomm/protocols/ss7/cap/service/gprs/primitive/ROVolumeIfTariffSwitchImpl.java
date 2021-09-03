
package org.restcomm.protocols.ss7.cap.service.gprs.primitive;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.ROVolumeIfTariffSwitch;
import org.restcomm.protocols.ss7.cap.primitives.SequenceBase;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class ROVolumeIfTariffSwitchImpl extends SequenceBase implements ROVolumeIfTariffSwitch {

    public static final int _ID_roVolumeSinceLastTariffSwitch = 0;
    public static final int _ID_roVolumeTariffSwitchInterval = 1;

    public static final int _ID_ROVolumeIfTariffSwitch = 1;

    private Integer roVolumeSinceLastTariffSwitch;
    private Integer roVolumeTariffSwitchInterval;

    public ROVolumeIfTariffSwitchImpl() {
        super("ROVolumeIfTariffSwitch");
    }

    public ROVolumeIfTariffSwitchImpl(Integer roVolumeSinceLastTariffSwitch, Integer roVolumeTariffSwitchInterval) {
        super("ROVolumeIfTariffSwitch");
        this.roVolumeSinceLastTariffSwitch = roVolumeSinceLastTariffSwitch;
        this.roVolumeTariffSwitchInterval = roVolumeTariffSwitchInterval;
    }

    public Integer getROVolumeSinceLastTariffSwitch() {
        return this.roVolumeSinceLastTariffSwitch;
    }

    public Integer getROVolumeTariffSwitchInterval() {
        return this.roVolumeTariffSwitchInterval;
    }

    public int getTag() throws CAPException {
        return _ID_ROVolumeIfTariffSwitch;
    }

    public int getTagClass() {
        return Tag.CLASS_CONTEXT_SPECIFIC;
    }

    @Override
    protected void _decode(AsnInputStream asnInputStream, int length) throws CAPParsingComponentException, IOException, AsnException {

        this.roVolumeSinceLastTariffSwitch = -1;
        this.roVolumeTariffSwitchInterval = -1;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {

                switch (tag) {
                    case _ID_roVolumeSinceLastTariffSwitch:
                        if (!ais.isTagPrimitive())
                            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".roVolumeSinceLastTariffSwitch: Parameter is not primitive",
                                    CAPParsingComponentExceptionReason.MistypedParameter);
                        this.roVolumeSinceLastTariffSwitch = (int) ais.readInteger();
                        break;
                    case _ID_roVolumeTariffSwitchInterval:
                        if (!ais.isTagPrimitive())
                            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".roVolumeTariffSwitchInterval: Parameter is not primitive",
                                    CAPParsingComponentExceptionReason.MistypedParameter);
                        this.roVolumeTariffSwitchInterval = (int) ais.readInteger();
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
            if (this.roVolumeSinceLastTariffSwitch != null)
                asnOutputStream.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _ID_roVolumeSinceLastTariffSwitch,
                        this.roVolumeSinceLastTariffSwitch.intValue());

            if (this.roVolumeTariffSwitchInterval != null)
                asnOutputStream.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _ID_roVolumeTariffSwitchInterval,
                        this.roVolumeTariffSwitchInterval.intValue());

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

        if (this.roVolumeSinceLastTariffSwitch != null) {
            sb.append("roVolumeSinceLastTariffSwitch=");
            sb.append(this.roVolumeSinceLastTariffSwitch.toString());
            sb.append(", ");
        }

        if (this.roVolumeTariffSwitchInterval != null) {
            sb.append("roVolumeTariffSwitchInterval=");
            sb.append(this.roVolumeTariffSwitchInterval.toString());
        }

        sb.append("]");

        return sb.toString();
    }

}
