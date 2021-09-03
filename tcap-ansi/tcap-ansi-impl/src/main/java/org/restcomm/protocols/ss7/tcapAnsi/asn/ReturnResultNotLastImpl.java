
package org.restcomm.protocols.ss7.tcapAnsi.asn;

import org.restcomm.protocols.ss7.tcapAnsi.api.asn.comp.ComponentType;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.comp.ReturnResultNotLast;

/**
 * @author baranowb
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public class ReturnResultNotLastImpl extends ReturnImpl implements ReturnResultNotLast {

    public ComponentType getType() {
        return ComponentType.ReturnResultNotLast;
    }

}
