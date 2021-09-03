
package org.restcomm.protocols.ss7.sccp;

import java.util.Map;

/**
 *
 * @author Amit Bhayani
 *
 */
public interface Mtp3ServiceAccessPoint {

    int getMtp3Id();

    int getOpc();

    int getNi();

    int getNetworkId();

    String getLocalGtDigits();

    Mtp3Destination getMtp3Destination(int destId);

    Map<Integer, Mtp3Destination> getMtp3Destinations();

    void addMtp3Destination(int destId, int firstDpc, int lastDpc, int firstSls, int lastSls, int slsMask)
            throws Exception;

    void modifyMtp3Destination(int destId, int firstDpc, int lastDpc, int firstSls, int lastSls, int slsMask)
            throws Exception;

    void removeMtp3Destination(int destId) throws Exception;

    boolean matches(int dpc, int sls);

    boolean matches(int dpc);

}
