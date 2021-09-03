
package org.restcomm.protocols.ss7.map.service.oam;

import org.restcomm.protocols.ss7.map.api.service.oam.ReportingTrigger;
import org.restcomm.protocols.ss7.map.primitives.OctetStringLength1Base;

/**
*
* @author sergey vetyutnev
*
*/
public class ReportingTriggerImpl extends OctetStringLength1Base implements ReportingTrigger {

    public ReportingTriggerImpl() {
        super("ReportingTrigger");
    }

    public ReportingTriggerImpl(int data) {
        super("ReportingTrigger", data);
    }

    public int getData() {
        return data;
    }

}
