package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * @author baranowb
 *
 */
public interface PivotStatus extends ISUPParameter {
    int _PARAMETER_CODE = 0x86;

    void setStatus(Status... status);

    Status[] getStatus();
}
