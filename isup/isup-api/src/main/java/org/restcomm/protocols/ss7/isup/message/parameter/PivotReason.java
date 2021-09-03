package org.restcomm.protocols.ss7.isup.message.parameter;

import java.io.Serializable;

/**
 * @author baranowb
 *
 */
public interface PivotReason extends Serializable{

    byte REASON_UKNOWN = 0;
    byte REASON_SERVICE_PROVIDER_PORTABILITY = 0x01;
    byte REASON_RESERVER_FOR_LOCAL_PORTABILITY = 0x02;
    byte REASON_RESERVER_FOR_SERVICE_PORTABILITY = 0x03;

    byte PERFORMING_EXCHANGE_NO_INDICATION = 0;
    byte PERFORMING_EXCHANGE_POSSIBLE_BEFORE_ACM = 0x01;
    byte PERFORMING_EXCHANGE_POSSIBLE_BEFORE_ANM = 0x02;
    byte PERFORMING_EXCHANGE_ANY_TIME = 0x03;

    byte getPivotReason();

    void setPivotReason(byte b);

    byte getPivotPossibleAtPerformingExchange();

    void setPivotPossibleAtPerformingExchange(byte b);
}
