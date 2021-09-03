
package org.restcomm.protocols.ss7.map.api.service.lsm;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.TypeOfShape;

/**
 *
<code>
Add-GeographicalInformation ::= OCTET STRING (SIZE (1..91))
-- Refers to geographical Information defined in 3GPP TS 23.032.
-- This is composed of 1 or more octets with an internal structure according to
-- 3GPP TS 23.032
-- Octet 1: Type of shape, all the shapes defined in 3GPP TS 23.032 are allowed:
-- Octets 2 to n (where n is the total number of octets necessary to encode the shape
-- according to 3GPP TS 23.032) are used to encode the shape itself in accordance with the
-- encoding defined in 3GPP TS 23.032
--
-- An Add-GeographicalInformation parameter, whether valid or invalid, received
-- together with a valid Ext-GeographicalInformation parameter in the same message
-- shall be discarded.
--
-- An Add-GeographicalInformation parameter containing any shape not defined in
-- 3GPP TS 23.032 or an incorrect number of octets or coding according to
-- 3GPP TS 23.032 shall be treated as invalid data by a receiver if not received
-- together with a valid Ext-GeographicalInformation parameter in the same message.

maxAdd-GeographicalInformation INTEGER ::= 91
-- the maximum length allows support for all the shapes currently defined in 3GPP TS 23.032
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface AddGeographicalInformation extends Serializable {

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

    /**
     * @return Uncertainty value in meters
     */
    double getUncertaintySemiMajorAxis();

    /**
     * @return Uncertainty value in meters
     */
    double getUncertaintySemiMinorAxis();

    /**
     * @return Angle value in degrees
     */
    double getAngleOfMajorAxis();

    int getConfidence();

    /**
     * @return Altitude value in meters
     */
    int getAltitude();

    /**
     * @return Uncertainty value in meters
     */
    double getUncertaintyAltitude();

    /**
     * @return Radius value in meters
     */
    int getInnerRadius();

    /**
     * @return Uncertainty value in meters
     */
    double getUncertaintyRadius();

    /**
     * @return Angle value in degrees
     */
    double getOffsetAngle();

    /**
     * @return Angle value in degrees
     */
    double getIncludedAngle();

    // TODO: add processing missed: TypeOfShape.EllipsoidPointWithAltitude

}
