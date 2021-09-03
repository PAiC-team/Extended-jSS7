package org.restcomm.protocols.ss7.m3ua.impl.router;

import org.restcomm.protocols.ss7.m3ua.impl.AsImpl;

/**
 * <p>
 * SINode has reference to {@link AsImpl}
 * </p>
 *
 * @author amit bhayani
 *
 */
public class SINode {

    protected int si;
    protected AsImpl asImpl;

    protected SINode(int si, AsImpl asImpl) {
        this.si = si;
        this.asImpl = asImpl;
    }

}
