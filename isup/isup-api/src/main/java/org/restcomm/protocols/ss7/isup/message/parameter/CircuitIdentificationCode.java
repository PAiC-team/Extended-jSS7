package org.restcomm.protocols.ss7.isup.message.parameter;

import java.io.Serializable;


/**
 * Start time:14:43:22 2009-09-18<br>
 * Project: mobicents-isup-stack<br>
 * This is not real parameter, its present to follow same way of defining message, null not present, != null present.
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface CircuitIdentificationCode extends Serializable {

    int getCIC();

    void setCIC(int cic);

}
