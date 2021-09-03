
package org.restcomm.protocols.ss7.tcap.asn;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.BitSetStrictLength;
import org.restcomm.protocols.ss7.tcap.asn.comp.PAbortCauseType;

/**
 * @author baranowb
 * @author sergey vetyutnev
 *
 */
public class ProtocolVersionImpl implements ProtocolVersion {

    private boolean supportedVersion = true;

    public boolean isSupportedVersion() {

        return supportedVersion;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.Encodable#decode(org.mobicents.protocols .asn.AsnInputStream)
     */
    public void decode(AsnInputStream asnInputStream) throws ParseException {
        try {
            BitSetStrictLength readV = asnInputStream.readBitString();
            if (readV.getStrictLength() >= 1 && readV.get(0)) {
                // ok
            } else {
                this.supportedVersion = false;
            }
        } catch (IOException e) {
            throw new ParseException(PAbortCauseType.BadlyFormattedTxPortion, null,
                    "IOException while decoding ProtocolVersion: " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new ParseException(PAbortCauseType.BadlyFormattedTxPortion, null,
                    "AsnException while decoding ProtocolVersion: " + e.getMessage(), e);
        }

    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.Encodable#encode(org.mobicents.protocols.asn.AsnOutputStream)
     */
    public void encode(AsnOutputStream asnOutputStream) throws EncodeException {
        // commented code is the whole case encoding implementation
        // now only one version is supported - we use for optimization purpose simple encoding
        // asnOutputStream.writeBitString(Tag.CLASS_CONTEXT_SPECIFIC, _TAG_PROTOCOL_VERSION, _VALUE);
        asnOutputStream.write(128);
        asnOutputStream.write(2);
        asnOutputStream.write(7);
        asnOutputStream.write(128);

        // BitSetStrictLength bs = new BitSetStrictLength(1);
        // bs.set(0);
        // try {
        // asnOutputStream.writeBitString(Tag.CLASS_CONTEXT_SPECIFIC, _TAG_PROTOCOL_VERSION, bs);
        // } catch (AsnException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // } catch (IOException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
    }
}
