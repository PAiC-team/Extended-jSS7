package org.restcomm.protocols.ss7.isup.message.parameter;


public interface MessageCompatibilityInformation  extends ISUPParameter {
    int _PARAMETER_CODE = 0x38;

    void setMessageCompatibilityInstructionIndicators(MessageCompatibilityInstructionIndicator... indicators);
    MessageCompatibilityInstructionIndicator[] getMessageCompatibilityInstructionIndicators();
}
