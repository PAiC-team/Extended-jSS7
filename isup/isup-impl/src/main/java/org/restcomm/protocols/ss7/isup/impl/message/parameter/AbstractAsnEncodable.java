package org.restcomm.protocols.ss7.isup.impl.message.parameter;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.restcomm.protocols.ss7.isup.ParameterException;

/**
 * @author baranowb
 */
public abstract class AbstractAsnEncodable {

    public abstract void encode(AsnOutputStream aos) throws ParameterException;

    public abstract void decode(AsnInputStream ais) throws ParameterException;

}
