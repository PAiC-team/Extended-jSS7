package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:10:55:57 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 */
public interface ApplicationTransport extends ISUPParameter {
    int _PARAMETER_CODE = 0x78;

    /**
     * Q.763 3.82 Application context identifier: Unidentified Context and Error Handling (UCEH) ASE PSS1 ASE (VPN)
     */
    int _ACI_UCEH = 0x00;
    /**
     * Q.763 3.82 Application context identifier: PSS1 ASE (VPN)
     */
    int _ACI_PSS1_ASE = 0x01;
    /**
     * Q.763 3.82 Application context identifier: Charging ASE
     */
    int _ACI_CHARGING_ASE = 0x03;
    /**
     * Q.763 3.82 Application context identifier:
     */
    int _ACI_GAT = 0x04;
    /**
     * Q.763 3.82 Release call indicator: release call
     */
    boolean _RCI_RELEASE_CALL = true;
    /**
     * Q.763 3.82 Release call indicator: do not release call
     */
    boolean _RCI_DO_NOT_RELEASE_CALL = false;
    /**
     * Q.763 3.82 Send notification info indicator: send notification
     */
    boolean _SNI_SEND_NOTIFICATION = true;
    /**
     * Q.763 3.82 Send notification info indicator: do not send notification
     */
    boolean _SNI_DO_NOT_SEND_NOTIFICATION = false;

    /**
     * Q.763 3.82 Sequence indicator: new sequence
     */
    boolean _SI_NEW_SEQUENCE = true;
    /**
     * Q.763 3.82 Sequence indicator: subsequent segment to first segment new sequence
     */
    boolean _SI_SUBSEQUENT_SEGMENT = false;

    Byte getApplicationContextIdentifier();

    void setApplicationContextIdentifier(Byte v);

    Boolean isSendNotificationIndicator();

    void setSendNotificationIndicator(Boolean v);

    Boolean isReleaseCallIndicator();

    void setReleaseCallIndicator(Boolean v);

    Boolean isSegmentationIndicator();

    void setSegmentationIndicator(Boolean v);

    Byte getAPMSegmentationIndicator();

    void setAPMSegmentationIndicator(Byte v);

    Byte getSegmentationLocalReference();

    void setSegmentationLocalReference(Byte v);

    //TODO hack this.
    byte[] getEncapsulatedApplicationInformation();

    void setEncapsulatedApplicationInformation(byte[] v);
}
