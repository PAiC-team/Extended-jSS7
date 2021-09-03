package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:16:05:47 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface RedirectionInformation extends ISUPParameter {
    int _PARAMETER_CODE = 0x13;
    /**
     * See Q.763 3.45 Redirecting indicator no redirection (national use)
     */
    int _RI_NO_REDIRECTION = 0;

    /**
     * See Q.763 3.45 Redirecting indicator no redirection (national use)
     */
    int _RI_CALL_REROUTED = 1;

    /**
     * See Q.763 3.45 Redirecting indicator call rerouted, all redirection information presentation restricted (national use)
     */
    int _RI_CALL_R_RID = 2;

    /**
     * See Q.763 3.45 Redirecting indicator call diverted
     */
    int _RI_CALL_D = 3;

    /**
     * See Q.763 3.45 Redirecting indicator call diverted, all redirection information presentation restricted
     */
    int _RI_CALL_D_RIR = 4;

    /**
     * See Q.763 3.45 Redirecting indicator call rerouted, redirection number presentation restricted (national use)
     */
    int _RI_CALL_R_RNPR = 5;

    /**
     * See Q.763 3.45 Redirecting indicator call diversion, redirection number presentation restricted (national use)
     */
    int _RI_CALL_D_RNPR = 6;

    /**
     * See Q.763 3.45 Original redirection reason unknown/not available
     */
    int _ORR_UNA = 0;
    /**
     * See Q.763 3.45 Original redirection reason user busy
     */
    int _ORR_USER_BUSY = 1;

    /**
     * See Q.763 3.45 Original redirection reason no reply
     */
    int _ORR_NO_REPLY = 2;
    /**
     * See Q.763 3.45 Original redirection reason unconditional
     */
    int _ORR_UNCONDITIONAL = 3;

    /**
     * See Q.763 3.45 Redirecting reason unknown/not available
     */
    int _RR_UNA = 0;
    /**
     * See Q.763 3.45 Redirecting reason user busy
     */
    int _RR_USER_BUSY = 1;

    /**
     * See Q.763 3.45 Redirecting reason no reply
     */
    int _RR_NO_REPLY = 2;
    /**
     * See Q.763 3.45 Redirecting reason unconditional
     */
    int _RR_UNCONDITIONAL = 3;

    /**
     * See Q.763 3.45 Redirecting reason deflection during alerting
     */
    int _RR_DEFLECTION_DA = 4;

    /**
     * See Q.763 3.45 Redirecting reason deflection immediate response
     */
    int _RR_DEFLECTION_IE = 5;

    /**
     * See Q.763 3.45 Redirecting reason mobile subscriber not reachable
     */
    int _RR_MOBILE_SNR = 6;

    int getRedirectingIndicator();

    void setRedirectingIndicator(int redirectingIndicator);

    int getOriginalRedirectionReason();

    void setOriginalRedirectionReason(int originalRedirectionReason);

    int getRedirectionCounter();

    void setRedirectionCounter(int redirectionCounter) throws IllegalArgumentException;

    int getRedirectionReason();

    void setRedirectionReason(int redirectionReason);

}
