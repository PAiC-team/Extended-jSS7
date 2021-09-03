
/**
 *
 */

package org.restcomm.protocols.ss7.tcapAnsi.api.asn.comp;

import org.restcomm.protocols.ss7.tcapAnsi.api.asn.Encodable;

/**
 * @author baranowb
 *
 */
public interface Component extends Encodable {

    int _TAG_INVOKE_ID = 15;


    void setCorrelationId(Long i);

    Long getCorrelationId();

    ComponentType getType();

}
