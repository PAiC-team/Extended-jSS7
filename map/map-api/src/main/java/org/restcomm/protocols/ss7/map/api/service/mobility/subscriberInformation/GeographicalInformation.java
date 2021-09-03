
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import java.io.Serializable;

/**
 *
<code>
GeographicalInformation ::= OCTET STRING (SIZE (8))

-- Refers to geographical Information defined in 3GPP TS 23.032.
-- Only the description of an ellipsoid point with uncertainty circle
-- as specified in 3GPP TS 23.032 is allowed to be used
-- The internal structure according to 3GPP TS 23.032 is as follows:
-- Type of shape (ellipsoid point with uncertainty circle) 1 octet
-- Degrees of Latitude 3 octets
-- Degrees of Longitude 3 octets
-- Uncertainty code 1 octet

The latitude is coded with 24 bits: 1 bit of sign and a number between 0 and 223-1 coded in binary on 23 bits.
The relation between the coded number N and the range of (absolute) latitudes X it encodes is the following (X in degrees):
except for N=223-1, for which the range is extended to include N+1.

The longitude, expressed in the range -180, +180, is coded as a number between -223 and 223-1, coded in 2's complement binary on 24 bits.
The relation between the coded number N and the range of longitude X it encodes is the following (X in degrees):

</code>
 *
 * @author sergey vetyutnev
 *
 */
public interface GeographicalInformation extends Serializable {

    byte[] getData();

    TypeOfShape getTypeOfShape();

    /**
     * @return Latitude value in degrees (-90 ... 90)
     */
    double getLatitude();

    /**
     * @return Longitude value in degrees (-180 ... 180)
     */
    double getLongitude();

    /**
     * @return Uncertainty value in meters
     */
    double getUncertainty();

}
