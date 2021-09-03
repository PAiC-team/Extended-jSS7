
package org.restcomm.protocols.ss7.tcap.asn;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;

/**
 * @author amit bhayani
 *
 */
public class TCUnidentifiedMessage implements Encodable {

    private static final Logger logger = Logger.getLogger(TCUnidentifiedMessage.class);

    public static final int _TAG_OTX = 0x08;

    public static final int _TAG_DTX = 0x09;

    // mandatory
    private byte[] originatingTransactionId;
    private byte[] destinationTransactionId;

    /**
     *
     */
    public TCUnidentifiedMessage() {
    }

    public byte[] getOriginatingTransactionId() {
        return this.originatingTransactionId;
    }

    public byte[] getDestinationTransactionId() {
        return this.destinationTransactionId;
    }

    public void encode(AsnOutputStream asnOutputStream) throws EncodeException {
        throw new EncodeException("Not Supported");
    }

    public void decode(AsnInputStream asnInputStream) throws ParseException {
        try {
            AsnInputStream localAis = asnInputStream.readSequenceStream();
            int tag = localAis.readTag();
            if (tag != _TAG_OTX) {
                return;
            }

            // this.originatingTransactionId = Utils.readTransactionId(localAis);
            this.originatingTransactionId = localAis.readOctetString();

            if (localAis.available() > 0) {
                tag = localAis.readTag();

                if (tag != _TAG_DTX) {
                    return;
                }

                // this.destinationTransactionId = Utils.readTransactionId(localAis);
                this.destinationTransactionId = localAis.readOctetString();
            }

        } catch (IOException e) {
            logger.error("Error while decoding for TCUnidentifiedMessage", e);
        } catch (AsnException e) {
            logger.error("Error while decoding for TCUnidentifiedMessage", e);
        }
    }

}
