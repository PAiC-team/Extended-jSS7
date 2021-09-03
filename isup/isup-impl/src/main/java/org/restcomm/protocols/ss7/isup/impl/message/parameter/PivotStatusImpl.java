package org.restcomm.protocols.ss7.isup.impl.message.parameter;

import java.util.ArrayList;
import java.util.List;

import org.restcomm.protocols.ss7.isup.ParameterException;
import org.restcomm.protocols.ss7.isup.message.parameter.PivotStatus;
import org.restcomm.protocols.ss7.isup.message.parameter.Status;

/**
 * @author baranowb
 *
 */
public class PivotStatusImpl extends AbstractISUPParameter implements PivotStatus {
    private List<Status> statusList = new ArrayList<Status>();

    public PivotStatusImpl() {
        // TODO Auto-generated constructor stub
    }

    public PivotStatusImpl(byte[] data) throws ParameterException {
        decode(data);
    }

    @Override
    public int getCode() {
        return _PARAMETER_CODE;
    }

    @Override
    public int decode(byte[] b) throws ParameterException {
        //FIXME: ? this does not take into account case when extension bit is used.
        for (byte v : b) {
            Status s = new StatusImpl();
            s.setStatus(v);
            this.statusList.add(s);
        }
        return b.length;
    }

    @Override
    public byte[] encode() throws ParameterException {
        byte[] data = new byte[this.statusList.size()];
        for (int index = 0; index < data.length; index++) {
            data[index] = this.statusList.get(index).getStatus();
        }
        return data;
    }

    @Override
    public void setStatus(Status... status) {
        this.statusList.clear();
        for (Status s : status) {
            if (s != null) {
                this.statusList.add(s);
            }
        }
    }

    @Override
    public Status[] getStatus() {
        return this.statusList.toArray(new Status[] {});
    }

}
