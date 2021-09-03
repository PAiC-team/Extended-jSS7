package org.restcomm.protocols.ss7.map.datacoding;

/**
 *
 * @author sergey vetyutnev
 *
 */
public enum Gsm7EncodingStyle {
    /**
     * 7-bit packing - SMS
     */
    bit7_sms_style,
    /**
     * 7-bit packing - USSD
     */
    bit7_ussd_style,
    /**
     * 8-bit packing - SMPP
     */
    bit8_smpp_style,
}
