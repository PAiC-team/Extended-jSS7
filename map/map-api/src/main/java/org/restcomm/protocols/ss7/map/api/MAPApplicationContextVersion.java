package org.restcomm.protocols.ss7.map.api;

/**
 *
 * @author sergey vetyutnev
 *
 */
public enum MAPApplicationContextVersion {

    version1(1), version2(2), version3(3), version4(4);

    private int version;

    private MAPApplicationContextVersion(int version) {
        this.version = version;
    }

    public int getVersion() {
        return this.version;
    }

    public static MAPApplicationContextVersion getInstance(long version) {
        switch ((int) version) {
            case 1:
                return MAPApplicationContextVersion.version1;
            case 2:
                return MAPApplicationContextVersion.version2;
            case 3:
                return MAPApplicationContextVersion.version3;
            case 4:
                return MAPApplicationContextVersion.version4;
        }

        return null;
    }

}
