
package org.restcomm.protocols.ss7.sccpext.impl.router;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javolution.text.TextBuilder;
import javolution.util.FastMap;
import javolution.xml.XMLBinding;
import javolution.xml.XMLObjectReader;
import javolution.xml.XMLObjectWriter;
import javolution.xml.stream.XMLStreamException;

import org.apache.log4j.Logger;
import org.restcomm.protocols.ss7.indicator.AddressIndicator;
import org.restcomm.protocols.ss7.indicator.NatureOfAddress;
import org.restcomm.protocols.ss7.indicator.NumberingPlan;
import org.restcomm.protocols.ss7.indicator.RoutingIndicator;
import org.restcomm.protocols.ss7.sccp.LoadSharingAlgorithm;
import org.restcomm.protocols.ss7.sccp.NetworkIdState;
import org.restcomm.protocols.ss7.sccp.OriginationType;
import org.restcomm.protocols.ss7.sccp.RemoteSignalingPointCode;
import org.restcomm.protocols.ss7.sccp.Router;
import org.restcomm.protocols.ss7.sccp.Rule;
import org.restcomm.protocols.ss7.sccp.RuleType;
import org.restcomm.protocols.ss7.sccp.SccpProtocolVersion;
import org.restcomm.protocols.ss7.sccp.SccpStack;
import org.restcomm.protocols.ss7.sccp.impl.oam.SccpOAMMessage;
import org.restcomm.protocols.ss7.sccp.impl.parameter.GlobalTitle0001Impl;
import org.restcomm.protocols.ss7.sccp.impl.parameter.GlobalTitle0010Impl;
import org.restcomm.protocols.ss7.sccp.impl.parameter.GlobalTitle0011Impl;
import org.restcomm.protocols.ss7.sccp.impl.parameter.GlobalTitle0100Impl;
import org.restcomm.protocols.ss7.sccp.impl.parameter.NoGlobalTitle;
import org.restcomm.protocols.ss7.sccp.impl.parameter.SccpAddressImpl;
import org.restcomm.protocols.ss7.sccp.impl.router.RouterImpl;
import org.restcomm.protocols.ss7.sccp.parameter.GlobalTitle;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.sccpext.impl.congestion.NetworkIdStateImpl;
import org.restcomm.protocols.ss7.sccpext.impl.congestion.SccpCongestionControl;
import org.restcomm.protocols.ss7.sccpext.router.RouterExt;

/**
*
* @author Amit Bhayani
* @author sergey vetyutnev
*
*/
public class RouterExtImpl implements RouterExt {
    private static final Logger logger = Logger.getLogger(RouterImpl.class);

    private static final String SCCP_ROUTER_PERSIST_DIR_KEY = "sccprouter.persist.dir";
    private static final String USER_DIR_KEY = "user.dir";
    private static final String PERSIST_FILE_NAME = "sccprouter3_ext.xml";

    private static final String RULE = "rule";
    private static final String ROUTING_ADDRESS = "routingAddress";

    private final TextBuilder persistFile = TextBuilder.newInstance();

    protected static final SccpRouterXMLBindingExt binding = new SccpRouterXMLBindingExt();
    private static final String TAB_INDENT = "\t";
    private static final String CLASS_ATTRIBUTE = "type";

    private String persistDir = null;

    private RuleComparatorFactory ruleComparatorFactory = null;
    // rule list
    private RuleMap<Integer, Rule> rulesMap = new RuleMap<Integer, Rule>();
    private SccpAddressMap<Integer, SccpAddressImpl> routingAddresses = new SccpAddressMap<Integer, SccpAddressImpl>();

    private final String name;
    private final SccpStack sccpStack;
    private final Router router;

    public RouterExtImpl(String name, SccpStack sccpStack, Router router) {
        this.name = name;
        this.sccpStack = sccpStack;
        this.router = router;
        this.ruleComparatorFactory = RuleComparatorFactory.getInstance("RuleComparatorFactory");

        binding.setAlias(RuleImpl.class, RULE);
        binding.setClassAttribute(CLASS_ATTRIBUTE);

        binding.setAlias(GlobalTitle0001Impl.class, "GT0001");
        binding.setAlias(GlobalTitle0010Impl.class, "GT0010");
        binding.setAlias(GlobalTitle0011Impl.class, "GT0011");
        binding.setAlias(GlobalTitle0100Impl.class, "GT0100");
        binding.setAlias(NoGlobalTitle.class, "NoGlobalTitle");
    }

    public String getName() {
        return name;
    }

    public String getPersistDir() {
        return persistDir;
    }

    public void setPersistDir(String persistDir) {
        this.persistDir = persistDir;
    }

    public void start() {
        this.persistFile.clear();

        if (persistDir != null) {
            this.persistFile.append(persistDir).append(File.separator).append(this.name).append("_").append(PERSIST_FILE_NAME);
        } else {
            persistFile.append(System.getProperty(SCCP_ROUTER_PERSIST_DIR_KEY, System.getProperty(USER_DIR_KEY)))
                    .append(File.separator).append(this.name).append("_").append(PERSIST_FILE_NAME);
        }

        logger.info(String.format("SCCP RouterExt configuration file path %s", persistFile.toString()));

        this.load();

        logger.info("Started SCCP Router");
    }

