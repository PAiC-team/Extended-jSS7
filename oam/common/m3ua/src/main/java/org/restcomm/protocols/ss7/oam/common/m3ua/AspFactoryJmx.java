
package org.restcomm.protocols.ss7.oam.common.m3ua;

import java.util.List;

import javolution.util.FastList;

import org.mobicents.protocols.api.Association;
import org.restcomm.protocols.ss7.m3ua.Asp;
import org.restcomm.protocols.ss7.m3ua.AspFactory;
import org.restcomm.protocols.ss7.m3ua.Functionality;
import org.restcomm.protocols.ss7.m3ua.IPSPType;
import org.restcomm.protocols.ss7.m3ua.parameter.ASPIdentifier;

/**
 * @author amit bhayani
 *
 */
public class AspFactoryJmx implements AspFactoryJmxMBean {

    private final AspFactory wrappedAspFactory;
    private FastList<Asp> appServerProcs = new FastList<Asp>();

    public AspFactoryJmx(AspFactory wrappedAspFactory) {
        this.wrappedAspFactory = wrappedAspFactory;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.m3ua.AspFactory#getAspList()
     */
    @Override
    public List<Asp> getAspList() {
        return this.appServerProcs.unmodifiable();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.m3ua.AspFactory#getAssociation()
     */
    @Override
    public Association getAssociation() {
        return this.wrappedAspFactory.getAssociation();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.m3ua.AspFactory#getFunctionality()
     */
    @Override
    public Functionality getFunctionality() {
        return this.wrappedAspFactory.getFunctionality();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.m3ua.AspFactory#getIpspType()
     */
    @Override
    public IPSPType getIpspType() {
        return this.wrappedAspFactory.getIpspType();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.m3ua.AspFactory#getName()
     */
    @Override
    public String getName() {
        return this.wrappedAspFactory.getName();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.m3ua.AspFactory#getStatus()
     */
    @Override
    public boolean getStatus() {
        return this.wrappedAspFactory.getStatus();
    }

    protected void addAppServerProcess(AspJmx aspJmx) {
        this.appServerProcs.add(aspJmx);
    }

    protected void removeAppServerProcess(AspJmx aspJmx) {
        this.appServerProcs.remove(aspJmx);
    }

    @Override
    public ASPIdentifier getAspId() {
        return this.wrappedAspFactory.getAspId();
    }

    @Override
    public boolean isHeartBeatEnabled() {
        return this.wrappedAspFactory.isHeartBeatEnabled();
    }

}
