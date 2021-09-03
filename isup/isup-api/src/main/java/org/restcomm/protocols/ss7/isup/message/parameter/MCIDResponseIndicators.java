package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:13:27:33 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface MCIDResponseIndicators extends ISUPParameter {
    int _PARAMETER_CODE = 0x3C;

    // FIXME: its byte[], there may be more indicators than one byte
    /**
     * Flag that indicates that information is requested
     */
    boolean _INDICATOR_PROVIDED = true;

    /**
     * Flag that indicates that information is not requested
     */
    boolean _INDICATOR_NOT_PROVIDED = false;

    boolean isMcidIncludedIndicator();

    void setMcidIncludedIndicator(boolean mcidIncludedIndicator);

    boolean isHoldingProvidedIndicator();

    void setHoldingProvidedIndicator(boolean holdingProvidedIndicator);
}