    public void stop() {
        this.store();
    }

    /**
     * Looks up rule for translation.
     *
     * @param calledParty called party address
     * @return the rule with match to the called party address
     */
    public Rule findRule(SccpAddress calledParty, SccpAddress callingParty, boolean isMtpOriginated, int msgNetworkId) {

        for (FastMap.Entry<Integer, Rule> e = this.rulesMap.head(), end = this.rulesMap.tail(); (e = e.getNext()) != end;) {
            Rule rule = e.getValue();
            if (rule.matches(calledParty, callingParty, isMtpOriginated, msgNetworkId)) {
                return rule;
            }
        }
        return null;
    }

    @Override
    public Rule getRule(int id) {
        return this.rulesMap.get(id);
    }

    @Override
    public SccpAddress getRoutingAddress(int id) {
        return this.routingAddresses.get(id);
    }

    @Override
    public Map<Integer, Rule> getRules() {
        Map<Integer, Rule> rulesMapTmp = new HashMap<Integer, Rule>();
        rulesMapTmp.putAll(rulesMap);
        return rulesMapTmp;
    }

    @Override
    public Map<Integer, SccpAddress> getRoutingAddresses() {
        Map<Integer, SccpAddress> routingAddressesTmp = new HashMap<Integer, SccpAddress>();
        routingAddressesTmp.putAll(routingAddresses);
        return routingAddressesTmp;
    }

    @Override
    public void addRule(int id, RuleType ruleType, LoadSharingAlgorithm algo, OriginationType originationType, SccpAddress pattern, String mask,
            int pAddressId, int sAddressId, Integer newCallingPartyAddressAddressId, int networkId, SccpAddress patternCallingAddress) throws Exception {

        Rule ruleTmp = this.getRule(id);

        if (ruleTmp != null) {
            throw new Exception(SccpOAMMessage.RULE_ALREADY_EXIST);
        }

        int maskumberOfSecs = (mask.split("/").length - 1);
        int patternNumberOfSecs = (pattern.getGlobalTitle().getDigits().split("/").length - 1);

        if (maskumberOfSecs != patternNumberOfSecs) {
            throw new Exception(SccpOAMMessage.SEC_MISMATCH_PATTERN);
        }

        SccpAddress pAddress = this.getRoutingAddress(pAddressId);
        if (pAddress == null) {
            throw new Exception(String.format(SccpOAMMessage.NO_PRIMARY_ADDRESS, pAddressId));
        }

        int primAddNumberOfSecs = (pAddress.getGlobalTitle().getDigits().split("/").length - 1);
        if (maskumberOfSecs != primAddNumberOfSecs) {
            throw new Exception(SccpOAMMessage.SEC_MISMATCH_PRIMADDRESS);
        }

        if (sAddressId != -1) {
            SccpAddress sAddress = this.getRoutingAddress(sAddressId);
            if (sAddress == null) {
                throw new Exception(String.format(SccpOAMMessage.NO_BACKUP_ADDRESS, sAddressId));
            }

            int secAddNumberOfSecs = (sAddress.getGlobalTitle().getDigits().split("/").length - 1);
            if (maskumberOfSecs != secAddNumberOfSecs) {
                throw new Exception(SccpOAMMessage.SEC_MISMATCH_SECADDRESS);
            }
        }

        if (sAddressId == -1 && ruleType != RuleType.SOLITARY) {
            throw new Exception(SccpOAMMessage.RULETYPE_NOT_SOLI_SEC_ADD_MANDATORY);
        }

        synchronized (this) {
            RuleImpl rule = new RuleImpl(ruleType, algo, originationType, pattern, mask, networkId, patternCallingAddress);
            rule.setPrimaryAddressId(pAddressId);
            rule.setSecondaryAddressId(sAddressId);
            rule.setNewCallingPartyAddressId(newCallingPartyAddressAddressId);

            rule.setRuleId(id);
            RuleImpl[] rulesArray = new RuleImpl[(this.rulesMap.size() + 1)];
            int count = 0;

            for (FastMap.Entry<Integer, Rule> e = this.rulesMap.head(), end = this.rulesMap.tail(); (e = e.getNext()) != end;) {
                Integer ruleId = e.getKey();
                RuleImpl ruleTemp1 = (RuleImpl) e.getValue();
                ruleTemp1.setRuleId(ruleId);
                rulesArray[count++] = ruleTemp1;
            }

            // add latest rule
            rulesArray[count++] = rule;

            // Sort
            Arrays.sort(rulesArray, this.ruleComparatorFactory.getRuleComparator());

            RuleMap<Integer, Rule> newRule = new RuleMap<Integer, Rule>();
            for (int i = 0; i < rulesArray.length; i++) {
                RuleImpl ruleTemp = rulesArray[i];
                newRule.put(ruleTemp.getRuleId(), ruleTemp);
            }
            this.rulesMap = newRule;
            this.store();
        }
    }

