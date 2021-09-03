
package org.restcomm.protocols.ss7.map.api.primitives;

/**
*
<code>
Address Type 0 and Address Length 4 are used when Address is an IPv4 address.
Address Type 1 and Address Length 16 are used when Address is an IPv6 address.
</code>
*
*
* @author sergey vetyutnev
*
*/
public enum GSNAddressAddressType {

    IPv4(0), IPv6(1);

    private int code;

    private GSNAddressAddressType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static GSNAddressAddressType getInstance(int code) {
        switch (code) {
            case 0:
                return IPv4;
            case 1:
                return IPv6;
            default:
                return null;
        }
    }

    // this part is for GSNAddress for first byte encoding purpose
    public int createGSNAddressFirstByte() {
        int val = (this.code << 6);
        if (this.code == 0) // IPv4
            val += 4;
        if (this.code == 1) // IPv6
            val += 16;
        return val;
    }

    public static GSNAddressAddressType getFromGSNAddressFirstByte(int firstByte) {
        int val1 = (firstByte >> 6);
        int len = (firstByte & 0x3F);
        GSNAddressAddressType res = getInstance(val1);
        if (res != null) {
            switch(res){
            case IPv4:
                if (len == 4)
                    return res;
                break;
            case IPv6:
                if (len == 16)
                    return res;
                break;
            }
        }
        return null;
    }

}
