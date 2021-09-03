package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:13:58:32 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface RedirectionNumberRestriction extends ISUPParameter {
    int _PARAMETER_CODE = 0x40;
    // FIXME: add C defs
    /**
     * See Q.763 Presentation restricted indicator presentation allowed
     */
    byte _PRI_PA = 0;
    /**
     * See Q.763 Presentation restricted indicator presentation restricted
     */
    byte _PRI_PR = 1;

    int getPresentationRestrictedIndicator();

    void setPresentationRestrictedIndicator(int presentationRestrictedIndicator);
}