    @Override
    public void modifyRule(int id, RuleType ruleType, LoadSharingAlgorithm algo, OriginationType originationType, SccpAddress pattern, String mask,
            int pAddressId, int sAddressId, Integer newCallingPartyAddressAddressId, int networkId, SccpAddress patternCallingAddress) throws Exception {
        synchronized (this) {
            Rule ruleTmp = this.getRule(id);

            if (ruleTmp == null) {
                throw new Exception(String.format(SccpOAMMessage.RULE_DOESNT_EXIST, name));
            }

            int maskumberOfSecs = (mask.split("/").length - 1);
            int patternNumberOfSecs = (pattern.getGlobalTitle().getDigits().split("/").length - 1);

            if (maskumberOfSecs != patternNumberOfSecs) {
                throw new Exception(SccpOAMMessage.SEC_MISMATCH_PATTERN);
            }

            SccpAddress pAddress = this.getRoutingAddress(pAddressId);

            if (pAddress == null) {
                throw new Exception(String.format(SccpOAMMessage.NO_PRIMARY_ADDRESS, pAddressId));
            }
            int primAddNumberOfSecs = (pattern.getGlobalTitle().getDigits().split("/").length - 1);
            if (maskumberOfSecs != primAddNumberOfSecs) {
                throw new Exception(SccpOAMMessage.SEC_MISMATCH_PRIMADDRESS);
            }

            if (sAddressId != -1) {
                SccpAddress sAddress = this.getRoutingAddress(sAddressId);
                if (sAddress == null) {
                    throw new Exception(String.format(SccpOAMMessage.NO_BACKUP_ADDRESS, sAddressId));
                }
                int secAddNumberOfSecs = (pattern.getGlobalTitle().getDigits().split("/").length - 1);
                if (maskumberOfSecs != secAddNumberOfSecs) {
                    throw new Exception(SccpOAMMessage.SEC_MISMATCH_SECADDRESS);
                }
            }

            if (sAddressId == -1 && ruleType != RuleType.SOLITARY) {
                throw new Exception(SccpOAMMessage.RULETYPE_NOT_SOLI_SEC_ADD_MANDATORY);
            }

            RuleImpl rule = new RuleImpl(ruleType, algo, originationType, pattern, mask, networkId, patternCallingAddress);
            rule.setPrimaryAddressId(pAddressId);
            rule.setSecondaryAddressId(sAddressId);
            rule.setNewCallingPartyAddressId(newCallingPartyAddressAddressId);

            rule.setRuleId(id);
            RuleImpl[] rulesArray = new RuleImpl[(this.rulesMap.size())];
            int count = 0;

            // Remove the old rule so that it doesn't overwrite the new modifications
            this.removeRule( id );

            for (FastMap.Entry<Integer, Rule> e = this.rulesMap.head(), end = this.rulesMap.tail(); (e = e.getNext()) != end;) {
                Integer ruleId = e.getKey();
                RuleImpl ruleTemp1 = (RuleImpl) e.getValue();
                ruleTemp1.setRuleId(ruleId);
                rulesArray[count++] = ruleTemp1;
            }

            // add latest rule
            rulesArray[count++] = rule;

            // Sort
            Arrays.sort(rulesArray, this.ruleComparatorFactory.getRuleComparator());

            RuleMap<Integer, Rule> newRule = new RuleMap<Integer, Rule>();
            for (int i = 0; i < rulesArray.length; i++) {
                RuleImpl ruleTemp = rulesArray[i];
                newRule.put(ruleTemp.getRuleId(), ruleTemp);
            }
            this.rulesMap = newRule;
            this.store();
        }
    }

