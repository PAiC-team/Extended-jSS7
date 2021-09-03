package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:13:28:58 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface MessageType extends ISUPParameter {

    int getCode();

    MessageName getMessageName();

}
