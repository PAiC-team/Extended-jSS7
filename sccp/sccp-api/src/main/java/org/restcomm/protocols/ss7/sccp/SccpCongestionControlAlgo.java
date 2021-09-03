
package org.restcomm.protocols.ss7.sccp;

/**
*
* @author sergey vetyutnev
*
*/
public enum SccpCongestionControlAlgo {

    // international algorithm - only one level is provided by MTP3 level (in MTP-STATUS primitive). Each MTP-STATUS
    // increases N / M levels
    international,
    // MTP3 level (MTP-STATUS primitive) provides 3 levels of a congestion (1-3) and SCCP congestion will increase to the
    // next level after MTP-STATUS next level increase (MTP-STATUS 1 to N up to 3, MTP-STATUS 2 to N up to 5, MTP-STATUS 3
    // to N up to 7)
    levelDepended,

}
