
package org.restcomm.protocols.ss7.map.api.service.mobility.imei;

import org.restcomm.protocols.ss7.map.api.primitives.IMEI;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.MobilityMessage;

/**
 *
 MAP V1-2-3:
 *
 * MAP V3: checkIMEI OPERATION ::= { --Timer m ARGUMENT CheckIMEI-Arg RESULT CheckIMEI-Res ERRORS { systemFailure | dataMissing
 * | unknownEquipment} CODE local:43 }
 *
 * MAP V2: CheckIMEI ::= OPERATION --Timer m ARGUMENT imei IMEI RESULT equipmentStatus EquipmentStatus ERRORS { SystemFailure,
 * DataMissing, UnknownEquipment }
 *
 * MAP V3: CheckIMEIArg ::= SEQUENCE { imei IMEI, requestedEquipmentInfo RequestedEquipmentInfo, extensionContainer
 * ExtensionContainer OPTIONAL, ...}
 *
 * MAP V2: ARGUMENT imei IMEI
 *
 *
 * @author normandes
 *
 */
public interface CheckImeiRequest extends MobilityMessage {

    IMEI getIMEI();

    RequestedEquipmentInfo getRequestedEquipmentInfo();

    MAPExtensionContainer getExtensionContainer();

    IMSI getIMSI();

}
