package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:11:18:11 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface Number extends ISUPParameter {
    boolean isOddFlag();

    String getAddress();

    void setAddress(String address);
}
