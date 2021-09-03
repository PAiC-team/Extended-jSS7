
package org.restcomm.protocols.ss7.oam.common.m3ua;

import org.restcomm.protocols.ss7.m3ua.As;
import org.restcomm.protocols.ss7.m3ua.Asp;
import org.restcomm.protocols.ss7.m3ua.AspFactory;
import org.restcomm.protocols.ss7.m3ua.State;
import org.restcomm.protocols.ss7.m3ua.parameter.ASPIdentifier;

/**
 * @author amit bhayani
 *
 */
public class AspJmx implements AspJmxMBean {

    private final Asp wrappedAsp;
    private final AsJmx asJmx;
    private AspFactoryJmx aspFactoryJmx;


    public AspJmx(Asp wrappedAsp, AsJmx asJmx, AspFactoryJmx aspFactoryJmx) {
        this.wrappedAsp = wrappedAsp;
        this.asJmx = asJmx;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.m3ua.Asp#getASPIdentifier()
     */
    @Override
    public ASPIdentifier getASPIdentifier() {
        return this.wrappedAsp.getASPIdentifier();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.m3ua.Asp#getAs()
     */
    @Override
    public As getAs() {
        return this.asJmx;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.m3ua.Asp#getName()
     */
    @Override
    public String getName() {
        return this.wrappedAsp.getName();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.m3ua.Asp#getState()
     */
    @Override
    public State getState() {
        return this.wrappedAsp.getState();
    }

    @Override
    public AspFactory getAspFactory() {
        return this.aspFactoryJmx;
    }

    @Override
    public boolean isConnected() {
        return this.wrappedAsp.isConnected();
    }

    @Override
    public boolean isStarted() {
        return this.wrappedAsp.isStarted();
    }

    @Override
    public boolean isUp() {
        return this.wrappedAsp.isUp();
    }

}
