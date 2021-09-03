package org.restcomm.protocols.ss7.map.datacoding;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;

/**
 *
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public class GSMCharsetDecoder extends CharsetDecoder {

    private byte[] mask = new byte[] { 0x7F, 0x3F, 0x1F, 0x0F, 0x07, 0x03, 0x01 };

    private int bitPos = 0;
    private int decodedBytes = 0;
    private byte leftOver;
    private GSMCharset cs;
    private boolean escape;
    private GSMCharsetDecodingData encodingData;

    /**
     * Constructs a Decoder.
     */
    protected GSMCharsetDecoder(Charset cs, float averageCharsPerByte, float maxCharsPerByte) {
        super(cs, averageCharsPerByte, maxCharsPerByte);
        implReset();
        this.cs = (GSMCharset) cs;
    }

    public void setGSMCharsetDecodingData(GSMCharsetDecodingData encodingData) {
        this.encodingData = encodingData;
    }

    public GSMCharsetDecodingData getGSMCharsetDecodingData() {
        return this.encodingData;
    }

    @Override
    protected void implReset() {
        bitPos = 0;
        decodedBytes = 0;
        leftOver = 0;
        escape = false;
    }

    // TODO is this ok?
    @Override
    protected CoderResult implFlush(CharBuffer out) {
        return CoderResult.UNDERFLOW;
    }

    @Override
    protected CoderResult decodeLoop(ByteBuffer in, CharBuffer out) {

        while (in.hasRemaining()) {

            // If CharBuffer doesn't have anything remaining, lets send OVERFLOW.
            // But ideally this should never happen as size of out is calculated
            // using the available bytes in in parameter
            if (!out.hasRemaining()) {
                return CoderResult.OVERFLOW;
            }

            // Read the first byte
            byte data = in.get();

            if (this.encodingData != null && this.encodingData.encodingStyle == Gsm7EncodingStyle.bit8_smpp_style) {
                putChar(data, out);
            } else {
                // take back-up of byte
                byte tempData = data;

                // System.out.println("data = "+ Integer.toBinaryString(data));

                // the rest of bits that we don't need now but for next iteration
                byte tempCurrHol = (byte) ((data & 0xFF) >>> (7 - bitPos));

                // System.out.println("Current = "+
                // Integer.toBinaryString(current));

                // The bits that will be consumed for formation of current char
                data = (byte) (data & mask[bitPos]);

                if (bitPos != 0) {
                    // we don't have enough bits to form char, we need to use the
                    // bits
                    // from previous iteration

                    // Move the curent bits read to left and append the previous
                    // bits
                    data = (byte) (data << bitPos);
                    data = (byte) (data | leftOver);

                    // We have 7 bits now to form char
                    putChar(data, out);

                    // This means we not only used previous 6 bits and 1 bit from
                    // current byte to form char, but now we also have 7 bits left
                    // over from current byte and hence we can get another char
                    if (bitPos == 6) {
                        data = (byte) (((tempData & 0xFE)) >>> 1);

                        if (this.encodingData != null && this.encodingData.encodingStyle == Gsm7EncodingStyle.bit7_ussd_style
                                && data == '\r' && !in.hasRemaining()) {
                            // case when found '\r' at the byte border if USSD style: skip final '\r' char
                        } else
                            putChar(data, out);
                    }
                } else {
                    // For this iteration we have all 7 bits to form the char
                    // from byte that we read
                    putChar(data, out);
                }

                // assign the left over bits
                leftOver = tempCurrHol;

                bitPos++;
                if (bitPos == 7) {
                    bitPos = 0;
                }
            }
        }

        return CoderResult.UNDERFLOW;
    }

    private void putChar(byte data, CharBuffer out) {

        this.decodedBytes++;
        if (this.encodingData != null) {
            if (this.decodedBytes <= this.encodingData.leadingSeptetSkipCount)
                return;
            if (this.encodingData.totalSeptetCount >= 0 && this.decodedBytes > this.encodingData.totalSeptetCount)
                return;
        }

        int code = 0;
        if (data >= 0 && data < 128) {
            if (escape) {
                escape = false;
                if (this.cs.extensionTable != null)
                    code = this.cs.extensionTable[data];
            } else {
                if (data == GSMCharset.ESCAPE) {
                    escape = true;
                    return;
                } else {
                    code = this.cs.mainTable[data];
                }
            }
        }

        if (code == 0)
            out.put(' ');
        else
            out.put((char) code);
    }
}
