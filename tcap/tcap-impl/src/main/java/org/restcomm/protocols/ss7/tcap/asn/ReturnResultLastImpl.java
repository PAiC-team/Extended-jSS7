
package org.restcomm.protocols.ss7.tcap.asn;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.tcap.asn.comp.ComponentType;
import org.restcomm.protocols.ss7.tcap.asn.comp.GeneralProblemType;
import org.restcomm.protocols.ss7.tcap.asn.comp.OperationCode;
import org.restcomm.protocols.ss7.tcap.asn.comp.Parameter;
import org.restcomm.protocols.ss7.tcap.asn.comp.ReturnResultLast;

/**
 * @author baranowb
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public class ReturnResultLastImpl implements ReturnResultLast {

    // mandatory
    private Long invokeId;

    // optional: this is sequence
    private OperationCode operationCode;

    // optional
    private Parameter parameter;

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.comp.Return#getInvokeId()
     */
    public Long getInvokeId() {
        return this.invokeId;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.comp.Return#getOperationCode()
     */
    public OperationCode getOperationCode() {
        return this.operationCode;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.comp.Return#getParameter()
     */
    public Parameter getParameter() {
        return this.parameter;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.comp.Return#setInvokeId(java.lang .Long)
     */
    public void setInvokeId(Long invokeId) {
        if ((invokeId == null) || (invokeId < -128 || invokeId > 127)) {
            throw new IllegalArgumentException("Invoke ID our of range: <-128,127>: " + invokeId);
        }
        this.invokeId = invokeId;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.comp.Return#setOperationCode(org
     * .mobicents.protocols.ss7.tcap.asn.comp.OperationCode[])
     */
    public void setOperationCode(OperationCode operationCode) {
        this.operationCode = operationCode;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.comp.Return#setParameter(org.mobicents .protocols.ss7.tcap.asn.comp.Parameter)
     */
    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }

    public ComponentType getType() {
        return ComponentType.ReturnResultLast;
    }

    public String toString() {
        return "ReturnResultLast[invokeId=" + invokeId + ", operationCode=" + operationCode + ", parameter=" + parameter + "]";
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.Encodable#decode(org.mobicents.protocols .asn.AsnInputStream)
     */
    public void decode(AsnInputStream asnInputStream) throws ParseException {
        try {
            AsnInputStream localAis = asnInputStream.readSequenceStream();
            int tag = localAis.readTag();
            if (tag != _TAG_IID || localAis.getTagClass() != Tag.CLASS_UNIVERSAL) {
                throw new ParseException(null, GeneralProblemType.MistypedComponent,
                        "Error while decoding ReturnResultLast: bad tag or tag class for InvokeID: tag=" + tag
                                + ", tagClass = " + localAis.getTagClass());
            }

            this.invokeId = localAis.readInteger();

            if (localAis.available() <= 0)
                return;

            tag = localAis.readTag();
            if (tag != Tag.SEQUENCE || localAis.getTagClass() != Tag.CLASS_UNIVERSAL) {
                throw new ParseException(null, GeneralProblemType.MistypedComponent,
                        "Error while decoding ReturnResultLast: bad tag or tag class for sequence: tag=" + tag
                                + ", tagClass = " + localAis.getTagClass());
            }

            // sequence of OperationCode
            AsnInputStream sequenceStream = localAis.readSequenceStream();

            tag = sequenceStream.readTag();
            if (tag != OperationCode._TAG_GLOBAL && tag != OperationCode._TAG_LOCAL
                    || localAis.getTagClass() != Tag.CLASS_UNIVERSAL) {
                throw new ParseException(null, GeneralProblemType.MistypedComponent,
                        "Error while decoding ReturnResultLast: bad tag or tag class for operationCode: tag=" + tag
                                + ", tagClass = " + localAis.getTagClass());
            }
            this.operationCode = TcapFactory.createOperationCode(tag, sequenceStream);

            tag = sequenceStream.readTag();
            this.parameter = TcapFactory.createParameter(tag, sequenceStream, true);

        } catch (IOException e) {
            throw new ParseException(null, GeneralProblemType.BadlyStructuredComponent,
                    "IOException while decoding ReturnResultLast: " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new ParseException(null, GeneralProblemType.BadlyStructuredComponent,
                    "AsnException while decoding ReturnResultLast: " + e.getMessage(), e);
        } catch (ParseException e) {
            e.setInvokeId(this.invokeId);
            throw e;
        }

    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.Encodable#encode(org.mobicents.protocols .asn.AsnOutputStream)
     */
    public void encode(AsnOutputStream asnOutputStream) throws EncodeException {
        if (invokeId == null)
            throw new EncodeException("No Invoke ID set.");
        try {
            asnOutputStream.writeTag(Tag.CLASS_CONTEXT_SPECIFIC, false, _TAG);
            int pos = asnOutputStream.StartContentDefiniteLength();

            asnOutputStream.writeInteger(this.invokeId);

            if (this.operationCode != null && this.parameter != null) {
                asnOutputStream.writeTag(Tag.CLASS_UNIVERSAL, false, Tag.SEQUENCE);
                int pos2 = asnOutputStream.StartContentDefiniteLength();
                this.operationCode.encode(asnOutputStream);
                this.parameter.encode(asnOutputStream);
                asnOutputStream.FinalizeContent(pos2);
            }

            asnOutputStream.FinalizeContent(pos);

        } catch (IOException e) {
            throw new EncodeException("IOException while encoding ReturnResultLast: " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new EncodeException("AsnException while encoding ReturnResultLast: " + e.getMessage(), e);
        }
    }

}
