
package org.restcomm.protocols.ss7.tcap.asn;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.tcap.asn.comp.PAbortCauseType;

/**
 * @author baranowb
 * @author sergey vetyutnev
 *
 */
public class DialogResponseAPDUImpl implements DialogResponseAPDU {

    // mandatory
    private ApplicationContextName applicationContextName;
    private Result result;
    private ResultSourceDiagnostic diagnostic;
    private ProtocolVersion protocolVersion = new ProtocolVersionImpl();
    private boolean doNotSendProtocolVersion = false;

    public DialogResponseAPDUImpl() {
    }

    public void setDoNotSendProtocolVersion(boolean isDoNotSendProtocolVersion) {
        doNotSendProtocolVersion = isDoNotSendProtocolVersion;
    }

    // optional
    private UserInformation userInformation;

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.DialogRequestAPDU# getApplicationContextName()
     */
    public ApplicationContextName getApplicationContextName() {
        return applicationContextName;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.DialogRequestAPDU#getProtocolVersion ()
     */
    public ProtocolVersion getProtocolVersion() {

        return protocolVersion;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.DialogRequestAPDU#getUserInformation ()
     */
    public UserInformation getUserInformation() {
        return this.userInformation;
    }

    /*
     * (non-Javadoc)
     *
     * @seeorg.restcomm.protocols.ss7.tcap.asn.DialogRequestAPDU# setApplicationContextName
     * (org.restcomm.protocols.ss7.tcap.asn.ApplicationContextName)
     */
    public void setApplicationContextName(ApplicationContextName applicationContextName) {
        this.applicationContextName = applicationContextName;

    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.DialogRequestAPDU#setUserInformation
     * (org.restcomm.protocols.ss7.tcap.asn.UserInformation[])
     */
    public void setUserInformation(UserInformation userInformation) {
        this.userInformation = userInformation;
    }

    public Result getResult() {
        return this.result;
    }

    public ResultSourceDiagnostic getResultSourceDiagnostic() {
        return this.diagnostic;
    }

    public void setResult(Result result) {
        this.result = result;

    }

    public void setResultSourceDiagnostic(ResultSourceDiagnostic resultSourceDiagnostic) {
        this.diagnostic = resultSourceDiagnostic;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.DialogAPDU#getType()
     */
    public DialogAPDUType getType() {
        return DialogAPDUType.Response;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.DialogAPDU#isUniDirectional()
     */
    public boolean isUniDirectional() {
        return false;
    }

    public String toString() {
        return "DialogResponseAPDU[applicationContextName=" + applicationContextName + ", result=" + result +
            ", diagnostic=" + diagnostic + ", userInformation=" + userInformation + "]";
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.Encodable#decode(org.mobicents.protocols .asn.AsnInputStream)
     */
    public void decode(AsnInputStream ais) throws ParseException {
        try {
            AsnInputStream localAis = ais.readSequenceStream();
            int tag = localAis.readTag();
            // optional protocol version
            if (tag == ProtocolVersion._TAG_PROTOCOL_VERSION && localAis.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                // we have protocol version on a
                // decode it
                this.protocolVersion = TcapFactory.createProtocolVersion(localAis);
                tag = localAis.readTag();
            }

            // mandatory
            if (tag != ApplicationContextName._TAG || localAis.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC)
                throw new ParseException(PAbortCauseType.IncorrectTxPortion, null,
                        "Error decoding DialogResponseAPDU.application-context-name: bad tag or tagClass, found tag=" + tag
                                + ", tagClass=" + localAis.getTagClass());
            this.applicationContextName = TcapFactory.createApplicationContextName(localAis);

            tag = localAis.readTag();
            if (tag != Result._TAG) {
                throw new ParseException(PAbortCauseType.IncorrectTxPortion, null, "Expected Result tag, found: " + tag);
            }
            this.result = TcapFactory.createResult(localAis);
            tag = localAis.readTag();
            if (tag != ResultSourceDiagnostic._TAG) {
                throw new ParseException(PAbortCauseType.IncorrectTxPortion, null,
                        "Expected Result Source Diagnotstic tag, found: " + tag);
            }

            this.diagnostic = TcapFactory.createResultSourceDiagnostic(localAis);

            // optional sequence.
            if (localAis.available() > 0) {
                tag = localAis.readTag();
                if (tag != UserInformation._TAG || localAis.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC)
                    throw new ParseException(PAbortCauseType.IncorrectTxPortion, null,
                            "Error decoding DialogResponseAPDU.user-information: bad tag or tagClass, found tag=" + tag
                                    + ", tagClass=" + localAis.getTagClass());
                this.userInformation = TcapFactory.createUserInformation(localAis);
            }
        } catch (IOException e) {
            throw new ParseException(PAbortCauseType.BadlyFormattedTxPortion, null,
                    "IOException while decoding DialogResponseAPDU: " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new ParseException(PAbortCauseType.BadlyFormattedTxPortion, null,
                    "AsnException while decoding DialogResponseAPDU: " + e.getMessage(), e);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.Encodable#encode(org.mobicents.protocols .asn.AsnOutputStream)
     */
    public void encode(AsnOutputStream asnOutputStream) throws EncodeException {

        if (applicationContextName == null)
            throw new EncodeException("Error encoding DialogResponseAPDU: Application Context Name must not be null");
        if (result == null)
            throw new EncodeException("Error encoding DialogResponseAPDU: Result must not be null");
        if (diagnostic == null)
            throw new EncodeException("Error encoding DialogResponseAPDU: Result-source-diagnostic must not be null");
        try {
            asnOutputStream.writeTag(Tag.CLASS_APPLICATION, false, _TAG_RESPONSE);
            int pos = asnOutputStream.StartContentDefiniteLength();
            if (!doNotSendProtocolVersion)
                this.protocolVersion.encode(asnOutputStream);
            this.applicationContextName.encode(asnOutputStream);
            this.result.encode(asnOutputStream);
            this.diagnostic.encode(asnOutputStream);
            if (userInformation != null)
                userInformation.encode(asnOutputStream);

            asnOutputStream.FinalizeContent(pos);

        } catch (AsnException e) {
            throw new EncodeException("AsnException while encoding DialogResponseAPDU: " + e.getMessage(), e);
        }
    }
}
