package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:13:41:49 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface ParameterCompatibilityInformation extends ISUPParameter {
    //TODO: this is not 100% correct, there might be more indicators than 1-2b...
    int _PARAMETER_CODE = 0x39;

    void setParameterCompatibilityInstructionIndicators(ParameterCompatibilityInstructionIndicators... compatibilityInstructionIndicators);

    ParameterCompatibilityInstructionIndicators[] getParameterCompatibilityInstructionIndicators();
}
