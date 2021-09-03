package org.restcomm.protocols.ss7.m3ua.impl.message.ssnm;

import io.netty.buffer.ByteBuf;

import org.restcomm.protocols.ss7.m3ua.impl.message.M3UAMessageImpl;
import org.restcomm.protocols.ss7.m3ua.impl.parameter.ParameterImpl;
import org.restcomm.protocols.ss7.m3ua.message.MessageClass;
import org.restcomm.protocols.ss7.m3ua.message.MessageType;
import org.restcomm.protocols.ss7.m3ua.message.ssnm.DestinationRestricted;
import org.restcomm.protocols.ss7.m3ua.parameter.AffectedPointCode;
import org.restcomm.protocols.ss7.m3ua.parameter.InfoString;
import org.restcomm.protocols.ss7.m3ua.parameter.NetworkAppearance;
import org.restcomm.protocols.ss7.m3ua.parameter.Parameter;
import org.restcomm.protocols.ss7.m3ua.parameter.RoutingContext;

/**
 *
 * @author amit bhayani
 *
 */
public class DestinationRestrictedImpl extends M3UAMessageImpl implements DestinationRestricted {

    public DestinationRestrictedImpl() {
        super(MessageClass.SIGNALING_NETWORK_MANAGEMENT, MessageType.DESTINATION_RESTRICTED, MessageType.S_DESTINATION_RESTRICTED);
    }

    public AffectedPointCode getAffectedPointCodes() {
        return (AffectedPointCode) parameters.get(Parameter.Affected_Point_Code);
    }

    public InfoString getInfoString() {
        return (InfoString) parameters.get(Parameter.INFO_String);
    }

    public NetworkAppearance getNetworkAppearance() {
        return (NetworkAppearance) parameters.get(Parameter.Network_Appearance);
    }

    public RoutingContext getRoutingContexts() {
        return (RoutingContext) parameters.get(Parameter.Routing_Context);
    }

    public void setAffectedPointCodes(AffectedPointCode affectedPointCode) {
        parameters.put(Parameter.Affected_Point_Code, affectedPointCode);
    }

    public void setInfoString(InfoString infoString) {
        if (infoString != null) {
            parameters.put(Parameter.INFO_String, infoString);
        }
    }

    public void setNetworkAppearance(NetworkAppearance networkAppearance) {
        if (networkAppearance != null) {
            parameters.put(Parameter.Network_Appearance, networkAppearance);
        }
    }

    public void setRoutingContexts(RoutingContext routingContext) {
        if (routingContext != null) {
            parameters.put(Parameter.Routing_Context, routingContext);
        }
    }

    @Override
    protected void encodeParams(ByteBuf byteBuf) {
        if (parameters.containsKey(Parameter.Network_Appearance)) {
            ((ParameterImpl) parameters.get(Parameter.Network_Appearance)).write(byteBuf);
        }

        if (parameters.containsKey(Parameter.Routing_Context)) {
            ((ParameterImpl) parameters.get(Parameter.Routing_Context)).write(byteBuf);
        }
        if (parameters.containsKey(Parameter.Affected_Point_Code)) {
            ((ParameterImpl) parameters.get(Parameter.Affected_Point_Code)).write(byteBuf);
        }
        if (parameters.containsKey(Parameter.INFO_String)) {
            ((ParameterImpl) parameters.get(Parameter.INFO_String)).write(byteBuf);
        }

    }

}
