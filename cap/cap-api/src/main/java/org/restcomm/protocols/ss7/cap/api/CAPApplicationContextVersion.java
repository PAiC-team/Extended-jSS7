
package org.restcomm.protocols.ss7.cap.api;

/**
 *
 * @author sergey vetyutnev
 *
 */
public enum CAPApplicationContextVersion {

    version1(1), version2(2), version3(3), version4(4);

    private int version;

    private CAPApplicationContextVersion(int version) {
        this.version = version;
    }

    public int getVersion() {
        return this.version;
    }
}
