
package org.restcomm.protocols.ss7.cap.api.service.gprs.primitive;

import java.io.Serializable;

/**
 *
<code>
SGSNCapabilities ::= OCTET STRING (SIZE (1))
-- Indicates the SGSN capabilities. The coding of the parameter is as follows:
-- Bit Value Meaning
-- 0 0 AoC not supported by SGSN
--   1 AoC supported by SGSN
-- 1 - This bit is reserved in CAP V.3
-- 2 - This bit is reserved in CAP V.3
-- 3 - This bit is reserved in CAP V.3
-- 4 - This bit is reserved in CAP V.3
-- 5 - This bit is reserved in CAP V.3
-- 6 - This bit is reserved in CAP V.3
-- 7 - This bit is reserved in CAP V.3
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface SGSNCapabilities extends Serializable {

    int getData();

    boolean getAoCSupportedBySGSN();

}
