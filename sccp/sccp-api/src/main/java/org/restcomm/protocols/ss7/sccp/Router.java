
package org.restcomm.protocols.ss7.sccp;

import java.util.Map;

/**
 *
 * @author Amit Bhayani
 *
 */
public interface Router {

    void addMtp3ServiceAccessPoint(int id, int mtp3Id, int opc, int ni, int networkId, String localGtDigits) throws Exception;

    void modifyMtp3ServiceAccessPoint(int id, int mtp3Id, int opc, int ni, int networkId, String localGtDigits) throws Exception;

    // void modifyMtp3ServiceAccessPoint(int id, Integer mtp3Id, Integer opc, Integer ni, Integer networkId, String localGtDigits) throws Exception;

    void removeMtp3ServiceAccessPoint(int id) throws Exception;

    Mtp3ServiceAccessPoint getMtp3ServiceAccessPoint(int id);

    Map<Integer, Mtp3ServiceAccessPoint> getMtp3ServiceAccessPoints();

    void addMtp3Destination(int sapId, int destId, int firstDpc, int lastDpc, int firstSls, int lastSls, int slsMask)
            throws Exception;

    void modifyMtp3Destination(int sapId, int destId, int firstDpc, int lastDpc, int firstSls, int lastSls, int slsMask)
            throws Exception;
//    void modifyMtp3Destination(int sapId, int destId, Integer firstDpc, Integer lastDpc, Integer firstSls, Integer lastSls, Integer slsMask)
//            throws Exception;

    void removeMtp3Destination(int sapId, int destId) throws Exception;

    void addLongMessageRule(int id, int firstSpc, int lastSpc, LongMessageRuleType longMessageRuleType) throws Exception;

    void modifyLongMessageRule(int id, int firstSpc, int lastSpc, LongMessageRuleType longMessageRuleType) throws Exception;

    void modifyLongMessageRule(int id, Integer firstSpc, Integer lastSpc, LongMessageRuleType longMessageRuleType) throws Exception;

    void removeLongMessageRule(int id) throws Exception;

    LongMessageRule getLongMessageRule(int id);

    Map<Integer, LongMessageRule> getLongMessageRules();

    boolean spcIsLocal(int spc);

}
