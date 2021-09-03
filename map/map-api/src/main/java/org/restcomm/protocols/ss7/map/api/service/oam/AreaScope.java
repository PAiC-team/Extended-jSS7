
package org.restcomm.protocols.ss7.map.api.service.oam;

import java.io.Serializable;
import java.util.ArrayList;

import org.restcomm.protocols.ss7.map.api.primitives.GlobalCellId;
import org.restcomm.protocols.ss7.map.api.primitives.LAIFixedLength;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.EUtranCgi;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.RAIdentity;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.TAId;

/**
 *
<code>
AreaScope ::= SEQUENCE {
  cgi-List              [0] CGI-List OPTIONAL,
  e-utran-cgi-List      [1] E-UTRAN-CGI-List OPTIONAL,
  routingAreaId-List    [2] RoutingAreaId-List OPTIONAL,
  locationAreaId-List   [3] LocationAreaId-List OPTIONAL,
  trackingAreaId-List   [4] TrackingAreaId-List OPTIONAL,
  extensionContainer    [5] ExtensionContainer OPTIONAL,
  ...
}

CGI-List ::= SEQUENCE SIZE (1..32) OF GlobalCellId
E-UTRAN-CGI-List ::= SEQUENCE SIZE (1..32) OF E-UTRAN-CGI
RoutingAreaId-List ::= SEQUENCE SIZE (1..8) OF RAIdentity
LocationAreaId-List ::= SEQUENCE SIZE (1..8) OF LAIFixedLength
TrackingAreaId-List ::= SEQUENCE SIZE (1..8) OF TA-Id
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface AreaScope extends Serializable {

    ArrayList<GlobalCellId> getCgiList();

    ArrayList<EUtranCgi> getEUutranCgiList();

    ArrayList<RAIdentity> getRoutingAreaIdList();

    ArrayList<LAIFixedLength> getLocationAreaIdList();

    ArrayList<TAId> getTrackingAreaIdList();

    MAPExtensionContainer getExtensionContainer();

}
