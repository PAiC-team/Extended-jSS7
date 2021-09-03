
package org.restcomm.protocols.ss7.sccp.impl.parameter;

import org.restcomm.protocols.ss7.sccp.parameter.EncodingScheme;
import org.restcomm.protocols.ss7.sccp.parameter.EncodingSchemeType;

/**
 * @author baranowb
 *
 */
public class BCDEvenEncodingScheme extends DefaultEncodingScheme {

    public static final EncodingScheme INSTANCE = new BCDEvenEncodingScheme();
    public static final int SCHEMA_CODE = 2;
    public BCDEvenEncodingScheme() {
        // TODO Auto-generated constructor stub
    }

    @Override
    protected boolean isOdd() {
        return false;
    }

    @Override
    public EncodingSchemeType getType() {
        return EncodingSchemeType.BCD_EVEN;
    }

    @Override
    public byte getSchemeCode() {
        return SCHEMA_CODE;
    }

}
