
package org.restcomm.protocols.ss7.ss7ext;

/**
*
* @author sergey vetyutnev
*
*/
public class Ss7ExtInterfaceDefault implements Ss7ExtInterface {

    @Override
    public void startMtpSs7Ext(String productName) {
    }

    @Override
    public Ss7ExtSccpInterface getSs7ExtSccpInterface() {
        return null;
    }

    @Override
    public void setSs7ExtSccpInterface(Ss7ExtSccpInterface val) {
    }

}
