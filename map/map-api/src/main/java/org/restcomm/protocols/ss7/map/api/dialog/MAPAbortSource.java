package org.restcomm.protocols.ss7.map.api.dialog;

/**
 * Source: This parameter indicates the source of the abort. For Transaction Capabilities (TC) applications the parameter may
 * take the following values: - MAP problem; - TC problem; - network service problem.
 *
 * @author sergey vetyutnev
 *
 */
public enum MAPAbortSource {

    MAPProblem(0), TCProblem(1), NetworkServiceProblem(2);

    private int code;

    private MAPAbortSource(int code) {
        this.code = code;
    }
}