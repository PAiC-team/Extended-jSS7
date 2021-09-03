package org.restcomm.protocols.ss7.sccp.impl.message;

import java.util.ArrayList;

import org.restcomm.protocols.ss7.sccp.parameter.ReturnCauseValue;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class EncodingResultData {

    private EncodingResult encodingResult;
    private byte[] solidData;
    private ArrayList<byte[]> segmentedData;
    private ReturnCauseValue returnCause;

    public EncodingResultData(EncodingResult encodingResult, byte[] solidData, ArrayList<byte[]> segmentedData,
            ReturnCauseValue returnCause) {
        this.encodingResult = encodingResult;
        this.solidData = solidData;
        this.segmentedData = segmentedData;
        this.returnCause = returnCause;
    }

    public EncodingResult getEncodingResult() {
        return this.encodingResult;
    }

    public byte[] getSolidData() {
        return this.solidData;
    }

    public ArrayList<byte[]> getSegmentedData() {
        return this.segmentedData;
    }

    public ReturnCauseValue getReturnCause() {
        return this.returnCause;
    }

}
