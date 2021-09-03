
package org.restcomm.protocols.ss7.map.api.service.mobility.authentication;

import java.util.ArrayList;

import org.restcomm.protocols.ss7.map.api.service.mobility.MobilityMessage;

/**
 *
 MAP V1: RESULT sentParameterList SentParameterList
 *
 * SentParameterList ::= SEQUENCE SIZE (1..6) OF SentParameter
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface SendParametersResponse extends MobilityMessage {

    ArrayList<SentParameter> getSentParameterList();

}
