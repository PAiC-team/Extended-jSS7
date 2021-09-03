
package org.restcomm.protocols.ss7.tools.simulator.level1;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface DialogicManMBean {

    int getSourceModuleId();

    void setSourceModuleId(int val);

    int getDestinationModuleId();

    void setDestinationModuleId(int val);

}
