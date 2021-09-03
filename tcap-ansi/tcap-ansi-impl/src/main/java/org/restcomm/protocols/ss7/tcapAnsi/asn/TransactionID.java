
package org.restcomm.protocols.ss7.tcapAnsi.asn;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class TransactionID {
    private byte[] firstElem;
    private byte[] secondElem;

    public byte[] getFirstElem() {
        return firstElem;
    }

    public void setFirstElem(byte[] firstElem) {
        this.firstElem = firstElem;
    }

    public byte[] getSecondElem() {
        return secondElem;
    }

    public void setSecondElem(byte[] secondElem) {
        this.secondElem = secondElem;
    }
}
