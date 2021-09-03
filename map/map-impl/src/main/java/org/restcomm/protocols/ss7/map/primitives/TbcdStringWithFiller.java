
package org.restcomm.protocols.ss7.map.primitives;

import java.io.IOException;
import java.io.OutputStream;

import org.mobicents.protocols.asn.AsnOutputStream;
import org.restcomm.protocols.ss7.map.api.MAPException;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class TbcdStringWithFiller extends TbcdString {

    protected static int DIGIT_MASK = 0xFF;

    public TbcdStringWithFiller(int minLength, int maxLength, String _PrimitiveName) {
        super(minLength, maxLength, _PrimitiveName);
    }

    public TbcdStringWithFiller(int minLength, int maxLength, String _PrimitiveName, String data) {
        super(minLength, maxLength, _PrimitiveName, data);
    }

    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {

        if (this.data == null)
            throw new MAPException("Error while encoding the " + _PrimitiveName + ": data is not defined");

        encodeString(asnOutputStream, this.data);
        this.encodeFiller(asnOutputStream);
    }

    public void encodeFiller(OutputStream asnOutputStream) throws MAPException {

        for (int i = data.length() + 1; i < this.maxLength * 2; i = i + 2) {
            try {
                asnOutputStream.write(DIGIT_MASK);
            } catch (IOException e) {
                throw new MAPException("Error when encoding TbcdString: " + e.getMessage(), e);
            }
        }

    }

}
