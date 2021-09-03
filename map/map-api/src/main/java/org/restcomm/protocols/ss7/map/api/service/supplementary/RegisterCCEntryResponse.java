
package org.restcomm.protocols.ss7.map.api.service.supplementary;

/**
 *
 RegisterCC-EntryRes ::= SEQUENCE { ccbs-Feature [0] CCBS-Feature OPTIONAL, ...}
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface RegisterCCEntryResponse extends SupplementaryMessage {

    CCBSFeature getCcbsFeature();

}