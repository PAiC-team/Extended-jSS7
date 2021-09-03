
package org.restcomm.protocols.ss7.sccpext.impl.oam;

import java.util.Arrays;
import java.util.Map;

import org.restcomm.protocols.ss7.indicator.AddressIndicator;
import org.restcomm.protocols.ss7.indicator.NatureOfAddress;
import org.restcomm.protocols.ss7.indicator.NumberingPlan;
import org.restcomm.protocols.ss7.sccp.LoadSharingAlgorithm;
import org.restcomm.protocols.ss7.sccp.OriginationType;
import org.restcomm.protocols.ss7.sccp.Rule;
import org.restcomm.protocols.ss7.sccp.RuleType;
import org.restcomm.protocols.ss7.sccp.SccpProtocolVersion;
import org.restcomm.protocols.ss7.sccp.impl.SccpStackImpl;
import org.restcomm.protocols.ss7.sccp.impl.Ss7ExtSccpDetailedInterface;
import org.restcomm.protocols.ss7.sccp.impl.oam.SccpExecutor;
import org.restcomm.protocols.ss7.sccp.impl.oam.SccpOAMMessage;
import org.restcomm.protocols.ss7.sccp.impl.parameter.SccpAddressImpl;
import org.restcomm.protocols.ss7.sccp.parameter.GlobalTitle;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.sccpext.impl.SccpExtModuleImpl;
import org.restcomm.protocols.ss7.sccpext.impl.router.RouterExtImpl;

/**
*
* @author amit bhayani
* @author sergey vetyutnev
*/
public class SccpExecutorExt extends SccpExecutor {

    private RouterExtImpl routerExt = null;

    public SccpExecutorExt() {

    }

    private void fillStackRouter(SccpStackImpl sccpStackImpl) {
        this.sccpStack = sccpStackImpl;
        fillStackRouter2();
    }

    private void fillStackRouter2() {
        Ss7ExtSccpDetailedInterface ss7ExtSccpDetailedInterface = (Ss7ExtSccpDetailedInterface) sccpStack
                .getSs7ExtSccpInterface();
        if (ss7ExtSccpDetailedInterface != null && ss7ExtSccpDetailedInterface instanceof SccpExtModuleImpl) {
            SccpExtModuleImpl sccpExtModule = (SccpExtModuleImpl) ss7ExtSccpDetailedInterface;
            this.routerExt = (RouterExtImpl) sccpExtModule.getRouterExt();
        } else {
            this.routerExt = null;
        }
    }

    public String execute(String[] options) {
        if (this.sccpStacks.size() == 0) {
            logger.warn("SCCP stack not set. Command will not be executed ");
            return SccpOAMMessage.SERVER_ERROR;
        }

        // Atleast 1 option is passed?
        if (options == null || options.length < 2) {
            return SccpOAMMessage.INVALID_COMMAND;
        }

        String firstOption = options[1];

        if (firstOption == null) {
            return SccpOAMMessage.INVALID_COMMAND;
        }

        try {
            if (firstOption.equals("rule")) {
                return this.manageRule(options);
            } else if (firstOption.equals("address")) {
                return this.manageAddress(options);
            }
        } catch (Throwable t) {
            logger.error(String.format("Error while executing command %s", Arrays.toString(options)), t);
            return t.getMessage();
        }

        return super.execute(options);
    }

