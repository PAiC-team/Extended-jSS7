
package org.restcomm.protocols.ss7.map.api.service.supplementary;

/**
 *
 RESULT ss-UserData -- optional
 *
 * SS-UserData ::= IA5String (SIZE (1.. 200))
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ProcessUnstructuredSSDataResponse extends SupplementaryMessage {

    String getData();

}
