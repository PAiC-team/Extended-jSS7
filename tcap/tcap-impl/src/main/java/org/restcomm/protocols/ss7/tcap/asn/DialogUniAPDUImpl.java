
package org.restcomm.protocols.ss7.tcap.asn;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.tcap.asn.comp.PAbortCauseType;

/**
 * @author baranowb
 *
 */
public class DialogUniAPDUImpl implements DialogUniAPDU {

    private ApplicationContextName applicationContextName;
    private UserInformation userInformation;
    private boolean doNotSendProtocolVersion = false;

    public DialogUniAPDUImpl() {
    }

    public void setDoNotSendProtocolVersion(boolean isDoNotSendProtocolVersion) {
        doNotSendProtocolVersion = isDoNotSendProtocolVersion;
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
     * @see org.restcomm.protocols.ss7.tcap.asn.DialogRequestAPDU#getProtocolVersion ()
     */
    public int getProtocolVersion() {
        return 1;
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
        return DialogAPDUType.UniDirectional;
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
        return "DialogUniAPDU[applicationContextName=" + applicationContextName +
            ", userInformation=" + userInformation + "]";
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
                TcapFactory.createProtocolVersion(localAis);
                tag = localAis.readTag();
            }

            // now there is mandatory part
            if (tag != ApplicationContextName._TAG || localAis.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC)
                throw new ParseException(PAbortCauseType.IncorrectTxPortion, null,
                        "Error decoding DialogUniAPDU.application-context-name: bad tag or tagClass, found tag=" + tag
                                + ", tagClass=" + localAis.getTagClass());
            this.applicationContextName = TcapFactory.createApplicationContextName(localAis);

            // optional sequence.
            if (localAis.available() > 0) {
                tag = localAis.readTag();
                if (tag != UserInformation._TAG || localAis.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC)
                    throw new ParseException(PAbortCauseType.IncorrectTxPortion, null,
                            "Error decoding DialogUniAPDU.user-information: bad tag or tagClass, found tag=" + tag
                                    + ", tagClass=" + localAis.getTagClass());
                this.userInformation = TcapFactory.createUserInformation(localAis);
            }

        } catch (IOException e) {
            throw new ParseException(PAbortCauseType.BadlyFormattedTxPortion, null,
                    "IOException while decoding DialogUniAPDU: " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new ParseException(PAbortCauseType.BadlyFormattedTxPortion, null,
                    "AsnException while decoding DialogUniAPDU: " + e.getMessage(), e);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.Encodable#encode(org.mobicents.protocols .asn.AsnOutputStream)
     */
    public void encode(AsnOutputStream asnOutputStream) throws EncodeException {
        if (applicationContextName == null)
            throw new EncodeException("Error encoding DialogUniAPDU: Application Context Name must not be null");
        try {
            asnOutputStream.writeTag(Tag.CLASS_APPLICATION, false, _TAG_UNIDIRECTIONAL);
            int pos = asnOutputStream.StartContentDefiniteLength();
            // lets not omit protocol version, we check byte[] in tests,
            // it screws them :)
            if (!doNotSendProtocolVersion) {
                ProtocolVersion pv = TcapFactory.createProtocolVersion();
                pv.encode(asnOutputStream);
            }
            this.applicationContextName.encode(asnOutputStream);

            if (userInformation != null)
                userInformation.encode(asnOutputStream);

            asnOutputStream.FinalizeContent(pos);

        } catch (AsnException e) {
            throw new EncodeException("AsnException while encoding DialogUniAPDU: " + e.getMessage(), e);
        }
    }
}