    private String manageAddress(String[] options) throws Exception {
        // Minimum 3 needed. Show
        if (options.length < 3) {
            return SccpOAMMessage.INVALID_COMMAND;
        }

        String command = options[2];

        if (command == null) {
            return SccpOAMMessage.INVALID_COMMAND;
        }

        if (command.equals("create")) {
            if (options.length < 4) {
                return SccpOAMMessage.INVALID_COMMAND;
            }
            int addressId = Integer.parseInt(options[3]);

            int count = 11;

            while (count < options.length) {
                String key = options[count++];
                if (key == null) {
                    return SccpOAMMessage.INVALID_COMMAND;
                }

                if (key.equals("stackname")) {
                    String m3uaStackName = options[count++];

                    SccpStackImpl sccpStackImplementation = this.sccpStacks.get(m3uaStackName);

                    if (sccpStackImplementation == null) {
                        return String.format(SccpOAMMessage.NO_SCCP_MANAGEMENT_BEAN_FOR_NAME, m3uaStackName);
                    }

                    fillStackRouter(sccpStackImplementation);
                } else {
                    return SccpOAMMessage.INVALID_COMMAND;
                }

            }

            this.setDefaultValue();
            SccpAddress address = this.createAddress(options, 4, false);

            if (routerExt == null)
                fillStackRouter2();
            if (routerExt != null)
                routerExt.addRoutingAddress(addressId, address);
            else
                return String.format(SccpOAMMessage.NO_SCCP_EXT_MODULE, this.sccpStack.getName());
            return String.format(SccpOAMMessage.ADDRESS_SUCCESSFULLY_ADDED, this.sccpStack.getName());
        } else if (command.equals("modify")) {
            //sccp address modify <id> address-indicator <address-indicator> point-code <point-code> subsystem-number <subsystem-number>
            //translation-type <translation-type> numbering-plan <numbering-plan> nature-of-address-indicator <nature-of-address-indicator>
            //digits <digits> stackname <stack-name>
            if (options.length < 4) {
                return SccpOAMMessage.INVALID_COMMAND;
            }
            int addressId = Integer.parseInt(options[3]);

            Integer ai = null;
            Integer pc = null;
            Integer ssnValue = null;
            Integer tt = null;
            Integer npValue = null;
            Integer naiValue = null;
            String digits = null;

            int count = 4;

            while (count < options.length) {
                String key = options[count++];
                if (key == null) {
                    return SccpOAMMessage.INVALID_COMMAND;
                }

                if (key.equals("stackname")) {
                    String m3uaStackName = options[count++];

                    SccpStackImpl sccpStackImplementation = this.sccpStacks.get(m3uaStackName);

                    if (sccpStackImplementation == null) {
                        return String.format(SccpOAMMessage.NO_SCCP_MANAGEMENT_BEAN_FOR_NAME, m3uaStackName);
                    }

                    fillStackRouter(sccpStackImplementation);
                } else if(key.equals("address-indicator")) {
                    ai = Integer.valueOf(options[count++]);
                } else if(key.equals("point-code")) {
                    pc = Integer.valueOf(options[count++]);
                } else if(key.equals("subsystem-number")) {
                    ssnValue = Integer.valueOf(options[count++]);
                } else if(key.equals("translation-type")) {
                    tt = Integer.valueOf(options[count++]);
                } else if(key.equals("numbering-plan")) {
                    npValue = Integer.valueOf(options[count++]);
                } else if(key.equals("nature-of-address-indicator")) {
                    naiValue = Integer.valueOf(options[count++]);
                } else if(key.equals("digits")) {
                    digits = options[count++];
                } else {
                    return SccpOAMMessage.INVALID_COMMAND;
                }
            }

            this.setDefaultValue();

            if (routerExt == null)
                fillStackRouter2();
            if (routerExt != null)
                routerExt.modifyRoutingAddress(addressId, ai, pc, ssnValue, tt, npValue, naiValue, digits);
            else
                return String.format(SccpOAMMessage.NO_SCCP_EXT_MODULE, this.sccpStack.getName());
            return String.format(SccpOAMMessage.ADDRESS_SUCCESSFULLY_MODIFIED, this.sccpStack.getName());
        } else if (command.equals("delete")) {
            // sccp address delete <id> stackname <stack-name>

            if (options.length < 4) {
                return SccpOAMMessage.INVALID_COMMAND;
            }
            int addressId = Integer.parseInt(options[3]);

            int count = 4;

            while (count < options.length) {
                String key = options[count++];
                if (key == null) {
                    return SccpOAMMessage.INVALID_COMMAND;
                }

                if (key.equals("stackname")) {
                    String m3uaStackName = options[count++];

                    SccpStackImpl sccpStaclImpl = this.sccpStacks.get(m3uaStackName);

                    if (sccpStaclImpl == null) {
                        return String.format(SccpOAMMessage.NO_SCCP_MANAGEMENT_BEAN_FOR_NAME, m3uaStackName);
                    }

                    fillStackRouter(sccpStaclImpl);
                } else {
                    return SccpOAMMessage.INVALID_COMMAND;
                }

            }

            this.setDefaultValue();

            if (routerExt == null)
                fillStackRouter2();
            if (routerExt != null)
                routerExt.removeRoutingAddress(addressId);
            else
                return String.format(SccpOAMMessage.NO_SCCP_EXT_MODULE, this.sccpStack.getName());
            return String.format(SccpOAMMessage.ADDRESS_SUCCESSFULLY_DELETED, this.sccpStack.getName());

        } else if (command.equals("show")) {
            // sccp address show id <id> stackname <stack-name>

            int count = 3;
            int addressId = -1;
            while (count < options.length) {
                String key = options[count++];
                if (key == null) {
                    return SccpOAMMessage.INVALID_COMMAND;
                }

                if (key.equals("stackname")) {
                    String m3uaStackName = options[count++];

                    SccpStackImpl sccpStaclImpl = this.sccpStacks.get(m3uaStackName);

                    if (sccpStaclImpl == null) {
                        return String.format(SccpOAMMessage.NO_SCCP_MANAGEMENT_BEAN_FOR_NAME, m3uaStackName);
                    }

                    fillStackRouter(sccpStaclImpl);
                } else if (key.equals("id")) {
                    addressId = Integer.parseInt(options[count++]);
                } else {
                    return SccpOAMMessage.INVALID_COMMAND;
                }

            }

            this.setDefaultValue();

            if (routerExt == null)
                fillStackRouter2();
            if (addressId != -1) {
                if (routerExt != null) {
                    SccpAddress pa = routerExt.getRoutingAddress(addressId);
                    if (pa == null) {
                        return String.format(SccpOAMMessage.ADDRESS_DOESNT_EXIST, this.sccpStack.getName());
                    }
                    return pa.toString();
                } else
                    return String.format(SccpOAMMessage.NO_SCCP_EXT_MODULE, this.sccpStack.getName());
            }

            if (routerExt != null) {
                if (routerExt.getRoutingAddresses().size() == 0) {
                    return String.format(SccpOAMMessage.ADDRESS_DOESNT_EXIST, this.sccpStack.getName());
                }
            } else
                return String.format(SccpOAMMessage.NO_SCCP_EXT_MODULE, this.sccpStack.getName());

            if (routerExt != null) {
                StringBuffer sb = new StringBuffer();
                Map<Integer, SccpAddress> idVsPrimAdd = routerExt.getRoutingAddresses();
                for (Integer e : idVsPrimAdd.keySet()) {
                    SccpAddress address = idVsPrimAdd.get(e);
                    sb.append("key=");
                    sb.append(e);
                    sb.append("  ");
                    sb.append(address);
                    sb.append("\n");
                }
                return sb.toString();
            } else
                return String.format(SccpOAMMessage.NO_SCCP_EXT_MODULE, this.sccpStack.getName());
        }

        return SccpOAMMessage.INVALID_COMMAND;
    }

