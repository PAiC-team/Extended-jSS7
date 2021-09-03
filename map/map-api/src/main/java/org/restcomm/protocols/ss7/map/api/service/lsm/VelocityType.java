
package org.restcomm.protocols.ss7.map.api.service.lsm;

/**
 *
 0 0 0 0 Horizontal Velocity 0 0 0 1 Horizontal with Vertical Velocity 0 0 1 0 Horizontal Velocity with Uncertainty 0 0 1 1
 * Horizontal with Vertical Velocity and Uncertainty
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum VelocityType {

    HorizontalVelocity(0), HorizontalWithVerticalVelocity(1), HorizontalVelocityWithUncertainty(2), HorizontalWithVerticalVelocityAndUncertainty(3);

    private final int type;

    private VelocityType(int type) {
        this.type = type;
    }

    public int getCode() {
        return this.type;
    }

    public static VelocityType getInstance(int type) {
        switch (type) {
            case 0:
                return HorizontalVelocity;
            case 1:
                return HorizontalWithVerticalVelocity;
            case 2:
                return HorizontalVelocityWithUncertainty;
            case 3:
                return HorizontalWithVerticalVelocityAndUncertainty;

            default:
                return null;
        }
    }
}
