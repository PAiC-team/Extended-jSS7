
package org.restcomm.protocols.ss7.map.api.service.supplementary;

/**
 *
 EraseCC-EntryRes ::= SEQUENCE { ss-Code [0] SS-Code, ss-Status [1] SS-Status OPTIONAL, ...}
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface EraseCCEntryResponse extends SupplementaryMessage {

    SSCode getSsEvent();

    SSStatus getSsStatus();

}
