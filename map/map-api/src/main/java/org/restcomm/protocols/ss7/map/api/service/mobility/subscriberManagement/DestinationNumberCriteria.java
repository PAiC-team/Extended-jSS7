
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;
import java.util.ArrayList;

import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;

/**
 *
 DestinationNumberCriteria ::= SEQUENCE { matchType [0] MatchType, destinationNumberList [1] DestinationNumberList OPTIONAL,
 * destinationNumberLengthList [2] DestinationNumberLengthList OPTIONAL, -- one or both of destinationNumberList and
 * destinationNumberLengthList -- shall be present ...}
 *
 * DestinationNumberList ::= SEQUENCE SIZE (1..10) OF ISDN-AddressString -- The receiving entity shall not check the format of a
 * number in -- the dialled number list
 *
 * DestinationNumberLengthList ::= SEQUENCE SIZE (1..3) OF INTEGER(1..15)
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface DestinationNumberCriteria extends Serializable {

    MatchType getMatchType();

    ArrayList<ISDNAddressString> getDestinationNumberList();

    ArrayList<Integer> getDestinationNumberLengthList();

}