    @Override
    public void modifyRule(int id, RuleType ruleType, LoadSharingAlgorithm algo, OriginationType originationType, SccpAddress pattern, String mask,
            Integer pAddressId, Integer sAddressId, Integer newCallingPartyAddressAddressId, Integer networkId, SccpAddress patternCallingAddress) throws Exception {
        synchronized (this) {
            Rule ruleTmp = this.getRule(id);

            if (ruleTmp == null) {
                throw new Exception(String.format(SccpOAMMessage.RULE_DOESNT_EXIST, name));
            }

            if(networkId == null)
                networkId = ruleTmp.getNetworkId();
            if(newCallingPartyAddressAddressId == null)
                newCallingPartyAddressAddressId = ruleTmp.getNewCallingPartyAddressId();
            if(sAddressId == null)
                sAddressId = ruleTmp.getSecondaryAddressId();
            if(pAddressId == null)
                pAddressId = ruleTmp.getPrimaryAddressId();
            if(mask == null)
                mask = ruleTmp.getMask();
            if(originationType == null)
                originationType = ruleTmp.getOriginationType();
            if(algo == null)
                algo = ruleTmp.getLoadSharingAlgorithm();
            if(ruleType == null)
                ruleType = ruleTmp.getRuleType();

            int maskumberOfSecs = (mask.split("/").length - 1);
            int patternNumberOfSecs = (pattern.getGlobalTitle().getDigits().split("/").length - 1);

            if (maskumberOfSecs != patternNumberOfSecs) {
                throw new Exception(SccpOAMMessage.SEC_MISMATCH_PATTERN);
            }

            SccpAddress pAddress = this.getRoutingAddress(pAddressId);

            if (pAddress == null) {
                throw new Exception(String.format(SccpOAMMessage.NO_PRIMARY_ADDRESS, pAddressId));
            }
            int primAddNumberOfSecs = (pattern.getGlobalTitle().getDigits().split("/").length - 1);
            if (maskumberOfSecs != primAddNumberOfSecs) {
                throw new Exception(SccpOAMMessage.SEC_MISMATCH_PRIMADDRESS);
            }

            if (sAddressId != -1) {
                SccpAddress sAddress = this.getRoutingAddress(sAddressId);
                if (sAddress == null) {
                    throw new Exception(String.format(SccpOAMMessage.NO_BACKUP_ADDRESS, sAddressId));
                }
                int secAddNumberOfSecs = (pattern.getGlobalTitle().getDigits().split("/").length - 1);
                if (maskumberOfSecs != secAddNumberOfSecs) {
                    throw new Exception(SccpOAMMessage.SEC_MISMATCH_SECADDRESS);
                }
            }

            if (sAddressId == -1 && ruleType != RuleType.SOLITARY) {
                throw new Exception(SccpOAMMessage.RULETYPE_NOT_SOLI_SEC_ADD_MANDATORY);
            }

            RuleImpl rule = new RuleImpl(ruleType, algo, originationType, pattern, mask, networkId, patternCallingAddress);
            rule.setPrimaryAddressId(pAddressId);
            rule.setSecondaryAddressId(sAddressId);
            rule.setNewCallingPartyAddressId(newCallingPartyAddressAddressId);

            rule.setRuleId(id);
            RuleImpl[] rulesArray = new RuleImpl[(this.rulesMap.size())];
            int count = 0;

            // Remove the old rule so that it doesn't overwrite the new modifications
            this.removeRule( id );

            for (FastMap.Entry<Integer, Rule> e = this.rulesMap.head(), end = this.rulesMap.tail(); (e = e.getNext()) != end;) {
                Integer ruleId = e.getKey();
                RuleImpl ruleTemp1 = (RuleImpl) e.getValue();
                ruleTemp1.setRuleId(ruleId);
                rulesArray[count++] = ruleTemp1;
            }

            // add latest rule
            rulesArray[count++] = rule;

            // Sort
            Arrays.sort(rulesArray, this.ruleComparatorFactory.getRuleComparator());

            RuleMap<Integer, Rule> newRule = new RuleMap<Integer, Rule>();
            for (int i = 0; i < rulesArray.length; i++) {
                RuleImpl ruleTemp = rulesArray[i];
                newRule.put(ruleTemp.getRuleId(), ruleTemp);
            }
            this.rulesMap = newRule;
            this.store();
        }
    }

    @Override
    public void removeRule(int id) throws Exception {

        if (this.getRule(id) == null) {
            throw new Exception(String.format(SccpOAMMessage.RULE_DOESNT_EXIST, name));
        }

        synchronized (this) {
            RuleMap<Integer, Rule> newRule = new RuleMap<Integer, Rule>();
            newRule.putAll(this.rulesMap);
            newRule.remove(id);
            this.rulesMap = newRule;
            this.store();
        }
    }

    @Override
    public void addRoutingAddress(int primAddressId, SccpAddress primaryAddress) throws Exception {

        if (this.getRoutingAddress(primAddressId) != null) {
            throw new Exception(SccpOAMMessage.ADDRESS_ALREADY_EXIST);
        }

        synchronized (this) {
            SccpAddressMap<Integer, SccpAddressImpl> newPrimaryAddress = new SccpAddressMap<Integer, SccpAddressImpl>();
            newPrimaryAddress.putAll(this.routingAddresses);
            newPrimaryAddress.put(primAddressId, (SccpAddressImpl) primaryAddress);
            this.routingAddresses = newPrimaryAddress;
            this.store();
        }
    }

    @Override
    public void modifyRoutingAddress(int primAddressId, SccpAddress primaryAddress) throws Exception {
        if (this.getRoutingAddress(primAddressId) == null) {
            throw new Exception(String.format(SccpOAMMessage.ADDRESS_DOESNT_EXIST, name));
        }

        synchronized (this) {
            SccpAddressMap<Integer, SccpAddressImpl> newPrimaryAddress = new SccpAddressMap<Integer, SccpAddressImpl>();
            newPrimaryAddress.putAll(this.routingAddresses);
            newPrimaryAddress.put(primAddressId, (SccpAddressImpl) primaryAddress);
            this.routingAddresses = newPrimaryAddress;
            this.store();
        }
    }

