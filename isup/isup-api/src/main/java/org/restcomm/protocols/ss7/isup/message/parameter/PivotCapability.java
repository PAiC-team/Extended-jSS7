package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:13:44:52 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface PivotCapability extends ISUPParameter {

    int _PARAMETER_CODE = 0x7B;

    // FIXME: add C defs
    /**
     * See Q.763 3.84 Pivot possible indicator : no indication
     */
    int _PPI_NO_INDICATION = 0;

    /**
     * See Q.763 3.84 Pivot possible indicator : pivot routing possible before ACM
     */
    int _PPI_PRPB_ACM = 1;

    /**
     * See Q.763 3.84 Pivot possible indicator : pivot routing possible before ANM
     */
    int _PPI_PRPB_ANM = 2;

    /**
     * See Q.763 3.84 Pivot possible indicator : pivot routing possible any time during the call
     */
    int _PPI_PRPB_ANY = 3;

    /**
     * See Q.763 3.84 Interworking to redirection indicator (national use)
     */
    boolean _ITRI_ALLOWED = false;

    /**
     * See Q.763 3.84 Interworking to redirection indicator (national use)
     */
    boolean _ITRI_NOT_ALLOWED = true;

    byte[] getPivotCapabilities();

    void setPivotCapabilities(byte[] pivotCapabilities);

    byte createPivotCapabilityByte(boolean itriNotAllowed, int pivotPossibility);

    boolean getITRINotAllowed(byte b);

    int getPivotCapability(byte b);
}
