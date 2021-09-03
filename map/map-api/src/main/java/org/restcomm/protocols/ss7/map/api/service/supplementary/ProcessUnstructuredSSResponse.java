package org.restcomm.protocols.ss7.map.api.service.supplementary;

import org.restcomm.protocols.ss7.map.api.datacoding.CBSDataCodingScheme;
import org.restcomm.protocols.ss7.map.api.primitives.USSDString;

/**
 *
 USSD-Res ::= SEQUENCE { ussd-DataCodingScheme USSD-DataCodingScheme, ussd-String USSD-String, ...}
 *
 *
 * @author amit bhayani
 *
 */
public interface ProcessUnstructuredSSResponse extends SupplementaryMessage {

    CBSDataCodingScheme getDataCodingScheme();

    USSDString getUSSDString();

}