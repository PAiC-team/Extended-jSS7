
package org.restcomm.protocols.ss7.tcapAnsi.asn;

import org.restcomm.protocols.ss7.tcapAnsi.api.asn.comp.ComponentType;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.comp.ReturnResultLast;

/**
 * @author baranowb
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public class ReturnResultLastImpl extends ReturnImpl implements ReturnResultLast {

    public ComponentType getType() {
        return ComponentType.ReturnResultLast;
    }

}
