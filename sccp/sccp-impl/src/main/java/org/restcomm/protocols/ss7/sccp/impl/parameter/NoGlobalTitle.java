package org.restcomm.protocols.ss7.sccp.impl.parameter;

import java.io.InputStream;
import java.io.OutputStream;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.restcomm.protocols.ss7.indicator.GlobalTitleIndicator;
import org.restcomm.protocols.ss7.sccp.SccpProtocolVersion;
import org.restcomm.protocols.ss7.sccp.message.ParseException;
import org.restcomm.protocols.ss7.sccp.parameter.ParameterFactory;

/**
 * @author amit bhayani
 *
 */
public class NoGlobalTitle extends AbstractGlobalTitle {


    public NoGlobalTitle() {
    }

    public NoGlobalTitle(String digits) {
        super.digits = digits;
    }

    @Override
    public GlobalTitleIndicator getGlobalTitleIndicator() {
        return GlobalTitleIndicator.NO_GLOBAL_TITLE_INCLUDED;
    }

    @Override
    public String getDigits() {
        return super.digits;
    }

    @Override
    public void decode(final InputStream in, final ParameterFactory factory, final SccpProtocolVersion sccpProtocolVersion)
            throws ParseException {
        this.digits = this.encodingScheme.decode(in);
    }

    @Override
    public void encode(OutputStream out, final boolean removeSpc, final SccpProtocolVersion sccpProtocolVersion)
            throws ParseException {
        if (this.digits == null) {
            throw new IllegalStateException();
        }
        this.encodingScheme.encode(this.digits, out);
    }

    // default XML representation.
    protected static final XMLFormat<NoGlobalTitle> XML = new XMLFormat<NoGlobalTitle>(NoGlobalTitle.class) {

        public void write(NoGlobalTitle ai, OutputElement xml) throws XMLStreamException {
            xml.setAttribute(DIGITS, ai.digits);
        }

        public void read(InputElement xml, NoGlobalTitle ai) throws XMLStreamException {
            ai.digits = xml.getAttribute(DIGITS).toString();
        }
    };
}
