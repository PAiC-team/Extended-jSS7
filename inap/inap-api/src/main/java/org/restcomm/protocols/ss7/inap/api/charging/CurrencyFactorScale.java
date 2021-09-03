package org.restcomm.protocols.ss7.inap.api.charging;

import java.io.Serializable;

/**
*
<code>
CurrencyFactorScale ::= SEQUENCE {
  currencyFactor [00] CurrencyFactor DEFAULT noCharge ,
  currencyScale  [01] CurrencyScale DEFAULT noScale
}

CurrencyFactor ::= INTEGER (0..999999)
-- Value 0 indicates "no charge".

CurrencyScale ::= INTEGER (-7..3)
-- The actual value for currency scale is given by 10x, where x is the value of the CurrencyScale.
--
-- the coding of CurrencyScale is as follows, all other values are spare:
-- -7 (249): 0,0000001
-- -6 (250): 0,000001
-- -5 (251): 0,00001
-- -4 (252): 0,0001
-- -3 (253): 0,001
-- -2 (254): 0,01
-- -1 (255): 0,1
-- 0 : 1
-- 1 : 10
-- 2 : 100
-- 3 : 1000

-- The charge amount is indicated by the currency factor multiplied with the currency scale.
-- "no charge" indicates CurrencyFactorScale has the value 0.
</code>
*
* @author sergey vetyutnev
*
*/
public interface CurrencyFactorScale extends Serializable {

    Integer getCurrencyFactor();

    Integer getCurrencyScale();

}
