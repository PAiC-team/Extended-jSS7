
package org.restcomm.protocols.ss7.tcapAnsi.api.asn;

/**
*
* @author sergey vetyutnev
*
*

UserInformation ::= [PRIVATE 29] IMPLICIT SEQUENCE OF EXTERNAL
External Identifier = Tag.EXTERNAL

*/
public interface UserInformation extends Encodable {

    int _TAG_USER_INFORMATION = 29;

    UserInformationElement[] getUserInformationElements();

    void setUserInformationElements(UserInformationElement[] val);

}
