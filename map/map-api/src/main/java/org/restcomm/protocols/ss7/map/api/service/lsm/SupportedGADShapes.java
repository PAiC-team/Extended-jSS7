package org.restcomm.protocols.ss7.map.api.service.lsm;

import java.io.Serializable;

/**
 * SupportedGADShapes ::= BIT STRING { ellipsoidPoint (0), ellipsoidPointWithUncertaintyCircle (1),
 * ellipsoidPointWithUncertaintyEllipse (2), polygon (3), ellipsoidPointWithAltitude (4),
 * ellipsoidPointWithAltitudeAndUncertaintyElipsoid (5), ellipsoidArc (6) } (SIZE (7..16)) -- A node shall mark in the BIT
 * STRING all Shapes defined in 3GPP TS 23.032 it supports. -- exception handling: bits 7 to 15 shall be ignored if received.
 *
 * @author amit bhayani
 *
 */
public interface SupportedGADShapes extends Serializable {

    boolean getEllipsoidPoint();

    boolean getEllipsoidPointWithUncertaintyCircle();

    boolean getEllipsoidPointWithUncertaintyEllipse();

    boolean getPolygon();

    boolean getEllipsoidPointWithAltitude();

    boolean getEllipsoidPointWithAltitudeAndUncertaintyEllipsoid();

    boolean getEllipsoidArc();

}
