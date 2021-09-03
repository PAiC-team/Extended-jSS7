
package org.restcomm.protocols.ss7.map.api.service.oam;

/**
*
<code>
MSC/BSS Trace Type:
2, 1 - Invoking Event
  0 - MOC, MTC, SMS MO, SMS MT, PDS MO, PDS MT, SS, Location Updates, IMSI attach, IMSI detach
  1 - MOC, MTC, SMS_MO, SMS_MT, PDS MO, PDS MT, SS only
  2 - Location updates, IMSI attach IMSI detach only
  3 - Operator definable

HLR Trace Type:
2, 1 - Invoking Event
  0 - All HLR Interactions
  1 - Spare
  2 - Spare
  3 - Operator definable
</code>
*
* @author sergey vetyutnev
*
*/
public enum TraceTypeInvokingEvent {

    InvokingEvent_0(0), InvokingEvent_1(1), InvokingEvent_2(2), InvokingEvent_3(3);

    private int code;

    private TraceTypeInvokingEvent(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static TraceTypeInvokingEvent getInstance(int code) {
        switch (code) {
        case 0:
            return TraceTypeInvokingEvent.InvokingEvent_0;
        case 1:
            return TraceTypeInvokingEvent.InvokingEvent_1;
        case 2:
            return TraceTypeInvokingEvent.InvokingEvent_2;
        case 3:
            return TraceTypeInvokingEvent.InvokingEvent_3;
        default:
            return null;
        }
    }

}
