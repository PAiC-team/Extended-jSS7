
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.MAPException;

/**
 *
<code>
APN ::= OCTET STRING (SIZE (2..63))
-- Octets are coded according to TS 3GPP TS 23.003 [17]

The encoding of the APN shall follow the Name Syntax defined in RFC 2181 [18], RFC 1035 [19] and RFC 1123 [20].
The APN consists of one or more labels. Each label is coded as a one octet length field followed by that number of
octets coded as 8 bit ASCII characters. Following RFC 1035 [19] the labels shall consist only of the alphabetic
characters (A-Z and a-z), digits (0-9) and the hyphen (-). Following RFC 1123 [20], the label shall begin and end with
either an alphabetic character or a digit. The case of alphabetic characters is not significant. The APN is not terminated
by a length byte of zero.
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface APN extends Serializable {

    byte[] getData();

    String getApn() throws MAPException;

}
