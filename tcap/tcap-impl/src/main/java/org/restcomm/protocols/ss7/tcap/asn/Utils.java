
package org.restcomm.protocols.ss7.tcap.asn;

import java.io.IOException;
import java.nio.ByteBuffer;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;

/**
 * Class with some utility methods.
 *
 * @author baranowb
 *
 */
public final class Utils {

    public static long readTransactionId(AsnInputStream asnInputStream) throws AsnException, IOException {
        // here we have AIS, with txid - this is integer, but its coded as
        // octet string so no extra byte is added....
        byte[] data = asnInputStream.readOctetString();
        byte[] longRep = new byte[8];
        // copy data so longRep = {0,0,0,...,data};
        System.arraycopy(data, 0, longRep, longRep.length - data.length, data.length);
        ByteBuffer bb = ByteBuffer.wrap(longRep);
        return bb.getLong();

    }

    public static void writeTransactionId(AsnOutputStream asnOutputStream, long txId, int tagClass, int tag) throws AsnException,
            IOException {
        // txId may only be up to 4 bytes, that is 0xFF FF FF FF
        byte[] data = new byte[4];
        // long ll = txId.longValue();
        // data[3] = (byte) ll;
        // data[2] = (byte) (ll>> 8);
        // data[1] = (byte) (ll>>16);
        // data[0] = (byte) (ll >> 24);
        data[3] = (byte) txId;
        data[2] = (byte) (txId >> 8);
        data[1] = (byte) (txId >> 16);
        data[0] = (byte) (txId >> 24);

        asnOutputStream.writeOctetString(tagClass, tag, data);

    }

    public static long decodeTransactionId(byte[] data, boolean swapBytes) {
        byte[] longRep = new byte[8];

        if (swapBytes) {
            // copy data so longRep = {0,0,0,...,data};
            System.arraycopy(data, 0, longRep, longRep.length - data.length, data.length);
        } else {
            longRep[4] = data[3];
            longRep[5] = data[2];
            longRep[6] = data[1];
            longRep[7] = data[0];
        }
        ByteBuffer bb = ByteBuffer.wrap(longRep);
        return bb.getLong();

    }

    public static byte[] encodeTransactionId(long txId, boolean swapBytes) {
        // txId may only be up to 4 bytes, that is 0xFF FF FF FF
        byte[] data = new byte[4];
        // long ll = txId.longValue();
        // data[3] = (byte) ll;
        // data[2] = (byte) (ll>> 8);
        // data[1] = (byte) (ll>>16);
        // data[0] = (byte) (ll >> 24);
        if (swapBytes) {
            data[3] = (byte) txId;
            data[2] = (byte) (txId >> 8);
            data[1] = (byte) (txId >> 16);
            data[0] = (byte) (txId >> 24);
        } else {
            data[0] = (byte) txId;
            data[1] = (byte) (txId >> 8);
            data[2] = (byte) (txId >> 16);
            data[3] = (byte) (txId >> 24);
        }

        return data;
    }
}
