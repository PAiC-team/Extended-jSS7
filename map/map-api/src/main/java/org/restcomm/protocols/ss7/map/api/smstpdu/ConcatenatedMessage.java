package org.restcomm.protocols.ss7.map.api.smstpdu;

import java.io.Serializable;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface ConcatenatedMessage extends Serializable {

    String getText();

    UserDataHeaderElement[] getExtraUserDataHeader();

}