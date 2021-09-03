
package org.restcomm.protocols.ss7.tcap.asn;

import java.io.IOException;
import java.util.Arrays;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.restcomm.protocols.ss7.tcap.asn.comp.ErrorCode;
import org.restcomm.protocols.ss7.tcap.asn.comp.ErrorCodeType;
import org.restcomm.protocols.ss7.tcap.asn.comp.GeneralProblemType;

/**
 * @author baranowb
 * @author sergey netyutnev
 *
 */
public class ErrorCodeImpl implements ErrorCode {

    private ErrorCodeType type;
    private Long localErrorCode;
    private long[] globalErrorCode;

    public void setErrorCodeType(ErrorCodeType type) {
        this.type = type;
    }

    public void setLocalErrorCode(Long localErrorCode) {
        this.localErrorCode = localErrorCode;
        this.globalErrorCode = null;
        this.type = ErrorCodeType.Local;
    }

    public void setGlobalErrorCode(long[] globalErrorCode) {
        this.localErrorCode = null;
        this.globalErrorCode = globalErrorCode;
        this.type = ErrorCodeType.Global;
    }

    public Long getLocalErrorCode() {
        return this.localErrorCode;
    }

    public long[] getGlobalErrorCode() {
        return this.globalErrorCode;
    }

    public ErrorCodeType getErrorType() {
        return type;
    }

    public String getStringValue() {
        if (this.localErrorCode != null)
            return this.localErrorCode.toString();
        else if (this.globalErrorCode != null)
            return Arrays.toString(this.globalErrorCode);
        else
            return "empty";
    }

    public String toString() {
        if (this.localErrorCode != null)
            return "ErrorCode[errorType=Local, data=" + this.localErrorCode.toString() + "]";
        else if (this.globalErrorCode != null)
            return "ErrorCode[errorType=Global, data=" + Arrays.toString(this.globalErrorCode) + "]";
        else
            return "ErrorCode[empty]";
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.Encodable#decode(org.mobicents.protocols .asn.AsnInputStream)
     */
    public void decode(AsnInputStream asnInputStream) throws ParseException {
        try {
            if (this.type == ErrorCodeType.Global) {
                this.globalErrorCode = asnInputStream.readObjectIdentifier();
            } else if (this.type == ErrorCodeType.Local) {
                this.localErrorCode = asnInputStream.readInteger();
            } else {
                throw new ParseException(null, GeneralProblemType.MistypedComponent);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new ParseException(null, GeneralProblemType.BadlyStructuredComponent, "IOException while parsing ErrorCode: "
                    + e.getMessage(), e);
        } catch (AsnException e) {
            e.printStackTrace();
            throw new ParseException(null, GeneralProblemType.BadlyStructuredComponent,
                    "AsnException while parsing ErrorCode: " + e.getMessage(), e);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.Encodable#encode(org.mobicents.protocols .asn.AsnOutputStream)
     */
    public void encode(AsnOutputStream asnOutputStream) throws EncodeException {
        if (this.localErrorCode == null && this.globalErrorCode == null)
            throw new EncodeException("Error code: No error code set!");
        try {
            if (this.type == ErrorCodeType.Local) {
                asnOutputStream.writeInteger(this.localErrorCode);
            } else if (this.type == ErrorCodeType.Global) {
                asnOutputStream.writeObjectIdentifier(this.globalErrorCode);
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
