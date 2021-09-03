
package org.restcomm.protocols.ss7.map.api.errors;

import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.smstpdu.SmsDeliverReportTpdu;


/**
 *
The MAP ReturnError message: MessageSMDeliveryFailure with parameters

sm-DeliveryFailure ERROR ::= {
  PARAMETER SM-DeliveryFailureCause
  CODE local:32
}

MAP V 2-3
SM-DeliveryFailureCause ::= SEQUENCE {
  sm-EnumeratedDeliveryFailureCause   SM-EnumeratedDeliveryFailureCause,
  diagnosticInfo                      SignalInfo OPTIONAL,
  extensionContainer                  ExtensionContainer OPTIONAL,
  ...
}

MAP V1
sm-EnumeratedDeliveryFailureCause     SM-EnumeratedDeliveryFailureCause

 *
 * @author sergey vetyutnev
 *
 */
public interface MAPErrorMessageSMDeliveryFailure extends MAPErrorMessage {

    SMEnumeratedDeliveryFailureCause getSMEnumeratedDeliveryFailureCause();

    byte[] getSignalInfo();

    MAPExtensionContainer getExtensionContainer();

    long getMapProtocolVersion();

    void setSMEnumeratedDeliveryFailureCause(SMEnumeratedDeliveryFailureCause sMEnumeratedDeliveryFailureCause);

    void setSignalInfo(byte[] signalInfo);

    void setExtensionContainer(MAPExtensionContainer extensionContainer);

    void setMapProtocolVersion(long mapProtocolVersion);

    SmsDeliverReportTpdu getSmsDeliverReportTpdu() throws MAPException;

    void setSmsDeliverReportTpdu(SmsDeliverReportTpdu tpdu) throws MAPException;

}
