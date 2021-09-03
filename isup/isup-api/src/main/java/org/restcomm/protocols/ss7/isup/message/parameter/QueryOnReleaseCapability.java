package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:13:51:18 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface QueryOnReleaseCapability extends ISUPParameter {
    int _PARAMETER_CODE = 0x85;

    // FIXME: add C defs
    /**
     * See Q.763 QoR capability indicator : no indication
     */
    boolean _QoRI_NO_INDICATION = false;

    /**
     * See Q.763 QoR capability indicator : QoR support
     */
    boolean _QoRI_SUPPORT = true;

    byte[] getCapabilities();

    void setCapabilities(byte[] capabilities);

    boolean isQoRSupport(byte b);

    byte createQoRSupport(boolean enabled);
}
