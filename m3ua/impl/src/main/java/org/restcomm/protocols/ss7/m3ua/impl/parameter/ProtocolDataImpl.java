
package org.restcomm.protocols.ss7.m3ua.impl.parameter;

import org.restcomm.protocols.ss7.m3ua.parameter.ProtocolData;

/**
 * Implements Protocol Data parameter.
 *
 * @author amit bhayani
 * @author kulikov
 */
public class ProtocolDataImpl extends ParameterImpl implements ProtocolData {

    private int opc;
    private int dpc;
    private int si;
    private int ni;
    private int messagePriority;
    private int sls;
    private byte[] data;

    protected ProtocolDataImpl() {
        this.tag = ParameterImpl.Protocol_Data;
    }

    protected ProtocolDataImpl(int opc, int dpc, int si, int ni, int messagePriority, int sls, byte[] data) {
        this();
        this.opc = opc;
        this.dpc = dpc;
        this.si = si;
        this.ni = ni;
        this.messagePriority = messagePriority;
        this.sls = sls;
        this.data = data;
        encode();
    }

    /**
     * Creates new parameter with specified value.
     *
     * @param valueData the value of this parameter
     */
    protected ProtocolDataImpl(byte[] valueData) {
        this();

        this.opc = ((valueData[0] & 0xff) << 24) | ((valueData[1] & 0xff) << 16) | ((valueData[2] & 0xff) << 8) | (valueData[3] & 0xff);
        this.dpc = ((valueData[4] & 0xff) << 24) | ((valueData[5] & 0xff) << 16) | ((valueData[6] & 0xff) << 8) | (valueData[7] & 0xff);

        this.si = valueData[8] & 0xff;
        this.ni = valueData[9] & 0xff;
        this.messagePriority = valueData[10] & 0xff;
        this.sls = valueData[11] & 0xff;

        this.data = new byte[valueData.length - 12];
        System.arraycopy(valueData, 12, data, 0, valueData.length - 12);
    }

    private byte[] encode() {
        // create byte array taking into account data, point codes and
        // indicators;
        byte[] value = new byte[data.length + 12];
        // insert data
        System.arraycopy(data, 0, value, 12, data.length);

        // encode originated point codes
        value[0] = (byte) (opc >> 24);
        value[1] = (byte) (opc >> 16);
        value[2] = (byte) (opc >> 8);
        value[3] = (byte) (opc);

        // encode destination point code
        value[4] = (byte) (dpc >> 24);
        value[5] = (byte) (dpc >> 16);
        value[6] = (byte) (dpc >> 8);
        value[7] = (byte) (dpc);

        // encode indicators
        value[8] = (byte) (si);
        value[9] = (byte) (ni);
        value[10] = (byte) (messagePriority);
        value[11] = (byte) (sls);

        return value;
    }

    public int getOpc() {
        return opc;
    }

    public int getDpc() {
        return dpc;
    }

    public int getSI() {
        return si;
    }

    public int getNI() {
        return ni;
    }

    public int getMP() {
        return messagePriority;
    }

    public int getSLS() {
        return sls;
    }

    public byte[] getData() {
        return data;
    }

    @Override
    protected byte[] getValue() {
        return this.encode();
    }

    @Override
    public String toString() {
        return String.format("Protocol opc=%d dpc=%d si=%d ni=%d sls=%d", opc, dpc, si, ni, sls);
    }

}
