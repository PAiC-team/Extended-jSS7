
/**
 *
 */

package org.restcomm.protocols.ss7.tcapAnsi.api.asn.comp;

/**
 * Super interface for Returns.
 *
 * @author baranowb
 * @author amit bhayani
 *
 */
public interface Return extends Component {

    void setParameter(Parameter p);

    Parameter getParameter();

    void setOperationCode(OperationCode i);

    OperationCode getOperationCode();

}
