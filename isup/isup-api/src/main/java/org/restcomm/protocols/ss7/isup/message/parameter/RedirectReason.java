package org.restcomm.protocols.ss7.isup.message.parameter;

import java.io.Serializable;

/**
 * @author baranowb
 *
 */
public interface RedirectReason extends Serializable{

    byte REASON_UNKNOWN = 0x0;
    byte REASON_SERVICE_PROVIDER_PORTABILITY = 0x01;
    byte REASON_RESERVED_FOR_LOCATION_PORTABILITY = 0x02;
    byte REASON_RESERVED_FOR_SERVICE_PORTABILITY = 0x03;
    byte PERFORMING_EXCANGE_NO_INDICATION = 0x0;
    byte PERFORMING_EXCANGE_REDIRECT_POSSIBLE_BEFORE_ACM = 0x1;
    byte PERFORMING_EXCANGE_REDIRECT_POSSIBLE_BEFORE_ANM = 0x2;
    byte PERFORMING_EXCANGE_REDIRECT_POSSIBLE_ANY_TIME = 0x3;

    byte getRedirectReason();

    void setRedirectReason(byte b);

    byte getRedirectPossibleAtPerformingExchange();

    void setRedirectPossibleAtPerformingExchange(byte b);
}
