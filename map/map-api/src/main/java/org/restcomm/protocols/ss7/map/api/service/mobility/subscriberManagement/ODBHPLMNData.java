
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;

/**
 *
 ODB-HPLMN-Data ::= BIT STRING { plmn-SpecificBarringType1 (0), plmn-SpecificBarringType2 (1), plmn-SpecificBarringType3 (2),
 * plmn-SpecificBarringType4 (3)} (SIZE (4..32)) -- exception handling: reception of unknown bit assignments in the --
 * ODB-HPLMN-Data type shall be treated like unsupported ODB-HPLMN-Data -- When the ODB-HPLMN-Data type is removed from the HLR
 * for a given subscriber, -- in NoteSubscriberDataModified operation sent toward the gsmSCF -- all bits shall be set to O.
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ODBHPLMNData extends Serializable {

    boolean getPlmnSpecificBarringType1();

    boolean getPlmnSpecificBarringType2();

    boolean getPlmnSpecificBarringType3();

    boolean getPlmnSpecificBarringType4();

}
