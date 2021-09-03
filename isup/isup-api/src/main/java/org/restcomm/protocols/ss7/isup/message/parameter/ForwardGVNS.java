package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:13:03:14 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface ForwardGVNS extends ISUPParameter {
    int _PARAMETER_CODE = 0x4C;

    OriginatingParticipatingServiceProvider getOpServiceProvider();

    void setOpServiceProvider(OriginatingParticipatingServiceProvider opServiceProvider);

    GVNSUserGroup getGvnsUserGroup();

    void setGvnsUserGroup(GVNSUserGroup gvnsUserGroup);

    TerminatingNetworkRoutingNumber getTnRoutingNumber();

    void setTnRoutingNumber(TerminatingNetworkRoutingNumber tnRoutingNumber);
}
