package org.restcomm.protocols.ss7.map.api.service.lsm;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.datacoding.CBSDataCodingScheme;
import org.restcomm.protocols.ss7.map.api.primitives.USSDString;

/**
 * LCSClientName ::= SEQUENCE { dataCodingScheme [0] USSD-DataCodingScheme, nameString [2] NameString, ..., lcs-FormatIndicator
 * [3] LCS-FormatIndicator OPTIONAL } -- The USSD-DataCodingScheme shall indicate use of the default alphabet through the --
 * following encoding -- bit 7 6 5 4 3 2 1 0 -- 0 0 0 0 1 1 1 1
 *
 * @author amit bhayani
 *
 */
public interface LCSClientName extends Serializable {

    CBSDataCodingScheme getDataCodingScheme();

    /**
     * NameString ::= USSD-String (SIZE (1..maxNameStringLength))
     *
     * maxNameStringLength INTEGER ::= 63
     *
     * @return
     */
    USSDString getNameString();

    LCSFormatIndicator getLCSFormatIndicator();
}
