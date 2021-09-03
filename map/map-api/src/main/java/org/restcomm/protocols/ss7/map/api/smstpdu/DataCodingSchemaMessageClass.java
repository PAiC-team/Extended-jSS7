package org.restcomm.protocols.ss7.map.api.smstpdu;

/**
 *
 Bit 1 Bit 0 Message Class 0 0 Class 0 0 1 Class 1 Default meaning: ME-specific. 1 0 Class 2 (U)SIM specific message 1 1 Class
 * 3 Default meaning: TE specific (see 3GPP TS 27.005 [8])
 *
 * @author sergey vetyutnev
 *
 */
public enum DataCodingSchemaMessageClass {

    Class0(0), Class1(1), Class2(2), Class3(3);

    private int code;

    private DataCodingSchemaMessageClass(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static DataCodingSchemaMessageClass getInstance(int code) {
        switch (code) {
            case 0:
                return Class0;
            case 1:
                return Class1;
            case 2:
                return Class2;
            default:
                return Class3;
        }
    }
}