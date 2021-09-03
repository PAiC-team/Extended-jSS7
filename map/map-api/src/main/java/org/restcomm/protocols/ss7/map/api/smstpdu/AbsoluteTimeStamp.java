
package org.restcomm.protocols.ss7.map.api.smstpdu;

import java.io.OutputStream;
import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.MAPException;

/**
 * The TP-Service-Centre-Time-Stamp field is given in semi-octet representation, and represents the local time in the following
 * way:
 *
 * Year: Month: Day: Hour: Minute: Second: Time Zone Digits: 2 2 2 2 2 2 2 (Semi-octets)
 *
 * The Time Zone indicates the difference, expressed in quarters of an hour, between the local time and GMT. In the first of the
 * two semi-octets, the first bit (bit 3 of the seventh octet of the TP-Service-Centre-Time-Stamp field) represents the
 * algebraic sign of this difference (0: positive, 1: negative).
 *
 * @author sergey vetyutnev
 *
 */
public interface AbsoluteTimeStamp extends Serializable {

    int getYear();

    int getMonth();

    int getDay();

    int getHour();

    int getMinute();

    int getSecond();

    /**
     * @return the timeZone in in quarters of an hour
     */
    int getTimeZone();

    void encodeData(OutputStream stm) throws MAPException;

}