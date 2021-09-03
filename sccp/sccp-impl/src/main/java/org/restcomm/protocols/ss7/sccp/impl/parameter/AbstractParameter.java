
package org.restcomm.protocols.ss7.sccp.impl.parameter;

import java.io.InputStream;
import java.io.OutputStream;

import org.restcomm.protocols.ss7.sccp.SccpProtocolVersion;
import org.restcomm.protocols.ss7.sccp.message.ParseException;
import org.restcomm.protocols.ss7.sccp.parameter.Parameter;
import org.restcomm.protocols.ss7.sccp.parameter.ParameterFactory;

/**
 * @author baranowb
 */
@SuppressWarnings("serial")
public abstract class AbstractParameter implements Parameter {

    //NOTE: decode methods take ParameterFactory to allow custom EncodingScheme

    public abstract void decode(InputStream inputStream, ParameterFactory factory, final SccpProtocolVersion sccpProtocolVersion) throws ParseException;

    public abstract void encode(OutputStream outputStream, final boolean removeSpc, final SccpProtocolVersion sccpProtocolVersion) throws ParseException;

    /**
     * Accepts only body for decoding operation, the len and tag must be processed.
     *
     * @param b
     * @throws ParseException
     */
    public abstract void decode(byte[] b, ParameterFactory factory, final SccpProtocolVersion sccpProtocolVersion) throws ParseException;

    /**
     * Encodes only body of parameter, tag and len must be encoded.
     *
     * @return
     * @throws ParseException
     */
    public abstract byte[] encode(boolean removeSPC, final SccpProtocolVersion sccpProtocolVersion) throws ParseException;

}