    @Override
    public void modifyRoutingAddress(int primAddressId, Integer ai, Integer pc, Integer ssnValue, Integer tt, Integer npValue,
            Integer naiValue, String digits) throws Exception {
        RoutingIndicator ri;
        GlobalTitle gt = null;
        int dpc;
        int ssn;

        SccpAddressImpl sccpAddress = (SccpAddressImpl) this.getRoutingAddress(primAddressId);

        if (sccpAddress == null) {
            throw new Exception(String.format(SccpOAMMessage.ADDRESS_DOESNT_EXIST, name));
        }

        if(ai != null) {
            AddressIndicator aiObj = new AddressIndicator(ai.byteValue(), SccpProtocolVersion.ITU);
            ri = aiObj.getRoutingIndicator();
        } else {
            ri = sccpAddress.getAddressIndicator().getRoutingIndicator();
        }

        if(pc != null) {
            dpc = pc;
        } else {
            dpc = sccpAddress.getSignalingPointCode();
        }

        if(ssnValue != null) {
            ssn = ssnValue;
        } else {
            ssn = sccpAddress.getSubsystemNumber();
        }

        if(tt != null || npValue != null || naiValue != null || digits != null) {
            gt = modifyGt(tt, npValue, naiValue, digits, sccpAddress);
        } else {
            gt = sccpAddress.getGlobalTitle();
        }
        SccpAddressImpl modifiedSccpAddress = new SccpAddressImpl(ri, gt, dpc, ssn);

        synchronized (this) {
            SccpAddressMap<Integer, SccpAddressImpl> newPrimaryAddress = new SccpAddressMap<Integer, SccpAddressImpl>();
            newPrimaryAddress.putAll(this.routingAddresses);
            newPrimaryAddress.put(primAddressId, modifiedSccpAddress);
            this.routingAddresses = newPrimaryAddress;
            this.store();
        }
    }

    public SccpAddress modifySccpAddress(SccpAddress sccpAddress, Integer ai, Integer pc, Integer ssnValue, Integer tt, Integer npValue,
            Integer naiValue, String digits) throws Exception {
        RoutingIndicator ri;
        GlobalTitle gt = null;
        int dpc;
        int ssn;

        if (sccpAddress == null) {
            throw new Exception(String.format(SccpOAMMessage.ADDRESS_DOESNT_EXIST, name));
        }

        if(ai != null) {
            AddressIndicator aiObj = new AddressIndicator(ai.byteValue(), SccpProtocolVersion.ITU);
            ri = aiObj.getRoutingIndicator();
        } else {
            ri = sccpAddress.getAddressIndicator().getRoutingIndicator();
        }

        if(pc != null) {
            dpc = pc;
        } else {
            dpc = sccpAddress.getSignalingPointCode();
        }

        if(ssnValue != null) {
            ssn = ssnValue;
        } else {
            ssn = sccpAddress.getSubsystemNumber();
        }

        if(tt != null || npValue != null || naiValue != null || digits != null) {
            gt = modifyGt(tt, npValue, naiValue, digits, sccpAddress);
        } else {
            gt = sccpAddress.getGlobalTitle();
        }
        return new SccpAddressImpl(ri, gt, dpc, ssn);
    }

    private GlobalTitle modifyGt(Integer ttValue, Integer npValue, Integer naiValue, String digits, SccpAddress sccpAddress) {

        GlobalTitle gt = null;

        if(digits == null)
            digits = sccpAddress.getGlobalTitle().getDigits();

        NumberingPlan np = null;
        NatureOfAddress nai = null;
        Integer tt = null;

        if (naiValue != null)
            nai = NatureOfAddress.valueOf(naiValue);

        if(npValue != null)
            np = NumberingPlan.valueOf(npValue);

        if(ttValue != null)
            tt = ttValue;

        switch (sccpAddress.getGlobalTitle().getGlobalTitleIndicator()) {
            case GLOBAL_TITLE_INCLUDES_NATURE_OF_ADDRESS_INDICATOR_ONLY:
                if(nai == null)
                    nai = ((GlobalTitle0001Impl)sccpAddress.getGlobalTitle()).getNatureOfAddress();
                gt = sccpStack.getSccpProvider().getParameterFactory().createGlobalTitle(digits, nai);
                break;
            case GLOBAL_TITLE_INCLUDES_TRANSLATION_TYPE_ONLY:
                if(tt == null)
                    tt = ((GlobalTitle0010Impl)sccpAddress.getGlobalTitle()).getTranslationType();
                gt = sccpStack.getSccpProvider().getParameterFactory().createGlobalTitle(digits, tt);
                break;
            case GLOBAL_TITLE_INCLUDES_TRANSLATION_TYPE_NUMBERING_PLAN_AND_ENCODING_SCHEME:
                if(np == null)
                    np = ((GlobalTitle0011Impl)sccpAddress.getGlobalTitle()).getNumberingPlan();
                if(tt == null)
                    tt = ((GlobalTitle0011Impl)sccpAddress.getGlobalTitle()).getTranslationType();
                gt = sccpStack.getSccpProvider().getParameterFactory().createGlobalTitle(digits, tt, np, null);
                break;
            case GLOBAL_TITLE_INCLUDES_TRANSLATION_TYPE_NUMBERING_PLAN_ENCODING_SCHEME_AND_NATURE_OF_ADDRESS:
                if(nai == null)
                    nai = ((GlobalTitle0100Impl)sccpAddress.getGlobalTitle()).getNatureOfAddress();
                if(np == null)
                    np = ((GlobalTitle0100Impl)sccpAddress.getGlobalTitle()).getNumberingPlan();
                if(tt == null)
                    tt = ((GlobalTitle0100Impl)sccpAddress.getGlobalTitle()).getTranslationType();
                gt = sccpStack.getSccpProvider().getParameterFactory().createGlobalTitle(digits, tt, np, null, nai);
                break;

            case NO_GLOBAL_TITLE_INCLUDED:
                gt = sccpStack.getSccpProvider().getParameterFactory().createGlobalTitle(digits);
                break;
        }
        return gt;
    }

