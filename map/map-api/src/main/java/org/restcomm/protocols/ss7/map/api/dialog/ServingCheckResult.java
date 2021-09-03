package org.restcomm.protocols.ss7.map.api.dialog;

/**
 * The result of checking if the MAPService can serve the given ApplicationContext AC_Serving - ApplicationContext can be served
 * AC_VersionIncorrect - ApplicationContext belongs to the MAPService serving zone but either not implemented or bad version. An
 * alternativeApplicationContext can be supplied AC_NotServing - ApplicationContext can not be served
 *
 * @author sergey vetyutnev
 *
 */
public enum ServingCheckResult {

    AC_Serving(0), AC_VersionIncorrect(1), AC_NotServing(2);

    private int code;

    private ServingCheckResult(int code) {
        this.code = code;
    }
}
