
package org.restcomm.protocols.ss7.sccp.impl;


/**
 *
 * @author sergey vetyutnev
 *
 */
public interface RemoteSignalingPointCodeExt {

    void clearCongLevel();

    void increaseCongLevel(int level);

}