    private String manageRule(String[] options) throws Exception {
        // Minimum 3 needed. Show
        if (options.length < 3) {
            return SccpOAMMessage.INVALID_COMMAND;
        }

        String command = options[2];

        if (command == null) {
            return SccpOAMMessage.INVALID_COMMAND;
        }

        if (command.equals("create")) {
            return this.createRule(options);
        } else if (command.equals("modify")) {
            return this.modifyRule(options);
        } else if (command.equals("delete")) {
            return this.deleteRule(options);
        } else if (command.equals("show")) {
            return this.showRule(options);
        }

        return SccpOAMMessage.INVALID_COMMAND;
    }

    /**
     * <p>
     * Command to create new rule.
     *
     * sccp rule create <id> <mask> <address-indicator> <point-code> <subsystem-number> <translation-type> <numbering-plan>
     * <nature-of-address-indicator> <digits> <ruleType> <primary-address-id> backup-addressid <backup-address-id>
     * loadsharing-algo <loadsharing-algorithm> newcgparty-addressid <new-callingPartyAddress-id> origination-type
     * <originationType> networkid <network-id> calling-ai <address-indicator> calling-pc <point-code> calling-ssn <calling-subsystem-number> calling-tt <calling-translation-type> calling-np <calling-numbering-plan>
     * calling-nai <calling-nature-of-address-indicator> calling-digits-pattern <calling-digits-pattern> stackname <stack-name>
     * </p>
     *
     * @param options
     * @return
     * @throws Exception
     */
    private String createRule(String[] options) throws Exception {
        // Minimum is 13
        if (options.length < 14 || options.length > 40) {
            return SccpOAMMessage.INVALID_COMMAND;
        }
        int ruleId = Integer.parseInt(options[3]);
        String mask = options[4];
        if (mask == null) {
            return SccpOAMMessage.INVALID_MASK;
        }

        RuleType ruleType;
        String s1 = options[12].toLowerCase();
        if (s1.equalsIgnoreCase(RuleType.SOLITARY.getValue())) {
            ruleType = RuleType.SOLITARY;
        } else if (s1.equalsIgnoreCase(RuleType.DOMINANT.getValue())) {
            ruleType = RuleType.DOMINANT;
        } else if (s1.equalsIgnoreCase(RuleType.LOADSHARED.getValue())) {
            ruleType = RuleType.LOADSHARED;
        } else if (s1.equals("broadcast")) {
            ruleType = RuleType.BROADCAST;
        } else {
            return SccpOAMMessage.INVALID_COMMAND;
        }

        int pAddressId = Integer.parseInt(options[13]);

        int count = 14;
        int sAddressId = -1;
        Integer newcgpartyAddressId = null;
        LoadSharingAlgorithm algo = LoadSharingAlgorithm.Undefined;
        OriginationType originationType = OriginationType.ALL;
        int networkId = 0;

        // Calling Address fields with default values
        int callingAI = -1;
        int callingPC = -1;
        int callingSSN = -1;
        int callingTT = -1;
        int callingNP = -1;
        int callingNAI = -1;
        String callingDigits = null; // having it default as * means everythign matches

        while (count < options.length) {
            String key = options[count++];
            if (key == null) {
                return SccpOAMMessage.INVALID_COMMAND;
            }

            if (key.equals("loadsharing-algo")) {
                algo = LoadSharingAlgorithm.getInstance(options[count++]);
            } else if (key.equals("backup-addressid")) {
                sAddressId = Integer.parseInt(options[count++]);
            } else if (key.equals("newcgparty-addressid")) {
                newcgpartyAddressId = Integer.parseInt(options[count++]);
            } else if (key.equals("origination-type")) {
                originationType = OriginationType.getInstance(options[count++]);
            } else if (key.equals("stackname")) {
                String m3uaStackName = options[count++];

                SccpStackImpl sccpStaclImpl = this.sccpStacks.get(m3uaStackName);

                if (sccpStaclImpl == null) {
                    return String.format(SccpOAMMessage.NO_SCCP_MANAGEMENT_BEAN_FOR_NAME, m3uaStackName);
                }

                fillStackRouter(sccpStaclImpl);
            } else if (key.equals("networkid")) {
                String networkIdS = options[ count++ ];
                networkId = Integer.parseInt( networkIdS );
            } else if (key.equals( "calling-ai" )) {
                callingAI = Integer.parseInt( options[count++] );
            } else if (key.equals( "calling-pc" )) {
                callingPC = Integer.parseInt( options[count++] );
            } else if (key.equals( "calling-ssn" )) {
                callingSSN = Integer.parseInt( options[count++] );
            } else if (key.equals( "calling-tt" )) {
                callingTT = Integer.parseInt( options[count++] );
            } else if (key.equals( "calling-np" )) {
                callingNP = Integer.parseInt( options[count++] );
            } else if (key.equals( "calling-nai" )) {
                callingNAI = Integer.parseInt( options[count++] );
            } else if (key.equals( "calling-digits-pattern" )) {
                callingDigits = options[count++];
            } else {
                return SccpOAMMessage.INVALID_COMMAND;
            }
        }

        this.setDefaultValue();
        SccpAddress pattern = this.createAddress(options, 5, true);
        SccpAddress callingPattern  = null;
        if ( callingDigits != null && !callingDigits.isEmpty()) {
            callingPattern = this.createAddress( callingAI, callingPC, callingSSN, callingTT, callingNP, callingNAI, callingDigits, true );
        }

        if (routerExt == null)
            fillStackRouter2();
        if (routerExt != null)
            routerExt.addRule(ruleId, ruleType, algo, originationType, pattern, mask, pAddressId, sAddressId,
                    newcgpartyAddressId, networkId, callingPattern);
        else
            return String.format(SccpOAMMessage.NO_SCCP_EXT_MODULE, this.sccpStack.getName());

        return String.format(SccpOAMMessage.RULE_SUCCESSFULLY_ADDED, this.sccpStack.getName());
    }

