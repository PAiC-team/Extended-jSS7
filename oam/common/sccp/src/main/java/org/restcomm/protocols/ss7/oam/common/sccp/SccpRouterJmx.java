
package org.restcomm.protocols.ss7.oam.common.sccp;

import org.restcomm.protocols.ss7.indicator.AddressIndicator;
import org.restcomm.protocols.ss7.indicator.NatureOfAddress;
import org.restcomm.protocols.ss7.indicator.NumberingPlan;
import org.restcomm.protocols.ss7.sccp.LoadSharingAlgorithm;
import org.restcomm.protocols.ss7.sccp.LongMessageRule;
import org.restcomm.protocols.ss7.sccp.LongMessageRuleType;
import org.restcomm.protocols.ss7.sccp.Mtp3ServiceAccessPoint;
import org.restcomm.protocols.ss7.sccp.OriginationType;
import org.restcomm.protocols.ss7.sccp.Router;
import org.restcomm.protocols.ss7.sccp.Rule;
import org.restcomm.protocols.ss7.sccp.RuleType;
import org.restcomm.protocols.ss7.sccp.SccpProtocolVersion;
import org.restcomm.protocols.ss7.sccp.SccpProvider;
import org.restcomm.protocols.ss7.sccp.parameter.GlobalTitle;
import org.restcomm.protocols.ss7.sccp.parameter.ParameterFactory;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.sccpext.router.RouterExt;

import java.util.Map;

/**
 * @author Amit Bhayani
 *
 */
public class SccpRouterJmx implements SccpRouterJmxMBean {

    private final Router wrappedRouter;
    private final RouterExt wrappedRouterExt;
    private final ParameterFactory parameterFactory;

    public SccpRouterJmx(final Router wrappedRouter, final RouterExt wrappedRouterExt, final SccpProvider sccpProvider) {
        this.wrappedRouter = wrappedRouter;
        this.wrappedRouterExt = wrappedRouterExt;
        this.parameterFactory = sccpProvider.getParameterFactory();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.sccp.Router#addLongMessageRule(int, int, int,
     * org.restcomm.protocols.ss7.sccp.LongMessageRuleType)
     */
    @Override
    public void addLongMessageRule(int id, int firstSpc, int lastSpc, LongMessageRuleType ruleType) throws Exception {
        this.wrappedRouter.addLongMessageRule(id, firstSpc, lastSpc, ruleType);
    }

