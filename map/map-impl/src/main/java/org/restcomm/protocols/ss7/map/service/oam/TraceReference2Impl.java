
package org.restcomm.protocols.ss7.map.service.oam;

import org.restcomm.protocols.ss7.map.api.service.oam.TraceReference2;
import org.restcomm.protocols.ss7.map.primitives.OctetStringBase;

/**
*
* @author sergey vetyutnev
*
*/
public class TraceReference2Impl extends OctetStringBase implements TraceReference2 {

    public TraceReference2Impl() {
        super(3, 3, "TraceReference2");
    }

    public TraceReference2Impl(byte[] data) {
        super(3, 3, "TraceReference2", data);
    }

    public byte[] getData() {
        return data;
    }

}
