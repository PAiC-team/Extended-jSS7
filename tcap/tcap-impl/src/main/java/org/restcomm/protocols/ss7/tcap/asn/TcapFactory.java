
package org.restcomm.protocols.ss7.tcap.asn;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.tcap.api.tc.component.InvokeClass;
import org.restcomm.protocols.ss7.tcap.asn.comp.Component;
import org.restcomm.protocols.ss7.tcap.asn.comp.ErrorCode;
import org.restcomm.protocols.ss7.tcap.asn.comp.ErrorCodeType;
import org.restcomm.protocols.ss7.tcap.asn.comp.GeneralProblemType;
import org.restcomm.protocols.ss7.tcap.asn.comp.Invoke;
import org.restcomm.protocols.ss7.tcap.asn.comp.OperationCode;
import org.restcomm.protocols.ss7.tcap.asn.comp.OperationCodeType;
import org.restcomm.protocols.ss7.tcap.asn.comp.PAbortCauseType;
import org.restcomm.protocols.ss7.tcap.asn.comp.Parameter;
import org.restcomm.protocols.ss7.tcap.asn.comp.Problem;
import org.restcomm.protocols.ss7.tcap.asn.comp.ProblemType;
import org.restcomm.protocols.ss7.tcap.asn.comp.Reject;
import org.restcomm.protocols.ss7.tcap.asn.comp.ReturnError;
import org.restcomm.protocols.ss7.tcap.asn.comp.ReturnResult;
import org.restcomm.protocols.ss7.tcap.asn.comp.ReturnResultLast;
import org.restcomm.protocols.ss7.tcap.asn.comp.TCAbortMessage;
import org.restcomm.protocols.ss7.tcap.asn.comp.TCBeginMessage;
import org.restcomm.protocols.ss7.tcap.asn.comp.TCContinueMessage;
import org.restcomm.protocols.ss7.tcap.asn.comp.TCEndMessage;
import org.restcomm.protocols.ss7.tcap.asn.comp.TCUniMessage;

