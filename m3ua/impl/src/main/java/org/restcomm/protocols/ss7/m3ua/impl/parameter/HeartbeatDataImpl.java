package org.restcomm.protocols.ss7.m3ua.impl.parameter;

import org.mobicents.commons.HexTools;
import org.restcomm.protocols.ss7.m3ua.parameter.HeartbeatData;
import org.restcomm.protocols.ss7.m3ua.parameter.Parameter;

/**
 *
 * @author amit bhayani
 *
 */
public class HeartbeatDataImpl extends ParameterImpl implements HeartbeatData {

    private byte[] value;

    protected HeartbeatDataImpl(byte[] value) {
        this.tag = Parameter.Heartbeat_Data;
        this.value = value;
    }

    public byte[] getData() {
        return this.value;
    }

    @Override
    protected byte[] getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return String.format("HeartbeatData : data = %s ", HexTools.dump(this.value, 0));
    }
}
