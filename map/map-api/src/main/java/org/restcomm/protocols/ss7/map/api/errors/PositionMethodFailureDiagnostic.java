package org.restcomm.protocols.ss7.map.api.errors;

/**
 *
 * PositionMethodFailure-Diagnostic ::= ENUMERATED { congestion (0), insufficientResources (1), insufficientMeasurementData (2),
 * inconsistentMeasurementData (3), locationProcedureNotCompleted (4), locationProcedureNotSupportedByTargetMS (5),
 * qoSNotAttainable (6), positionMethodNotAvailableInNetwork (7), positionMethodNotAvailableInLocationArea (8), ... } --
 * exception handling: -- any unrecognized value shall be ignored
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum PositionMethodFailureDiagnostic {

    congestion(0), insufficientResources(1), insufficientMeasurementData(2), inconsistentMeasurementData(3),
    locationProcedureNotCompleted(4), locationProcedureNotSupportedByTargetMS(5), qoSNotAttainable(6),
    positionMethodNotAvailableInNetwork(7), positionMethodNotAvailableInLocationArea(8);

    private int code;

    private PositionMethodFailureDiagnostic(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static PositionMethodFailureDiagnostic getInstance(int code) {
        switch (code) {
            case 0:
                return congestion;
            case 1:
                return insufficientResources;
            case 2:
                return insufficientMeasurementData;
            case 3:
                return inconsistentMeasurementData;
            case 4:
                return locationProcedureNotCompleted;
            case 5:
                return locationProcedureNotSupportedByTargetMS;
            case 6:
                return qoSNotAttainable;
            case 7:
                return positionMethodNotAvailableInNetwork;
            case 8:
                return positionMethodNotAvailableInLocationArea;
            default:
                return null;
        }
    }

}
