
package org.restcomm.protocols.ss7.cap.EsiGprs;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.cap.api.EsiGprs.DisconnectSpecificInformation;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.InitiatingEntity;
import org.restcomm.protocols.ss7.cap.primitives.SequenceBase;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class DisconnectSpecificInformationImpl extends SequenceBase implements DisconnectSpecificInformation {

    public static final int _ID_initiatingEntity = 0;
    public static final int _ID_routeingAreaUpdate = 1;

    public static final int _ID_DisconnectSpecificInformation = 3;

    private InitiatingEntity initiatingEntity;
    private boolean routeingAreaUpdate;

    public DisconnectSpecificInformationImpl() {
        super("DisconnectSpecificInformation");
    }

    public DisconnectSpecificInformationImpl(InitiatingEntity initiatingEntity, boolean routeingAreaUpdate) {
        super("DisconnectSpecificInformation");
        this.initiatingEntity = initiatingEntity;
        this.routeingAreaUpdate = routeingAreaUpdate;
    }

    @Override
    public InitiatingEntity getInitiatingEntity() {
        return this.initiatingEntity;
    }

    @Override
    public boolean getRouteingAreaUpdate() {
        return this.routeingAreaUpdate;
    }

    public int getTag() throws CAPException {
        return Tag.SEQUENCE;
    }

    public int getTagClass() {
        return Tag.CLASS_CONTEXT_SPECIFIC;
    }

    @Override
    protected void _decode(AsnInputStream asnInputStream, int length) throws CAPParsingComponentException, IOException, AsnException,
            MAPParsingComponentException {
        this.initiatingEntity = null;
        this.routeingAreaUpdate = false;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {

                    case _ID_initiatingEntity:
                        if (!ais.isTagPrimitive())
                            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".initiatingEntity: Parameter is not  primitive",
                                    CAPParsingComponentExceptionReason.MistypedParameter);
                        int i1 = (int) ais.readInteger();

                        this.initiatingEntity = InitiatingEntity.getInstance(i1);
                        break;
                    case _ID_routeingAreaUpdate:
                        if (!ais.isTagPrimitive())
                            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".routeingAreaUpdate: Parameter is not  primitive",
                                    CAPParsingComponentExceptionReason.MistypedParameter);
                        this.routeingAreaUpdate = true;
                        ais.readNull();
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
            if (this.initiatingEntity != null)
                asnOutputStream.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _ID_initiatingEntity, this.initiatingEntity.getCode());

            if (routeingAreaUpdate)
                asnOutputStream.writeNull(Tag.CLASS_CONTEXT_SPECIFIC, _ID_routeingAreaUpdate);

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

        if (this.initiatingEntity != null) {
            sb.append("initiatingEntity=");
            sb.append(this.initiatingEntity.toString());
            sb.append(", ");
        }

        if (this.routeingAreaUpdate) {
            sb.append("routeingAreaUpdate ");
            sb.append(" ");
        }

        sb.append("]");

        return sb.toString();
    }

}
