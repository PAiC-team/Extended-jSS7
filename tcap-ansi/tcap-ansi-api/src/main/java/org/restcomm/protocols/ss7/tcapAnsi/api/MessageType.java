
package org.restcomm.protocols.ss7.tcapAnsi.api;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;

/**
 * Message type tag - it holds whole representation of tag - along with universal and other bits set.
 *
 * @author baranowb
 *
 */
public enum MessageType {

    Unidirectional(1), QueryWithPerm(2), QueryWithoutPerm(3), Response(4),
    ConversationWithPerm(6), ConversationWithoutPerm(7), Abort(22), Unknown(-1);

    private int tagContent = -1;

    private MessageType(int tagContent) {
        this.tagContent = tagContent;
    }

    public MessageType decode(AsnInputStream asnIs) throws IOException {
        int t = asnIs.readTag();
        switch (t) {
            case 1:
                return Unidirectional;
            case 2:
                return QueryWithPerm;
            case 3:
                return QueryWithoutPerm;
            case 4:
                return Response;
            case 5:
                return ConversationWithPerm;
            case 6:
                return ConversationWithoutPerm;
            case 22:
                return Abort;
            default:
                return Unknown;
        }

    }

    public void encode(AsnOutputStream asnO) {
        // write directly, we know its applciation class, constructed and num is
        // in range of 5 bits
        // this way its faster.
        asnO.write(tagContent);
    }

    public int getTag() {
        return this.tagContent;
    }
}
