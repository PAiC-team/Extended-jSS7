
package org.restcomm.protocols.ss7.tcapAnsi.api.tc.dialog.events;

import org.restcomm.protocols.ss7.tcapAnsi.api.asn.comp.PAbortCause;

/**
 *
 * @author baranowb
 * @author sergey vetyutnev
 *
 */
public interface TCPAbortIndication extends DialogIndication {

    PAbortCause getPAbortCause();

}