    private String modifyRule(String[] options) throws Exception {
        // Minimum is 6
//        sccp rule modify <id>
//        mask <mask>
//        address-indicator <address-indicator>
//        point-code <point-code>
//        subsystem-number <subsystem-number>
//        translation-type <translation-type>
//        numbering-plan <numbering-plan>
//        nature-of-address-indicator <nature-of-address-indicator>
//        digits <digits>
//        rule-type <ruleType>
//        primary-address-id <primary-address-id>
//        backup-addressid <backup-address-id>
//        loadsharing-algo <loadsharing-algorithm>
//        newcgparty-addressid <new-callingPartyAddress-id>
//        origination-type <originationType>
//        networkid <networkId>
//        calling-ai <calling-address-indicator>
//        calling-pc <calling-point-code>
//        calling-ssn <calling-subsystem-number>
//        calling-tt <calling-translation-type>
//        calling-np <calling-numbering-plan>
//        calling-nai <calling-nature-of-address-indicator>
//        calling-digits-pattern <calling-digits-pattern>
//        stackname <stack-name>
        if (options.length < 6 || options.length > 50) {
            return SccpOAMMessage.INVALID_COMMAND;
        }
        int ruleId = Integer.parseInt(options[3]);

        String mask = null;
        RuleType ruleType = null;
        Integer pAddressId = null;

        Integer patternAI = null;
        Integer patternPC = null;
        Integer patternSSN = null;
        Integer patternTT = null;
        Integer patternNP = null;
        Integer patternNAI = null;
        String patternDigits = null;

        LoadSharingAlgorithm algo = null;
        Integer sAddressId = null;
        Integer newcgpartyAddressId = null;
        OriginationType originationType = null;
        Integer networkId = null;

        // Calling Address fields with default values
        Integer callingAI = null;
        Integer callingPC = null;
        Integer callingSSN = null;
        Integer callingTT = null;
        Integer callingNP = null;
        Integer callingNAI = null;
        String callingDigits = null; // having it default as null means no matching on callingPattern
        // TODO: Validate the AI/TT/NP/NAI in case callingDigits are provided

        int count = 4;
        while (count < options.length) {
            String key = options[count++];
            if (key == null) {
                return SccpOAMMessage.INVALID_COMMAND;
            }
            if (key.equals("mask")) {
                mask = options[count++];
            } else if (key.equals("rule-type")) {
                String s1 = options[count++].toLowerCase();
                if (s1.equals("solitary")) {
                    ruleType = RuleType.SOLITARY;
                } else if (s1.equals("dominant")) {
                    ruleType = RuleType.DOMINANT;
                } else if (s1.equals("loadshared")) {
                    ruleType = RuleType.LOADSHARED;
                } else if (s1.equals("broadcast")) {
                    ruleType = RuleType.BROADCAST;
                } else {
                    return SccpOAMMessage.INVALID_COMMAND;
                }
            } else if (key.equals( "address-indicator" )) {
                patternAI = Integer.valueOf( options[count++] );
            } else if (key.equals( "point-code" )) {
                patternPC = Integer.valueOf( options[count++] );
            } else if (key.equals( "subsystem-number" )) {
                patternSSN = Integer.valueOf( options[count++] );
            } else if (key.equals( "translation-type" )) {
                patternTT = Integer.valueOf( options[count++] );
            } else if (key.equals( "numbering-plan" )) {
                patternNP = Integer.valueOf( options[count++] );
            } else if (key.equals( "nature-of-address-indicator" )) {
                patternNAI = Integer.valueOf( options[count++] );
            } else if (key.equals( "digits" )) {
                patternDigits = options[count++];
            } else if (key.equals("primary-address-id")) {
                pAddressId = Integer.valueOf(options[count++]);
            }  else if (key.equals("loadsharing-algo")) {
                algo = LoadSharingAlgorithm.getInstance(options[count++]);
            } else if (key.equals("backup-addressid")) {
                sAddressId = Integer.valueOf(options[count++]);
            } else if (key.equals("newcgparty-addressid")) {
                newcgpartyAddressId = Integer.valueOf(options[count++]);
            } else if (key.equals("origination-type")) {
                originationType = OriginationType.getInstance(options[count++]);
            } else if (key.equals("stackname")) {
                String m3uaStackName = options[count++];
                SccpStackImpl sccpStaclImpl = this.sccpStacks.get(m3uaStackName);
                if (sccpStaclImpl == null) {
                    return String.format(SccpOAMMessage.NO_SCCP_MANAGEMENT_BEAN_FOR_NAME, m3uaStackName);
                }
                fillStackRouter(sccpStaclImpl);
            } else if (key.equals("networkid")) {
                String networkIdS = options[count++];
                networkId = Integer.valueOf(networkIdS);
            } else if (key.equals( "calling-ai" )) {
                callingAI = Integer.valueOf( options[count++] );
            } else if (key.equals( "calling-pc" )) {
                callingPC = Integer.valueOf( options[count++] );
            } else if (key.equals( "calling-ssn" )) {
                callingSSN = Integer.valueOf( options[count++] );
            } else if (key.equals( "calling-tt" )) {
                callingTT = Integer.valueOf( options[count++] );
            } else if (key.equals( "calling-np" )) {
                callingNP = Integer.valueOf( options[count++] );
            } else if (key.equals( "calling-nai" )) {
                callingNAI = Integer.valueOf( options[count++] );
            } else if (key.equals( "calling-digits-pattern" )) {
                callingDigits = options[count++];
            }  else {
                return SccpOAMMessage.INVALID_COMMAND;
            }
        }

        this.setDefaultValue();

        SccpAddress pattern = null;

        if (routerExt == null)
            fillStackRouter2();
        if (routerExt != null) {

            Rule rule = routerExt.getRule(ruleId);

            if(rule == null) {
                return String.format(SccpOAMMessage.RULE_DOESNT_EXIST, this.sccpStack.getName());
            }
            SccpAddress callingPattern  = rule.getPatternCallingAddress();
            if(patternAI != null || patternPC != null || patternSSN != null ||
                    patternTT != null || patternNP != null || patternNAI != null || patternDigits != null) {
                pattern = routerExt.modifySccpAddress(rule.getPattern(), patternAI, patternPC, patternSSN, patternTT, patternNP, patternNAI, patternDigits);
            } else {
                pattern = routerExt.getRule(ruleId).getPattern();
            }

            if(callingPattern != null && (callingAI != null || callingPC != null || callingSSN != null || callingTT != null || callingNP != null || callingNAI != null || callingDigits != null)) {
                    callingPattern = routerExt.modifySccpAddress(rule.getPatternCallingAddress(), callingAI, callingPC, callingSSN, callingTT, callingNP, callingNAI, callingDigits);
            } else if(callingPattern == null && callingDigits != null) {
                    callingPattern = this.createAddress( callingAI, callingPC, callingSSN, callingTT, callingNP, callingNAI, callingDigits, true );
            }

            routerExt.modifyRule(ruleId, ruleType, algo, originationType, pattern, mask, pAddressId, sAddressId,
                    newcgpartyAddressId, networkId, callingPattern);
        }
        else
            return String.format(SccpOAMMessage.NO_SCCP_EXT_MODULE, this.sccpStack.getName());
        return String.format(SccpOAMMessage.RULE_SUCCESSFULLY_MODIFIED, this.sccpStack.getName());
    }

