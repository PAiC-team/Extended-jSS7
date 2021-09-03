
package org.restcomm.protocols.ss7.map.api.datacoding;

/**
 * 3GPP TS 23.038 - 5. CBS Data Coding Scheme
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum CBSDataCodingGroup {

    // Coding Group Bits == 0000 or 0010
    GeneralGsm7,
    // Coding Group Bits == 0001
    GeneralWithLanguageIndication,

    // Coding Group Bits == 01xx
    GeneralDataCodingIndication,
    // Coding Group Bits == 1001
    MessageWithUserDataHeader,
    // Coding Group Bits == 1101
    I1ProtocolMessage,
    // Coding Group Bits == 1110
    DefinedByTheWAPForum,
    // Coding Group Bits == 1111
    DataCodingMessageClass, Reserved;
}
