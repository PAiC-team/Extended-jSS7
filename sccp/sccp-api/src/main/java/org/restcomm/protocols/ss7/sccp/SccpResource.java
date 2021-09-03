
package org.restcomm.protocols.ss7.sccp;

import java.util.Map;

/**
 *
 * @author Amit Bhayani
 *
 */
public interface SccpResource {

    /**
     * Add remote sub system number.
     *
     * @param remoteSsnId
     * @param remoteSpc
     * @param remoteSsn
     * @param remoteSsnFlag
     * @param markProhibitedWhenSpcResuming
     * @throws Exception
     */
    void addRemoteSsn(int remoteSsnId, int remoteSpc, int remoteSsn, int remoteSsnFlag,
            boolean markProhibitedWhenSpcResuming) throws Exception;

    void modifyRemoteSsn(int remoteSsnId, int remoteSpc, int remoteSsn, int remoteSsnFlag,
            boolean markProhibitedWhenSpcResuming) throws Exception;

    //void modifyRemoteSsn(int remoteSsnId, Integer remoteSpc, Integer remoteSsn, Integer remoteSsnFlag,
    //        Boolean markProhibitedWhenSpcResuming) throws Exception;

    void removeRemoteSsn(int remoteSsnId) throws Exception;

    RemoteSubSystem getRemoteSsn(int remoteSsnId);

    RemoteSubSystem getRemoteSsn(int spc, int remoteSsn);

    Map<Integer, RemoteSubSystem> getRemoteSsns();

    void addRemoteSpc(int remoteSpcId, int remoteSpc, int remoteSpcFlag, int mask) throws Exception;

    void modifyRemoteSpc(int remoteSpcId, int remoteSpc, int remoteSpcFlag, int mask) throws Exception;

    // void modifyRemoteSpc(int remoteSpcId, Integer remoteSpc, Integer remoteSpcFlag, Integer mask) throws Exception;

    void removeRemoteSpc(int remoteSpcId) throws Exception;

    RemoteSignalingPointCode getRemoteSpc(int remoteSpcId);

    RemoteSignalingPointCode getRemoteSpcByPC(int remotePC);

    Map<Integer, RemoteSignalingPointCode> getRemoteSpcs();

    void addConcernedSpc(int concernedSpcId, int remoteSpc) throws Exception;

    void removeConcernedSpc(int concernedSpcId) throws Exception;

    void modifyConcernedSpc(int concernedSpcId, int remoteSpc) throws Exception;

    ConcernedSignalingPointCode getConcernedSpc(int concernedSpcId);

    ConcernedSignalingPointCode getConcernedSpcByPC(int remotePC);

    Map<Integer, ConcernedSignalingPointCode> getConcernedSpcs();

}
