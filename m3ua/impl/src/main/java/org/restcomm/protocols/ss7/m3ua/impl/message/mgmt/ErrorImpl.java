package org.restcomm.protocols.ss7.m3ua.impl.message.mgmt;

import io.netty.buffer.ByteBuf;

import org.restcomm.protocols.ss7.m3ua.impl.message.M3UAMessageImpl;
import org.restcomm.protocols.ss7.m3ua.impl.parameter.ParameterImpl;
import org.restcomm.protocols.ss7.m3ua.message.MessageClass;
import org.restcomm.protocols.ss7.m3ua.message.MessageType;
import org.restcomm.protocols.ss7.m3ua.message.mgmt.Error;
import org.restcomm.protocols.ss7.m3ua.parameter.AffectedPointCode;
import org.restcomm.protocols.ss7.m3ua.parameter.DiagnosticInfo;
import org.restcomm.protocols.ss7.m3ua.parameter.ErrorCode;
import org.restcomm.protocols.ss7.m3ua.parameter.NetworkAppearance;
import org.restcomm.protocols.ss7.m3ua.parameter.Parameter;
import org.restcomm.protocols.ss7.m3ua.parameter.RoutingContext;

/**
 *
 * @author amit bhayani
 *
 */
public class ErrorImpl extends M3UAMessageImpl implements Error {

    public ErrorImpl() {
        super(MessageClass.MANAGEMENT, MessageType.ERROR, MessageType.S_ERROR);
    }

    @Override
    protected void encodeParams(ByteBuf byteBuf) {
        ((ParameterImpl) parameters.get(Parameter.Error_Code)).write(byteBuf);

        if (parameters.containsKey(Parameter.Routing_Context)) {
            ((ParameterImpl) parameters.get(Parameter.Routing_Context)).write(byteBuf);
        }

        if (parameters.containsKey(Parameter.Affected_Point_Code)) {
            ((ParameterImpl) parameters.get(Parameter.Affected_Point_Code)).write(byteBuf);
        }

        if (parameters.containsKey(Parameter.Network_Appearance)) {
            ((ParameterImpl) parameters.get(Parameter.Network_Appearance)).write(byteBuf);
        }

        if (parameters.containsKey(Parameter.Diagnostic_Information)) {
            ((ParameterImpl) parameters.get(Parameter.Diagnostic_Information)).write(byteBuf);
        }
    }

    public AffectedPointCode getAffectedPointCode() {
        return ((AffectedPointCode) parameters.get(Parameter.Affected_Point_Code));
    }

    public DiagnosticInfo getDiagnosticInfo() {
        return ((DiagnosticInfo) parameters.get(Parameter.Diagnostic_Information));
    }

    public ErrorCode getErrorCode() {
        return ((ErrorCode) parameters.get(Parameter.Error_Code));
    }

    public NetworkAppearance getNetworkAppearance() {
        return ((NetworkAppearance) parameters.get(Parameter.Network_Appearance));
    }

    public RoutingContext getRoutingContext() {
        return ((RoutingContext) parameters.get(Parameter.Routing_Context));
    }

    public void setAffectedPointCode(AffectedPointCode affectedPointCode) {
        parameters.put(Parameter.Affected_Point_Code, affectedPointCode);
    }

    public void setDiagnosticInfo(DiagnosticInfo diagnosticInfo) {
        parameters.put(Parameter.Diagnostic_Information, diagnosticInfo);
    }

    public void setErrorCode(ErrorCode errorCode) {
        parameters.put(Parameter.Error_Code, errorCode);
    }

    public void setNetworkAppearance(NetworkAppearance netApp) {
        parameters.put(Parameter.Network_Appearance, netApp);
    }

    public void setRoutingContext(RoutingContext routingContext) {
        if (routingContext != null) {
            parameters.put(Parameter.Routing_Context, routingContext);
        }
    }

}
