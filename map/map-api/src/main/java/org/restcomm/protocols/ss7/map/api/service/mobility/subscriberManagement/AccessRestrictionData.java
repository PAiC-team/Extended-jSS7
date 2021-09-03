
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;

/**
 *
<code>
 AccessRestrictionData ::= BIT STRING {
   utranNotAllowed (0),
   geranNotAllowed (1),
   ganNotAllowed (2),
   i-hspa-evolutionNotAllowed (3),
   e-utranNotAllowed (4),
   ho-toNon3GPP-AccessNotAllowed (5)
} (SIZE (2..8))
-- exception handling:
-- access restriction data related to an access type not supported by a node
-- shall be ignored
-- bits 6 to 7 shall be ignored if received and not understood
</code>
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface AccessRestrictionData extends Serializable {

    boolean getUtranNotAllowed();

    boolean getGeranNotAllowed();

    boolean getGanNotAllowed();

    boolean getIHspaEvolutionNotAllowed();

    boolean getEUtranNotAllowed();

    boolean getHoToNon3GPPAccessNotAllowed();

}
