package org.restcomm.protocols.ss7.inap.api.primitives;

import java.io.Serializable;
import java.util.ArrayList;

/**
*
<code>
extensions :==  SEQUENCE SIZE (1..bound.&numOfExtensions) OF ExtensionField {bound} OPTIONAL,
</code>

*
* @author sergey vetyutnev
*
*/
public interface INAPExtensions extends Serializable {

    ArrayList<ExtensionField> getExtensionFields();

    void setExtensionFields(ArrayList<ExtensionField> fieldsList);

}
