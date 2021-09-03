
package org.restcomm.protocols.ss7.tcapAnsi.api.asn;

/**
 * @author baranowb
 * @author sergey vetyutnev

ApplicationContext  CHOICE {
        integerApplicationId    IntegerApplicationContext,
        objectApplicationId ObjectIDApplicationContext
 } OPTIONAL,

IntegerApplicationContext ::= [PRIVATE 27] IMPLICIT INTEGER

ObjectIDApplicationContext ::= [PRIVATE 28] IMPLICIT OBJECT IDENTIFIER

 *
 */
public interface ApplicationContext extends Encodable {

    // its type of OID

    int _TAG_INTEGER = 27;
    int _TAG_OBJECT_ID = 28;

    boolean isInteger();

    boolean isObjectID();

    long[] getOid();

    void setOid(long[] val);

    long getInteger();

    void setInteger(long val);

}
