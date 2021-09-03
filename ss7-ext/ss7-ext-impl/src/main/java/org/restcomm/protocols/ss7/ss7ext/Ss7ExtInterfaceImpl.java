
package org.restcomm.protocols.ss7.ss7ext;

import org.apache.log4j.Logger;

/**
*
* @author sergey vetyutnev
*
*/
public class Ss7ExtInterfaceImpl implements Ss7ExtInterface {

    protected final Logger logger = Logger.getLogger(Ss7ExtInterfaceImpl.class);
    private Ss7ExtSccpInterface ss7ExtSccpInterface;

    @Override
    public void startMtpSs7Ext(String productName) {
    }

    @Override
    public Ss7ExtSccpInterface getSs7ExtSccpInterface() {
        return ss7ExtSccpInterface;
    }

    @Override
    public void setSs7ExtSccpInterface(Ss7ExtSccpInterface ss7ExtSccpInterface) {
        this.ss7ExtSccpInterface = ss7ExtSccpInterface;
    }

}
