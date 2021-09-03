
package org.restcomm.protocols.ss7.ss7ext;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface Ss7ExtInterface {

    void startMtpSs7Ext(String productName);

    Ss7ExtSccpInterface getSs7ExtSccpInterface();

    void setSs7ExtSccpInterface(Ss7ExtSccpInterface val);

}
