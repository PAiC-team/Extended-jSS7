
package org.restcomm.protocols.ss7.map.api.service.mobility.authentication;

import java.util.ArrayList;

import org.restcomm.protocols.ss7.map.api.service.mobility.MobilityMessage;

/**
 *
 MAP V1: SendParameters ::= OPERATION --Timer m ARGUMENT sendParametersArg SendParametersArg RESULT sentParameterList
 * SentParameterList -- optional -- nothing is returned, if no requested parameter is -- available or exists ERRORS {
 * UnexpectedDataValue, UnknownSubscriber, UnidentifiedSubscriber}
 *
 * MAP V1: SendParametersArg ::= SEQUENCE { subscriberId SubscriberId, requestParameterList RequestParameterList}
 *
 * requestParameterList SEQUENCE SIZE (1..2) OF RequestParameter
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface SendParametersRequest extends MobilityMessage {

    SubscriberId getSubscriberId();

    ArrayList<RequestParameter> getRequestParameterList();

}
