package org.restcomm.protocols.ss7.map.api.service.supplementary;

import org.restcomm.protocols.ss7.map.api.datacoding.CBSDataCodingScheme;
import org.restcomm.protocols.ss7.map.api.primitives.USSDString;

/**
 *
 RESULT USSD-Res -- optional
 *
 *
 * @author amit bhayani
 *
 */
public interface UnstructuredSSResponse extends SupplementaryMessage {

    CBSDataCodingScheme getDataCodingScheme();

    USSDString getUSSDString();

}