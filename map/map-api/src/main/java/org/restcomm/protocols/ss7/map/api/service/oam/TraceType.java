
package org.restcomm.protocols.ss7.map.api.service.oam;

import java.io.Serializable;

/**
 *
<code>
TraceType ::= INTEGER (0..255)
-- Trace types are fully defined in 3GPP TS 52.008. [61]

MSC/BSS Trace Type:
8 - Priority Indication
7 - For future expansion (Set to 0)
6, 5 - BSS Record Type
  0 - Basic
  1 - Handover
  2 - Radio
  3 - No BSS Trace
4, 3 - MSC Record Type
  0 - Basic
  1 - Detailed (Optional)
  2 - Spare
  3 - No MSC Trace
2, 1 - Invoking Event
  0 - MOC, MTC, SMS MO, SMS MT, PDS MO, PDS MT, SS, Location Updates, IMSI attach, IMSI detach
  1 - MOC, MTC, SMS_MO, SMS_MT, PDS MO, PDS MT, SS only
  2 - Location updates, IMSI attach IMSI detach only
  3 - Operator definable

HLR Trace Type:
8 - Priority Indication
7, 6, 5 - For future expansion (Set to 0)
4, 3 - HLR Record Type
  0 - Basic
  1 - Detailed
  2 - Spare
  3 - No HLR Trace
2, 1 - Invoking Event
  0 - All HLR Interactions
  1 - Spare
  2 - Spare
  3 - Operator definable

</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface TraceType extends Serializable {

    int getData();

    BssRecordType getBssRecordType();

    MscRecordType getMscRecordType();

    HlrRecordType getHlrRecordType();

    TraceTypeInvokingEvent getTraceTypeInvokingEvent();

    boolean isPriorityIndication();

}
