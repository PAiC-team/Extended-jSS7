package org.restcomm.protocols.ss7.sccp.parameter;

public interface SequencingSegmenting {

    SequenceNumber getSendSequenceNumber();
    void setSendSequenceNumber(SequenceNumber sendSequenceNumber);

    SequenceNumber getReceiveSequenceNumber();
    void setReceiveSequenceNumber(SequenceNumber receiveSequenceNumber);

    boolean isMoreData();
    void setMoreData(boolean moreData);
}
