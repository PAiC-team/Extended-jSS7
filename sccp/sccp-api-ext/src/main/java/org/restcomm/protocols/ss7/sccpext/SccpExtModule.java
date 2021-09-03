
package org.restcomm.protocols.ss7.sccpext;

import org.restcomm.protocols.ss7.sccp.impl.Ss7ExtSccpDetailedInterface;
import org.restcomm.protocols.ss7.sccpext.router.RouterExt;

/**
*
* @author sergey vetyutnev
*
*/
public interface SccpExtModule extends Ss7ExtSccpDetailedInterface {

    RouterExt getRouterExt();

}
