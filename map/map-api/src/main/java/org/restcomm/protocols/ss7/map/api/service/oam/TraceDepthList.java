
package org.restcomm.protocols.ss7.map.api.service.oam;

import java.io.Serializable;

/**
 *
<code>
TraceDepthList ::= SEQUENCE {
  msc-s-TraceDepth   [0] TraceDepth OPTIONAL,
  mgw-TraceDepth     [1] TraceDepth OPTIONAL,
  sgsn-TraceDepth    [2] TraceDepth OPTIONAL,
  ggsn-TraceDepth    [3] TraceDepth OPTIONAL,
  rnc-TraceDepth     [4] TraceDepth OPTIONAL,
  bmsc-TraceDepth    [5] TraceDepth OPTIONAL,
  ...,
  mme-TraceDepth     [6] TraceDepth OPTIONAL,
  sgw-TraceDepth     [7] TraceDepth OPTIONAL,
  pgw-TraceDepth     [8] TraceDepth OPTIONAL,
  eNB-TraceDepth     [9] TraceDepth OPTIONAL
}
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface TraceDepthList extends Serializable {

    TraceDepth getMscSTraceDepth();

    TraceDepth getMgwTraceDepth();

    TraceDepth getSgsnTraceDepth();

    TraceDepth getGgsnTraceDepth();

    TraceDepth getRncTraceDepth();

    TraceDepth getBmscTraceDepth();

    TraceDepth getMmeTraceDepth();

    TraceDepth getSgwTraceDepth();

    TraceDepth getPgwTraceDepth();

    TraceDepth getEnbTraceDepth();

}
