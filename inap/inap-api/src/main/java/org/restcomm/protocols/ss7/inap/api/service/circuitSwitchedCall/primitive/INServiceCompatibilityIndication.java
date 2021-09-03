package org.restcomm.protocols.ss7.inap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;
import java.util.ArrayList;

/**
*
<code>
INServiceCompatibilityIndication {PARAMETERS-BOUND : bound} ::= SEQUENCE SIZE
    (1..bound.&numOfInServiceCompatibilityIndLength) OF Entry
</code>

*
* @author sergey vetyutnev
*
*/
public interface INServiceCompatibilityIndication extends Serializable {

    ArrayList<Entry> getEntries();

}
