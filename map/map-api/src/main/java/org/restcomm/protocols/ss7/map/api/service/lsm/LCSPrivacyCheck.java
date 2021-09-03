package org.restcomm.protocols.ss7.map.api.service.lsm;

import java.io.Serializable;

/**
 * LCS-PrivacyCheck ::= SEQUENCE { callSessionUnrelated [0] PrivacyCheckRelatedAction, callSessionRelated [1]
 * PrivacyCheckRelatedAction OPTIONAL, ...}
 *
 * @author amit bhayani
 *
 */
public interface LCSPrivacyCheck extends Serializable {

    PrivacyCheckRelatedAction getCallSessionUnrelated();

    PrivacyCheckRelatedAction getCallSessionRelated();
}
