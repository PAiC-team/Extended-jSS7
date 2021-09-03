
package org.restcomm.protocols.ss7.map.api.service.supplementary;

import java.io.Serializable;

/**
 *
<code>
Password ::= NumericString (FROM ("0"|"1"|"2"|"3"|"4"|"5"|"6"|"7"|"8"|"9")) (SIZE (4))
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface Password extends Serializable {

    String getData();

}