    @Override
    public void addSccpLongMessageRule(int id, int firstSpc, int lastSpc, String ruleType) throws Exception {
        LongMessageRuleType currRuleType = LongMessageRuleType.valueOf(ruleType);
        this.wrappedRouter.addLongMessageRule(id, firstSpc, lastSpc, currRuleType);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.sccp.Router#addMtp3Destination(int, int, int, int, int, int, int)
     */
    @Override
    public void addMtp3Destination(int sapId, int destId, int firstDpc, int lastDpc, int firstSls, int lastSls, int slsMask) throws Exception {
        this.wrappedRouter.addMtp3Destination(sapId, destId, firstDpc, lastDpc, firstSls, lastSls, slsMask);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.sccp.Router#addMtp3ServiceAccessPoint(int, int, int, int)
     */
    @Override
    public void addMtp3ServiceAccessPoint(int id, int mtp3Id, int opc, int ni, int networkId, String localGtDigits) throws Exception {
        this.wrappedRouter.addMtp3ServiceAccessPoint(id, mtp3Id, opc, ni, networkId, localGtDigits);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.sccp.Router#addRoutingAddress(int,
     * org.restcomm.protocols.ss7.sccp.parameter.SccpAddress)
     */
    @Override
    public void addRoutingAddress(int id, SccpAddress routingAddress) throws Exception {
        if (wrappedRouterExt != null)
            this.wrappedRouterExt.addRoutingAddress(id, routingAddress);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.sccp.Router#addRule(int, org.restcomm.protocols.ss7.sccp.RuleType,
     * org.restcomm.protocols.ss7.sccp.LoadSharingAlgorithm, org.restcomm.protocols.ss7.sccp.OriginationType,
     * org.restcomm.protocols.ss7.sccp.parameter.SccpAddress, java.lang.String, int, int, java.lang.Integer)
     */
    //RuleType;LoadSharingAlgorithm;OriginationType;SccpAddress;String;Integer;SccpAddress;
    //int,RuleType,LoadSharingAlgorithm,OriginationType,SccpAddress,String, int pAddressId, int sAddressId, Integer newCallingPartyAddressAddressId, int networkId, SccpAddress patternCallingAddress
    @Override
    public void addRule(int id, RuleType ruleType, LoadSharingAlgorithm algo, OriginationType originationType,
            SccpAddress pattern, String mask, int pAddressId, int sAddressId, Integer newCallingPartyAddressAddressId,
            int networkId, SccpAddress patternCallingAddress) throws Exception {
        if (wrappedRouterExt != null)
            this.wrappedRouterExt.addRule(id, ruleType, algo, originationType, pattern, mask, pAddressId, sAddressId,
                    newCallingPartyAddressAddressId, networkId, patternCallingAddress);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.sccp.Router#getLongMessageRule(int)
     */
    @Override
    public LongMessageRule getLongMessageRule(int id) {
        return this.wrappedRouter.getLongMessageRule(id);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.sccp.Router#getLongMessageRules()
     */
    @Override
    public Map<Integer, LongMessageRule> getLongMessageRules() {
        return this.wrappedRouter.getLongMessageRules();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.sccp.Router#getMtp3ServiceAccessPoint(int)
     */
    @Override
    public Mtp3ServiceAccessPoint getMtp3ServiceAccessPoint(int id) {
        return this.wrappedRouter.getMtp3ServiceAccessPoint(id);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.sccp.Router#getMtp3ServiceAccessPoints()
     */
    @Override
    public Map<Integer, Mtp3ServiceAccessPoint> getMtp3ServiceAccessPoints() {
        return this.wrappedRouter.getMtp3ServiceAccessPoints();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.sccp.Router#getRoutingAddress(int)
     */
    @Override
    public SccpAddress getRoutingAddress(int id) {
        if (wrappedRouterExt != null)
            return this.wrappedRouterExt.getRoutingAddress(id);
        else
            return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.sccp.Router#getRoutingAddresses()
     */
    @Override
    public Map<Integer, SccpAddress> getRoutingAddresses() {
        if (wrappedRouterExt != null)
            return this.wrappedRouterExt.getRoutingAddresses();
        else
            return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.sccp.Router#getRule(int)
     */
    @Override
    public Rule getRule(int id) {
        if (wrappedRouterExt != null)
            return this.wrappedRouterExt.getRule(id);
        else
            return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.sccp.Router#getRules()
     */
    @Override
    public Map<Integer, Rule> getRules() {
        if (wrappedRouterExt != null)
            return this.wrappedRouterExt.getRules();
        else
            return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.sccp.Router#modifyLongMessageRule(int, int, int,
     * org.restcomm.protocols.ss7.sccp.LongMessageRuleType)
     */
    @Override
    public void modifyLongMessageRule(int id, int firstSpc, int lastSpc, LongMessageRuleType ruleType) throws Exception {
        this.wrappedRouter.modifyLongMessageRule(id, firstSpc, lastSpc, ruleType);
    }

    @Override
    public void modifySccpLongMessageRule(int id, int firstSpc, int lastSpc, String ruleType) throws Exception {
        LongMessageRuleType currRuleType = LongMessageRuleType.valueOf(ruleType);
        this.wrappedRouter.modifyLongMessageRule(id, firstSpc, lastSpc, currRuleType);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.sccp.Router#modifyMtp3Destination(int, int, int, int, int, int, int)
     */
    @Override
    public void modifyMtp3Destination(int sapId, int destId, int firstDpc, int lastDpc, int firstSls, int lastSls, int slsMask) throws Exception {
        if (wrappedRouterExt != null)
            this.wrappedRouter.modifyMtp3Destination(sapId, destId, firstDpc, lastDpc, firstSls, lastSls, slsMask);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.sccp.Router#modifyMtp3ServiceAccessPoint(int, int, int, int)
     */
    @Override
    public void modifyMtp3ServiceAccessPoint(int id, int mtp3Id, int opc, int ni, int networkId, String localGtDigits) throws Exception {
        if (wrappedRouterExt != null)
            this.wrappedRouter.modifyMtp3ServiceAccessPoint(id, mtp3Id, opc, ni, networkId, localGtDigits);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.sccp.Router#modifyRoutingAddress(int,
     * org.restcomm.protocols.ss7.sccp.parameter.SccpAddress)
     */
    @Override
    public void modifyRoutingAddress(int routingAddressId, SccpAddress routingAddress) throws Exception {
        if (wrappedRouterExt != null)
            this.wrappedRouterExt.modifyRoutingAddress(routingAddressId, routingAddress);
    }

    @Override
    public void modifySccpRoutingAddress(int id, int ai, int pc, int ssn, int tt, int np, int nao, String digits) throws Exception {
        SccpAddress sccpAddress = this.createSccpAddress(ai, pc, ssn, tt, np, nao, digits);
        if (wrappedRouterExt != null)
            this.wrappedRouterExt.modifyRoutingAddress(id, sccpAddress);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.sccp.Router#modifyRule(int, org.restcomm.protocols.ss7.sccp.RuleType,
     * org.restcomm.protocols.ss7.sccp.LoadSharingAlgorithm, org.restcomm.protocols.ss7.sccp.OriginationType,
     * org.restcomm.protocols.ss7.sccp.parameter.SccpAddress, java.lang.String, int, int, java.lang.Integer)
     */
    @Override
    public void modifyRule(int id, RuleType ruleType, LoadSharingAlgorithm algo, OriginationType originationType,
            SccpAddress pattern, String mask, int pAddressId, int sAddressId, Integer newCallingPartyAddressAddressId,
            int networkId, SccpAddress patternCallingAddress
                           ) throws Exception {
        if (wrappedRouterExt != null)
            this.wrappedRouterExt.modifyRule(id, ruleType, algo, originationType, pattern, mask, pAddressId, sAddressId,
                    newCallingPartyAddressAddressId, networkId, patternCallingAddress);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.sccp.Router#removeLongMessageRule(int)
     */
    @Override
    public void removeLongMessageRule(int id) throws Exception {
        this.wrappedRouter.removeLongMessageRule(id);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.sccp.Router#removeMtp3Destination(int, int)
     */
    @Override
    public void removeMtp3Destination(int sapId, int destId) throws Exception {
        this.wrappedRouter.removeMtp3Destination(sapId, destId);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.sccp.Router#removeMtp3ServiceAccessPoint(int)
     */
    @Override
    public void removeMtp3ServiceAccessPoint(int id) throws Exception {
        if (wrappedRouterExt != null)
            this.wrappedRouter.removeMtp3ServiceAccessPoint(id);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.sccp.Router#removeRoutingAddress(int)
     */
    @Override
    public void removeRoutingAddress(int id) throws Exception {
        if (wrappedRouterExt != null)
            this.wrappedRouterExt.removeRoutingAddress(id);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.sccp.Router#removeRule(int)
     */
    @Override
    public void removeRule(int id) throws Exception {
        if (wrappedRouterExt != null)
            this.wrappedRouterExt.removeRule(id);
    }

    @Override
    public void addRoutingAddress(int id, int ai, int pc, int ssn, int tt, int np, int nao, String digits) throws Exception {
        SccpAddress sccpAddress = this.createSccpAddress(ai, pc, ssn, tt, np, nao, digits);
        if (wrappedRouterExt != null)
            this.wrappedRouterExt.addRoutingAddress(id, sccpAddress);
    }

    private SccpAddress createSccpAddress(int ai, int pc, int ssn, int tt, int np, int nao, String digits) throws Exception {
        AddressIndicator aiObj = new AddressIndicator((byte) ai, SccpProtocolVersion.ITU);

//        if (aiObj.isSSNPresent() && ssn == 0) {
//            throw new Exception(String.format("Address Indicator %d indicates that SSN is present, however SSN passed is 0", ai));
//        }

        if (aiObj.isPCPresent() && pc < 1) {
            throw new Exception(String.format("Address Indicator %d indicates that PointCode is present, however PointCode passed is < 1", ai));
        }

        if (aiObj.getGlobalTitleIndicator() == null) {
            throw new Exception("GlobalTitle type is not recognized, possible bad AddressIndicator value");
        }

        NumberingPlan npObj = NumberingPlan.valueOf(np);
        NatureOfAddress naiObj = NatureOfAddress.valueOf(nao);
        //TODO: encoding scheme?
        GlobalTitle gt = null;

        switch (aiObj.getGlobalTitleIndicator()) {
            case GLOBAL_TITLE_INCLUDES_NATURE_OF_ADDRESS_INDICATOR_ONLY:
                gt = this.parameterFactory.createGlobalTitle(digits,naiObj);
                break;
            case GLOBAL_TITLE_INCLUDES_TRANSLATION_TYPE_ONLY:
                gt = this.parameterFactory.createGlobalTitle(digits,tt );
                break;
            case GLOBAL_TITLE_INCLUDES_TRANSLATION_TYPE_NUMBERING_PLAN_AND_ENCODING_SCHEME:
                gt = this.parameterFactory.createGlobalTitle(digits,tt, npObj,null);
                break;
            case GLOBAL_TITLE_INCLUDES_TRANSLATION_TYPE_NUMBERING_PLAN_ENCODING_SCHEME_AND_NATURE_OF_ADDRESS:
                gt = this.parameterFactory.createGlobalTitle(digits,tt, npObj, null, naiObj);
                break;

            case NO_GLOBAL_TITLE_INCLUDED:
                gt = this.parameterFactory.createGlobalTitle(digits);
                break;
        }

        return this.parameterFactory.createSccpAddress(aiObj.getRoutingIndicator(), gt, pc, ssn);
    }

    @Override
    public void addRule(int id, String ruleType, String algo, String originationType, int ai, int pc, int ssn, int tt, int np,
            int nao, String digits, String mask, int pAddressId, int sAddressId, int newCallingPartyAddressAddressId, int networkId,
                        int callingai, int callingpc, int callingssn, int callingtt, int callingnp,int callingnao, String callingdigits)
            throws Exception {

        SccpAddress patternAddress = this.createSccpAddress(ai, pc, ssn, tt, np, nao, digits);

        SccpAddress patternAddressCalling = null;
        if (callingdigits != null && !callingdigits.isEmpty()) {
            patternAddressCalling = this.createSccpAddress(callingai, callingpc, callingssn, callingtt, callingnp, callingnao,
                    callingdigits);
        }

        if (wrappedRouterExt != null)
            this.wrappedRouterExt.addRule(id, RuleType.getInstance(ruleType), LoadSharingAlgorithm.getInstance(algo),
                    OriginationType.getInstance(originationType), patternAddress, mask, pAddressId, sAddressId,
                    newCallingPartyAddressAddressId == -1 ? null : newCallingPartyAddressAddressId, networkId,
                    patternAddressCalling);
    }

    @Override
    public void modifySccpRule(int id, String ruleType, String algo, String originationType, int ai, int pc, int ssn, int tt, int np,
            int nao, String digits, String mask, int pAddressId, int sAddressId, int newCallingPartyAddressAddressId, int networkId,
                        int callingai, int callingpc, int callingssn, int callingtt, int callingnp,int callingnao, String callingdigits)
            throws Exception {

        SccpAddress patternAddress = this.createSccpAddress(ai, pc, ssn, tt, np, nao, digits);

        SccpAddress patternAddressCalling = null;
        if (callingdigits != null && !callingdigits.isEmpty()) {
            patternAddressCalling = this.createSccpAddress(callingai, callingpc, callingssn, callingtt, callingnp, callingnao,
                    callingdigits);
        }

        if (wrappedRouterExt != null)
            this.wrappedRouterExt.modifyRule(id, RuleType.getInstance(ruleType), LoadSharingAlgorithm.getInstance(algo),
                    OriginationType.getInstance(originationType), patternAddress, mask, pAddressId, sAddressId,
                    newCallingPartyAddressAddressId == -1 ? null : newCallingPartyAddressAddressId, networkId,
                    patternAddressCalling);
    }

    @Override
    public boolean spcIsLocal(int spc) {
        return this.wrappedRouter.spcIsLocal(spc);
    }

    /* (non-Javadoc)
     * @see org.restcomm.protocols.ss7.sccpext.router.RouterExt#modifyRoutingAddress(int, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String)
     */
    @Override
    public void modifyRoutingAddress(int routingAddressId, Integer ai, Integer pc, Integer ssn, Integer tt, Integer npValue,
            Integer naiValue, String digits) throws Exception {
        this.wrappedRouterExt.modifyRoutingAddress(routingAddressId, ai, pc, ssn, tt, npValue, naiValue, digits);
    }

    /* (non-Javadoc)
     * @see org.restcomm.protocols.ss7.sccpext.router.RouterExt#modifyRule(int, org.restcomm.protocols.ss7.sccp.RuleType, org.restcomm.protocols.ss7.sccp.LoadSharingAlgorithm, org.restcomm.protocols.ss7.sccp.OriginationType, org.restcomm.protocols.ss7.sccp.parameter.SccpAddress, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, org.restcomm.protocols.ss7.sccp.parameter.SccpAddress)
     */
    @Override
    public void modifyRule(int id, RuleType ruleType, LoadSharingAlgorithm algo, OriginationType originationType,
            SccpAddress pattern, String mask, Integer pAddressId, Integer sAddressId, Integer newCallingPartyAddressAddressId,
            Integer networkId, SccpAddress patternCallingAddress) throws Exception {
        this.wrappedRouterExt.modifyRule(id, ruleType, algo, originationType, pattern, mask, pAddressId, sAddressId, newCallingPartyAddressAddressId, networkId, patternCallingAddress);
    }

//    @Override
//    public void modifyMtp3ServiceAccessPoint(int id, Integer mtp3Id, Integer opc, Integer ni, Integer networkId,
//            String localGtDigits) throws Exception {
//        this.wrappedRouter.modifyMtp3ServiceAccessPoint(id, mtp3Id, opc, ni, networkId, localGtDigits);
//    }

//    @Override
//    public void modifyMtp3Destination(int sapId, int destId, Integer firstDpc, Integer lastDpc, Integer firstSls,
//            Integer lastSls, Integer slsMask) throws Exception {
//        this.wrappedRouter.modifyMtp3Destination(sapId, destId, firstDpc, lastDpc, firstSls, lastSls, slsMask);
//    }

    @Override
    public void modifyLongMessageRule(int id, Integer firstSpc, Integer lastSpc, LongMessageRuleType ruleType) throws Exception {
        this.wrappedRouter.modifyLongMessageRule(id, firstSpc, lastSpc, ruleType);
    }

}
