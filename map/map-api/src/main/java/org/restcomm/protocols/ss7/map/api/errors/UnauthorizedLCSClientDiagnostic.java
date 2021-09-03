package org.restcomm.protocols.ss7.map.api.errors;

/**
 *
 * UnauthorizedLCSClient-Diagnostic ::= ENUMERATED { noAdditionalInformation (0), clientNotInMSPrivacyExceptionList (1),
 * callToClientNotSetup (2), privacyOverrideNotApplicable (3), disallowedByLocalRegulatoryRequirements (4), ...,
 * unauthorizedPrivacyClass (5), unauthorizedCallSessionUnrelatedExternalClient (6),
 * unauthorizedCallSessionRelatedExternalClient (7) } -- exception handling: -- any unrecognized value shall be ignored
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum UnauthorizedLCSClientDiagnostic {
    noAdditionalInformation(0), clientNotInMSPrivacyExceptionList(1), callToClientNotSetup(2), privacyOverrideNotApplicable(3),
    disallowedByLocalRegulatoryRequirements(4), unauthorizedPrivacyClass(5), unauthorizedCallSessionUnrelatedExternalClient(6),
    unauthorizedCallSessionRelatedExternalClient(7);

    private int code;

    private UnauthorizedLCSClientDiagnostic(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static UnauthorizedLCSClientDiagnostic getInstance(int code) {
        switch (code) {
            case 0:
                return noAdditionalInformation;
            case 1:
                return clientNotInMSPrivacyExceptionList;
            case 2:
                return callToClientNotSetup;
            case 3:
                return privacyOverrideNotApplicable;
            case 4:
                return disallowedByLocalRegulatoryRequirements;
            case 5:
                return unauthorizedPrivacyClass;
            case 6:
                return unauthorizedCallSessionUnrelatedExternalClient;
            case 7:
                return unauthorizedCallSessionRelatedExternalClient;
            default:
                return null;
        }
    }

}
