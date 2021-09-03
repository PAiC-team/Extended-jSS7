
package org.restcomm.protocols.ss7.cap.service.gprs.primitive;

import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.FreeFormatDataGprs;
import org.restcomm.protocols.ss7.cap.primitives.OctetStringBase;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class FreeFormatDataGprsImpl extends OctetStringBase implements FreeFormatDataGprs {

    public FreeFormatDataGprsImpl() {
        super(1, 160, "FreeFormatDataGprs");
    }

    public FreeFormatDataGprsImpl(byte[] data) {
        super(1, 160, "FreeFormatDataGprs", data);
    }

    @Override
    public byte[] getData() {
        return data;
    }

}
