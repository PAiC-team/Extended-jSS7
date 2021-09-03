
package org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement;

import java.io.Serializable;

/**
 *
 SupportedRAT-Types::= BIT STRING { utran (0), geran (1), gan (2), i-hspa-evolution (3), e-utran (4)} (SIZE (2..8)) --
 * exception handling: bits 5 to 7 shall be ignored if received and not understood
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface SupportedRATTypes extends Serializable {

    boolean getUtran();

    boolean getGeran();

    boolean getGan();

    boolean getIHspaEvolution();

    boolean getEUtran();

}