    @Override
    public void removeRoutingAddress(int id) throws Exception {
        if (this.getRoutingAddress(id) == null) {
            throw new Exception(String.format(SccpOAMMessage.ADDRESS_DOESNT_EXIST, name));
        }

        synchronized (this) {
            SccpAddressMap<Integer, SccpAddressImpl> newPrimaryAddress = new SccpAddressMap<Integer, SccpAddressImpl>();
            newPrimaryAddress.putAll(this.routingAddresses);
            newPrimaryAddress.remove(id);
            this.routingAddresses = newPrimaryAddress;
            this.store();
        }
    }

    public void removeAllResources() {

        synchronized (this) {
            if (this.rulesMap.size() == 0 && this.routingAddresses.size() == 0)
                // no resources allocated - nothing to do
                return;

            rulesMap = new RuleMap<Integer, Rule>();
            routingAddresses = new SccpAddressMap<Integer, SccpAddressImpl>();

            // We store the cleared state
            this.store();
        }
    }

    public FastMap<Integer, NetworkIdState> getNetworkIdStateList() {
        return getNetworkIdList(-1);
    }

    public FastMap<Integer, NetworkIdState> getNetworkIdList(int affectedPc) {
        FastMap<Integer, NetworkIdState> res = new FastMap<Integer, NetworkIdState>();

        for (FastMap.Entry<Integer, Rule> e = this.rulesMap.head(), end = rulesMap.tail(); (e = e.getNext()) != end;) {
            Rule rule = e.getValue();
            NetworkIdStateImpl networkIdState = getRoutingAddressStatusForRoutingRule(rule, affectedPc);
            if (networkIdState != null) {
                NetworkIdState prevNetworkIdState = res.get(rule.getNetworkId());
                if (prevNetworkIdState != null) {
                    if (prevNetworkIdState.isAvailable()) {
                        if (networkIdState.isAvailable()) {
                            if (prevNetworkIdState.getCongLevel() < networkIdState.getCongLevel()) {
                                res.put(rule.getNetworkId(), networkIdState);
                            }
                        } else {
                            res.put(rule.getNetworkId(), networkIdState);
                        }
                    }
                } else {
                    res.put(rule.getNetworkId(), networkIdState);
                }
            }
        }

        return res;
    }

    private NetworkIdStateImpl getRoutingAddressStatusForRoutingRule(Rule rule, int affectedPc) {
        SccpAddress translationAddressPri = getRoutingAddress(rule.getPrimaryAddressId());
        NetworkIdStateImpl rspStatusPri = getRoutingAddressStatusForRoutingAddress(translationAddressPri, affectedPc);

        if (rule.getRuleType() == RuleType.DOMINANT || rule.getRuleType() == RuleType.LOADSHARED) {
            SccpAddress translationAddressSec = getRoutingAddress(rule.getSecondaryAddressId());
            NetworkIdStateImpl rspStatusSec = getRoutingAddressStatusForRoutingAddress(translationAddressSec, affectedPc);

            if (rspStatusPri.isAffectedByPc() || rspStatusSec.isAffectedByPc()) {
                if (rule.getRuleType() == RuleType.DOMINANT) {
                    if (rspStatusPri.isAvailable())
                        return rspStatusPri;

                    return rspStatusSec;
                }
                if (rule.getRuleType() == RuleType.LOADSHARED) {
                    if (rspStatusPri.isAvailable()) {
                        if (rspStatusSec.isAvailable()) {
                            if (rspStatusPri.getCongLevel() >= rspStatusSec.getCongLevel())
                                return rspStatusPri;
                            else
                                return rspStatusSec;
                        } else {
                            return rspStatusPri;
                        }
                    } else {
                        if (rspStatusSec.isAvailable()) {
                            return rspStatusSec;
                        } else {
                            // both are prohibited - we can select any response
                            return rspStatusPri;
                        }
                    }
                }
            } else {
                return null;
            }
        } else {
            if (rspStatusPri.isAffectedByPc())
                return rspStatusPri;
            else
                return null;
        }

        return null;
    }

    private NetworkIdStateImpl getRoutingAddressStatusForRoutingAddress(SccpAddress routingAddress, int affectedPc) {
        if (routingAddress != null && routingAddress.getAddressIndicator().isPCPresent()) {
            boolean affectedByPc = true;
            if ((affectedPc >= 0 && routingAddress.getSignalingPointCode() != affectedPc))
                affectedByPc = false;
            boolean spcIsLocal = router.spcIsLocal(routingAddress.getSignalingPointCode());
            if (spcIsLocal) {
                return new NetworkIdStateImpl(affectedByPc);
            }

            RemoteSignalingPointCode remoteSpc = sccpStack.getSccpResource().getRemoteSpcByPC(
                    routingAddress.getSignalingPointCode());
            if (remoteSpc == null) {
                return new NetworkIdStateImpl(affectedByPc);
            }
            if (remoteSpc.isRemoteSpcProhibited()) {
                return new NetworkIdStateImpl(false, affectedByPc);
            }
            int congLevel = SccpCongestionControl.generateSccpUserCongLevel(remoteSpc.getCurrentRestrictionLevel());
            if (congLevel > 0) {
                return new NetworkIdStateImpl(congLevel, affectedByPc);
            }
            return new NetworkIdStateImpl(affectedByPc);
        }

        // we return here value that this affectedPc does not affect this rule
        return new NetworkIdStateImpl(false);
    }

