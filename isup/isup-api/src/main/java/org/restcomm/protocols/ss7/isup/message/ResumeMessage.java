package org.restcomm.protocols.ss7.isup.message;

import org.restcomm.protocols.ss7.isup.message.parameter.CallReference;
import org.restcomm.protocols.ss7.isup.message.parameter.SuspendResumeIndicators;

/**
 * Start time:09:54:07 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 * <TABLE id="Table21" style="FONT-SIZE: 9pt; WIDTH: 584px; HEIGHT: 72px; TEXT-ALIGN: center" cellSpacing="1" cellPadding="1" width="584" align="center" border="1">
 * <TR>
 * <TD style="FONT-WEIGHT: bold; WIDTH: 328px; COLOR: teal; HEIGHT: 28px; TEXT-ALIGN: center" align="center" colSpan="3">
 * <TABLE id="Table50" style="WIDTH: 575px; COLOR: navy; HEIGHT: 49px" cellSpacing="1" cellPadding="1" width="575" border="0">
 * <TR>
 * <TD style="FONT-WEIGHT: bold; FONT-SIZE: 10pt; COLOR: teal; HEIGHT: 28px; TEXT-ALIGN: center" colSpan="3">
 * Resume (RES) &nbsp;Message</TD>
 * </TR>
 *
 * <TR>
 * <TD style="FONT-SIZE: 9pt; BORDER-BOTTOM: silver thin solid" colSpan="3">
 * <FONT face="Times New Roman" size="3">RES is&nbsp;sent in either direction indicating that the calling or called party, after
 * having been suspended, is reconnected. </FONT></TD>
 * </TR>
 *
 * </TABLE>
 *
 * </TD>
 * </TR>
 * <TR>
 * <TD style="FONT-WEIGHT: bold; WIDTH: 283px; HEIGHT: 30px; TEXT-ALIGN: center">
 * Parameter</TD>
 * <TD style="FONT-WEIGHT: bold; WIDTH: 145px; HEIGHT: 30px">Type</TD>
 * <TD style="FONT-WEIGHT: bold; HEIGHT: 30px">Length (octet)</TD>
 * </TR>
 *
 * <TR>
 * <TD style="WIDTH: 283px; TEXT-ALIGN: left">Message type</TD>
 * <TD style="WIDTH: 145px">F</TD>
 * <TD>1</TD>
 * </TR>
 * <TR>
 * <TD style="WIDTH: 283px; TEXT-ALIGN: left">Suspend, Resume Indicators</TD>
 *
 * <TD style="WIDTH: 145px">F</TD>
 * <TD>1</TD>
 * </TR>
 * <TR>
 * <TD style="WIDTH: 283px; TEXT-ALIGN: left">Call Reference</TD>
 * <TD style="WIDTH: 145px">O</TD>
 * <TD>7</TD>
 *
 * </TR>
 * <TR>
 * <TD style="WIDTH: 283px; TEXT-ALIGN: left">End of Optional Parameters</TD>
 * <TD style="WIDTH: 145px">O</TD>
 * <TD>1</TD>
 * </TR>
 * </TABLE>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface ResumeMessage extends ISUPMessage {
    /**
     * Resume Message, Q.763 reference table 38 <br>
     * {@link ResumeMessage}
     */
    int MESSAGE_CODE = 0x0E;

    void setSuspendResumeIndicators(SuspendResumeIndicators ri);
    SuspendResumeIndicators getSuspendResumeIndicators();

    void setCallReference(CallReference cr);
    CallReference getCallReference();
}