/**
 * @author baranowb
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public final class TcapFactory {

    public static DialogPortion createDialogPortion(AsnInputStream asnInputStream) throws ParseException {
        DialogPortionImpl dpi = new DialogPortionImpl();
        dpi.decode(asnInputStream);
        return dpi;
    }

    public static DialogPortion createDialogPortion() {
        return new DialogPortionImpl();
    }

    public static DialogAPDU createDialogAPDU(AsnInputStream asnInputStream, int tag, boolean unidirectional) throws ParseException {
        if (asnInputStream.getTagClass() != Tag.CLASS_APPLICATION)
            throw new ParseException(PAbortCauseType.BadlyFormattedTxPortion, null,
                    "Error decoding dialog APDU: wrong tag class for APDU, found: " + asnInputStream.getTagClass());

        if (unidirectional) {
            // only one
            if (tag != DialogAPDU._TAG_UNIDIRECTIONAL) {
                throw new ParseException(PAbortCauseType.BadlyFormattedTxPortion, null,
                        "Error decoding dialog APDU: wrong tag for APDU, found: " + tag);
            } else {
                // create UNIPDU
                DialogUniAPDUImpl d = new DialogUniAPDUImpl();
                d.decode(asnInputStream);
                return d;
            }

        } else {

            if (tag == DialogAPDU._TAG_REQUEST) {
                DialogRequestAPDUImpl d = new DialogRequestAPDUImpl();
                d.decode(asnInputStream);
                return d;
            }
            if (tag == DialogAPDU._TAG_RESPONSE) {
                DialogResponseAPDUImpl d = new DialogResponseAPDUImpl();
                d.decode(asnInputStream);
                return d;
            }
            if (tag == DialogAPDU._TAG_ABORT) {
                DialogAbortAPDUImpl da = new DialogAbortAPDUImpl();
                da.decode(asnInputStream);
                return da;
            }

            throw new ParseException(PAbortCauseType.BadlyFormattedTxPortion, null, "Wrong tag for APDU, found: " + tag);
        }
    }

    public static DialogRequestAPDU createDialogAPDURequest() {
        return new DialogRequestAPDUImpl();
    }

    public static DialogResponseAPDU createDialogAPDUResponse() {
        return new DialogResponseAPDUImpl();
    }

    public static DialogUniAPDU createDialogAPDUUni() {
        return new DialogUniAPDUImpl();
    }

    public static DialogAbortAPDU createDialogAPDUAbort() {
        return new DialogAbortAPDUImpl();
    }

    public static ProtocolVersion createProtocolVersion() {
        return new ProtocolVersionImpl();
    }

    public static ProtocolVersion createProtocolVersion(AsnInputStream asnInputStream) throws ParseException {
        ProtocolVersionImpl pv = new ProtocolVersionImpl();
        pv.decode(asnInputStream);
        return pv;
    }

    public static ApplicationContextName createApplicationContextName(long[] oid) {
        ApplicationContextNameImpl acn = new ApplicationContextNameImpl();
        acn.setOid(oid);
        return acn;
    }

    public static ApplicationContextName createApplicationContextName(AsnInputStream asnInputStream) throws ParseException {
        ApplicationContextNameImpl acn = new ApplicationContextNameImpl();
        acn.decode(asnInputStream);
        return acn;
    }

    public static UserInformation createUserInformation() {
        return new UserInformationImpl();
    }

    public static UserInformation createUserInformation(AsnInputStream localAsnInputStream) throws ParseException {
        UserInformationImpl ui = new UserInformationImpl();
        ui.decode(localAsnInputStream);
        return ui;
    }

    public static Result createResult() {
        return new ResultImpl();
    }

    public static Result createResult(AsnInputStream localAsnInputStream) throws ParseException {
        ResultImpl ui = new ResultImpl();
        ui.decode(localAsnInputStream);
        return ui;
    }

    public static ResultSourceDiagnostic createResultSourceDiagnostic() {
        return new ResultSourceDiagnosticImpl();
    }

    public static ResultSourceDiagnostic createResultSourceDiagnostic(AsnInputStream localAsnInputStream) throws ParseException {
        ResultSourceDiagnosticImpl ui = new ResultSourceDiagnosticImpl();
        ui.decode(localAsnInputStream);
        return ui;
    }

    public static AbortSource createAbortSource() {
        AbortSourceImpl as = new AbortSourceImpl();
        return as;
    }

    public static AbortSource createAbortSource(AsnInputStream localAsnInputStream) throws ParseException {
        AbortSourceImpl as = new AbortSourceImpl();
        as.decode(localAsnInputStream);
        return as;
    }

    public static TCUniMessage createTCUniMessage(AsnInputStream localAsnInputStream) throws ParseException {
        TCUniMessageImpl tc = new TCUniMessageImpl();
        tc.decode(localAsnInputStream);
        return tc;
    }

    public static TCUniMessage createTCUniMessage() {
        TCUniMessageImpl tc = new TCUniMessageImpl();
        return tc;
    }

    public static TCContinueMessage createTCContinueMessage(AsnInputStream localAsnInputStream) throws ParseException {
        TCContinueMessageImpl tc = new TCContinueMessageImpl();
        tc.decode(localAsnInputStream);
        return tc;
    }

    public static TCContinueMessage createTCContinueMessage() {
        TCContinueMessageImpl tc = new TCContinueMessageImpl();
        return tc;
    }

    public static TCEndMessage createTCEndMessage(AsnInputStream localAsnInputStream) throws ParseException {
        TCEndMessageImpl tc = new TCEndMessageImpl();
        tc.decode(localAsnInputStream);
        return tc;
    }

    public static TCEndMessage createTCEndMessage() {
        TCEndMessageImpl tc = new TCEndMessageImpl();
        return tc;
    }

    public static TCAbortMessage createTCAbortMessage(AsnInputStream localAsnInputStream) throws ParseException {
        TCAbortMessageImpl tc = new TCAbortMessageImpl();
        tc.decode(localAsnInputStream);
        return tc;
    }

    public static TCAbortMessage createTCAbortMessage() {
        TCAbortMessageImpl tc = new TCAbortMessageImpl();
        return tc;
    }

    public static TCBeginMessage createTCBeginMessage(AsnInputStream localAsnInputStream) throws ParseException {
        TCBeginMessageImpl tc = new TCBeginMessageImpl();
        tc.decode(localAsnInputStream);
        return tc;
    }

    public static TCBeginMessage createTCBeginMessage() {
        TCBeginMessageImpl tc = new TCBeginMessageImpl();
        return tc;
    }

    public static OperationCode createOperationCode() {
        OperationCodeImpl oc = new OperationCodeImpl();
        return oc;
    }

    public static OperationCode createOperationCode(int tag, AsnInputStream localAsnInputStream) throws ParseException {
        OperationCodeImpl oc = new OperationCodeImpl();
        oc.setOperationType(OperationCode._TAG_GLOBAL == tag ? OperationCodeType.Global : OperationCodeType.Local);
        oc.decode(localAsnInputStream);
        return oc;
    }

    public static Parameter createParameter() {
        ParameterImpl p = new ParameterImpl();
        return p;
    }

    public static Parameter createParameter(int tag, AsnInputStream localAsnInputStream, boolean singleParameterInAsn)
            throws ParseException {
        ParameterImpl parameter = new ParameterImpl();
        parameter.setTag(tag);
        // p.setPrimitive(localAis.isTagPrimitive());
        // p.setTagClass(localAis.getTagClass());
        if (singleParameterInAsn)
            parameter.setSingleParameterInAsn();
        parameter.decode(localAsnInputStream);
        return parameter;
    }

    public static Component createComponent(AsnInputStream localAsnInputStream) throws ParseException {

        try {
            try {
                int tag = localAsnInputStream.readTag();

                Component component = null;
                if (localAsnInputStream.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC) {
                    throw new ParseException(null, GeneralProblemType.UnrecognizedComponent,
                            "Error decoding a component: bad tag class: " + localAsnInputStream.getTagClass());
                }

                switch (tag) {
                    case Invoke._TAG:
                        component = createComponentInvoke();
                        component.decode(localAsnInputStream);
                        break;
                    case ReturnResult._TAG:
                        component = createComponentReturnResult();
                        component.decode(localAsnInputStream);
                        break;
                    case ReturnResultLast._TAG:
                        component = createComponentReturnResultLast();
                        component.decode(localAsnInputStream);
                        break;
                    case ReturnError._TAG:
                        component = createComponentReturnError();
                        component.decode(localAsnInputStream);
                        break;
                    case Reject._TAG:
                        component = createComponentReject();
                        component.decode(localAsnInputStream);
                        break;
                    default:
                        localAsnInputStream.advanceElement();
                        throw new ParseException(null, GeneralProblemType.UnrecognizedComponent,
                                "Error decoding a component: bad tag: " + tag);
                }

                return component;
            } catch (IOException e) {
                throw new ParseException(null, GeneralProblemType.BadlyStructuredComponent,
                        "IOException while decoding a component: " + e.getMessage(), e);
            } catch (AsnException e) {
                throw new ParseException(null, GeneralProblemType.BadlyStructuredComponent,
                        "AsnException while decoding a component: " + e.getMessage(), e);
            }
        } catch (ParseException e) {
            if (e.getProblem() != null) {
                Reject rej = TcapFactory.createComponentReject();
                rej.setLocalOriginated(true);
                rej.setInvokeId(e.getInvokeId());
                Problem problem = new ProblemImpl();
                problem.setGeneralProblemType(e.getProblem());
                rej.setProblem(problem);
                return rej;
            } else {
                throw e;
            }
        }
    }

    public static Reject createComponentReject() {
        return new RejectImpl();
    }

    public static ReturnResultLast createComponentReturnResultLast() {
        return new ReturnResultLastImpl();
    }

    public static ReturnResult createComponentReturnResult() {
        return new ReturnResultImpl();
    }

    public static Invoke createComponentInvoke() {
        return new InvokeImpl();
    }

    public static Invoke createComponentInvoke(InvokeClass invokeClass) {
        return new InvokeImpl(invokeClass);
    }

    public static ReturnError createComponentReturnError() {
        return new ReturnErrorImpl();
    }

    public static Problem createProblem(ProblemType pt, AsnInputStream asnInputStream) throws ParseException {
        Problem problem = createProblem(pt);
        problem.decode(asnInputStream);
        return problem;
    }

    public static Problem createProblem(ProblemType problemType) {
        Problem problem = new ProblemImpl();
        problem.setType(problemType);
        return problem;
    }

    public static ErrorCode createErrorCode(int tag, AsnInputStream asnInputStream) throws ParseException {
        ErrorCode errorCode = createErrorCode();
        ((ErrorCodeImpl) errorCode).setErrorCodeType(ErrorCode._TAG_GLOBAL == tag ? ErrorCodeType.Global : ErrorCodeType.Local);
        errorCode.decode(asnInputStream);
        return errorCode;
    }

    public static ErrorCode createErrorCode() {
        ErrorCode errorCode = new ErrorCodeImpl();
        return errorCode;
    }
}
