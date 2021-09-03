
package org.restcomm.protocols.ss7.map.api.service.oam;

import org.restcomm.protocols.ss7.map.api.primitives.IMSI;

/**
 *
<code>
 RESULT IMSI
</code>
 *
 * @author sergey vetyutnev
 *
 */
public interface SendImsiResponse extends OamMessage {

    IMSI getImsi();

}
