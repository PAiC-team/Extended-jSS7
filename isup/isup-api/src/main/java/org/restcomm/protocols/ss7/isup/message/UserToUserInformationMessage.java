package org.restcomm.protocols.ss7.isup.message;

import org.restcomm.protocols.ss7.isup.message.parameter.UserToUserInformation;
import org.restcomm.protocols.ss7.isup.message.parameter.accessTransport.AccessTransport;

/**
 * Start time:09:54:07 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 * <TABLE id="Table19" style="FONT-SIZE: 9pt; WIDTH: 584px; HEIGHT: 72px; TEXT-ALIGN: center" cellSpacing="1" cellPadding="1" width="584" align="center" border="1">
 * <TR>
 * <TD style="FONT-WEIGHT: bold; WIDTH: 328px; COLOR: teal; HEIGHT: 28px; TEXT-ALIGN: center" align="center" colSpan="3">
 * <TABLE id="Table48" style="WIDTH: 575px; HEIGHT: 49px" cellSpacing="1" cellPadding="1" width="575" border="0">
 * <TR>
 *
 * <TD style="FONT-WEIGHT: bold; FONT-SIZE: 10pt; COLOR: teal; HEIGHT: 28px; TEXT-ALIGN: center" colSpan="3">
 * User to User Information Message (USR)</TD>
 * </TR>
 * <TR>
 * <TD style="FONT-SIZE: 9pt; COLOR: navy" colSpan="3">USR is&nbsp;used for the transport of user to user signalingindependent
 * of call control messages.</TD>
 * </TR>
 * </TABLE>
 * </TD>
 *
 * </TR>
 * <TR>
 * <TD style="FONT-WEIGHT: bold; WIDTH: 283px; HEIGHT: 30px; TEXT-ALIGN: center">
 * Parameter</TD>
 * <TD style="FONT-WEIGHT: bold; WIDTH: 145px; HEIGHT: 30px">Type</TD>
 * <TD style="FONT-WEIGHT: bold; HEIGHT: 30px">Length (octet)</TD>
 * </TR>
 * <TR>
 *
 * <TD style="WIDTH: 283px; TEXT-ALIGN: left">Message type</TD>
 * <TD style="WIDTH: 145px">F</TD>
 * <TD>1</TD>
 * </TR>
 * <TR>
 * <TD style="WIDTH: 283px; TEXT-ALIGN: left">User to User Information</TD>
 * <TD style="WIDTH: 145px">V</TD>
 *
 * <TD>2-130</TD>
 * </TR>
 * <TR>
 * <TD style="WIDTH: 283px; TEXT-ALIGN: left">Access Transport</TD>
 * <TD style="WIDTH: 145px">O</TD>
 * <TD>3-?</TD>
 * </TR>
 *
 * <TR>
 * <TD style="WIDTH: 283px; TEXT-ALIGN: left">End of Optional Parameters</TD>
 * <TD style="WIDTH: 145px">O</TD>
 * <TD>1</TD>
 * </TR>
 * </TABLE>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface UserToUserInformationMessage extends ISUPMessage {
    /**
     * User To User Information Message, Q.763 reference table 36 <br>
     * {@link UserToUserInformationMessage}
     */
    int MESSAGE_CODE = 0x2D;

    UserToUserInformation getUserToUserInformation();

    void setUserToUserInformation(UserToUserInformation u2ui);

    AccessTransport getAccessTransport();

    void setAccessTransport(AccessTransport at);
}
