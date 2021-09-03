package org.restcomm.protocols.ss7.map.datacoding;

/**
 *
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public class GSMCharsetEncodingData {

    protected byte[] leadingBuffer;
    protected Gsm7EncodingStyle encodingStyle;

    protected int totalSeptetCount;
    protected boolean leadingBufferIsEncoded = false;

    /**
     * constructor
     *
     * @param leadingBuffer Encoded UserDataHeader (if does not exist == null)
     */
    public GSMCharsetEncodingData(Gsm7EncodingStyle encodingStyle, byte[] leadingBuffer) {
        this.leadingBuffer = leadingBuffer;
        this.encodingStyle = encodingStyle;
    }

    public int getTotalSeptetCount() {
        return totalSeptetCount;
    }
}
