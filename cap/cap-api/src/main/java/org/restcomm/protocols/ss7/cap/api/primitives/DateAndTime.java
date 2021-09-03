
package org.restcomm.protocols.ss7.cap.api.primitives;

import java.io.Serializable;

/**
 *
 DateAndTime ::= OCTET STRING (SIZE(7)) -- DateAndTime is BCD encoded. The year digit indicating millenium occupies bits --
 * 0-3 of the first octet, and the year digit indicating century occupies bits -- 4-7 of the first octet. -- The year digit
 * indicating decade occupies bits 0-3 of the second octet, -- whilst the digit indicating the year within the decade occupies
 * bits 4-7 of -- the second octet. -- The most significant month digit occupies bits 0-3 of the third octet, -- and the least
 * significant month digit occupies bits 4-7 of the third octet. -- The most significant day digit occupies bits 0-3 of the
 * fourth octet, -- and the least significant day digit occupies bits 4-7 of the fourth octet. -- The most significant hours
 * digit occupies bits 0-3 of the fifth octet, -- and the least significant digit occupies bits 4-7 of the fifth octet. -- The
 * most significant minutes digit occupies bits 0-3 of the sixth octet, -- and the least significant digit occupies bits 4-7 of
 * the sixth octet. -- The most significant seconds digit occupies bits 0-3 of the seventh octet, -- and the least seconds
 * significant digit occupies bits 4-7 of the seventh octet. -- For the encoding of digits in an octet, refer to the
 * timeAndtimezone parameter.
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface DateAndTime extends Serializable {

    byte[] getData();

    int getYear();

    int getMonth();

    int getDay();

    int getHour();

    int getMinute();

    int getSecond();

    void setYear(int year);

    void setMonth(int month);

    void setDay(int day);

    void setHour(int hour);

    void setMinute(int minute);

    void setSecond(int second);

}