    /**
     * Command is "sccp rule delete <id> stackname <stack-name>"
     *
     * @param options
     * @return
     * @throws Exception
     */
    private String deleteRule(String[] options) throws Exception {
        // Minimum is 4
        if (options.length < 4) {
            return SccpOAMMessage.INVALID_COMMAND;
        }
        int ruleId;
        ruleId = Integer.parseInt(options[3]);

        int count = 4;

        while (count < options.length) {
            String key = options[count++];
            if (key == null) {
                return SccpOAMMessage.INVALID_COMMAND;
            }

            if (key.equals("stackname")) {
                String m3uaStackName = options[count++];

                SccpStackImpl sccpStaclImpl = this.sccpStacks.get(m3uaStackName);

                if (sccpStaclImpl == null) {
                    return String.format(SccpOAMMessage.NO_SCCP_MANAGEMENT_BEAN_FOR_NAME, m3uaStackName);
                }

                fillStackRouter(sccpStaclImpl);
            } else {
                return SccpOAMMessage.INVALID_COMMAND;
            }

        }

        this.setDefaultValue();

        if (routerExt == null)
            fillStackRouter2();
        if (routerExt != null)
            routerExt.removeRule(ruleId);
        else
            return String.format(SccpOAMMessage.NO_SCCP_EXT_MODULE, this.sccpStack.getName());

        return String.format(SccpOAMMessage.RULE_SUCCESSFULLY_REMOVED, this.sccpStack.getName());
    }

