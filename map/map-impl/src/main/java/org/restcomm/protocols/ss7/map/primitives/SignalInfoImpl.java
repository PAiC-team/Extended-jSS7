
package org.restcomm.protocols.ss7.map.primitives;

import org.restcomm.protocols.ss7.map.api.primitives.SignalInfo;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class SignalInfoImpl extends OctetStringBase implements SignalInfo {

    public SignalInfoImpl() {
        super(1, 200, "SignalInfo");
    }

    public SignalInfoImpl(byte[] data) {
        super(1, 200, "SignalInfo", data);
    }

    public byte[] getData() {
        return data;
    }

}
