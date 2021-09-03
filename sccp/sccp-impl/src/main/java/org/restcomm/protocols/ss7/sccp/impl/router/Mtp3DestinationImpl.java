
package org.restcomm.protocols.ss7.sccp.impl.router;

import javolution.xml.XMLFormat;
import javolution.xml.XMLSerializable;
import javolution.xml.stream.XMLStreamException;

import org.restcomm.protocols.ss7.sccp.Mtp3Destination;

/**
 *
 * @author sergey vetyutnev
 * @author Amit Bhayani
 */
public class Mtp3DestinationImpl implements XMLSerializable, Mtp3Destination {

    private static final String FIRST_DPC = "firstDpc";
    private static final String LAST_DPC = "lastDpc";
    private static final String FIRST_SLS = "firstSls";
    private static final String LAST_SLS = "lastSls";
    private static final String SLS_MASK = "slsMask";

    private int firstDpc;
    private int lastDpc;
    private int firstSls;
    private int lastSls;
    private int slsMask;

    public Mtp3DestinationImpl() {
    }

    public Mtp3DestinationImpl(int firstDpc, int lastDpc, int firstSls, int lastSls, int slsMask) {
        this.firstDpc = firstDpc;
        this.lastDpc = lastDpc;
        this.firstSls = firstSls;
        this.lastSls = lastSls;
        this.slsMask = slsMask;
    }

    public int getFirstDpc() {
        return this.firstDpc;
    }

    public int getLastDpc() {
        return this.lastDpc;
    }

    public int getFirstSls() {
        return this.firstSls;
    }

    public int getLastSls() {
        return this.lastSls;
    }

    public int getSlsMask() {
        return this.slsMask;
    }

    public boolean match(int dpc, int sls) {
        sls = (sls & this.slsMask);
        if (dpc >= this.firstDpc && dpc <= this.lastDpc && sls >= this.firstSls && sls <= this.lastSls)
            return true;
        else
            return false;
    }

    public boolean match(int dpc) {
        if (dpc >= this.firstDpc && dpc <= this.lastDpc)
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("firstDpc=").append(this.firstDpc).append(", lastDpc=").append(this.lastDpc).append(", firstSls=")
                .append(this.firstSls).append(", lastSls=").append(this.lastSls).append(", slsMask=").append(this.slsMask);
        return sb.toString();
    }

    protected static final XMLFormat<Mtp3DestinationImpl> XML = new XMLFormat<Mtp3DestinationImpl>(Mtp3DestinationImpl.class) {

        public void write(Mtp3DestinationImpl dest, OutputElement xml) throws XMLStreamException {
            xml.setAttribute(FIRST_DPC, dest.firstDpc);
            xml.setAttribute(LAST_DPC, dest.lastDpc);
            xml.setAttribute(FIRST_SLS, dest.firstSls);
            xml.setAttribute(LAST_SLS, dest.lastSls);
            xml.setAttribute(SLS_MASK, dest.slsMask);
        }

        public void read(InputElement xml, Mtp3DestinationImpl dest) throws XMLStreamException {
            dest.firstDpc = xml.getAttribute(FIRST_DPC).toInt();
            dest.lastDpc = xml.getAttribute(LAST_DPC).toInt();
            dest.firstSls = xml.getAttribute(FIRST_SLS).toInt();
            dest.lastSls = xml.getAttribute(LAST_SLS).toInt();
            dest.slsMask = xml.getAttribute(SLS_MASK).toInt();
        }
    };
}
