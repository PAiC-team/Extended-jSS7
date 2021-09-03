
package org.restcomm.protocols.ss7.tcapAnsi.api.tc.dialog.events;

/**
 * @author sergey vetyutnev
 *
 */
public enum EventType {

    Uni,
    QueryWithPerm,  QueryWithoutPerm,
    ConversationWithPerm, ConversationWithoutPerm,
    Response,
    Abort;

    // Permission == a permission to release a Dialog
}
