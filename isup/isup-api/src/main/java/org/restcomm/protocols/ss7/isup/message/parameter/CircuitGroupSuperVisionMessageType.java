package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:12:20:48 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface CircuitGroupSuperVisionMessageType extends ISUPParameter {
    int _PARAMETER_CODE = 0x15;
    // FIXME: V->v

    /**
     * See Q.763 3.13 Circuit group supervision message type indicator maintenance oriented
     */
    int _CIRCUIT_GROUP_SMTIMO = 0;
    /**
     * See Q.763 3.13 Circuit group supervision message type indicator hardware failure oriented
     */
    int _CIRCUIT_GROUP_SMTIHFO = 1;

    int getCircuitGroupSuperVisionMessageTypeIndicator();

    void setCircuitGroupSuperVisionMessageTypeIndicator(int CircuitGroupSuperVisionMessageTypeIndicator);
}
