
package org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement;

import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.MobilityMessage;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationInformation;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationInformationGPRS;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.MMCode;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.OfferedCamel4Functionalities;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.SupportedCamelPhases;

/**
 *
 MAP V3: noteMM-Event OPERATION ::= { --Timer m ARGUMENT NoteMM-EventArg RESULT NoteMM-EventRes ERRORS { dataMissing |
 * unexpectedDataValue | unknownSubscriber | mm-EventNotSupported} CODE local:89 }
 *
 * NoteMM-EventArg::= SEQUENCE { serviceKey ServiceKey, eventMet [0] MM-Code, imsi [1] IMSI, msisdn [2] ISDN-AddressString,
 * locationInformation [3] LocationInformation OPTIONAL, supportedCAMELPhases [5] SupportedCamelPhases OPTIONAL,
 * extensionContainer [6] ExtensionContainer OPTIONAL, ..., locationInformationGPRS [7] LocationInformationGPRS OPTIONAL,
 * offeredCamel4Functionalities [8] OfferedCamel4Functionalities OPTIONAL }
 *
 * ServiceKey ::= INTEGER (0..2147483647)
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface NoteMMEventRequest extends MobilityMessage {

    long getServiceKey();

    MMCode getMMCode();

    IMSI getImsi();

    ISDNAddressString getMsisdn();

    LocationInformation getLocationInformation();

    SupportedCamelPhases getSupportedCamelPhases();

    MAPExtensionContainer getExtensionContainer();

    LocationInformationGPRS getLocationInformationGPRS();

    OfferedCamel4Functionalities getOfferedCamel4Functionalities();

}
