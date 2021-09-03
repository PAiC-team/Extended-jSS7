package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:13:29:53 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface MLPPPrecedence extends ISUPParameter {
    int _PARAMETER_CODE = 0x3A;

    /**
     * See Q.763 3.34 LFB (Look ahead for busy) : LFB allowed
     */
    int _LFB_INDICATOR_ALLOWED = 0;
    /**
     * See Q.763 3.34 LFB (Look ahead for busy) : path reserved (national use)
     */
    int _LFB_INDICATOR_PATH_RESERVED = 1;
    /**
     * See Q.763 3.34 LFB (Look ahead for busy) : LFB not allowed
     */
    int _LFB_INDICATOR_NOT_ALLOWED = 2;

    /**
     * See Q.763 3.34 Precedence level : flash override
     */
    int _PLI_FLASH_OVERRIDE = 0;

    /**
     * See Q.763 3.34 Precedence level : flash
     */
    int _PLI_FLASH = 1;
    /**
     * See Q.763 3.34 Precedence level : immediate
     */
    int _PLI_IMMEDIATE = 2;
    /**
     * See Q.763 3.34 Precedence level : priority
     */
    int _PLI_PRIORITY = 3;

    /**
     * See Q.763 3.34 Precedence level : routine
     */
    int _PLI_ROUTINE = 4;

    byte getLfb();

    void setLfb(byte lfb);

    byte getPrecedenceLevel();

    void setPrecedenceLevel(byte precedenceLevel);

    int getMllpServiceDomain();

    void setMllpServiceDomain(int mllpServiceDomain);

    byte[] getNiDigits();

    void setNiDigits(byte[] niDigits);
}
