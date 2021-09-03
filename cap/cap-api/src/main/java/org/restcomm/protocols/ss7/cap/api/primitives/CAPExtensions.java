
package org.restcomm.protocols.ss7.cap.api.primitives;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
<code>
Extensions {PARAMETERS-BOUND : bound} ::= SEQUENCE SIZE (1..bound.&numOfExtensions) OF ExtensionField
numOfExtensions ::= 10
</code>
 *
 * @author sergey vetyutnev
 *
 */
public interface CAPExtensions extends Serializable {

    ArrayList<ExtensionField> getExtensionFields();

    void setExtensionFields(ArrayList<ExtensionField> fieldsList);

}
