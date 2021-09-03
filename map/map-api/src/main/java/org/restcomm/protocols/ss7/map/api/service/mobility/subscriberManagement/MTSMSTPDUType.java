
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

/**
 *
 MT-SMS-TPDU-Type ::= ENUMERATED { sms-DELIVER (0), sms-SUBMIT-REPORT (1), sms-STATUS-REPORT (2), ... }
 *
 * -- exception handling: -- For TPDU-TypeCriterion sequences containing this parameter with any -- other value than the ones
 * listed above the receiver shall ignore -- the whole TPDU-TypeCriterion sequence. -- In CAMEL phase 4, sms-SUBMIT-REPORT shall
 * not be used and a received TPDU-TypeCriterion -- sequence containing sms-SUBMIT-REPORT shall be wholly ignored.
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum MTSMSTPDUType {
    smsDELIVER(0), smsSUBMITREPORT(1), smsSTATUSREPORT(2);

    private int code;

    private MTSMSTPDUType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static MTSMSTPDUType getInstance(int code) {
        switch (code) {
            case 0:
                return MTSMSTPDUType.smsDELIVER;
            case 1:
                return MTSMSTPDUType.smsSUBMITREPORT;
            case 2:
                return MTSMSTPDUType.smsSTATUSREPORT;
            default:
                return null;
        }
    }
}
