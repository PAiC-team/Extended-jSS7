package org.restcomm.protocols.ss7.inap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;
import java.util.ArrayList;

import org.restcomm.protocols.ss7.inap.api.isup.GenericNumberInap;

/**
*
<code>
GenericNumbers {PARAMETERS-BOUND : bound} ::= SET SIZE(1..bound.&numOfGenericNumbers) OF
    GenericNumber {bound}
</code>
*
* @author sergey vetyutnev
*
*/
public interface GenericNumbers extends Serializable {

    ArrayList<GenericNumberInap> getGenericNumbers();

}
