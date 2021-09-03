
package org.restcomm.protocols.ss7.map.datacoding;

import java.nio.charset.Charset;

import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class EncodingData {

    @Test(groups = { "functional.encode", "datacoding" })
    public void testEncode() throws Exception {
        Charset ucs2Charset = Charset.forName("UTF-16BE");
        Charset utf8Charset = Charset.forName("UTF-8");

        String s1 = "ABab$[]ß";

        byte[] b1 = s1.getBytes(ucs2Charset);
        byte[] b2 = s1.getBytes(utf8Charset);

        String sa = this.toByteArray(b1);
        String sb = this.toByteArray(b2);
    }

    private String toByteArray(byte[] b1) {
        StringBuilder sb = new StringBuilder();
        int i1 = 0;
        for (byte b : b1) {
            if (i1 == 0)
                i1 = 1;
            else
                sb.append(", ");

            int i2 = (b & 0xFF);
            String s1 = Integer.toHexString(i2);
            sb.append("0x");
            if (s1.length() == 1)
                sb.append("0");
            sb.append(s1);
        }
        return sb.toString();
    }
}
