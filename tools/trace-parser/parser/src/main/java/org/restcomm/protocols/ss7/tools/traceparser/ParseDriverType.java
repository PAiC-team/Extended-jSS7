package org.restcomm.protocols.ss7.tools.traceparser;

/**
 *
 * @author sergey vetyutnev
 *
 */
public enum ParseDriverType {
    Acterna(1), SimpleSeq(2), Pcap(3), HexStream(4);

    int code;

    ParseDriverType(int code) {
        this.code = code;
    }
}
