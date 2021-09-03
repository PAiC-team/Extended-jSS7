package org.restcomm.protocols.ss7.mtp;

/**
 * @author sergey vetyutnev
 *
 */
public interface Mtp3UserPartListener {

    void onMtp3TransferMessage(Mtp3TransferPrimitive msg);

    void onMtp3PauseMessage(Mtp3PausePrimitive msg);

    void onMtp3ResumeMessage(Mtp3ResumePrimitive msg);

    void onMtp3StatusMessage(Mtp3StatusPrimitive msg);

    void onMtp3EndCongestionMessage(Mtp3EndCongestionPrimitive msg);

}
