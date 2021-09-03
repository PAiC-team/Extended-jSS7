
package org.restcomm.protocols.ss7.tcap.asn;

import java.io.IOException;
import java.util.Arrays;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.restcomm.protocols.ss7.tcap.asn.comp.GeneralProblemType;
import org.restcomm.protocols.ss7.tcap.asn.comp.OperationCode;
import org.restcomm.protocols.ss7.tcap.asn.comp.OperationCodeType;

/**
 * @author baranowb
 *
 */
public class OperationCodeImpl implements OperationCode {

    private Long localOperationCode;
    private long[] globalOperationCode;
    private OperationCodeType type;

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.OperationCode#getOperationType()
     */
    public OperationCodeType getOperationType() {
        return type;
    }

    public void setOperationType(OperationCodeType operationCodeType) {
        this.type = operationCodeType;
    }

    public void setOperationCodeType(OperationCodeType operationCodeType) {
        this.type = operationCodeType;
    }

    public void setLocalOperationCode(Long localOperationCode) {
        this.localOperationCode = localOperationCode;
        this.globalOperationCode = null;
        this.type = OperationCodeType.Local;
    }

    public void setGlobalOperationCode(long[] globalOperationCode) {
        this.localOperationCode = null;
        this.globalOperationCode = globalOperationCode;
        this.type = OperationCodeType.Global;
    }

    public Long getLocalOperationCode() {
        return this.localOperationCode;
    }

    public long[] getGlobalOperationCode() {
        return this.globalOperationCode;
    }

    public String getStringValue() {
        if (this.localOperationCode != null)
            return this.localOperationCode.toString();
        else if (this.globalOperationCode != null)
            return Arrays.toString(this.globalOperationCode);
        else
            return "empty";
    }

    public String toString() {
        if (this.localOperationCode != null)
            return "OperationCode[OperationType=Local, data=" + this.localOperationCode.toString() + "]";
        else if (this.globalOperationCode != null)
            return "OperationCode[OperationType=Global, data=" + Arrays.toString(this.globalOperationCode) + "]";
        else
            return "OperationCode[empty]";
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.Encodable#decode(org.mobicents.protocols .asn.AsnInputStream)
     */
    public void decode(AsnInputStream asnInputStream) throws ParseException {
        try {
            if (this.type == OperationCodeType.Global) {
                this.globalOperationCode = asnInputStream.readObjectIdentifier();
            } else if (this.type == OperationCodeType.Local) {
                this.localOperationCode = asnInputStream.readInteger();
            } else {
                throw new ParseException(null, GeneralProblemType.MistypedComponent);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new ParseException(null, GeneralProblemType.BadlyStructuredComponent,
                    "IOException while parsing OperationCode: " + e.getMessage(), e);
        } catch (AsnException e) {
            e.printStackTrace();
            throw new ParseException(null, GeneralProblemType.BadlyStructuredComponent,
                    "AsnException while parsing OperationCode: " + e.getMessage(), e);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.Encodable#encode(org.mobicents.protocols.asn.AsnOutputStream)
     */
    public void encode(AsnOutputStream asnOutputStream) throws EncodeException {
        if (this.localOperationCode == null && this.globalOperationCode == null)
            throw new EncodeException("Operation code: No Operation code set!");
        try {
            if (this.type == OperationCodeType.Local) {
                asnOutputStream.writeInteger(this.localOperationCode);
            } else if (this.type == OperationCodeType.Global) {
                asnOutputStream.writeObjectIdentifier(this.globalOperationCode);
            } else {
                throw new EncodeException();
            }
        } catch (IOException e) {
            throw new EncodeException(e);
        } catch (AsnException e) {
            throw new EncodeException(e);
        }
    }

}
