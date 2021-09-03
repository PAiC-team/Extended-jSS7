
package org.restcomm.protocols.ss7.sccp.impl.message;

import org.restcomm.protocols.ss7.sccp.message.SccpConnMessage;
import org.restcomm.protocols.ss7.sccp.parameter.LocalReference;

/**
 * @author baranowb
 *
 */
public class MessageUtil {

    private MessageUtil() {
        // TODO Auto-generated constructor stub
    }

    public static int calculateUdtFieldsLengthWithoutData(int calledPartyLengthgth, int callingPartyLengthgth) {
        // 8 = 2 (fixed fields length) + 3 (variable fields pointers) + 3
        // (variable fields lengths)
        return 8 + calledPartyLengthgth + callingPartyLengthgth;
    }

    public static int calculateXudtFieldsLengthWithoutData(int calledPartyLength, int callingPartyLength, boolean segmented,
                                                           boolean importancePresence) {
        // 10 = 3 (fixed fields length) + 4 (variable fields pointers) + 3
        // (variable fields lengths)
        int res = 10 + calledPartyLength + callingPartyLength;
        if (segmented || importancePresence)
            res++; // optional part present - adding End of optional parameters
        if (segmented)
            res += 6;
        if (importancePresence)
            res += 3;

        return res;
    }

    public static int calculateXudtFieldsLengthWithoutData2(int calledPartyLength, int callingPartyLength) {
        int res = 254 - (3 + calledPartyLength + callingPartyLength);
        return res;
    }

    public static int calculateLudtFieldsLengthWithoutData(int calledPartyLength, int callingPartyLength, boolean segmented,
                                                           boolean importancePresence) {
        // 15 = 3 (fixed fields length) + 8 (variable fields pointers) + 4 (variable fields lengths)
        int res = 15 + calledPartyLength + callingPartyLength;
        if (segmented || importancePresence)
            res++; // optional part present - adding End of optional parameters
        if (segmented)
            res += 6;
        if (importancePresence)
            res += 3;

        return res;
    }

    public static int calculateCrFieldsLengthWithoutData(int calledPartyLength, boolean creditPresence, int callingPartyLength,
                                                         boolean hopCounterPresence, boolean importancePresence) {
        // 11 = 8 (fixed fields length) + 2 (pointers) + 1 (variable field length)
        int res = 11;
        if (calledPartyLength > 0 || creditPresence || callingPartyLength > 0 || hopCounterPresence || importancePresence)
            res++; // optional part present - adding End of optional parameters
        if (calledPartyLength > 0) {
            // + field type octet, length octet
            res += calledPartyLength + 2;
        }
        if (creditPresence)
            res += 3;
        if (callingPartyLength > 0) {
            // + field type octet, length octet
            res += callingPartyLength + 2;
        }
        if (hopCounterPresence) {
            res += 3;
        }
        if (importancePresence) {
            res += 3;
        }

        return res;
    }

    public static int calculateCcFieldsLengthWithoutData(boolean creditPresence, int calledPartyLength, boolean dataPresence,
                                                         boolean importancePresence) {
        // 12 = 11 (fixed fields length) + 1 (pointers)
        int res = 12;
        if (creditPresence || calledPartyLength != 0 || dataPresence || importancePresence)
            res++; // optional part present - adding End of optional parameters
        if (creditPresence)
            res += 3;
        // when it's 0 it will be ignored
        res += calledPartyLength + 2; // + type field, length
        if (dataPresence)
            res += 2; // field type, length
        if (importancePresence)
            res += 3;

        return res;
    }

    public static int calculateCrefFieldsLengthWithoutData(int calledPartyAddressLen, boolean importancePresence) {
        // 6 = 5 (fixed fields length) + 1 (pointers)
        int res = 6;

        if (calledPartyAddressLen != 0 || importancePresence)
            res++; // optional part present - adding End of optional parameters
        // when it's 0 it will be ignored
        res += calledPartyAddressLen + 2; // + type field, length
        if (importancePresence)
            res += 3;

        return res;
    }

    public static int calculateRlsdFieldsLengthWithoutData(boolean dataPresence, boolean importancePresence) {
        // 9 = 8 (fixed fields length) + 1 (pointers)

        int res = 9;
        if (dataPresence || importancePresence)
            res++; // optional part present - adding End of optional parameters
        if (dataPresence)
            res += 2; // type field, field length
        if (importancePresence)
            res += 3;

        return res;
    }

    public static LocalReference getSln(SccpConnMessage sccpConnMessage) {
        LocalReference sln;
        if (sccpConnMessage instanceof SccpConnReferencedMessageImpl) {
            sln = ((SccpConnReferencedMessageImpl)sccpConnMessage).getSourceLocalReferenceNumber();
        } else if (sccpConnMessage instanceof SccpConnCrMessageImpl) {
            sln = ((SccpConnCrMessageImpl) sccpConnMessage).getSourceLocalReferenceNumber();
        } else {
            throw new IllegalStateException("Not implemented");
        }
        return sln;
    }

    public static LocalReference getDln(SccpConnMessage sccpConnMessage) {
        LocalReference dln;
        if (sccpConnMessage instanceof SccpConnReferencedMessageImpl) {
            dln = ((SccpConnReferencedMessageImpl)sccpConnMessage).getDestinationLocalReferenceNumber();
        } else {
            throw new IllegalStateException("Not implemented");
        }
        return dln;
    }
}
