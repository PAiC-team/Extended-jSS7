package org.restcomm.protocols.ss7.cap;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.restcomm.protocols.ss7.cap.api.CAPApplicationContext;
import org.restcomm.protocols.ss7.cap.api.CAPDialog;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentException;
import org.restcomm.protocols.ss7.cap.api.CAPProvider;
import org.restcomm.protocols.ss7.cap.api.CAPServiceBase;
import org.restcomm.protocols.ss7.cap.api.CAPServiceListener;
import org.restcomm.protocols.ss7.cap.api.errors.CAPErrorMessage;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.tcap.api.TCAPException;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog;
import org.restcomm.protocols.ss7.tcap.asn.comp.ComponentType;
import org.restcomm.protocols.ss7.tcap.asn.comp.Invoke;
import org.restcomm.protocols.ss7.tcap.asn.comp.OperationCode;
import org.restcomm.protocols.ss7.tcap.asn.comp.Parameter;
import org.restcomm.protocols.ss7.tcap.asn.comp.Problem;

/**
 * This class must be the super class of all CAP services
 *
 * @author sergey vetyutnev
 *
 */
public abstract class CAPServiceBaseImpl implements CAPServiceBase {

    protected Boolean _isActivated = false;
    protected List<CAPServiceListener> serviceListeners = new CopyOnWriteArrayList<CAPServiceListener>();
    protected CAPProviderImpl capProviderImpl;

    protected CAPServiceBaseImpl(CAPProviderImpl capProviderImpl) {
        this.capProviderImpl = capProviderImpl;
    }

    @Override
    public CAPProvider getCAPProvider() {
        return this.capProviderImpl;
    }

    /**
     * Creation a CAP Dialog implementation for the specific service
     *
     * @param capApplicationContext
     * @param tcapDialog
     * @return CAPDialogImpl
     */
    protected abstract CAPDialogImpl createNewDialogIncoming(CAPApplicationContext capApplicationContext, Dialog tcapDialog);

    /**
     * Creating new outgoing TCAP Dialog. Used when creating a new outgoing CAP Dialog
     *
     * @param sccpCallingPartyAddress
     * @param sccpCalledPartyAddress
     * @param localTransactionId
     * @return Dialog
     * @throws CAPException
     */
    protected Dialog createNewTCAPDialog(SccpAddress sccpCallingPartyAddress, SccpAddress sccpCalledPartyAddress, Long localTransactionId) throws CAPException {
        try {
            return this.capProviderImpl.getTCAPProvider().getNewDialog(sccpCallingPartyAddress, sccpCalledPartyAddress, localTransactionId);
        } catch (TCAPException e) {
            throw new CAPException(e.getMessage(), e);
        }
    }

    public abstract void processComponent(ComponentType compType, OperationCode oc, Parameter parameter, CAPDialog capDialog,
            Long invokeId, Long linkedId, Invoke linkedInvoke) throws CAPParsingComponentException;

    /**
     * Returns a list of linked operations for operationCode operation
     *
     * @param operationCode
     * @return
     */
    public long[] getLinkedOperationList(long operationCode) {
        return null;
    }

    /**
     * Adding CAP Dialog into CAPProviderImpl.dialogs Used when creating a new outgoing CAP Dialog
     *
     * @param capDialog
     */
    protected void putCAPDialogIntoCollection(CAPDialogImpl capDialog) {
        this.capProviderImpl.addDialog((CAPDialogImpl) capDialog);
    }

    protected void addCAPServiceListener(CAPServiceListener capServiceListener) {
        this.serviceListeners.add(capServiceListener);
    }

    protected void removeCAPServiceListener(CAPServiceListener capServiceListener) {
        this.serviceListeners.remove(capServiceListener);
    }

    /**
     * This method is invoked when CAPProviderImpl.onInvokeTimeOut() is invoked. An InvokeTimeOut may be a normal situation for
     * the component class 2, 3, or 4. In this case checkInvokeTimeOut() should return true and deliver to the CAP-user correct
     * indication
     *
     * @param dialog
     * @param invoke
     * @return
     */
    public boolean checkInvokeTimeOut(CAPDialog dialog, Invoke invoke) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isActivated() {
        return this._isActivated;
    }

    /**
     * {@inheritDoc}
     */
    public void activate() {
        this._isActivated = true;
    }

    /**
     * {@inheritDoc}
     */
    public void deactivate() {
        this._isActivated = false;

        // TODO: abort all active dialogs ?
    }

    protected void deliverErrorComponent(CAPDialog capDialog, Long invokeId, CAPErrorMessage capErrorMessage) {
        for (CAPServiceListener serLis : this.serviceListeners) {
            serLis.onErrorComponent(capDialog, invokeId, capErrorMessage);
        }
    }

    protected void deliverRejectComponent(CAPDialog capDialog, Long invokeId, Problem problem, boolean isLocalOriginated) {
        for (CAPServiceListener serLis : this.serviceListeners) {
            serLis.onRejectComponent(capDialog, invokeId, problem, isLocalOriginated);
        }
    }

    protected void deliverInvokeTimeout(CAPDialog capDialog, Invoke invoke) {
        for (CAPServiceListener serLis : this.serviceListeners) {
            serLis.onInvokeTimeout(capDialog, invoke.getInvokeId());
        }
    }
}