    /**
     * Command is "sccp rule show id <id> stackname <stack-name>" where id is optional. If id is not passed, all rules
     * configured are shown
     *
     * @param options
     * @return
     * @throws Exception
     */
    private String showRule(String[] options) throws Exception {
        // Minimum is 4
        if (options.length < 3) {
            return SccpOAMMessage.INVALID_COMMAND;
        }

        int count = 3;
        int ruleId = -1;
        while (count < options.length) {
            String key = options[count++];
            if (key == null) {
                return SccpOAMMessage.INVALID_COMMAND;
            }

            if (key.equals("stackname")) {
                String m3uaStackName = options[count++];

                SccpStackImpl sccpStackImpl = this.sccpStacks.get(m3uaStackName);

                if (sccpStackImpl == null) {
                    return String.format(SccpOAMMessage.NO_SCCP_MANAGEMENT_BEAN_FOR_NAME, m3uaStackName);
                }

                fillStackRouter(sccpStackImpl);
            } else if (key.equals("id")) {
                ruleId = Integer.parseInt(options[count++]);
            } else {
                return SccpOAMMessage.INVALID_COMMAND;
            }

        }

        this.setDefaultValue();

        if (routerExt == null)
            fillStackRouter2();
        if (routerExt == null)
            return String.format(SccpOAMMessage.NO_SCCP_EXT_MODULE, this.sccpStack.getName());

        if (ruleId != -1) {
            Rule rule = routerExt.getRule(ruleId);
            if (rule == null) {
                return String.format(SccpOAMMessage.RULE_DOESNT_EXIST, this.sccpStack.getName());
            }
            return rule.toString();
        }

        if (routerExt.getRules().size() == 0) {
            return String.format(SccpOAMMessage.RULE_DOESNT_EXIST, this.sccpStack.getName());
        }

        Map<Integer, Rule> idVsRule = routerExt.getRules();

        StringBuffer sb = new StringBuffer();
        for (Integer e : idVsRule.keySet()) {
            Rule rule = idVsRule.get(e);
            sb.append("key=");
            sb.append(e);
            sb.append("  Rule=");
            sb.append(rule);
            sb.append("\n");
        }

        return sb.toString();
    }

