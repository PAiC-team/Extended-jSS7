package org.restcomm.protocols.ss7.map.datacoding;

/**
 *
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public class GSMCharsetDecodingData {

    protected int totalSeptetCount;
    protected int leadingSeptetSkipCount;
    protected Gsm7EncodingStyle encodingStyle;

    /**
     * constructor
     *
     * @param totalSeptetCount Length of a decoded message in characters (for SMS case)
     * @param leadingSeptetSkipCount Count of leading septets to skip
     */
    public GSMCharsetDecodingData(Gsm7EncodingStyle encodingStyle, int totalSeptetCount, int leadingSeptetSkipCount) {
        this.totalSeptetCount = totalSeptetCount;
        this.leadingSeptetSkipCount = leadingSeptetSkipCount;
        this.encodingStyle = encodingStyle;
    }
}
