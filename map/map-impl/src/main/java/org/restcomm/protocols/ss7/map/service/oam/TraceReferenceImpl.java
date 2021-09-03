
package org.restcomm.protocols.ss7.map.service.oam;

import org.restcomm.protocols.ss7.map.api.service.oam.TraceReference;
import org.restcomm.protocols.ss7.map.primitives.OctetStringBase;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class TraceReferenceImpl extends OctetStringBase implements TraceReference {

    public TraceReferenceImpl() {
        super(1, 2, "TraceReference");
    }

    public TraceReferenceImpl(byte[] data) {
        super(1, 2, "TraceReference", data);
    }

    public byte[] getData() {
        return data;
    }

}
