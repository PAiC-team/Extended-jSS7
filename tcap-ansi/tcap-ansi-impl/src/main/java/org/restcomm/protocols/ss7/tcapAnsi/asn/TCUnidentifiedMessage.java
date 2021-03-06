
package org.restcomm.protocols.ss7.tcapAnsi.asn;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.DialogPortion;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.Encodable;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.EncodeException;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.ParseException;

/**
 * @author amit bhayani
 *
 */
public class TCUnidentifiedMessage implements Encodable {

    private static final Logger logger = Logger.getLogger(TCUnidentifiedMessage.class);

    private byte[] originatingTransactionId;
    private byte[] destinationTransactionId;
    private boolean dialogPortionExists = false;

    /**
     *
     */
    public TCUnidentifiedMessage() {
    }

    public byte[] getOriginatingTransactionId() {
        return originatingTransactionId;
    }

    public byte[] getDestinationTransactionId() {
        return destinationTransactionId;
    }

    public boolean isDialogPortionExists() {
        return dialogPortionExists;
    }

    public void encode(AsnOutputStream aos) throws EncodeException {
        throw new EncodeException("Not Supported");
    }

    public void decode(AsnInputStream ais) throws ParseException {
        try {
            AsnInputStream localAis = ais.readSequenceStream();

            // transaction portion
            TransactionID tid = TcapFactory.readTransactionID(localAis);
            this.originatingTransactionId = tid.getFirstElem();
            this.destinationTransactionId = tid.getSecondElem();

            // dialog portion
            int tag = localAis.readTag();
            if (tag != DialogPortion._TAG_DIALOG_PORTION || localAis.getTagClass() != Tag.CLASS_PRIVATE || localAis.isTagPrimitive()) {
                return;
            }
            dialogPortionExists = true;

        } catch (IOException e) {
            logger.error("Error while decoding for TCUnidentifiedMessage", e);
        } catch (AsnException e) {
            logger.error("Error while decoding for TCUnidentifiedMessage", e);
        }

    }

}
