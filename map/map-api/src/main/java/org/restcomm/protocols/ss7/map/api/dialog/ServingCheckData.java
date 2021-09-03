package org.restcomm.protocols.ss7.map.api.dialog;

import java.io.Serializable;

import org.restcomm.protocols.ss7.tcap.asn.ApplicationContextName;

/**
 * @author sergey vetyutnev
 *
 */
public interface ServingCheckData extends Serializable {

    ServingCheckResult getResult();

    ApplicationContextName getAlternativeApplicationContext();
}
