
package org.restcomm.protocols.ss7.map.api.service.supplementary;

/**
 *
 MAP V1: ProcessUnstructuredSS-Data ::= ARGUMENT ss-UserData RESULT ss-UserData -- optional ERRORS { SystemFailure,
 * UnexpectedDataValue}
 *
 * SS-UserData ::= IA5String (SIZE (1.. 200))
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ProcessUnstructuredSSDataRequest extends SupplementaryMessage {

    String getData();

}