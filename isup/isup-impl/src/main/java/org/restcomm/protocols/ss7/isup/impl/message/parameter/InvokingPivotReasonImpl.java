package org.restcomm.protocols.ss7.isup.impl.message.parameter;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.restcomm.protocols.ss7.isup.ParameterException;
import org.restcomm.protocols.ss7.isup.message.parameter.InformationType;
import org.restcomm.protocols.ss7.isup.message.parameter.InvokingPivotReason;
import org.restcomm.protocols.ss7.isup.message.parameter.PivotReason;

/**
 * Start time:09:11:07 2009-04-06<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 */
public class InvokingPivotReasonImpl extends AbstractInformationImpl implements InvokingPivotReason {

    private List<PivotReason> reasons = new ArrayList<PivotReason>();


    public InvokingPivotReasonImpl() {
        super(InformationType.InvokingPivotReason);
    }
    @Override
    public void setReason(PivotReason... reasons) {
        this.reasons.clear();
        if(reasons == null){
            return;
        }
        for(PivotReason pr: reasons){
            if(pr!=null){
                this.reasons.add(pr);
            }
        }
    }

    @Override
    public PivotReason[] getReason() {
        return this.reasons.toArray(new PivotReason[this.reasons.size()]);
    }
    @Override
    byte[] encode() throws ParameterException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (int index = 0; index < this.reasons.size(); index++) {
            PivotReasonImpl ai = (PivotReasonImpl) this.reasons.get(index);
            ai.trim();

            byte b = (byte) (ai.encode()[0] & 0x7F);
            if (index + 1 == this.reasons.size()) {
                b |= 0x80;
            }
            baos.write(b);

        }
        return baos.toByteArray();
    }

    @Override
    void decode(byte[] data) throws ParameterException {
        for(int index = 0;index<data.length;index++){
            byte b = data[index];
            PivotReasonImpl pr = new PivotReasonImpl();
            pr.setPivotReason((byte) (b & 0x7F));
            if( (b & 0x80) == 0 && index +1 == data.length){
                throw new ParameterException("Extension bit incicates more bytes, but we ran out of them!");
            }
            this.reasons.add(pr);
        }
    }
}
