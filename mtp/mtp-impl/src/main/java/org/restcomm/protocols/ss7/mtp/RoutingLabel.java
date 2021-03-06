package org.restcomm.protocols.ss7.mtp;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.restcomm.protocols.ss7.mtp.util.MTPUtility;

/**
 * This class is defined to allow Dialogic like communication with mtp3 layer - dialogic expects fully formed Mtp3 message in
 * byte[]. So we need to create and retain back route header for incoming data(and have one forged for outgoing for newly
 * created sessions)!.
 *
 * @author baranowb
 *
 */
public class RoutingLabel {
    private static final Logger logger = Logger.getLogger(RoutingLabel.class);
    // used for forging mtp3 message,
    private byte[] mtp3Header;

    public RoutingLabel(int opc, int dpc, int sls, int si, int ssi) {
        super();
        this.mtp3Header = new byte[5];
        MTPUtility.writeRoutingLabel(mtp3Header, si, ssi, sls, dpc, opc);
    }

    /**
     * Constructor takes buffer, which should be atleast 5 bytes.
     *
     * @param buffer
     */
    public RoutingLabel(byte[] buffer) {
        super();
        if (buffer == null || buffer.length < 5) {
            throw new IllegalArgumentException();
        }
        this.mtp3Header = buffer;

    }

    public RoutingLabel() {
        this.mtp3Header = new byte[5];
    }

    /**
     * Expects MTP3 MSU, it extracts routing labels and stores inside byte[], which can be copied directly after its created.
     * This method switches opc and dpc - so stored routing label is ready to be used in answer message.
     *
     * @param data
     */
    public void setBackRouteHeader(byte[] data) {
        // here in data is whole message, we want first 5 bytes!
        int thisPointCode = Mtp3.dpc(data, 1);
        int remotePointCode = Mtp3.opc(data, 1);
        int sls = Mtp3.sls(data, 1);
        int si = Mtp3.si(data);
        int ssi = Mtp3.ssi(data);
        // this.mtp3Header = new byte[5];
        MTPUtility.writeRoutingLabel(mtp3Header, si, ssi, sls, remotePointCode, thisPointCode);
        if (logger.isInfoEnabled()) {
            logger.info("DPC[" + remotePointCode + "] OPC[" + thisPointCode + "] SLS[" + sls + "] SI[" + si + "] SSI[" + ssi
                    + "] Label" + Arrays.toString(mtp3Header));
        }
    }

    /**
     * @return the mtp3Header
     */
    public byte[] getBackRouteHeader() {
        return mtp3Header;
    }

}
