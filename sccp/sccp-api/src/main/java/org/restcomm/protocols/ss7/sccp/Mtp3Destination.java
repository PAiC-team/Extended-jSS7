
package org.restcomm.protocols.ss7.sccp;

/**
 *
 * @author Amit Bhayani
 *
 */
public interface Mtp3Destination {

    int getFirstDpc();

    int getLastDpc();

    int getFirstSls();

    int getLastSls();

    int getSlsMask();

    boolean match(int dpc, int sls);

    boolean match(int dpc);
}
