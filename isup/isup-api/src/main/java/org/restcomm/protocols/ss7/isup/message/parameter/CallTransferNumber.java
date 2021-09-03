package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:12:21:24 2009-09-07<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface CallTransferNumber extends ISUPParameter, NAINumber {
    int _PARAMETER_CODE = 0x45;

    /**
     * numbering plan indicator indicator value. See Q.763 - 3.64d
     */
    int _NPI_ISDN = 1;
    /**
     * numbering plan indicator indicator value. See Q.763 - 3.64d
     */
    int _NPI_DATA = 3;
    /**
     * numbering plan indicator indicator value. See Q.763 - 3.64d
     */
    int _NPI_TELEX = 4;
    /**
     * numbering plan indicator indicator value. See Q.763 - 3.64d
     */
    int _NPI_PRIVATE = 5;

    /**
     * address presentation restricted indicator indicator value. See Q.763 - 3.64d
     */
    int _APRI_ALLOWED = 0;

    /**
     * address presentation restricted indicator indicator value. See Q.763 - 3.64d
     */
    int _APRI_RESTRICTED = 1;

    /**
     * screening indicator indicator value. See Q.763 - 3.64e
     */
    int _SI_USER_PROVIDED_NVERIFIED_PASSED = 0;
    /**
     * screening indicator indicator value. See Q.763 - 3.64e
     */
    int _SI_USER_PROVIDED_VERIFIED_PASSED = 1;
    /**
     * screening indicator indicator value. See Q.763 - 3.64e
     */
    int _SI_USER_PROVIDED_VERIFIED_FAILED = 2;

    /**
     * screening indicator indicator value. See Q.763 - 3.64e
     */
    int _SI_NETWORK_PROVIDED = 3;

    int getNumberingPlanIndicator();

    void setNumberingPlanIndicator(int numberingPlanIndicator);

    int getAddressRepresentationRestrictedIndicator();

    void setAddressRepresentationRestrictedIndicator(int addressRepresentationREstrictedIndicator);

    int getScreeningIndicator();

    void setScreeningIndicator(int screeningIndicator);
}