    /**
     * Persist
     */
    public void store() {

        // TODO : Should we keep reference to Objects rather than recreating
        // everytime?
        try {
            XMLObjectWriter writer = XMLObjectWriter.newInstance(new FileOutputStream(persistFile.toString()));
            writer.setBinding(binding);
            writer.setIndentation(TAB_INDENT);

            writer.write(rulesMap, RULE, RuleMap.class);
            writer.write(routingAddresses, ROUTING_ADDRESS, SccpAddressMap.class);

            writer.close();
        } catch (Exception e) {
            logger.error("Error while persisting the Rule state in file", e);
        }
    }

    /**
     * Load and create LinkSets and Link from persisted file
     *
     * @throws Exception
     */
    protected void load() {

        try {
            File f = new File(persistFile.toString());
            if (f.exists()) {
                loadVer34(persistFile.toString());
            } else {
                String s1 = persistFile.toString().replace("3_ext.xml", "2_ext.xml");
                f = new File(s1);

                if (f.exists()) {
                    loadVer34(s1);
                    this.store();
                    f.delete();
                } else {
                    s1 = persistFile.toString().replace("3_ext.xml", "_ext.xml");
                    f = new File(s1);

                    if (f.exists()) {
                        if (!loadVer1(s1)) {
                            loadVer2(s1);
                        }
                    }

                    this.store();
                    f.delete();
                }
            }

//            File f = new File(persistFile.toString());
//            if (f.exists()) {
//                // we have V3 config
//                loadVer3(persistFile.toString());
//            } else {
//                String s1 = persistFile.toString().replace("2.xml", ".xml");
//                f = new File(s1);
//
//                if (f.exists()) {
//                    if (!loadVer1(s1)) {
//                        loadVer2(s1);
//                    }
//                }
//
//                this.store();
//                f.delete();
//            }
        } catch (XMLStreamException ex) {
            ex.printStackTrace();
            logger.error(String.format("Failed to load the SS7 configuration file. \n%s", ex.getMessage()));
        } catch (FileNotFoundException e) {
            logger.warn(String.format("Failed to load the SS7 configuration file. \n%s", e.getMessage()));
        } catch (IOException e) {
            logger.error(String.format("Failed to load the SS7 configuration file. \n%s", e.getMessage()));
        }
    }

    private void moveBackupToRoutingAddress(SccpAddressMap<Integer, SccpAddress> backupAddresses) {
        FastMap<Integer, Integer> lstChange = new FastMap<Integer, Integer>();
        for (Integer bId : backupAddresses.keySet()) {
            SccpAddress addr = backupAddresses.get(bId);

            int i1 = bId + 100;
            while (true) {
                if (routingAddresses.get(i1) == null)
                    break;
                i1++;
            }
            routingAddresses.putEntry(i1, (SccpAddressImpl) addr);
            lstChange.putEntry(bId, i1);
        }

        for (Rule rule : rulesMap.values()) {
            Integer newVal = lstChange.get(rule.getSecondaryAddressId());
            if (newVal != null) {
                ((RuleImpl) rule).setSecondaryAddressId(newVal);
            }
        }
    }

    private boolean loadVer1(String fn) throws XMLStreamException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fn)));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String s1 = br.readLine();
            if (s1 == null)
                break;
            sb.append(s1);
            sb.append("\n");
        }
        br.close();
        String s2 = sb.toString();
        s2 = s2.replace("type=\"org.restcomm.protocols.ss7.sccp.parameter.NoGlobalTitle\"", "type=\"NoGlobalTitle\"");

        s2 = s2.replace("type=\"rule\"", "");
        s2 = s2.replace("pattern type=\"org.restcomm.protocols.ss7.sccp.parameter.SccpAddress\"", "patternSccpAddress");
        s2 = s2.replace("ai type=\"org.restcomm.protocols.ss7.indicator.AddressIndicator\" ai=", "ai value=");
        s2 = s2.replace("gt type=\"org.restcomm.protocols.ss7.sccp.parameter.", "gt type=\"");
        s2 = s2.replace("Key type=\"java.lang.Integer\"", "id");
        s2 = s2.replace("Value", "value");
        s2 = s2.replace("/pattern", "/patternSccpAddress");
        s2 = s2.replace("value type=\"org.restcomm.protocols.ss7.sccp.parameter.SccpAddress\"", "sccpAddress");
        s2 = s2.replace("</value>\r\n</primaryAddress>", "</sccpAddress>\r\n</primaryAddress>");
        s2 = s2.replace("</value>\n</primaryAddress>", "</sccpAddress>\n</primaryAddress>");
        s2 = s2.replace("</value>\r\n</backupAddress>", "</sccpAddress>\r\n</backupAddress>");
        s2 = s2.replace("</value>\n</backupAddress>", "</sccpAddress>\n</backupAddress>");
        s2 = s2.replace("type=\"org.restcomm.protocols.ss7.sccp.parameter.", "type=\"");
        s2 = s2.replace("type=\"org.restcomm.protocols.ss7.sccp.impl.router.Mtp3ServiceAccessPoint\"", "");
        s2 = s2.replace("javolution.util.FastMap", "mtp3DestinationMap");
        s2 = s2.replace("type=\"org.restcomm.protocols.ss7.sccp.impl.router.Mtp3Destination\"", "");

        StringReader sr = new StringReader(s2);
        XMLObjectReader reader = XMLObjectReader.newInstance(sr);

        reader.setBinding(binding);

        XMLBinding binding2 = new XMLBinding();
        binding2.setClassAttribute(CLASS_ATTRIBUTE);

        String BACKUP_ADDRESS_V2 = "backupAddress";
        String ROUTING_ADDRESS_V2 = "primaryAddress";

        try {
            rulesMap = reader.read(RULE, RuleMap.class);
        } catch (XMLStreamException e) {
            return false;
        }
        routingAddresses = reader.read(ROUTING_ADDRESS_V2, SccpAddressMap.class);
        SccpAddressMap<Integer, SccpAddress> backupAddresses = reader.read(BACKUP_ADDRESS_V2, SccpAddressMap.class);

