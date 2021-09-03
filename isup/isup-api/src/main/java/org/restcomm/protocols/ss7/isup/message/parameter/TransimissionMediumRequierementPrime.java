package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:14:11:20 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface TransimissionMediumRequierementPrime extends ISUPParameter {
    int _PARAMETER_CODE = 0x3E;

    // FIXME: add C desf

    /**
     * 0 0 0 0 0 0 0 0 speech
     */
    int _MEDIUM_SPEECH = 0;

    /**
     * 0 0 0 0 0 0 1 0 - 64 kbit/s unrestricted
     */
    int _MEDIUM_64_KBIT_UNRESTRICTED = 2;
    /**
     * 0 0 0 0 0 0 1 1 - 3.1 kHz audio
     */
    int _MEDIUM_3_1_KHz_AUDIO = 3;
    /**
     * 0 0 0 0 0 1 0 0 - reserved for alternate speech (service 2)/64 kbit/s unrestricted (service 1)
     */
    int _MEDIUM_RESERVED_ASS2 = 4;
    /**
     * 0 0 0 0 0 1 0 1 - reserved for alternate 64 kbit/s unrestricted (service 1)/speech (service 2)
     */
    int _MEDIUM_RESERVED_ASS1 = 5;
    /**
     * 0 0 0 0 0 1 1 0 - 64 kbit/s preferred
     */
    int _MEDIUM_64_KBIT_PREFERED = 6;
    /**
     * 0 0 0 0 0 1 1 1 - 2 64 kbit/s unrestricted
     */
    int _MEDIUM_2x64_KBIT_UNRESTRICTED = 7;
    /**
     * 0 0 0 0 1 0 0 0 - 384 kbit/s unrestricted
     */
    int _MEDIUM_384_KBIT_UNRESTRICTED = 8;
    /**
     * 0 0 0 0 1 0 0 1 - 1536 kbit/s unrestricted
     */
    int _MEDIUM_1536_KBIT_UNRESTRICTED = 9;
    /**
     * 0 0 0 0 1 0 1 0 - 1920 kbit/s unrestricted
     */
    int _MEDIUM_1920_KBIT_UNRESTRICTED = 10;

    int getTransimissionMediumRequirement();

    void setTransimissionMediumRequirement(int transimissionMediumRequirement);
}
