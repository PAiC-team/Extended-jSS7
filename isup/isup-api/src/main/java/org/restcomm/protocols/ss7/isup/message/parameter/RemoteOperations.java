package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:14:01:11 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface RemoteOperations extends ISUPParameter {
    // FIXME: fill this!
    int _PARAMETER_CODE = 0x32;

    int PROTOCOL_REMOTE_OPERATIONS = 0x11;
    void setProtocol(byte protocol);
    byte getProtocol();

    void setOperations(RemoteOperation...operations);
    RemoteOperation[] getOperations();
}
