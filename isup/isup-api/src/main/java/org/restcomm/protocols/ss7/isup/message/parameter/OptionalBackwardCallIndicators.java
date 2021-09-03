package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:13:36:04 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface OptionalBackwardCallIndicators extends ISUPParameter {
    int _PARAMETER_CODE = 0x29;

    /**
     * See Q.763 3.37 In-band information indicator
     */
    boolean _IBII_NO_INDICATION = false;
    /**
     * See Q.763 3.37 In-band information indicator
     */
    boolean _IBII_AVAILABLE = true;

    /**
     * See Q.763 3.37 Call diversion may occur indicator
     */
    boolean _CDI_NO_INDICATION = false;

    /**
     * See Q.763 3.37 Call diversion may occur indicator
     */
    boolean _CDI_MAY_OCCUR = true;

    /**
     * See Q.763 3.37 Simple segmentation indicator
     */
    boolean _SSIR_NO_ADDITIONAL_INFO = false;

    /**
     * See Q.763 3.37 Simple segmentation indicator
     */
    boolean _SSIR_ADDITIONAL_INFO = true;

    /**
     * See Q.763 3.37 MLPP user indicator
     */
    boolean _MLLPUI_NO_INDICATION = false;

    /**
     * See Q.763 3.37 MLPP user indicator
     */
    boolean _MLLPUI_USER = true;

    boolean isInbandInformationIndicator();

    void setInbandInformationIndicator(boolean inbandInformationIndicator);

    boolean isCallDiversionMayOccurIndicator();

    void setCallDiversionMayOccurIndicator(boolean callDiversionMayOccurIndicator);

    boolean isSimpleSegmentationIndicator();

    void setSimpleSegmentationIndicator(boolean simpleSegmentationIndicator);

    boolean isMllpUserIndicator();

    void setMllpUserIndicator(boolean mllpUserIndicator);
}
