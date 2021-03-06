
package org.restcomm.protocols.ss7.oam.common.tcap;

import static org.testng.Assert.*;

import org.restcomm.protocols.ss7.oam.common.statistics.api.CounterCampaign;
import org.restcomm.protocols.ss7.oam.common.statistics.api.CounterMediator;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class ConfigurationTest {

    @BeforeMethod
    public void setUp() throws Exception {
        MBeanHostProxy mBeanHostProxy = new MBeanHostProxy();
        CounterProviderProxy counterProvider = new CounterProviderProxy(mBeanHostProxy);
        counterProvider.setName("Test");
        counterProvider.start();

        counterProvider.getCounterCampaignMap().clear();
        counterProvider.store();

        counterProvider.stop();
    }

    @Test(groups = { "confuguration" })
    public void storingCampaignsTest() throws Exception {

        MBeanHostProxy mBeanHostProxy = new MBeanHostProxy();
        CounterProviderProxy counterProvider = new CounterProviderProxy(mBeanHostProxy);
        counterProvider.setName("Test");
        counterProvider.start();

        CounterMediator cm = new CounterMediatorProxy();
        counterProvider.registerCounterMediator(cm);

        counterProvider.createCampaign("camp1", "counterSet1", 5, 1);
        counterProvider.createCampaign("camp2", "counterSet2", 60, 1);
        try {
            counterProvider.createCampaign("camp1", "counterSet3", 60, 1);
            fail("Same campaignName must bring Exception");
        } catch (Exception e) {
        }
        try {
            counterProvider.createCampaign("camp3", "counterSet1", 59, 1);
            fail("Wrong duration must bring Exception");
        } catch (Exception e) {
        }

        assertEquals(counterProvider.getCounterCampaignMap().size(), 2);

        counterProvider.stop();


        counterProvider = new CounterProviderProxy(mBeanHostProxy);
        counterProvider.setName("Test");
        counterProvider.start();

        assertEquals(counterProvider.getCampaignsList().length, 2);

        CounterCampaign camp = counterProvider.getCampaign("camp1");
        assertEquals(camp.getName(), "camp1");
        assertEquals(camp.getCounterSetName(), "counterSet1");
        assertEquals(camp.getDuration(), 5);

        camp = counterProvider.getCampaign("camp2");
        assertEquals(camp.getName(), "camp2");
        assertEquals(camp.getCounterSetName(), "counterSet2");
        assertEquals(camp.getDuration(), 60);

        counterProvider.destroyCampaign("camp1");
        assertEquals(counterProvider.getCampaignsList().length, 1);

        counterProvider.stop();
    }

}
