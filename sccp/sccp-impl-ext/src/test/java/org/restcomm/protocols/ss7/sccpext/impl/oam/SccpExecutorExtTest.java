
package org.restcomm.protocols.ss7.sccpext.impl.oam;

import javolution.util.FastMap;

import org.restcomm.protocols.ss7.Util;
import org.restcomm.protocols.ss7.indicator.GlobalTitleIndicator;
import org.restcomm.protocols.ss7.indicator.NatureOfAddress;
import org.restcomm.protocols.ss7.indicator.NumberingPlan;
import org.restcomm.protocols.ss7.indicator.RoutingIndicator;
import org.restcomm.protocols.ss7.mtp.Mtp3TransferPrimitive;
import org.restcomm.protocols.ss7.mtp.Mtp3TransferPrimitiveFactory;
import org.restcomm.protocols.ss7.mtp.Mtp3UserPart;
import org.restcomm.protocols.ss7.mtp.Mtp3UserPartListener;
import org.restcomm.protocols.ss7.mtp.RoutingLabelFormat;
import org.restcomm.protocols.ss7.sccp.LoadSharingAlgorithm;
import org.restcomm.protocols.ss7.sccp.OriginationType;
import org.restcomm.protocols.ss7.sccp.Router;
import org.restcomm.protocols.ss7.sccp.Rule;
import org.restcomm.protocols.ss7.sccp.RuleType;
import org.restcomm.protocols.ss7.sccp.SccpProtocolVersion;
import org.restcomm.protocols.ss7.sccp.SccpResource;
import org.restcomm.protocols.ss7.sccp.impl.SccpStackImpl;
import org.restcomm.protocols.ss7.sccp.impl.oam.SccpOAMMessage;
import org.restcomm.protocols.ss7.sccp.parameter.GlobalTitle0100;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.sccpext.impl.SccpExtModuleImpl;
import org.restcomm.protocols.ss7.sccpext.impl.oam.SccpExecutorExt;
import org.restcomm.protocols.ss7.sccpext.router.RouterExt;
import org.restcomm.protocols.ss7.ss7ext.Ss7ExtInterface;
import org.restcomm.protocols.ss7.ss7ext.Ss7ExtInterfaceImpl;
import org.restcomm.ss7.congestion.ExecutorCongestionMonitor;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 *
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public class SccpExecutorExtTest {

    private Router router = null;
    private RouterExt routerExt = null;
    private SccpResource sccpResource = null;
    private SccpStackImpl sccpStack = null;

    private SccpExecutorExt sccpExecutorExt = null;

    /**
	 *
	 */
    public SccpExecutorExtTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUp() throws IllegalStateException {
        Mtp3UserPartImpl mtp3UserPartImpl = new Mtp3UserPartImpl();

        Ss7ExtInterface ss7ExtInterface = new Ss7ExtInterfaceImpl();
        SccpExtModuleImpl sccpExtModule = new SccpExtModuleImpl();
        ss7ExtInterface.setSs7ExtSccpInterface(sccpExtModule);
        this.sccpStack = new SccpStackImpl("SccpExecutorTest", ss7ExtInterface);
        this.sccpStack.setPersistDir(Util.getTmpTestDir());
        this.sccpStack.setMtp3UserPart(1, mtp3UserPartImpl);
        this.sccpStack.start();
        this.sccpStack.removeAllResources();
        this.router = this.sccpStack.getRouter();
        this.routerExt = sccpExtModule.getRouterExt();
        this.sccpResource = this.sccpStack.getSccpResource();

        sccpExecutorExt = new SccpExecutorExt();
        
        FastMap<String, SccpStackImpl> sccpStacks = new FastMap<String, SccpStackImpl>();
        sccpStacks.put(this.sccpStack.getName(), this.sccpStack);
        
        sccpExecutorExt.setSccpStacks(sccpStacks);
    }

    @AfterMethod
    public void tearDown() {
        this.sccpStack.stop();
    }

    @Test(groups = { "oam", "functional.mgmt" })
    public void testManageRule() {

        String addressCmd = "sccp rule modify 4 K 18 0 146 0 1 4 * loadshared 2 backup-addressid 3 loadsharing-algo bit0";
        String result = this.sccpExecutorExt.execute(addressCmd.split(" "));

        addressCmd = "sccp address create 1 71 2 8 0 0 3 123456789";
        result = this.sccpExecutorExt.execute(addressCmd.split(" "));
        assertEquals(result, String.format(SccpOAMMessage.ADDRESS_SUCCESSFULLY_ADDED, this.sccpStack.getName()));
        assertEquals(this.routerExt.getRoutingAddresses().size(), 1);

        String createRuleCmd = "sccp rule create 1 R 71 2 8 0 0 3 123456789 solitary 1";

        result = this.sccpExecutorExt.execute(createRuleCmd.split(" "));
        assertEquals(result, String.format(SccpOAMMessage.RULE_SUCCESSFULLY_ADDED, this.sccpStack.getName()));
        assertEquals(this.routerExt.getRules().size(), 1);
        assertEquals(this.routerExt.getRules().get(1).getPrimaryAddressId(), 1);

        // test show
        createRuleCmd = "sccp rule show";
        result = this.sccpExecutorExt.execute(createRuleCmd.split(" "));

        assertEquals(
                result,
                "key=1  Rule=ruleId(1);ruleType(Solitary);originatingType(All);patternSccpAddress(pc=2,ssn=8,AI=71,gt=GlobalTitle0001Impl [digits=123456789, natureOfAddress=NATIONAL, encodingScheme=BCDOddEncodingScheme[type=BCD_ODD, code=1]]);paddress(1);saddress(-1);mask(R);networkId(0)\n");

        // TODO: this update for fixing "sccp rule create needs not zero pc"
//        createRuleCmd = "sccp rule create 2 K 18 0 180 0 1 4 * solitary 1";
        createRuleCmd = "sccp rule create 2 K 18 1 180 0 1 4 * solitary 1";
        result = this.sccpExecutorExt.execute(createRuleCmd.split(" "));
        assertEquals(result, String.format(SccpOAMMessage.RULE_SUCCESSFULLY_ADDED, this.sccpStack.getName()));

        assertEquals(this.routerExt.getRules().size(), 2);

        Rule rule = this.routerExt.getRules().get(2);
        assertNotNull(rule);
        SccpAddress pattern = rule.getPattern();
        assertNotNull(pattern);
//        assertEquals((int) pattern.getAddressIndicator().getValue(SccpProtocolVersion.ITU), 18);
        assertEquals((int) pattern.getAddressIndicator().getValue(SccpProtocolVersion.ITU), 19);
        assertEquals(pattern.getAddressIndicator().getRoutingIndicator(), RoutingIndicator.ROUTING_BASED_ON_GLOBAL_TITLE);
        assertEquals(
                pattern.getGlobalTitle().getGlobalTitleIndicator(),
                GlobalTitleIndicator.GLOBAL_TITLE_INCLUDES_TRANSLATION_TYPE_NUMBERING_PLAN_ENCODING_SCHEME_AND_NATURE_OF_ADDRESS);
        GlobalTitle0100 gt = (GlobalTitle0100) pattern.getGlobalTitle();
        assertEquals(gt.getTranslationType(), 0);
        assertEquals(gt.getNumberingPlan(), NumberingPlan.ISDN_TELEPHONY);
        assertEquals(gt.getNatureOfAddress(), NatureOfAddress.INTERNATIONAL);
        assertEquals(rule.getRuleType(), RuleType.SOLITARY);

        String sec_addressCmd = "sccp address create 2 71 3 8 0 0 3 123456789";
        result = this.sccpExecutorExt.execute(sec_addressCmd.split(" "));
        assertEquals(result, String.format(SccpOAMMessage.ADDRESS_SUCCESSFULLY_ADDED, this.sccpStack.getName()));
        assertEquals(this.routerExt.getRoutingAddresses().size(), 2);

        String createRuleCmd2 = "sccp rule create 3 R 71 2 8 0 0 3 123456789 dominant 1 backup-addressid 2";
        result = this.sccpExecutorExt.execute(createRuleCmd2.split(" "));
        assertEquals(result, String.format(SccpOAMMessage.RULE_SUCCESSFULLY_ADDED, this.sccpStack.getName()));
        assertEquals(this.routerExt.getRules().size(), 3);
        assertEquals(this.routerExt.getRule(3).getRuleType(), RuleType.DOMINANT);

        createRuleCmd2 = "sccp rule create 4 R 71 2 8 0 0 3 123456789 loadshared 1 backup-addressid 2 loadsharing-algo bit3";
        result = this.sccpExecutorExt.execute(createRuleCmd2.split(" "));
        assertEquals(result, String.format(SccpOAMMessage.RULE_SUCCESSFULLY_ADDED, this.sccpStack.getName()));
        assertEquals(this.routerExt.getRules().size(), 4);
        assertEquals(this.routerExt.getRule(4).getRuleType(), RuleType.LOADSHARED);
        assertEquals(this.routerExt.getRule(4).getLoadSharingAlgorithm(), LoadSharingAlgorithm.Bit3);

        createRuleCmd2 = "sccp rule create 11 R 71 2 8 0 0 3 123456789 dominant 1 backup-addressid 3";
        result = this.sccpExecutorExt.execute(createRuleCmd2.split(" "));
        assertTrue(result.substring(0, 10).equals(SccpOAMMessage.NO_BACKUP_ADDRESS.substring(0, 10)));
        assertEquals(this.routerExt.getRules().size(), 4);

        createRuleCmd2 = "sccp rule create 11 R 71 2 8 0 0 3 123456789 dominant 4 backup-addressid 3";
        result = this.sccpExecutorExt.execute(createRuleCmd2.split(" "));
        assertTrue(result.substring(0, 10).equals(SccpOAMMessage.NO_PRIMARY_ADDRESS.substring(0, 10)));
        assertEquals(this.routerExt.getRules().size(), 4);

        // Full rule command
        createRuleCmd2 = "sccp rule create 12 R 71 2 8 0 0 3 123456789 dominant 2 backup-addressid 1 loadsharing-algo bit3 newcgparty-addressid 1 origination-type remoteoriginated";
        result = this.sccpExecutorExt.execute(createRuleCmd2.split(" "));
        assertEquals(result, String.format(SccpOAMMessage.RULE_SUCCESSFULLY_ADDED, this.sccpStack.getName()));
        assertEquals(this.routerExt.getRules().size(), 5);

        rule = this.routerExt.getRule(12);
        assertEquals(rule.getOriginationType(), OriginationType.REMOTE);



        // Test Modify Rule

        createRuleCmd2 = "sccp rule modify 12 loadsharing-algo bit1 origination-type localoriginated";
        result = this.sccpExecutorExt.execute(createRuleCmd2.split(" "));
        assertEquals(result, String.format(SccpOAMMessage.RULE_SUCCESSFULLY_MODIFIED, this.sccpStack.getName()));
        assertEquals(this.routerExt.getRule(12).getLoadSharingAlgorithm(), LoadSharingAlgorithm.Bit1);
        assertEquals(this.routerExt.getRule(12).getOriginationType(), OriginationType.LOCAL);

        createRuleCmd2 = "sccp rule modify 12 loadsharing-algo bit1 origination-type localoriginated";
        result = this.sccpExecutorExt.execute(createRuleCmd2.split(" "));
        assertEquals(result, String.format(SccpOAMMessage.RULE_SUCCESSFULLY_MODIFIED, this.sccpStack.getName()));
        assertEquals(this.routerExt.getRule(12).getLoadSharingAlgorithm(), LoadSharingAlgorithm.Bit1);
        assertEquals(this.routerExt.getRule(12).getOriginationType(), OriginationType.LOCAL);

        createRuleCmd2 = "sccp rule modify 12 digits 55555";
        result = this.sccpExecutorExt.execute(createRuleCmd2.split(" "));
        assertEquals(result, String.format(SccpOAMMessage.RULE_SUCCESSFULLY_MODIFIED, this.sccpStack.getName()));
        assertEquals(this.routerExt.getRule(12).getPattern().getGlobalTitle().getDigits(), "55555");

        createRuleCmd2 = "sccp rule modify 1 mask R address-indicator 71 point-code 2 subsystem-number 8"
                + " translation-type 0 numbering-plan 0 nature-of-address-indicator 3"
                + " digits 123456789 rule-type loadshared primary-address-id 1 backup-addressid 2 loadsharing-algo bit4";
        result = this.sccpExecutorExt.execute(createRuleCmd2.split(" "));
        assertEquals(result, String.format(SccpOAMMessage.RULE_SUCCESSFULLY_MODIFIED, this.sccpStack.getName()));
        assertEquals(this.routerExt.getRules().size(), 5);
        assertEquals(this.routerExt.getRule(1).getRuleType(), RuleType.LOADSHARED);
        assertEquals(this.routerExt.getRule(1).getLoadSharingAlgorithm(), LoadSharingAlgorithm.Bit4);

        createRuleCmd2 = "sccp rule modify 1 mask R address-indicator 71 point-code 2 subsystem-number 8"
                + " translation-type 0 numbering-plan 0 nature-of-address-indicator 3"
                + " digits 123456789 rule-type dominant primary-address-id 1 backup-addressid 2";
        result = this.sccpExecutorExt.execute(createRuleCmd2.split(" "));
        assertEquals(result, String.format(SccpOAMMessage.RULE_SUCCESSFULLY_MODIFIED, this.sccpStack.getName()));
        assertEquals(this.routerExt.getRules().size(), 5);
        assertEquals(this.routerExt.getRule(1).getRuleType(), RuleType.DOMINANT);

        createRuleCmd2 = "sccp rule modify 1 mask R address-indicator 71 point-code 2 subsystem-number 8"
                + " translation-type 0 numbering-plan 0 nature-of-address-indicator 3"
                + " digits 123456789 rule-type solitary primary-address-id 1 backup-addressid 2";
        result = this.sccpExecutorExt.execute(createRuleCmd2.split(" "));
        assertEquals(result, String.format(SccpOAMMessage.RULE_SUCCESSFULLY_MODIFIED, this.sccpStack.getName()));
        assertEquals(this.routerExt.getRules().size(), 5);
        assertEquals(this.routerExt.getRule(1).getRuleType(), RuleType.SOLITARY);

        createRuleCmd2 = "sccp rule modify 1 mask R address-indicator 71 point-code 2 subsystem-number 8"
                + " translation-type 0 numbering-plan 0 nature-of-address-indicator 3"
                + " digits 123456789 rule-type dominant primary-address-id 1";
        result = this.sccpExecutorExt.execute(createRuleCmd2.split(" "));
        assertEquals(result,  String.format(SccpOAMMessage.RULE_SUCCESSFULLY_MODIFIED, this.sccpStack.getName()));
        assertEquals(this.routerExt.getRules().size(), 5);
        assertEquals(this.routerExt.getRule(1).getRuleType(), RuleType.DOMINANT);

        createRuleCmd2 = "sccp rule modify 1 mask R address-indicator 71 point-code 2 subsystem-number 8"
                + " translation-type 0 numbering-plan 0 nature-of-address-indicator 3"
                + " digits 123456789 rule-type dominant primary-address-id 1 backup-addressid 3";
        result = this.sccpExecutorExt.execute(createRuleCmd2.split(" "));
        assertTrue(result.substring(0, 10).equals(SccpOAMMessage.NO_BACKUP_ADDRESS.substring(0, 10)));
        assertEquals(this.routerExt.getRules().size(), 5);

        createRuleCmd2 = "sccp rule modify 1 mask R address-indicator 71 point-code 2 subsystem-number 8"
                + " translation-type 0 numbering-plan 0 nature-of-address-indicator 3"
                + " digits 123456789 rule-type dominant primary-address-id 3 backup-addressid 3";
        result = this.sccpExecutorExt.execute(createRuleCmd2.split(" "));
        assertTrue(result.substring(0, 10).equals(SccpOAMMessage.NO_PRIMARY_ADDRESS.substring(0, 10)));
        assertEquals(this.routerExt.getRules().size(), 5);

        createRuleCmd2 = "sccp rule modify 15 mask R address-indicator 71 point-code 2 subsystem-number 8"
                + " translation-type 0 numbering-plan 0 nature-of-address-indicator 3"
                + " digits 123456789 rule-type dominant primary-address-id 1 backup-addressid 2";
        result = this.sccpExecutorExt.execute(createRuleCmd2.split(" "));
        assertEquals(result, String.format(SccpOAMMessage.RULE_DOESNT_EXIST, this.sccpStack.getName()));
        assertEquals(this.routerExt.getRules().size(), 5);

        createRuleCmd2 = "sccp rule delete 15";
        result = this.sccpExecutorExt.execute(createRuleCmd2.split(" "));
        assertEquals(result, String.format(SccpOAMMessage.RULE_DOESNT_EXIST, this.sccpStack.getName()));
        assertEquals(this.routerExt.getRules().size(), 5);

        createRuleCmd2 = "sccp rule delete 1";
        result = this.sccpExecutorExt.execute(createRuleCmd2.split(" "));
        assertEquals(result, String.format(SccpOAMMessage.RULE_SUCCESSFULLY_REMOVED, this.sccpStack.getName()));
        assertEquals(this.routerExt.getRules().size(), 4);

        createRuleCmd2 = "sccp rule show 2";
        result = this.sccpExecutorExt.execute(createRuleCmd2.split(" "));

        createRuleCmd2 = "sccp rule show";
        result = this.sccpExecutorExt.execute(createRuleCmd2.split(" "));

        // Rules for callingAddress matching
        // Command with all calling address related params
        createRuleCmd2 = "sccp rule create 20 R 71 2 8 0 0 3 123456789 dominant 2 backup-addressid 1 loadsharing-algo bit3 newcgparty-addressid 1 origination-type remoteoriginated calling-ai 0 calling-pc 0 calling-ssn 0 calling-tt 0 calling-nai 0 calling-np 0 calling-digits-pattern 4567";
        result = this.sccpExecutorExt.execute(createRuleCmd2.split(" "));
        assertEquals(result, String.format(SccpOAMMessage.RULE_SUCCESSFULLY_ADDED, this.sccpStack.getName()));
        assertEquals(this.routerExt.getRules().size(), 5);

        rule = this.routerExt.getRule(20);
        assertEquals(rule.getOriginationType(), OriginationType.REMOTE);
        assertTrue(rule.getPatternCallingAddress().getGlobalTitle().getDigits().equals( "4567" ));

        createRuleCmd2 = "sccp rule create 21 R 71 2 8 0 0 3 123456789 dominant 2 backup-addressid 1 loadsharing-algo bit3 newcgparty-addressid 1 origination-type remoteoriginated calling-ai 18 calling-pc 0 calling-ssn 0 calling-tt 0 calling-nai 0 calling-np 0 calling-digits-pattern 567*";
        result = this.sccpExecutorExt.execute(createRuleCmd2.split(" "));
        assertEquals(result, String.format(SccpOAMMessage.RULE_SUCCESSFULLY_ADDED, this.sccpStack.getName()));
        assertEquals(this.routerExt.getRules().size(), 6);

        rule = this.routerExt.getRule(21);
        assertEquals(rule.getOriginationType(), OriginationType.REMOTE);
        assertTrue(rule.getPatternCallingAddress().getGlobalTitle().getDigits().equals( "567*" ));


        createRuleCmd2 = "sccp rule create 22 R 71 2 8 0 0 3 123456789 dominant 2 backup-addressid 1 loadsharing-algo bit3 newcgparty-addressid 1 origination-type remoteoriginated calling-ai 0 calling-pc 0 calling-ssn 0 calling-np 0";
        result = this.sccpExecutorExt.execute(createRuleCmd2.split(" "));
        assertEquals(result, String.format(SccpOAMMessage.RULE_SUCCESSFULLY_ADDED, this.sccpStack.getName()));
        assertEquals(this.routerExt.getRules().size(), 7);

        rule = this.routerExt.getRule(22);
        assertEquals(rule.getOriginationType(), OriginationType.REMOTE);
        assertTrue(rule.getPatternCallingAddress()==null);

        // Calling party modify rule
        createRuleCmd2 = "sccp rule modify 20 calling-digits-pattern 44444";
        result = this.sccpExecutorExt.execute(createRuleCmd2.split(" "));
        assertEquals(result, String.format(SccpOAMMessage.RULE_SUCCESSFULLY_MODIFIED, this.sccpStack.getName()));
        assertEquals(this.routerExt.getRules().size(), 7);
        rule = this.routerExt.getRule(20);
        assertEquals(rule.getOriginationType(), OriginationType.REMOTE);
        assertEquals(rule.getPatternCallingAddress().getGlobalTitle().getDigits(), "44444");

        createRuleCmd2 = "sccp rule modify 22 mask R address-indicator 71 point-code 2 subsystem-number 8"
                + " translation-type 0 numbering-plan 0 nature-of-address-indicator 3"
                + " digits 123456789 rule-type dominant primary-address-id 2 backup-addressid 1 loadsharing-algo bit3"
                + " newcgparty-addressid 1 origination-type remoteoriginated calling-ai 0 calling-pc 0"
                + " calling-ssn 0 calling-tt 0 calling-nai 0 calling-np 0 calling-digits-pattern 4567";
        result = this.sccpExecutorExt.execute(createRuleCmd2.split(" "));
        assertEquals(result, String.format(SccpOAMMessage.RULE_SUCCESSFULLY_MODIFIED, this.sccpStack.getName()));
        assertEquals(this.routerExt.getRules().size(), 7);

        rule = this.routerExt.getRule(22);
        assertEquals(rule.getOriginationType(), OriginationType.REMOTE);
        assertTrue(rule.getPatternCallingAddress().getGlobalTitle().getDigits().equals( "4567" ));

        // Delete the rules
        createRuleCmd2 = "sccp rule delete 20";
        result = this.sccpExecutorExt.execute(createRuleCmd2.split(" "));
        assertEquals(result, String.format(SccpOAMMessage.RULE_SUCCESSFULLY_REMOVED, this.sccpStack.getName()));
        assertEquals(this.routerExt.getRules().size(), 6);
        createRuleCmd2 = "sccp rule delete 21";
        result = this.sccpExecutorExt.execute(createRuleCmd2.split(" "));
        assertEquals(result, String.format(SccpOAMMessage.RULE_SUCCESSFULLY_REMOVED, this.sccpStack.getName()));
        assertEquals(this.routerExt.getRules().size(), 5);
        createRuleCmd2 = "sccp rule delete 22";
        result = this.sccpExecutorExt.execute(createRuleCmd2.split(" "));
        assertEquals(result, String.format(SccpOAMMessage.RULE_SUCCESSFULLY_REMOVED, this.sccpStack.getName()));
        assertEquals(this.routerExt.getRules().size(), 4);

    }
    
    @Test(groups = { "oam", "functional.mgmt" })
    public void testMaskSectionsValidations() {

        String incorrect_prim_addressCmd = "sccp address create 1 71 6535 8 0 0 12 93707100007";
        String incorrect_prim_address_deleteCmd = "sccp address delete 1";
        String correct_prim_addressCmd = "sccp address create 1 71 6535 8 0 0 12 -/-";

        // TODO: this update for fixing "sccp rule create needs not zero pc"
//        String incorrectCreateRuleCmd = "sccp rule create 2 R/K 18 0 180 0 1 4 * solitary 1";
//        String correctCreateRuleCmd = "sccp rule create 2 R/K 18 0 180 0 1 4 937/* solitary 1";
        String incorrectCreateRuleCmd = "sccp rule create 2 R/K 18 1 180 0 1 4 * solitary 1";
        String correctCreateRuleCmd = "sccp rule create 2 R/K 18 1 180 0 1 4 937/* solitary 1";

        String incorrect_sec_addressCmd = "sccp address create 3 71 6535 8 0 0 12 93707100007";
        // TODO: this update for fixing "sccp rule create needs not zero pc"
//        String correctCreateRuleCmdWithSecId = "sccp rule create 2 R/K 18 0 180 0 1 4 937/* solitary 1 backup-addressid 3";
        String correctCreateRuleCmdWithSecId = "sccp rule create 2 R/K 18 1 180 0 1 4 937/* solitary 1 backup-addressid 3";

        String result = this.sccpExecutorExt.execute(incorrectCreateRuleCmd.split(" "));
        assertEquals(result, SccpOAMMessage.SEC_MISMATCH_PATTERN);

        this.sccpExecutorExt.execute(incorrect_prim_addressCmd.split(" "));
        result = this.sccpExecutorExt.execute(correctCreateRuleCmd.split(" "));
        assertEquals(result, SccpOAMMessage.SEC_MISMATCH_PRIMADDRESS);

        this.sccpExecutorExt.execute(incorrect_prim_address_deleteCmd.split(" "));
        this.sccpExecutorExt.execute(correct_prim_addressCmd.split(" "));
        this.sccpExecutorExt.execute(incorrect_sec_addressCmd.split(" "));
        result = this.sccpExecutorExt.execute(correctCreateRuleCmdWithSecId.split(" "));

        assertEquals(result, SccpOAMMessage.SEC_MISMATCH_SECADDRESS);
    }
    
    /**
     * Test for bug http://code.google.com/p/mobicents/issues/detail?id=3057 NPE when creating SCCP primary address via CLI
     */
    @Test(groups = { "oam", "functional.mgmt" })
    public void testManageAddress() {
        String prim_addressCmd = "sccp address create 1 71 6535 8 0 0 12 93707100007";
        String result = this.sccpExecutorExt.execute(prim_addressCmd.split(" "));
        assertEquals(result, String.format(SccpOAMMessage.ADDRESS_SUCCESSFULLY_ADDED, this.sccpStack.getName()));
        assertEquals(this.routerExt.getRoutingAddresses().size(), 1);

        // test show
        prim_addressCmd = "sccp address show";
        result = this.sccpExecutorExt.execute(prim_addressCmd.split(" "));
        assertEquals(result, "key=1  pc=6535,ssn=8,AI=71,gt=GlobalTitle0001Impl [digits=93707100007, natureOfAddress=SPARE_12, encodingScheme=BCDOddEncodingScheme[type=BCD_ODD, code=1]]\n");
    }

    @Test(groups = { "oam", "functional.mgmt" })
    public void testPrimAddress() {

        String rspCmd = "sccp address create 11 71 6535 8 0 0 12 93707100007";
        // <id> <address-indicator> <point-code> <subsystem-number>
        // <translation-type> <numbering-plan> <nature-of-address-indicator>
        // <digits>

        //sccp address modify <id> address-indicator <address-indicator> point-code <point-code> subsystem-number <subsystem-number> 
        //translation-type <translation-type> numbering-plan <numbering-plan> nature-of-address-indicator <nature-of-address-indicator> 
        //digits <digits> stackname <stack-name>
        String res = this.sccpExecutorExt.execute(rspCmd.split(" "));
        assertEquals(this.routerExt.getRoutingAddresses().size(), 1);
        SccpAddress addr = this.routerExt.getRoutingAddress(11);
        assertEquals(addr.getAddressIndicator().getValue(SccpProtocolVersion.ITU), 71);
        assertEquals(addr.getSignalingPointCode(), 6535);
        assertEquals(addr.getSubsystemNumber(), 8);
        assertTrue(addr.getGlobalTitle().getDigits().equals("93707100007"));

        rspCmd = "sccp address create 11 71 6536 8 0 0 12 93707100007";
        res = this.sccpExecutorExt.execute(rspCmd.split(" "));
        assertTrue(res.equals(SccpOAMMessage.ADDRESS_ALREADY_EXIST));
        assertEquals(this.routerExt.getRoutingAddresses().size(), 1);
        addr = this.routerExt.getRoutingAddress(11);
        assertEquals(addr.getSignalingPointCode(), 6535);

        rspCmd = "sccp address modify 11 address-indicator 71 point-code 6537 subsystem-number 8 translation-type 0 numbering-plan 0 nature-of-address-indicator 12 digits 93707100007";
        res = this.sccpExecutorExt.execute(rspCmd.split(" "));
        assertEquals(this.routerExt.getRoutingAddresses().size(), 1);
        addr = this.routerExt.getRoutingAddress(11);
        assertEquals(addr.getSignalingPointCode(), 6537);

        rspCmd = "sccp address modify 11 point-code 7777";
        res = this.sccpExecutorExt.execute(rspCmd.split(" "));
        addr = this.routerExt.getRoutingAddress(11);
        assertEquals(addr.getSignalingPointCode(), 7777);

        rspCmd = "sccp address modify 11 digits 88888888";
        res = this.sccpExecutorExt.execute(rspCmd.split(" "));
        addr = this.routerExt.getRoutingAddress(11);
        assertEquals(addr.getGlobalTitle().getDigits(), "88888888");

        rspCmd = "sccp address modify 12 address-indicator 71 point-code 7777 subsystem-number 8 translation-type 0 numbering-plan 0 nature-of-address-indicator 12 digits 93707100007";
        res = this.sccpExecutorExt.execute(rspCmd.split(" "));
        assertTrue(res.equals(String.format(SccpOAMMessage.ADDRESS_DOESNT_EXIST, this.sccpStack.getName())));
        assertEquals(this.routerExt.getRoutingAddresses().size(), 1);
        addr = this.routerExt.getRoutingAddress(11);
        assertEquals(addr.getSignalingPointCode(), 7777);

        rspCmd = "sccp address show 11";
        res = this.sccpExecutorExt.execute(rspCmd.split(" "));

        rspCmd = "sccp address show";
        res = this.sccpExecutorExt.execute(rspCmd.split(" "));

        rspCmd = "sccp address delete 12";
        res = this.sccpExecutorExt.execute(rspCmd.split(" "));
        assertTrue(res.equals(String.format(SccpOAMMessage.ADDRESS_DOESNT_EXIST, this.sccpStack.getName())));
        assertEquals(this.routerExt.getRoutingAddresses().size(), 1);

        rspCmd = "sccp address delete 11";
        res = this.sccpExecutorExt.execute(rspCmd.split(" "));
        assertEquals(this.routerExt.getRoutingAddresses().size(), 0);
    }

    class Mtp3UserPartImpl implements Mtp3UserPart {

        public void addMtp3UserPartListener(Mtp3UserPartListener arg0) {
            // TODO Auto-generated method stub

        }

        public int getMaxUserDataLength(int arg0) {
            // TODO Auto-generated method stub
            return 0;
        }

        public void removeMtp3UserPartListener(Mtp3UserPartListener arg0) {
            // TODO Auto-generated method stub

        }

        public void sendMessage(Mtp3TransferPrimitive arg0) throws IOException {
            // TODO Auto-generated method stub

        }

        /*
         * (non-Javadoc)
         *
         * @see org.restcomm.protocols.ss7.mtp.Mtp3UserPart# getMtp3TransferPrimitiveFactory()
         */
        @Override
        public Mtp3TransferPrimitiveFactory getMtp3TransferPrimitiveFactory() {
            // TODO Auto-generated method stub
            return null;
        }

        /*
         * (non-Javadoc)
         *
         * @see org.restcomm.protocols.ss7.mtp.Mtp3UserPart#getRoutingLabelFormat()
         */
        @Override
        public RoutingLabelFormat getRoutingLabelFormat() {
            // TODO Auto-generated method stub
            return null;
        }

        /*
         * (non-Javadoc)
         *
         * @see org.restcomm.protocols.ss7.mtp.Mtp3UserPart#setRoutingLabelFormat
         * (org.restcomm.protocols.ss7.mtp.RoutingLabelFormat)
         */
        @Override
        public void setRoutingLabelFormat(RoutingLabelFormat arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public boolean isUseLsbForLinksetSelection() {
            return false;
        }

        @Override
        public void setUseLsbForLinksetSelection(boolean arg0) {

        }

        @Override
        public int getDeliveryMessageThreadCount() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public void setDeliveryMessageThreadCount(int deliveryMessageThreadCount) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public ExecutorCongestionMonitor getExecutorCongestionMonitor() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void start() throws Exception {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void stop() throws Exception {
            // TODO Auto-generated method stub
            
        }
    }
}
