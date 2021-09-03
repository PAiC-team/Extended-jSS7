
package org.restcomm.protocols.ss7.sccp.impl.parameter;

import org.restcomm.protocols.ss7.sccp.parameter.EncodingScheme;
import org.restcomm.protocols.ss7.sccp.parameter.EncodingSchemeType;

/**
 * @author baranowb
 */
public class BCDOddEncodingScheme extends DefaultEncodingScheme {

    public static final EncodingScheme INSTANCE = new BCDOddEncodingScheme();
    public static final int SCHEMA_CODE = 1;
    public BCDOddEncodingScheme() {
        // TODO Auto-generated constructor stub
    }

    @Override
    protected boolean isOdd() {
        return true;
    }

    @Override
    public EncodingSchemeType getType() {
        return EncodingSchemeType.BCD_ODD;
    }

    @Override
    public byte getSchemeCode() {
        return SCHEMA_CODE;
    }

}
