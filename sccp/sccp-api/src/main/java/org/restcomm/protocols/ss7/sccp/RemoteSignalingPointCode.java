
package org.restcomm.protocols.ss7.sccp;

/**
 * @author Amit Bhayani
 * @author sergey vetyutnev
 *
 */
public interface RemoteSignalingPointCode {

    int getRemoteSpc();

    int getRemoteSpcFlag();

    int getMask();

    boolean isRemoteSpcProhibited();

    boolean isRemoteSccpProhibited();

    int getCurrentRestrictionLevel();

}
