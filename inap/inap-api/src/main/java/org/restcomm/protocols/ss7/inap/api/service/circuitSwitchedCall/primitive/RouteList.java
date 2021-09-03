package org.restcomm.protocols.ss7.inap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;
import java.util.ArrayList;

/**
*
<code>
RouteList {PARAMETERS-BOUND : bound} ::= SEQUENCE SIZE(1..3) OF OCTET STRING (
SIZE (bound.&minRouteListLength..bound.&maxRouteListLength))
-- Indicates a list of trunk groups or a route index. See Q.1224 for additional information on this item.
</code>

*
* @author sergey vetyutnev
*
*/
public interface RouteList extends Serializable {

    ArrayList<byte[]> getDataList();

}
