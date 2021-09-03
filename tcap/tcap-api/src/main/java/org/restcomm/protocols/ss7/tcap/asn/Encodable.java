package org.restcomm.protocols.ss7.tcap.asn;

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

    void encode(AsnOutputStream asnOutputStream) throws EncodeException;

    void decode(AsnInputStream asnInputStream) throws ParseException;

}