//        longMessageRules = reader.read(LONG_MESSAGE_RULE, LongMessageRuleMap.class);
//        saps = reader.read(MTP3_SERVICE_ACCESS_POINT, Mtp3ServiceAccessPointMap.class);

//        for (FastMap.Entry<Integer, Mtp3ServiceAccessPoint> e = this.saps.head(), end = this.saps.tail(); (e = e.getNext()) != end;) {
//            Mtp3ServiceAccessPoint sap = e.getValue();
//            ((Mtp3ServiceAccessPointImpl)sap).setStackName(name);
//        }

        reader.close();

        moveBackupToRoutingAddress(backupAddresses);

        return true;
    }

    private void loadVer2(String fn) throws XMLStreamException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fn)));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String s1 = br.readLine();
            if (s1 == null)
                break;
            sb.append(s1);
            sb.append("\n");
        }
        br.close();
        String s2 = sb.toString();
        s2 = s2.replace("type=\"org.restcomm.protocols.ss7.sccp.parameter.NoGlobalTitle\"", "type=\"NoGlobalTitle\"");

        StringReader sr = new StringReader(s2);
        XMLObjectReader reader = XMLObjectReader.newInstance(sr);

        String ROUTING_ADDRESS_V2 = "primaryAddress";
        String BACKUP_ADDRESS_V2 = "backupAddress";

        reader.setBinding(binding);
        rulesMap = reader.read(RULE, RuleMap.class);
        routingAddresses = reader.read(ROUTING_ADDRESS_V2, SccpAddressMap.class);
        SccpAddressMap<Integer, SccpAddress> backupAddresses = reader.read(BACKUP_ADDRESS_V2, SccpAddressMap.class);

//        longMessageRules = reader.read(LONG_MESSAGE_RULE, LongMessageRuleMap.class);
//        saps = reader.read(MTP3_SERVICE_ACCESS_POINT, Mtp3ServiceAccessPointMap.class);
//
//        for (FastMap.Entry<Integer, Mtp3ServiceAccessPoint> e = this.saps.head(), end = this.saps.tail(); (e = e.getNext()) != end;) {
//            Mtp3ServiceAccessPoint sap = e.getValue();
//            ((Mtp3ServiceAccessPointImpl)sap).setStackName(name);
//        }

        reader.close();

        moveBackupToRoutingAddress(backupAddresses);
    }

    protected void loadVer34(String fn) throws XMLStreamException, FileNotFoundException {
        XMLObjectReader reader = XMLObjectReader.newInstance(new FileInputStream(fn));

        reader.setBinding(binding);
        loadVer4(reader);
    }

    protected void loadVer4(XMLObjectReader reader) throws XMLStreamException{
        rulesMap = reader.read(RULE, RuleMap.class);
        routingAddresses = reader.read(ROUTING_ADDRESS, SccpAddressMap.class);

        reader.close();
    }

    public static void makeOldConfigCopy(String persistDir, String name) {
        TextBuilder persistFile = new TextBuilder();

        if (persistDir != null) {
            persistFile.append(persistDir).append(File.separator).append(name).append("_").append(PERSIST_FILE_NAME);
        } else {
            persistFile.append(System.getProperty(SCCP_ROUTER_PERSIST_DIR_KEY, System.getProperty(USER_DIR_KEY)))
                    .append(File.separator).append(name).append("_").append(PERSIST_FILE_NAME);
        }

        String s1 = persistFile.toString().replace("3_ext.xml", "2.xml");
        File f1 = new File(s1);
        if (f1.exists()) {
            String s2 = persistFile.toString().replace("3_ext.xml", "2_ext.xml");
            File f2 = new File(s2);
            try {
                Files.copy(f1.toPath(), f2.toPath());
            } catch (Exception e) {
                // we ignore errors here
            }
        }
    }
}
