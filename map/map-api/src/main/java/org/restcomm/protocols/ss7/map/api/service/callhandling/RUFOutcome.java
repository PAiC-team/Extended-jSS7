
package org.restcomm.protocols.ss7.map.api.service.callhandling;

/**
 *
 RUF-Outcome ::= ENUMERATED{ accepted (0), rejected (1), noResponseFromFreeMS (2), -- T4 Expiry noResponseFromBusyMS (3), --
 * T10 Expiry udubFromFreeMS (4), udubFromBusyMS (5), ...} -- exception handling: -- reception of values 6-20 shall be mapped to
 * 'accepted' -- reception of values 21-30 shall be mapped to 'rejected' -- reception of values 31-40 shall be mapped to
 * 'noResponseFromFreeMS' -- reception of values 41-50 shall be mapped to 'noResponseFromBusyMS' -- reception of values 51-60
 * shall be mapped to 'udubFromFreeMS' -- reception of values > 60 shall be mapped to 'udubFromBusyMS'
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum RUFOutcome {

    accepted(0), rejected(1), noResponseFromFreeMS(2), // -- T4 Expiry
    noResponseFromBusyMS(3), // -- T10 Expiry
    udubFromFreeMS(4), udubFromBusyMS(5);

    private int code;

    private RUFOutcome(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static RUFOutcome getInstance(int code) {
        if (code == 0 || code >= 6 && code <= 20)
            return RUFOutcome.accepted;
        else if (code == 1 || code >= 21 && code <= 30)
            return RUFOutcome.rejected;
        else if (code == 2 || code >= 31 && code <= 40)
            return RUFOutcome.rejected;
        else if (code == 3 || code >= 41 && code <= 50)
            return RUFOutcome.rejected;
        else if (code == 4 || code >= 51 && code <= 60)
            return RUFOutcome.rejected;
        else
            return RUFOutcome.udubFromBusyMS;
    }
}
