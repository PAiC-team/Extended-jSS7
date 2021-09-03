
package org.restcomm.protocols.ss7.sccp.impl.parameter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.restcomm.protocols.ss7.indicator.GlobalTitleIndicator;
import org.restcomm.protocols.ss7.indicator.NumberingPlan;
import org.restcomm.protocols.ss7.sccp.SccpProtocolVersion;
import org.restcomm.protocols.ss7.sccp.message.ParseException;
import org.restcomm.protocols.ss7.sccp.parameter.EncodingScheme;
import org.restcomm.protocols.ss7.sccp.parameter.GlobalTitle0011;
import org.restcomm.protocols.ss7.sccp.parameter.ParameterFactory;

/**
 * @author baranowb
 * @author sergey vetyutnev
 *
 */
public class GlobalTitle0011Impl extends AbstractGlobalTitle implements GlobalTitle0011 {

    private NumberingPlan numberingPlan;
    private int translationType;

    public GlobalTitle0011Impl() {
    }

    /**
     * @param digits
     * @param translationType
     * @param encodingScheme
     * @param numberingPlan
     */
    public GlobalTitle0011Impl(final String digits,final int translationType, final EncodingScheme encodingScheme, final NumberingPlan numberingPlan) {
        super();

        if(digits == null){
            throw new IllegalArgumentException();
        }
        if(encodingScheme == null){
            throw new IllegalArgumentException();
        }
        if(numberingPlan == null){
            throw new IllegalArgumentException();
        }
        this.encodingScheme = encodingScheme;
        this.numberingPlan = numberingPlan;
        this.translationType = translationType;
        super.digits = digits;
    }

    @Override
    public GlobalTitleIndicator getGlobalTitleIndicator() {
        return GlobalTitleIndicator.GLOBAL_TITLE_INCLUDES_TRANSLATION_TYPE_NUMBERING_PLAN_AND_ENCODING_SCHEME;
    }

    @Override
    public EncodingScheme getEncodingScheme() {
        return this.encodingScheme;
    }

    @Override
    public NumberingPlan getNumberingPlan() {
        return this.numberingPlan;
    }

    @Override
    public int getTranslationType() {
        return this.translationType;
    }

    @Override
    public void decode(final InputStream in,final ParameterFactory factory, final SccpProtocolVersion sccpProtocolVersion) throws ParseException {
        try{
        this.translationType = in.read() & 0xff;

        int b = in.read() & 0xff;

        this.encodingScheme = factory.createEncodingScheme((byte) (b & 0x0f));
        this.numberingPlan = NumberingPlan.valueOf((b & 0xf0) >> 4);
        super.digits = this.encodingScheme.decode(in);
        } catch (IOException e) {
            throw new ParseException(e);
        }
    }

    @Override
    public void encode(OutputStream out, final boolean removeSpc, final SccpProtocolVersion sccpProtocolVersion) throws ParseException {
        try{
        if(super.digits == null){
            throw new IllegalStateException();
        }
        out.write(this.translationType);
        out.write((this.numberingPlan.getValue() << 4) | this.encodingScheme.getSchemeCode());
        this.encodingScheme.encode(digits, out);
        } catch (IOException e) {
            throw new ParseException(e);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((numberingPlan == null) ? 0 : numberingPlan.hashCode());
        result = prime * result + translationType;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        GlobalTitle0011Impl other = (GlobalTitle0011Impl) obj;
        if (numberingPlan != other.numberingPlan)
            return false;
        if (translationType != other.translationType)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "GlobalTitle0011Impl [digits=" + digits + ",numberingPlan=" + numberingPlan + ", translationType=" + translationType + ", encodingScheme=" + encodingScheme + "]";
    }
 // default XML representation.
    protected static final XMLFormat<GlobalTitle0011Impl> XML = new XMLFormat<GlobalTitle0011Impl>(GlobalTitle0011Impl.class) {
        private final ParameterFactoryImpl factory = new ParameterFactoryImpl();
        public void write(GlobalTitle0011Impl ai, OutputElement xml) throws XMLStreamException {
            xml.setAttribute(TRANSLATION_TYPE, ai.translationType);
            xml.setAttribute(ENCODING_SCHEME, ai.encodingScheme.getSchemeCode());
            xml.setAttribute(NUMBERING_PLAN, ai.numberingPlan.getValue());
            xml.setAttribute(DIGITS, ai.digits);
        }

        public void read(InputElement xml, GlobalTitle0011Impl ai) throws XMLStreamException {
            try {
                ai.translationType = xml.getAttribute(TRANSLATION_TYPE).toInt();
                //wrong...
                final byte esCode = (byte) xml.getAttribute(ENCODING_SCHEME).toInt();
                ai.encodingScheme = factory.createEncodingScheme(esCode);
                ai.numberingPlan = NumberingPlan.valueOf(xml.getAttribute(NUMBERING_PLAN).toInt());
                ai.digits = xml.getAttribute(DIGITS).toString();
            } catch (Exception e) {
                throw new XMLStreamException(e);
            }
        }
    };
}
