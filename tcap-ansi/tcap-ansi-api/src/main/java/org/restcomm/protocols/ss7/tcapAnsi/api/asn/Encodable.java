
package org.restcomm.protocols.ss7.tcapAnsi.api.asn;

import java.io.Serializable;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;

/**
 * Marker interface.
 *
 * @author baranowb
 *
 */
public interface Encodable extends Serializable {

    void encode(AsnOutputStream aos) throws EncodeException;

    void decode(AsnInputStream ais) throws ParseException;

}
