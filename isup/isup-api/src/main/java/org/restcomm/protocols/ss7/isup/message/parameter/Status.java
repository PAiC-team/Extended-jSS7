package org.restcomm.protocols.ss7.isup.message.parameter;

import java.io.Serializable;

/**
 * Status holder for {@link PivotStatus}
 *
 * @author baranowb
 *
 */
public interface Status extends Serializable{

    int STATUS_NOT_USED = 0x00;
    int STATUS_ACK_OF_PIVOT_ROUTING = 0x01;
    int STATUS_WILL_NOT_BE_INVOKED = 0x02;
    int STATUS_SPARE = 0x03;

    byte getStatus();

    void setStatus(byte b);
}
