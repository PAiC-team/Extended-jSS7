
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

/**
 *
 <code>
The Type of Shape information field identifies the type which is being coded in the Shape Description.
The Type of Shape is coded as shown in table 2a.

Bits 4 3 2 1
0 0 0 0 Ellipsoid Point
0 0 0 1 Ellipsoid point with uncertainty Circle
0 0 1 1 Ellipsoid point with uncertainty Ellipse
0 1 0 1 Polygon
1 0 0 0 Ellipsoid point with altitude
1 0 0 1 Ellipsoid point with altitude and uncertainty Ellipsoid
1 0 1 0 Ellipsoid Arc other values reserved for future use
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum TypeOfShape {

    EllipsoidPoint(0), EllipsoidPointWithUncertaintyCircle(1), EllipsoidPointWithUncertaintyEllipse(3), Polygon(5),
    EllipsoidPointWithAltitude(8), EllipsoidPointWithAltitudeAndUncertaintyEllipsoid(9), EllipsoidArc(10);

    private final int type;

    private TypeOfShape(int type) {
        this.type = type;
    }

    public int getCode() {
        return this.type;
    }

    public static TypeOfShape getInstance(int type) {
        switch (type) {
            case 0:
                return EllipsoidPoint;
            case 1:
                return EllipsoidPointWithUncertaintyCircle;
            case 3:
                return EllipsoidPointWithUncertaintyEllipse;
            case 5:
                return Polygon;
            case 8:
                return EllipsoidPointWithAltitude;
            case 9:
                return EllipsoidPointWithAltitudeAndUncertaintyEllipsoid;
            case 10:
                return EllipsoidArc;
            default:
                return null;
        }
    }
}
