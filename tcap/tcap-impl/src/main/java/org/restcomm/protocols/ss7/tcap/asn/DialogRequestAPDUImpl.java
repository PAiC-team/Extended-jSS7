
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
 * @author alerant appngin
 *
 */
public class DialogRequestAPDUImpl implements DialogRequestAPDU {

    private ApplicationContextName applicationContextName;
    private UserInformation userInformation;
    private ProtocolVersion protocolVersion = null;
    private boolean doNotSendProtocolVersion = false;
    private boolean malformedUserInformation = false;

    public DialogRequestAPDUImpl() {
    }

    public void setDoNotSendProtocolVersion(boolean val) {
        doNotSendProtocolVersion = val;
    }

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
     * @see org.restcomm.protocols.ss7.tcap.asn.DialogRequestAPDU#getProtocolVersion()
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
     * @see org.restcomm.protocols.ss7.tcap.asn.DialogRequestAPDU# setApplicationContextName
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

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.DialogAPDU#getType()
     */
    public DialogAPDUType getType() {
        return DialogAPDUType.Request;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.DialogAPDU#isUniDirectional()
     */
    public boolean isUniDirectional() {

        return false;
    }

    /**
     * Return true if the decoded request contained malformed User Information element
     *
     * @return true if the decoded request contained malformed User Information element
     */
    public boolean isMalformedUserInformation() {
        return malformedUserInformation;
    }

    public String toString() {
        return "DialogRequestAPDU[applicationContextName=" + applicationContextName +
            ", userInformation=" + (malformedUserInformation ? "<MALFORMED>" : userInformation) + "]";
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
            // optional protocol version
            if (tag == ProtocolVersion._TAG_PROTOCOL_VERSION && localAis.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                // we have protocol version on a
                // decode it
                this.protocolVersion = TcapFactory.createProtocolVersion(localAis);
                tag = localAis.readTag();
            }

            // now there is mandatory part
            if (tag != ApplicationContextName._TAG || localAis.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC)
                throw new ParseException(PAbortCauseType.IncorrectTxPortion, null,
                        "Error decoding DialogRequestAPDU.application-context-name: bad tag or tagClass, found tag=" + tag
                                + ", tagClass=" + localAis.getTagClass());
            this.applicationContextName = TcapFactory.createApplicationContextName(localAis);

            // optional sequence.
            if (localAis.available() > 0) {
                // we have optional seq;

                tag = localAis.readTag();
                if (tag != UserInformation._TAG || localAis.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC)
                    throw new ParseException(PAbortCauseType.IncorrectTxPortion, null,
                            "Error decoding DialogRequestAPDU.user-information: bad tag or tagClass, found tag=" + tag
                                    + ", tagClass=" + localAis.getTagClass());
                    // Ensure all data is read even in case of malformed user information content.
                    // Don't throw exception, as user info is not necessary to establish the dialog;
                    // if TC-User requires ui to be present, it can always send TC-U-Abort.
                    int uiPos = localAis.position();
                    try {
                        this.userInformation = TcapFactory.createUserInformation(localAis);
                    } catch (ParseException uiEx) {
                        this.userInformation = null;
                        malformedUserInformation = true;
                        localAis.position(uiPos); // "reset"
                        localAis.advanceElement(); // advance without parsing the element
                    }
            }
        } catch (IOException e) {
            throw new ParseException(PAbortCauseType.BadlyFormattedTxPortion, null,
                    "IOException while decoding DialogRequestAPDU: " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new ParseException(PAbortCauseType.BadlyFormattedTxPortion, null,
                    "AsnException while decoding DialogRequestAPDU: " + e.getMessage(), e);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.Encodable#encode(org.mobicents.protocols.asn.AsnOutputStream)
     */
    public void encode(AsnOutputStream asnOutputStream) throws EncodeException {
        if (applicationContextName == null)
            throw new EncodeException("Error encoding DialogRequestAPDU: Application Context Name must not be null");
        try {
            asnOutputStream.writeTag(Tag.CLASS_APPLICATION, false, _TAG_REQUEST);
            int pos = asnOutputStream.StartContentDefiniteLength();

            if (!doNotSendProtocolVersion) {
                this.protocolVersion = new ProtocolVersionImpl();
                this.protocolVersion.encode(asnOutputStream);
            }
            this.applicationContextName.encode(asnOutputStream);

            if (userInformation != null)
                userInformation.encode(asnOutputStream);

            asnOutputStream.FinalizeContent(pos);

        } catch (AsnException e) {
            throw new EncodeException("IOException while encoding DialogRequestAPDU: " + e.getMessage(), e);
        }

    }
}
