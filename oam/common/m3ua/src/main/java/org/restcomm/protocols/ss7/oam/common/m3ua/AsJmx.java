
package org.restcomm.protocols.ss7.oam.common.m3ua;

import java.util.List;

import javolution.util.FastList;

import org.restcomm.protocols.ss7.m3ua.As;
import org.restcomm.protocols.ss7.m3ua.Asp;
import org.restcomm.protocols.ss7.m3ua.ExchangeType;
import org.restcomm.protocols.ss7.m3ua.Functionality;
import org.restcomm.protocols.ss7.m3ua.IPSPType;
import org.restcomm.protocols.ss7.m3ua.State;
import org.restcomm.protocols.ss7.m3ua.parameter.NetworkAppearance;
import org.restcomm.protocols.ss7.m3ua.parameter.RoutingContext;
import org.restcomm.protocols.ss7.m3ua.parameter.TrafficModeType;

/**
 * @author amit bhayani
 *
 */
public class AsJmx implements AsJmxMBean {

    private final As wrappedAs;

    protected FastList<Asp> appServerProcs = new FastList<Asp>();

    /**
     *
     * @param wrappedAs
     */
    public AsJmx(As wrappedAs) {
        this.wrappedAs = wrappedAs;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.m3ua.As#getAspList()
     */
    @Override
    public List<Asp> getAspList() {
        return this.appServerProcs.unmodifiable();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.m3ua.As#getDefaultTrafficModeType()
     */
    @Override
    public TrafficModeType getDefaultTrafficModeType() {
        return this.wrappedAs.getDefaultTrafficModeType();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.m3ua.As#getExchangeType()
     */
    @Override
    public ExchangeType getExchangeType() {
        return this.wrappedAs.getExchangeType();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.m3ua.As#getFunctionality()
     */
    @Override
    public Functionality getFunctionality() {
        return this.wrappedAs.getFunctionality();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.m3ua.As#getIpspType()
     */
    @Override
    public IPSPType getIpspType() {
        return this.wrappedAs.getIpspType();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.m3ua.As#getMinAspActiveForLb()
     */
    @Override
    public int getMinAspActiveForLb() {
        return this.wrappedAs.getMinAspActiveForLb();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.m3ua.As#getName()
     */
    @Override
    public String getName() {
        return this.wrappedAs.getName();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.m3ua.As#getNetworkAppearance()
     */
    @Override
    public NetworkAppearance getNetworkAppearance() {
        return this.wrappedAs.getNetworkAppearance();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.m3ua.As#getRoutingContext()
     */
    @Override
    public RoutingContext getRoutingContext() {
        return this.wrappedAs.getRoutingContext();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.m3ua.As#getState()
     */
    @Override
    public State getState() {
        return this.wrappedAs.getState();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.m3ua.As#getTrafficModeType()
     */
    @Override
    public TrafficModeType getTrafficModeType() {
        return this.wrappedAs.getTrafficModeType();
    }

    protected void addAppServerProcess(AspJmx aspJmx) {
        this.appServerProcs.add(aspJmx);
    }

    protected AspJmx removeAppServerProcess(String aspName) {
        AspJmx aspJmx = null;
        for (FastList.Node<Asp> n = this.appServerProcs.head(), end = this.appServerProcs.tail(); (n = n.getNext()) != end;) {
            Asp asp = n.getValue();
            if (asp.getName().equals(aspName)) {
                aspJmx = (AspJmx) asp;
                break;
            }
        }// for

        this.appServerProcs.remove(aspJmx);

        return aspJmx;
    }

    @Override
    public boolean isConnected() {
        return this.wrappedAs.isConnected();
    }

    @Override
    public boolean isUp() {
        return this.wrappedAs.isUp();
    }

}
