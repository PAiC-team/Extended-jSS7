
package org.restcomm.protocols.ss7.map.api.service.supplementary;

/**
 *
 MAP V3:
 *
 * registerCC-Entry OPERATION ::= { --Timer m ARGUMENT RegisterCC-EntryArg RESULT RegisterCC-EntryRes ERRORS { systemFailure |
 * dataMissing | unexpectedDataValue | callBarred | illegalSS-Operation | ss-ErrorStatus | ss-Incompatibility | shortTermDenial
 * | longTermDenial | facilityNotSupported} CODE local:76 }
 *
 * RegisterCC-EntryArg ::= SEQUENCE { ss-Code [0] SS-Code, ccbs-Data [1] CCBS-Data OPTIONAL, ...}
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface RegisterCCEntryRequest extends SupplementaryMessage {

    SSCode getSsCode();

    CCBSData getCcbsData();

}