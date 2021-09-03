package org.restcomm.protocols.ss7.map.api.service.supplementary;

import org.restcomm.protocols.ss7.map.api.datacoding.CBSDataCodingScheme;
import org.restcomm.protocols.ss7.map.api.primitives.AlertingPattern;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.USSDString;

/**
 * unstructuredSS-Notify OPERATION ::= { --Timer ml ARGUMENT USSD-Arg RETURN RESULT TRUE ERRORS { systemFailure | dataMissing |
 * unexpectedDataValue | absentSubscriber | illegalSubscriber | illegalEquipment | unknownAlphabet | ussd-Busy} CODE local:61 }
 *
 * @author amit bhayani
 *
 */
public interface UnstructuredSSNotifyRequest extends SupplementaryMessage {

    CBSDataCodingScheme getDataCodingScheme();

    USSDString getUSSDString();

    ISDNAddressString getMSISDNAddressString();

    AlertingPattern getAlertingPattern();
}