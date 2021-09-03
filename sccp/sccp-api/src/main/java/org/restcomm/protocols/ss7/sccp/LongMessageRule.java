
package org.restcomm.protocols.ss7.sccp;

/**
 *
 * @author Amit Bhayani
 *
 */
public interface LongMessageRule {

    LongMessageRuleType getLongMessageRuleType();

    int getFirstSpc();

    int getLastSpc();

    boolean matches(int dpc);

}
