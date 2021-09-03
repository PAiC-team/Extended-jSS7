package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;

/**
 *
 Long-GroupId ::= TBCD-STRING (SIZE (4)) -- When Long-Group-Id is less than eight characters in length, the TBCD filler (1111)
 * -- is used to fill unused half octets. -- Refers to the Group Identification as specified in 3GPP TS 23.003 -- and 3GPP TS
 * 43.068/ 43.069
 *
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface LongGroupId extends Serializable {

    String getLongGroupId();

}
