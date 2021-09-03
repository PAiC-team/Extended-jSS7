
package org.restcomm.protocols.ss7.tools.simulator.level3;

import org.restcomm.protocols.ss7.tools.simulator.common.AddressNatureType;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface MapManMBean {

    // int getLocalSsn();
    //
    // void setLocalSsn(int val);
    //
    // int getRemoteSsn();
    //
    // void setRemoteSsn(int val);

    String getRemoteAddressDigits();

    void setRemoteAddressDigits(String val);

    String getOrigReference();

    void setOrigReference(String val);

    AddressNatureType getOrigReferenceAddressNature();

    String getOrigReferenceAddressNature_Value();

    void setOrigReferenceAddressNature(AddressNatureType val);

    NumberingPlanMapType getOrigReferenceNumberingPlan();

    String getOrigReferenceNumberingPlan_Value();

    void setOrigReferenceNumberingPlan(NumberingPlanMapType val);

    String getDestReference();

    void setDestReference(String val);

    AddressNatureType getDestReferenceAddressNature();

    String getDestReferenceAddressNature_Value();

    void setDestReferenceAddressNature(AddressNatureType val);

    NumberingPlanMapType getDestReferenceNumberingPlan();

    String getDestReferenceNumberingPlan_Value();

    void setDestReferenceNumberingPlan(NumberingPlanMapType val);

    void putOrigReferenceAddressNature(String val);

    void putOrigReferenceNumberingPlan(String val);

    void putDestReferenceAddressNature(String val);

    void putDestReferenceNumberingPlan(String val);

}
