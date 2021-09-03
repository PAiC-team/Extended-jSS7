
package org.restcomm.protocols.ss7.map.api.service.supplementary;

/**
 *
 MAP V3:
 *
 * eraseCC-Entry OPERATION ::= { --Timer m ARGUMENT EraseCC-EntryArg RESULT EraseCC-EntryRes ERRORS { systemFailure |
 * dataMissing | unexpectedDataValue | callBarred | illegalSS-Operation | ss-ErrorStatus} CODE local:77 }
 *
 * EraseCC-EntryArg ::= SEQUENCE { ss-Code [0] SS-Code, ccbs-Index [1] CCBS-Index OPTIONAL, ...}
 *
 * CCBS-Index ::= INTEGER (1..5)
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface EraseCCEntryRequest extends SupplementaryMessage {

    SSCode getSsEvent();

    Integer getCCBSIndex();

}
