
package org.restcomm.protocols.ss7.tcap.asn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.restcomm.protocols.ss7.tcap.asn.comp.GeneralProblemType;
import org.restcomm.protocols.ss7.tcap.asn.comp.Parameter;

/**
 * @author baranowb
 * @author sergey vetyutnev
 *
 */
public class ParameterImpl implements Parameter {

    private byte[] data;
    private Parameter[] parameters;
    private boolean primitive = true;
    private int tag;
    private int tagClass;
    private int encodingLength = -1;

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.comp.Parameter#getData()
     */
    public byte[] getData() {
        return data;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.comp.Parameter#isPrimitive()
     */
    public boolean isPrimitive() {
        return primitive;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.comp.Parameter#setData(byte[])
     */
    public void setData(byte[] b) {
        this.data = b;
        if (data != null)
            this.setParameters(null);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.comp.Parameter#setPrimitive(boolean)
     */
    public void setPrimitive(boolean isPrimitive) {
        if (this.parameters != null && isPrimitive) {
            // bad
            throw new IllegalArgumentException("Can not set primitive flag since Parameter[] is present!");
        }
        this.primitive = isPrimitive;
    }

    public int getEncodingLength() {
        if (encodingLength >= 0) {
            return encodingLength;
        } else if (this.data != null) {
            return this.data.length;
        } else {
            return 0;
        }
    }

    public void setEncodingLength(int encodingLength) {
        encodingLength = encodingLength;
    }

    /**
     * @return the tag
     */
    public int getTag() {
        return tag;
    }

    /**
     * @param tag the tag to set
     */
    public void setTag(int tag) {
        this.tag = tag;
    }

    /**
     * @return the tagClass
     */
    public int getTagClass() {
        return tagClass;
    }

    /**
     * @param tagClass the tagClass to set
     */
    public void setTagClass(int tagClass) {
        this.tagClass = tagClass;
    }

    public String toString() {
        return "Parameter[data=" + Arrays.toString(data) + ", parameters=" + Arrays.toString(parameters) + ", primitive="
                + primitive + ", tag=" + tag + ", tagClass=" + tagClass + ", encodingLength=" + this.getEncodingLength() + "]";
    }

    public Parameter[] getParameters() {

        if (this.parameters == null && !this.isPrimitive()) {
            // we may want to decode
            if (this.data == null) {
                return this.parameters;
            }
            List<Parameter> paramsList = new ArrayList<Parameter>();
            // else we try to decode :)
            try {
                AsnInputStream ais = new AsnInputStream(this.data);
                while (ais.available() > 0) {
                    int tag = ais.readTag();
                    Parameter _p = TcapFactory.createParameter(tag, ais, false);
                    paramsList.add(_p);
                }
                this.parameters = new Parameter[paramsList.size()];
                this.parameters = paramsList.toArray(this.parameters);
            } catch (Exception e) {
                throw new IllegalArgumentException("Failed to parse raw data into constructed parameter", e);
            }
        }
        return this.parameters;
    }

    public void setParameters(Parameter[] paramss) {
        this.parameters = paramss;
        if (this.parameters != null) {
            this.setData(null);
            this.setPrimitive(false);
        }
    }

    private boolean singleParameterInAsn = false;

    public void setSingleParameterInAsn() {
        singleParameterInAsn = true;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.Encodable#decode(org.mobicents.protocols.asn.AsnInputStream)
     */
    public void decode(AsnInputStream asnInputStream) throws ParseException {
        try {
            primitive = asnInputStream.isTagPrimitive();
            tagClass = asnInputStream.getTagClass();
            data = asnInputStream.readSequence();
            this.encodingLength = data.length;
            if (singleParameterInAsn && asnInputStream.available() > 0) { // extra data found after main array
                byte[] buf = new byte[data.length + asnInputStream.available()];
                System.arraycopy(data, 0, buf, 0, data.length);
                asnInputStream.read(buf, data.length, asnInputStream.available());
                data = buf;
            }
        } catch (IOException e) {
            throw new ParseException(null, GeneralProblemType.BadlyStructuredComponent,
                    "IOException while decoding the parameter: " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new ParseException(null, GeneralProblemType.BadlyStructuredComponent,
                    "AsnException while decoding the parameter: " + e.getMessage(), e);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.Encodable#encode(org.mobicents.protocols.asn.AsnOutputStream)
     */
    public void encode(AsnOutputStream asnOutputStream) throws EncodeException {
        if (data == null && parameters == null) {
            throw new EncodeException("Parameter data not set.");
        }
        try {
            asnOutputStream.writeTag(tagClass, primitive, tag);
            if (data == null) {
                AsnOutputStream localAos = new AsnOutputStream();
                for (Parameter p : this.parameters) {
                    p.encode(localAos);
                }
                data = localAos.toByteArray();
            }
            if (this.encodingLength >= 0)
                asnOutputStream.writeLength(this.encodingLength);
            else
                asnOutputStream.writeLength(data.length);
            asnOutputStream.write(data);
        } catch (IOException e) {
            throw new EncodeException(e);
        } catch (AsnException e) {
            throw new EncodeException(e);
        }
    }

}
