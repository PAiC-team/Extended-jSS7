
package org.restcomm.protocols.ss7.map.api.service.oam;

import java.io.Serializable;

/**
 *
<code>
TraceInterfaceList ::= SEQUENCE {
  msc-s-List      [0] MSC-S-InterfaceList OPTIONAL,
  mgw-List        [1] MGW-InterfaceList OPTIONAL,
  sgsn-List       [2] SGSN-InterfaceList OPTIONAL,
  ggsn-List       [3] GGSN-InterfaceList OPTIONAL,
  rnc-List        [4] RNC-InterfaceList OPTIONAL,
  bmsc-List       [5] BMSC-InterfaceList OPTIONAL,
  ...,
  mme-List        [6] MME-InterfaceList OPTIONAL,
  sgw-List        [7] SGW-InterfaceList OPTIONAL,
  pgw-List        [8] PGW-InterfaceList OPTIONAL,
  eNB-List        [9] ENB-InterfaceList OPTIONAL
}
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface TraceInterfaceList extends Serializable {

    MSCSInterfaceList getMscSList();

    MGWInterfaceList getMgwList();

    SGSNInterfaceList getSgsnList();

    GGSNInterfaceList getGgsnList();

    RNCInterfaceList getRncList();

    BMSCInterfaceList getBmscList();

    MMEInterfaceList getMmeList();

    SGWInterfaceList getSgwList();

    PGWInterfaceList getPgwList();

    ENBInterfaceList getEnbList();

}
