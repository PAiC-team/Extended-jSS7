
package org.restcomm.protocols.ss7.sccp.impl;

import org.restcomm.protocols.ss7.sccp.impl.SccpHarness3;
import org.restcomm.protocols.ss7.sccpext.impl.SccpExtModuleImpl;
import org.restcomm.protocols.ss7.sccpext.router.RouterExt;
import org.restcomm.protocols.ss7.ss7ext.Ss7ExtInterface;
import org.restcomm.protocols.ss7.ss7ext.Ss7ExtInterfaceImpl;

/**
 * @author sergey vetyutnev
 *
 */
public class SccpHarness3Ext extends SccpHarness3 {

    protected SccpExtModuleImpl sccpExtModule1 = null;
    protected SccpExtModuleImpl sccpExtModule2 = null;
    protected SccpExtModuleImpl sccpExtModule3 = null;
    protected RouterExt routerExt1 = null;
    protected RouterExt routerExt2 = null;
    protected RouterExt routerExt3 = null;

    protected void createStack1() {
        Ss7ExtInterface ss7ExtInterface = new Ss7ExtInterfaceImpl();
        sccpExtModule1 = new SccpExtModuleImpl();
        ss7ExtInterface.setSs7ExtSccpInterface(sccpExtModule1);
        sccpStack1 = createStack(sccpStack1Name, ss7ExtInterface);
        sccpExtModule1.init(sccpStack1);
    }

    protected void createStack2() {
        Ss7ExtInterface ss7ExtInterface = new Ss7ExtInterfaceImpl();
        sccpExtModule2 = new SccpExtModuleImpl();
        ss7ExtInterface.setSs7ExtSccpInterface(sccpExtModule2);
        sccpStack2 = createStack(sccpStack2Name, ss7ExtInterface);
        sccpExtModule2.init(sccpStack2);
    }

    protected void createStack3() {
        Ss7ExtInterface ss7ExtInterface = new Ss7ExtInterfaceImpl();
        sccpExtModule3 = new SccpExtModuleImpl();
        ss7ExtInterface.setSs7ExtSccpInterface(sccpExtModule3);
        sccpStack3 = createStack(sccpStack1Name, ss7ExtInterface);
        sccpExtModule3.init(sccpStack1);
    }

    protected void setUpStack1() throws Exception {
        super.setUpStack1();

        routerExt1 = sccpExtModule1.getRouterExt();
    }

    protected void setUpStack2() throws Exception {
        super.setUpStack2();

        routerExt2 = sccpExtModule2.getRouterExt();
    }

    protected void setUpStack3() throws Exception {
        super.setUpStack3();

        routerExt3 = sccpExtModule3.getRouterExt();
    }

    public void setReferenceNumberCounterStack1WithoutChecking(int val) {
        if (sccpStack1 != null)
            sccpStack1.referenceNumberCounter = val;
    }

    public void setReferenceNumberCounterStack2WithoutChecking(int val) {
        if (sccpStack2 != null)
            sccpStack2.referenceNumberCounter = val;
    }

    public void setReferenceNumberCounterStack3WithoutChecking(int val) {
        if (sccpStack3 != null)
            sccpStack3.referenceNumberCounter = val;
    }

    public void setIasTimerDelayStack1WithoutChecking(int val) {
        if (sccpStack1 != null)
            sccpStack1.iasTimerDelay = val;
    }

    public void setIasTimerDelayStack2WithoutChecking(int val) {
        if (sccpStack2 != null)
            sccpStack2.iasTimerDelay = val;
    }

    public void setIasTimerDelayStack3WithoutChecking(int val) {
        if (sccpStack3 != null)
            sccpStack3.iasTimerDelay = val;
    }

    public void setIarTimerDelayStack1WithoutChecking(int val) {
        if (sccpStack1 != null)
            sccpStack1.iarTimerDelay = val;
    }

    public void setIarTimerDelayStack2WithoutChecking(int val) {
        if (sccpStack2 != null)
            sccpStack2.iarTimerDelay = val;
    }

    public void setIarTimerDelayStack3WithoutChecking(int val) {
        if (sccpStack3 != null)
            sccpStack3.iarTimerDelay = val;
    }

    public void setRelTimerDelayStack1WithoutChecking(int val) {
        if (sccpStack1 != null)
            sccpStack1.relTimerDelay = val;
    }

    public void setRelTimerDelayStack2WithoutChecking(int val) {
        if (sccpStack2 != null)
            sccpStack2.relTimerDelay = val;
    }

    public void setRelTimerDelayStack3WithoutChecking(int val) {
        if (sccpStack3 != null)
            sccpStack3.relTimerDelay = val;
    }

    public void setRepeatRelTimerDelayStack1WithoutChecking(int val) {
        if (sccpStack1 != null)
            sccpStack1.repeatRelTimerDelay = val;
    }

    public void setRepeatRelTimerDelayStack2WithoutChecking(int val) {
        if (sccpStack2 != null)
            sccpStack2.repeatRelTimerDelay = val;
    }

    public void setRepeatRelTimerDelayStack3WithoutChecking(int val) {
        if (sccpStack3 != null)
            sccpStack3.repeatRelTimerDelay = val;
    }

    public void setIntTimerDelayStack1WithoutChecking(int val) {
        if (sccpStack1 != null)
            sccpStack1.intTimerDelay = val;
    }

    public void setIntTimerDelayStack2WithoutChecking(int val) {
        if (sccpStack2 != null)
            sccpStack2.intTimerDelay = val;
    }

    public void setIntTimerDelayStack3WithoutChecking(int val) {
        if (sccpStack3 != null)
            sccpStack3.intTimerDelay = val;
    }

    public void setSstTimerDuration_MinStack1WithoutChecking(int val) {
        if (sccpStack1 != null)
            sccpStack1.sstTimerDuration_Min = val;
    }

    public void setSstTimerDuration_MinStack2WithoutChecking(int val) {
        if (sccpStack2 != null)
            sccpStack2.sstTimerDuration_Min = val;
    }

    public void setSstTimerDuration_MinStack3WithoutChecking(int val) {
        if (sccpStack3 != null)
            sccpStack3.sstTimerDuration_Min = val;
    }

    public void setConnEstTimerDelayStack1WithoutChecking(int val) {
        if (sccpStack1 != null)
            sccpStack1.connEstTimerDelay = val;
    }

    public void setConnEstTimerDelayStack2WithoutChecking(int val) {
        if (sccpStack2 != null)
            sccpStack2.connEstTimerDelay = val;
    }

    public void setConnEstTimerDelayStack3WithoutChecking(int val) {
        if (sccpStack3 != null)
            sccpStack3.connEstTimerDelay = val;
    }

}
