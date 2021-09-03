
package org.restcomm.protocols.ss7.isup.impl.message.parameter;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.isup.ParameterException;
import org.restcomm.protocols.ss7.isup.message.parameter.Problem;
import org.restcomm.protocols.ss7.isup.message.parameter.ProblemType;
import org.restcomm.protocols.ss7.isup.message.parameter.Reject;


/**
 * @author baranowb
 * @author sergey vetyutnev
 *
 */
public class RejectImpl extends AbstractRemoteOperation implements Reject {

    // all are mandatory

    // this can actaully be null in this case.
    private Long invokeId;

    private Problem problem;

    public RejectImpl() {
        super(OperationType.Reject);
    }

    public Long getInvokeId() {

        return this.invokeId;
    }

    public Problem getProblem() {

        return this.problem;
    }

    public void setInvokeId(Long i) {
        if (i != null && (i < -128 || i > 127)) {
            throw new IllegalArgumentException("Invoke ID our of range: <-128,127>: " + i);
        }
        this.invokeId = i;

    }

    public void setProblem(Problem p) {

        this.problem = p;

    }


    public String toString() {
        return "Reject[invokeId=" + invokeId + ", problem=" + problem + "]";
    }

    public void decode(AsnInputStream ais) throws ParameterException {

        try {

            AsnInputStream localAis = ais.readSequenceStream();

            int tag = localAis.readTag();
            if (localAis.getTagClass() != Tag.CLASS_UNIVERSAL) {
                throw new ParameterException("Error while decoding Reject: bad tag class for InvokeID or NULL: tagClass = " + localAis.getTagClass());
            }
            switch (tag) {
                case _TAG_IID:
                    this.invokeId = localAis.readInteger();
                    break;
                case Tag.NULL:
                    localAis.readNull();
                    break;
            }

            tag = localAis.readTag();
            if (localAis.getTagClass() != ProblemType._TAG_CLASS) {
                throw new ParameterException(
                        "Error while decoding Reject: bad tag class for a problem: tagClass = " + localAis.getTagClass());
            }
            ProblemType pt = ProblemType.getFromInt(tag);
            if (pt == null) {
                throw new ParameterException(
                        "Error while decoding Reject: ProblemType not found");
            }
            this.problem = new ProblemImpl();
            this.problem.setType(pt);
            ((AbstractAsnEncodable)this.problem).decode(localAis);

        } catch (IOException e) {
            throw new ParameterException("IOException while decoding Reject: "
                    + e.getMessage(), e);
        } catch (AsnException e) {
            throw new ParameterException( "AsnException while decoding Reject: "
                    + e.getMessage(), e);
        }

    }

    public void encode(AsnOutputStream aos) throws ParameterException {

        if (this.problem == null) {
            throw new ParameterException("Problem not set!");
        }
        try {
            aos.writeTag(Tag.CLASS_CONTEXT_SPECIFIC, false, _TAG);
            int pos = aos.StartContentDefiniteLength();

            if (this.invokeId == null)
                aos.writeNull();
            else
                aos.writeInteger(this.invokeId);
            ((AbstractAsnEncodable)this.problem).encode(aos);

            aos.FinalizeContent(pos);

        } catch (IOException e) {
            throw new ParameterException("IOException while encoding Reject: " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new ParameterException("AsnException while encoding Reject: " + e.getMessage(), e);
        }

    }

}
