package org.restcomm.protocols.ss7.tools.simulator.tests.ati;

/**
*
* @author sergey vetyutnev
*
*/
public interface TestAtiServerManMBean {

    ATIReaction getATIReaction();

    String getATIReaction_Value();

    void setATIReaction(ATIReaction val);

    void putATIReaction(String val);

    String getCurrentRequestDef();

}
