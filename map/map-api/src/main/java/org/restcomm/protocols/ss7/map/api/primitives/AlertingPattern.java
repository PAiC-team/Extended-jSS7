
package org.restcomm.protocols.ss7.map.api.primitives;

import java.io.Serializable;

/**
 * AlertingPattern ::= OCTET STRING (SIZE (1) ) -- This type is used to represent Alerting Pattern -- bits 8765 : 0000 (unused)
 * -- bits 43 : type of Pattern -- 00 level -- 01 category -- 10 category -- all other values are reserved. -- bits 21 : type of
 * alerting alertingLevel-0 AlertingPattern ::= '00000000'B alertingLevel-1 AlertingPattern ::= '00000001'B alertingLevel-2
 * AlertingPattern ::= '00000010'B -- all other values of Alerting level are reserved -- Alerting Levels are defined in GSM
 * 02.07 alertingCategory-1 AlertingPattern ::= '00000100'B alertingCategory-2 AlertingPattern ::= '00000101'B
 * alertingCategory-3 AlertingPattern ::= '00000110'B alertingCategory-4 AlertingPattern ::= '00000111'B alertingCategory-5
 * AlertingPattern ::= '00001000'B -- all other values of Alerting Category are reserved -- Alerting categories are defined in
 * GSM 02.07
 *
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public interface AlertingPattern extends Serializable {

    int getData();

    AlertingLevel getAlertingLevel();

    AlertingCategory getAlertingCategory();

}
