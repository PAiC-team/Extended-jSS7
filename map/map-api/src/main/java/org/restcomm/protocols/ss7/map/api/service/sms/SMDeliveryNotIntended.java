package org.restcomm.protocols.ss7.map.api.service.sms;

/**
 *
<code>
SM-DeliveryNotIntended ::= ENUMERATED {
  onlyIMSI-requested (0),
  onlyMCC-MNC-requested (1),
  ...
}
</code>
 *
 *
 * @author eva ogallar
 *
 */
public enum SMDeliveryNotIntended {

    onlyIMSIRequested(0), onlyMCCMNCRequested(1);

    private int code;

    private SMDeliveryNotIntended(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static SMDeliveryNotIntended getInstance(int code) {
        switch (code) {
            case 0:
                return onlyIMSIRequested;
            case 1:
                return onlyMCCMNCRequested;
            default:
                return null;
        }
    }

}
