
package org.restcomm.protocols.ss7.map.api.primitives;

import java.io.Serializable;

/**
 * SignalInfo ::= OCTET STRING (SIZE (1..maxSignalInfoLength)) maxSignalInfoLength INTEGER ::= 200 -- This NamedValue represents
 * the theoretical maximum number of octets which is -- available to carry a single instance of the SignalInfo data type, --
 * without requiring segmentation to cope with the network layer service. -- However, the actual maximum size available for an
 * instance of the data -- type may be lower, especially when other information elements -- have to be included in the same
 * component
 *
 * @author cristian veliscu
 *
 */
public interface SignalInfo extends Serializable {

    byte[] getData();

}