    private SccpAddress createAddress(String[] options, int index, boolean isRule) throws Exception {
        // TODO: add encoding scheme

        // sccp address create <id> <address-indicator> <point-code> <subsystemnumber> <translation-type> <numbering-plan>
        // <nature-of-address-indicator> <digits>
        return createAddress(Integer.parseInt(options[index++]), Integer.parseInt(options[index++]), Integer.parseInt(options[index++]),
                Integer.parseInt(options[index++]), Integer.parseInt(options[index++]), Integer.parseInt(options[index++]),
                options[index++], isRule);
    }

    private SccpAddress createAddress(int ai, int pc, int ssn, int tt, int npValue, int naiValue, String digits, boolean isRule) throws Exception {
        SccpAddress sccpAddress = null;

        AddressIndicator aiObj = new AddressIndicator((byte) ai, SccpProtocolVersion.ITU);

        if (!isRule && pc <= 0) {
            throw new Exception(String.format("Point code parameter is mandatory and must be > 0"));
        }
        if (aiObj.getGlobalTitleIndicator() == null) {
            throw new Exception(String.format("GlobalTitle type is not recognized, possible bad AddressIndicator value"));
        }

        NumberingPlan np = NumberingPlan.valueOf(npValue);
        NatureOfAddress nai = NatureOfAddress.valueOf(naiValue);

        GlobalTitle gt = null;

        switch (aiObj.getGlobalTitleIndicator()) {
            case GLOBAL_TITLE_INCLUDES_NATURE_OF_ADDRESS_INDICATOR_ONLY:
                gt = sccpStack.getSccpProvider().getParameterFactory().createGlobalTitle(digits, nai);
                break;
            case GLOBAL_TITLE_INCLUDES_TRANSLATION_TYPE_ONLY:
                gt = sccpStack.getSccpProvider().getParameterFactory().createGlobalTitle(digits, tt);
                break;
            case GLOBAL_TITLE_INCLUDES_TRANSLATION_TYPE_NUMBERING_PLAN_AND_ENCODING_SCHEME:
                gt = sccpStack.getSccpProvider().getParameterFactory().createGlobalTitle(digits, tt, np, null);
                break;
            case GLOBAL_TITLE_INCLUDES_TRANSLATION_TYPE_NUMBERING_PLAN_ENCODING_SCHEME_AND_NATURE_OF_ADDRESS:
                gt = sccpStack.getSccpProvider().getParameterFactory().createGlobalTitle(digits, tt, np, null, nai);
                break;

            case NO_GLOBAL_TITLE_INCLUDED:
                gt = sccpStack.getSccpProvider().getParameterFactory().createGlobalTitle(digits);
                break;
        }

        sccpAddress = new SccpAddressImpl(aiObj.getRoutingIndicator(), gt, pc, ssn);

        return sccpAddress;
    }

}
