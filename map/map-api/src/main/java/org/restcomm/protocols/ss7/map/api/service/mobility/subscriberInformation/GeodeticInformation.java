
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import java.io.Serializable;

/**
 *
<code>
GeodeticInformation ::= OCTET STRING (SIZE (10))
-- Refers to Calling Geodetic Location defined in Q.763 (1999).
-- Only the description of an ellipsoid point with uncertainty circle
-- as specified in Q.763 (1999) is allowed to be used
-- The internal structure according to Q.763 (1999) is as follows:
-- Screening and presentation indicators 1 octet
-- Type of shape (ellipsoid point with uncertainty circle) 1 octet
-- Degrees of Latitude 3 octets
-- Degrees of Longitude 3 octets
-- Uncertainty code 1 octet
-- Confidence 1 octet
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface GeodeticInformation extends Serializable {

    byte[] getData();

    int getScreeningAndPresentationIndicators();

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

    int getConfidence();

}
