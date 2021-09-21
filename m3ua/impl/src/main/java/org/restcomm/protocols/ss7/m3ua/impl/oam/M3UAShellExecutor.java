
package org.restcomm.protocols.ss7.m3ua.impl.oam;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javolution.util.FastMap;


import org.apache.log4j.Logger;
import org.restcomm.protocols.ss7.m3ua.As;
import org.restcomm.protocols.ss7.m3ua.AspFactory;
import org.restcomm.protocols.ss7.m3ua.ErrorRetryAction;
import org.restcomm.protocols.ss7.m3ua.ExchangeType;
import org.restcomm.protocols.ss7.m3ua.Functionality;
import org.restcomm.protocols.ss7.m3ua.IPSPType;
import org.restcomm.protocols.ss7.m3ua.RouteAs;
import org.restcomm.protocols.ss7.m3ua.impl.AsImpl;
import org.restcomm.protocols.ss7.m3ua.impl.AspFactoryImpl;
import org.restcomm.protocols.ss7.m3ua.impl.M3UAManagementImpl;
import org.restcomm.protocols.ss7.m3ua.impl.parameter.ParameterFactoryImpl;
import org.restcomm.protocols.ss7.m3ua.parameter.NetworkAppearance;
import org.restcomm.protocols.ss7.m3ua.parameter.ParameterFactory;
import org.restcomm.protocols.ss7.m3ua.parameter.RoutingContext;
import org.restcomm.protocols.ss7.m3ua.parameter.TrafficModeType;
import org.restcomm.protocols.ss7.mtp.RoutingLabelFormat;
import org.restcomm.ss7.management.console.ShellExecutor;
/**
 *
 * @author amit bhayani
 *
 */
public class M3UAShellExecutor implements ShellExecutor {

    private static final Logger logger = Logger.getLogger(M3UAShellExecutor.class);

    private FastMap<String, M3UAManagementImpl> m3uaManagements = new FastMap<String, M3UAManagementImpl>();

    private M3UAManagementImpl m3uaManagement;

    protected ParameterFactory parameterFactory = new ParameterFactoryImpl();

    public M3UAShellExecutor() {

    }

    public Map<String, M3UAManagementImpl> getM3uaManagements() {
        return this.m3uaManagements;
    }

    public void setM3uaManagements(Map<String, M3UAManagementImpl> m3uaManagementsTemp) {
        if (m3uaManagementsTemp != null) {
            synchronized (this) {
                FastMap<String, M3UAManagementImpl> newM3uaManagements = new FastMap<String, M3UAManagementImpl>();
                newM3uaManagements.putAll(m3uaManagementsTemp);
                this.m3uaManagements = newM3uaManagements;
            }
        }
    }

    private void setDefaultValue() {
        if (this.m3uaManagement == null) {
            Map.Entry<String, M3UAManagementImpl> m3uaManagementsTmp = this.m3uaManagements.entrySet().iterator().next();
            this.m3uaManagement = m3uaManagementsTmp.getValue();
        }
    }

    /**
     * m3ua as create <as-name> <AS | SGW | IPSP> mode <SE | DE> ipspType < client | server > rc <routing-context> traffic-mode
     * <traffic mode> min-asp <minimum asp active for TrafficModeType.Loadshare> network-appearance <network appearance>
     * stackname <stack-name>
     *
     * @param args
     * @return
     */
    private String createAs(String[] args) throws Exception {
        if (args.length < 5 || args.length > 19) {
            return M3UAOAMMessages.INVALID_COMMAND;
        }

        // Create new Rem AS
        String asName = args[3];
        if (asName == null) {
            return M3UAOAMMessages.INVALID_COMMAND;
        }

        Functionality functionality = Functionality.getFunctionality(args[4]);
        ExchangeType exchangeType = null;
        IPSPType ipspType = null;
        RoutingContext rc = null;
        TrafficModeType trafficModeType = null;
        NetworkAppearance na = null;

        if (functionality == null) {
            return M3UAOAMMessages.INVALID_COMMAND;
        }

        int count = 5;
        int minAspActiveForLoadbalance = 1;

        while (count < args.length) {
            String key = args[count++];
            if (key == null) {
                return M3UAOAMMessages.INVALID_COMMAND;
            }

            if (key.equals("mode")) {
                exchangeType = ExchangeType.getExchangeType(args[count++]);
                if (exchangeType == null) {
                    return M3UAOAMMessages.INVALID_COMMAND;
                }
            } else if (key.equals("ipspType")) {
                ipspType = IPSPType.getIPSPType(args[count++]);
            } else if (key.equals("rc")) {
                long rcLong = Long.parseLong(args[count++]);
                rc = parameterFactory.createRoutingContext(new long[] { rcLong });
            } else if (key.equals("traffic-mode")) {
                trafficModeType = getTrafficModeType(args[count++]);
            } else if (key.equals("network-appearance")) {
                na = parameterFactory.createNetworkAppearance(Long.parseLong(args[count++]));
            } else if (key.equals("min-asp")) {
                minAspActiveForLoadbalance = Integer.parseInt(args[count++]);
            } else if (key.equals("stackname")) {
                String m3uaStackName = args[count++];

                M3UAManagementImpl m3uaManagementImpl = this.m3uaManagements.get(m3uaStackName);

                if (m3uaManagementImpl == null) {
                    return String.format(M3UAOAMMessages.NO_M3UA_MANAGEMENT_BEAN_FOR_NAME, m3uaStackName);
                }

                this.m3uaManagement = m3uaManagementImpl;
            } else {
                return M3UAOAMMessages.INVALID_COMMAND;
            }
        }

        this.setDefaultValue();

        As asImpl = this.m3uaManagement.createAs(asName, functionality, exchangeType, ipspType, rc, trafficModeType, minAspActiveForLoadbalance, na);
        return String.format(M3UAOAMMessages.CREATE_AS_SUCCESSFUL, asImpl.getName(), this.m3uaManagement.getName());
    }

    /**
     * m3ua as destroy <as-name> stackname <stack-name>
     *
     * @param args
     * @return
     * @throws Exception
     */
    private String destroyAs(String[] args) throws Exception {
        if (args.length < 4) {
            return M3UAOAMMessages.INVALID_COMMAND;
        }

        String asName = args[3];
        if (asName == null) {
            return M3UAOAMMessages.INVALID_COMMAND;
        }

        String m3uaStackName;

        if (args.length > 4) {
            if (!args[4].equals("stackname")) {
                return M3UAOAMMessages.INVALID_COMMAND;
            }

            m3uaStackName = args[5];
            M3UAManagementImpl m3uaManagementTmp = this.m3uaManagements.get(m3uaStackName);

            if (m3uaManagementTmp == null) {
                return String.format(M3UAOAMMessages.NO_M3UA_MANAGEMENT_BEAN_FOR_NAME, m3uaStackName);
            }

            this.m3uaManagement = m3uaManagementTmp;
        } else {
            this.setDefaultValue();
        }

        AsImpl asImpl = this.m3uaManagement.destroyAs(asName);

        return String.format(M3UAOAMMessages.DESTROY_AS_SUCCESSFUL, asName, this.m3uaManagement.getName());
    }

    /**
     * m3ua as add <as-name> <asp-name> stackname <stack-name>
     *
     * @param args
     * @return
     * @throws Exception
     */
    private String addAspToAs(String[] args) throws Exception {
        if (args.length < 5) {
            return M3UAOAMMessages.INVALID_COMMAND;
        }

        // Add Rem ASP to Rem AS
        if (args[3] == null || args[4] == null) {
            return M3UAOAMMessages.INVALID_COMMAND;
        }

        if (args.length > 5) {
            if (!args[5].equals("stackname")) {
                return M3UAOAMMessages.INVALID_COMMAND;
            }

            String m3uaStackName = args[6];
            M3UAManagementImpl m3uaManagementTmp = this.m3uaManagements.get(m3uaStackName);

            if (m3uaManagementTmp == null) {
                return String.format(M3UAOAMMessages.NO_M3UA_MANAGEMENT_BEAN_FOR_NAME, m3uaStackName);
            }

            this.m3uaManagement = m3uaManagementTmp;
        } else {
            this.setDefaultValue();
        }

        this.m3uaManagement.assignAspToAs(args[3], args[4]);
        return String.format(M3UAOAMMessages.ADD_ASP_TO_AS_SUCCESSFUL, args[4], args[3], this.m3uaManagement.getName());
    }

    /**
     * m3ua as remove <as-name> <asp-name> stackname <stack-name>
     *
     * @param args
     * @return
     * @throws Exception
     */
    private String removeAspFromAs(String[] args) throws Exception {
        if (args.length < 5) {
            return M3UAOAMMessages.INVALID_COMMAND;
        }

        // Add Rem ASP to Rem AS
        if (args[3] == null || args[4] == null) {
            return M3UAOAMMessages.INVALID_COMMAND;
        }

        if (args.length > 5) {
            if (!args[5].equals("stackname")) {
                return M3UAOAMMessages.INVALID_COMMAND;
            }

            String m3uaStackName = args[6];
            M3UAManagementImpl m3uaManagementTmp = this.m3uaManagements.get(m3uaStackName);

            if (m3uaManagementTmp == null) {
                return String.format(M3UAOAMMessages.NO_M3UA_MANAGEMENT_BEAN_FOR_NAME, m3uaStackName);
            }

            this.m3uaManagement = m3uaManagementTmp;
        } else {
            this.setDefaultValue();
        }

        this.m3uaManagement.unassignAspFromAs(args[3], args[4]);
        return String.format(M3UAOAMMessages.REMOVE_ASP_FROM_AS_SUCCESSFUL, args[4], args[3], this.m3uaManagement.getName());
    }

    private TrafficModeType getTrafficModeType(String mode) {
        int iMode = -1;
        if (mode == null) {
            return null;
        } else if (mode.equals("loadshare")) {
            iMode = TrafficModeType.Loadshare;
        } else if (mode.equals("override")) {
            iMode = TrafficModeType.Override;
        } else if (mode.equals("broadcast")) {
            iMode = TrafficModeType.Broadcast;
        } else {
            return null;
        }

        return this.parameterFactory.createTrafficModeType(iMode);
    }

    private String showAspFactories(String[] args) {
        // m3ua asp show stackname <stack-name>

        String m3uaStackName;

        if (args.length > 3) {
            if (!args[3].equals("stackname")) {
                return M3UAOAMMessages.INVALID_COMMAND;
            }

            m3uaStackName = args[4];
            M3UAManagementImpl m3uaManagementTmp = this.m3uaManagements.get(m3uaStackName);

            if (m3uaManagementTmp == null) {
                return String.format(M3UAOAMMessages.NO_M3UA_MANAGEMENT_BEAN_FOR_NAME, m3uaStackName);
            }

            this.m3uaManagement = m3uaManagementTmp;
        } else {
            this.setDefaultValue();
        }

        List<AspFactory> aspFactories = this.m3uaManagement.getAspfactories();

        if (aspFactories.size() == 0) {
            return String.format(M3UAOAMMessages.NO_ASP_DEFINED_YET, this.m3uaManagement.getName());
        }

        StringBuffer sb = new StringBuffer();
        for (AspFactory aspFactory : aspFactories) {
            AspFactoryImpl aspFactoryImpl = (AspFactoryImpl) aspFactory;
            sb.append(M3UAOAMMessages.NEW_LINE);
            aspFactoryImpl.show(sb);
            sb.append(M3UAOAMMessages.NEW_LINE);
        }
        return sb.toString();
    }

    private String showRoutes(String[] args) {
        // m3ua route show stackname <stack-name>

        if (args.length > 3) {
            if (!args[3].equals("stackname")) {
                return M3UAOAMMessages.INVALID_COMMAND;
            }

            String m3uaStackName = args[4];
            M3UAManagementImpl m3uaManagementTmp = this.m3uaManagements.get(m3uaStackName);

            if (m3uaManagementTmp == null) {
                return String.format(M3UAOAMMessages.NO_M3UA_MANAGEMENT_BEAN_FOR_NAME, m3uaStackName);
            }

            this.m3uaManagement = m3uaManagementTmp;
        } else {
            this.setDefaultValue();
        }

        Map<String, RouteAs> route = this.m3uaManagement.getRoute();

        if (route.size() == 0) {
            return String.format(M3UAOAMMessages.NO_ROUTE_DEFINED_YET, this.m3uaManagement.getName());
        }

        StringBuffer sb = new StringBuffer();

        Set<String> keys = route.keySet();
        for (String key : keys) {
            RouteAs routeAs = route.get(key);
            As[] asList = routeAs.getAsArray();

            sb.append(M3UAOAMMessages.NEW_LINE);
            sb.append(key);
            sb.append(M3UAOAMMessages.TAB);
            sb.append(routeAs.getTrafficModeType());
            sb.append(M3UAOAMMessages.TAB);
            for (int i = 0; i < asList.length; i++) {
                As asImpl = asList[i];
                if (asImpl != null) {
                    sb.append(asImpl.getName());
                    sb.append(M3UAOAMMessages.COMMA);
                }
            }
            sb.append(M3UAOAMMessages.NEW_LINE);
        }
        return sb.toString();
    }

    private String showAs(String[] args) {
        if (args.length > 3) {
            if (!args[3].equals("stackname")) {
                return M3UAOAMMessages.INVALID_COMMAND;
            }

            String m3uaStackName = args[4];
            M3UAManagementImpl m3uaManagementTmp = this.m3uaManagements.get(m3uaStackName);

            if (m3uaManagementTmp == null) {
                return String.format(M3UAOAMMessages.NO_M3UA_MANAGEMENT_BEAN_FOR_NAME, m3uaStackName);
            }

            this.m3uaManagement = m3uaManagementTmp;
        } else {
            this.setDefaultValue();
        }

        List<As> appServers = this.m3uaManagement.getAppServers();
        if (appServers.size() == 0) {
            return String.format(M3UAOAMMessages.NO_AS_DEFINED_YET, this.m3uaManagement.getName());
        }

        StringBuffer sb = new StringBuffer();
        for (As as : appServers) {
            AsImpl asImpl = (AsImpl) as;
            sb.append(M3UAOAMMessages.NEW_LINE);
            asImpl.show(sb);
            sb.append(M3UAOAMMessages.NEW_LINE);
        }
        return sb.toString();
    }

    private String executeM3UA(String[] args) {
        try {
            if (args.length < 2 || args.length > 19) {
                // any command will have at least 3 args
                return M3UAOAMMessages.INVALID_COMMAND;
            }

            if (args[1] == null) {
                return M3UAOAMMessages.INVALID_COMMAND;
            }

            if (args[1].equals("as")) {
                String rasCmd = args[2];
                if (rasCmd == null) {
                    return M3UAOAMMessages.INVALID_COMMAND;
                }

                if (rasCmd.equals("create")) {
                    return this.createAs(args);
                } else if (rasCmd.equals("destroy")) {
                    return this.destroyAs(args);
                } else if (rasCmd.equals("add")) {
                    return this.addAspToAs(args);
                } else if (rasCmd.equals("remove")) {
                    return this.removeAspFromAs(args);
                } else if (rasCmd.equals("show")) {
                    return this.showAs(args);
                }
                return M3UAOAMMessages.INVALID_COMMAND;
            } else if (args[1].equals("asp")) {

                if (args.length < 3 || args.length > 11) {
                    return M3UAOAMMessages.INVALID_COMMAND;
                }

                // related to rem AS for SigGatewayImpl
                String raspCmd = args[2];

                if (raspCmd == null) {
                    return M3UAOAMMessages.INVALID_COMMAND;
                } else if (raspCmd.equals("create")) {
                    // m3ua asp create <asp-name> <sctp-association> aspid <aspid> heartbeat <true|false> stackname <stack-name>

                    // Create new ASP
                    if (args.length < 5) {
                        return M3UAOAMMessages.INVALID_COMMAND;
                    }

                    String aspName = args[3];
                    String associationName = args[4];

                    if (aspName == null || associationName == null) {
                        return M3UAOAMMessages.INVALID_COMMAND;
                    }

                    AspFactory factory;
                    long aspId = -1;
                    boolean isHeartBeatEnabled = false;

                    if (args.length > 5) {
                        int count = 5;

                        while (count < args.length) {
                            String key = args[count++];
                            if (key == null) {
                                return M3UAOAMMessages.INVALID_COMMAND;
                            }

                            if (key.equals("aspid")) {
                                aspId = Long.parseLong(args[count++]);
                            } else if (key.equals("heartbeat")) {
                                isHeartBeatEnabled = Boolean.parseBoolean(args[count++]);
                            } else if (key.equals("stackname")) {
                                String m3uaStackName = args[count++];

                                M3UAManagementImpl m3uaManagementImpl = this.m3uaManagements.get(m3uaStackName);

                                if (m3uaManagementImpl == null) {
                                    return String.format(M3UAOAMMessages.NO_M3UA_MANAGEMENT_BEAN_FOR_NAME, m3uaStackName);
                                }

                                this.m3uaManagement = m3uaManagementImpl;
                            } else {
                                return M3UAOAMMessages.INVALID_COMMAND;
                            }
                        }
                    }

                    this.setDefaultValue();

                    if (aspId == -1) {
                        factory = this.m3uaManagement.createAspFactory(aspName, associationName, isHeartBeatEnabled);
                    } else {
                        factory = this.m3uaManagement.createAspFactory(aspName, associationName, aspId, isHeartBeatEnabled);
                    }
                    return String.format(M3UAOAMMessages.CREATE_ASP_SUCCESSFUL, factory.getName(),
                            this.m3uaManagement.getName());
                } else if (raspCmd.equals("destroy")) {
                    // m3ua asp destroy <asp-name> stackname <stack-name>
                    if (args.length < 4 || args.length > 6) {
                        return M3UAOAMMessages.INVALID_COMMAND;
                    }

                    String aspName = args[3];
                    String m3uaStackName = null;

                    if (args.length > 4) {
                        if (!args[4].equals("stackname")) {
                            return M3UAOAMMessages.INVALID_COMMAND;
                        }

                        m3uaStackName = args[5];
                        M3UAManagementImpl m3uaManagementTmp = this.m3uaManagements.get(m3uaStackName);

                        if (m3uaManagementTmp == null) {
                            return String.format(M3UAOAMMessages.NO_M3UA_MANAGEMENT_BEAN_FOR_NAME, m3uaStackName);
                        }

                        this.m3uaManagement = m3uaManagementTmp;
                    } else {
                        this.setDefaultValue();
                    }

                    this.m3uaManagement.destroyAspFactory(aspName);
                    return String.format(M3UAOAMMessages.DESTROY_ASP_SUCCESSFUL, aspName, this.m3uaManagement.getName());

                } else if (raspCmd.equals("show")) {
                    return this.showAspFactories(args);

                } else if (raspCmd.equals("start")) {
                    // m3ua asp start <asp-name> stackname <stack-name>

                    if (args.length < 4) {
                        return M3UAOAMMessages.INVALID_COMMAND;
                    }

                    String aspName = args[3];

                    String m3uaStackName = null;

                    if (args.length > 4) {
                        if (!args[4].equals("stackname")) {
                            return M3UAOAMMessages.INVALID_COMMAND;
                        }

                        m3uaStackName = args[5];
                        M3UAManagementImpl m3uaManagementTmp = this.m3uaManagements.get(m3uaStackName);

                        if (m3uaManagementTmp == null) {
                            return String.format(M3UAOAMMessages.NO_M3UA_MANAGEMENT_BEAN_FOR_NAME, m3uaStackName);
                        }

                        this.m3uaManagement = m3uaManagementTmp;
                    } else {
                        this.setDefaultValue();
                    }

                    this.m3uaManagement.startAsp(aspName);
                    return String.format(M3UAOAMMessages.ASP_START_SUCCESSFUL, aspName, this.m3uaManagement.getName());
                } else if (raspCmd.equals("stop")) {
                    // m3ua asp stop <asp-name> stackname <stack-name>

                    if (args.length < 4) {
                        return M3UAOAMMessages.INVALID_COMMAND;
                    }

                    String aspName = args[3];

                    String m3uaStackName;

                    if (args.length > 4) {
                        if (!args[4].equals("stackname")) {
                            return M3UAOAMMessages.INVALID_COMMAND;
                        }

                        m3uaStackName = args[5];
                        M3UAManagementImpl m3uaManagementTmp = this.m3uaManagements.get(m3uaStackName);

                        if (m3uaManagementTmp == null) {
                            return String.format(M3UAOAMMessages.NO_M3UA_MANAGEMENT_BEAN_FOR_NAME, m3uaStackName);
                        }

                        this.m3uaManagement = m3uaManagementTmp;
                    } else {
                        this.setDefaultValue();
                    }

                    this.m3uaManagement.stopAsp(aspName);
                    return String.format(M3UAOAMMessages.ASP_STOP_SUCCESSFUL, aspName, this.m3uaManagement.getName());
                }

                return M3UAOAMMessages.INVALID_COMMAND;
            } else if (args[1].equals("route")) {

                String routeCmd = args[2];

                if (routeCmd == null) {
                    return M3UAOAMMessages.INVALID_COMMAND;
                }

                if (routeCmd.equals("add")) {
                    // m3ua route add <as-name> <dpc> <opc> <si> trafficmode <traffic-mode> stackname <stack-name>

                    if (args.length < 5 || args.length > 11) {
                        return M3UAOAMMessages.INVALID_COMMAND;
                    }

                    int count = 3;
                    String asName = args[count++];
                    int dpc = -1;
                    int opc = -1;
                    int si = -1;
                    int trafficMode = TrafficModeType.Loadshare;

                    if (asName == null) {
                        return M3UAOAMMessages.INVALID_COMMAND;
                    }

                    dpc = Integer.parseInt(args[count++]);

                    opc = Integer.parseInt(args[count++]);
                    si = Integer.parseInt(args[count++]);

                    count = 7;

                    while (count < args.length) {
                        String key = args[count++];
                        if (key == null) {
                            return M3UAOAMMessages.INVALID_COMMAND;
                        }

                        if (key.equals("trafficmode")) {
                            trafficMode = Integer.parseInt(args[count++]);
                        } else if (key.equals("stackname")) {
                            String m3uaStackName = args[count++];

                            M3UAManagementImpl m3uaManagementImpl = this.m3uaManagements.get(m3uaStackName);

                            if (m3uaManagementImpl == null) {
                                return String.format(M3UAOAMMessages.NO_M3UA_MANAGEMENT_BEAN_FOR_NAME, m3uaStackName);
                            }

                            this.m3uaManagement = m3uaManagementImpl;
                        } else {
                            return M3UAOAMMessages.INVALID_COMMAND;
                        }
                    }

                    this.setDefaultValue();

                    this.m3uaManagement.addRoute(dpc, opc, si, asName, trafficMode);

                    return String.format(M3UAOAMMessages.ADD_ROUTE_AS_FOR_DPC_SUCCESSFUL, asName, dpc,
                            this.m3uaManagement.getName());
                } else if (routeCmd.equals("remove")) {
                    // m3ua route remove <as-name> <dpc> <opc> <si> stackname <stack-name>
                    if (args.length < 5 || args.length > 9) {
                        return M3UAOAMMessages.INVALID_COMMAND;
                    }

                    int count = 3;
                    String asName = args[count++];
                    int dpc = -1;
                    int opc = -1;
                    int si = -1;

                    if (asName == null) {
                        return M3UAOAMMessages.INVALID_COMMAND;
                    }

                    dpc = Integer.parseInt(args[count++]);
                    opc = Integer.parseInt(args[count++]);
                    si = Integer.parseInt(args[count++]);

                    if (args.length > 7) {
                        if (!args[7].equals("stackname")) {
                            return M3UAOAMMessages.INVALID_COMMAND;
                        }

                        String m3uaStackName = args[8];
                        M3UAManagementImpl m3uaManagementTmp = this.m3uaManagements.get(m3uaStackName);

                        if (m3uaManagementTmp == null) {
                            return String.format(M3UAOAMMessages.NO_M3UA_MANAGEMENT_BEAN_FOR_NAME, m3uaStackName);
                        }

                        this.m3uaManagement = m3uaManagementTmp;
                    } else {
                        this.setDefaultValue();
                    }

                    this.m3uaManagement.removeRoute(dpc, opc, si, asName);
                    return String.format(M3UAOAMMessages.REMOVE_AS_ROUTE_FOR_DPC_SUCCESSFUL, asName, dpc,
                            this.m3uaManagement.getName());
                }

                if (routeCmd.equals("show")) {
                    return this.showRoutes(args);

                }
            } else if (args[1].equals("set")) {
                return this.manageSet(args);
            } else if (args[1].equals("get")) {
                return this.manageGet(args);
            } else if (args[1].equals("error")) {
                String errCmd = args[2];
                if (errCmd.equals("add")) {
                    return this.handleAddError(args);
                }
                if (errCmd.equals("remove")) {
                    return this.handleRemoveError(args);
                }
                return this.handleErrorList(args);
            }
            return M3UAOAMMessages.INVALID_COMMAND;
        } catch (Exception e) {
            logger.error(String.format("Error while executing command %s", Arrays.toString(args)), e);
            return e.getMessage();
        } catch (Throwable t) {
            logger.error(String.format("Error while executing command %s", Arrays.toString(args)), t);
            return t.getMessage();
        }
    }

    private String handleAddError(String[] commandArgs) {
        if (commandArgs.length != 6) // e.g. m3ua error add 25 invalidRoutingContext -1
        {
            return M3UAOAMMessages.INVALID_COMMAND;
        }
        try {
            int errorCode = Integer.parseInt(commandArgs[3]);
            String errorName = commandArgs[4];
            int retryCount = Integer.parseInt(commandArgs[5]);

            if (retryCount < -1) {
                retryCount = -1;
            }

            this.setDefaultValue();
            this.m3uaManagement.setErrorRetryAction(errorCode, errorName, retryCount);
            return String.format(M3UAOAMMessages.M3UA_ERROR_MANAGEMENT_ADD_SUCCESS, errorCode);
        } catch (NumberFormatException e) {
            logger.error(e.getMessage());
        }
        return M3UAOAMMessages.GENERIC_ERROR_CODE;
    }

    private String handleErrorList(String[] args) {
        this.setDefaultValue();
        List<ErrorRetryAction> errorList = this.m3uaManagement.getErrorRetry();
        StringBuilder sb = new StringBuilder();
        sb.append(padRight("Error", 7));
        sb.append("| ");
        sb.append(padRight("Name", 35));
        sb.append("| ");
        sb.append("Retries");
        sb.append(M3UAOAMMessages.NEW_LINE);
        sb.append("--------------------------------------------------------");
        for (ErrorRetryAction eList : errorList) {
            sb.append(M3UAOAMMessages.NEW_LINE);
            sb.append(String.format("%-7d", eList.getErrorCode()));
            sb.append("| ");
            sb.append(padRight(eList.getName(), 35));
            sb.append("| ");
            sb.append(eList.getRetryCount());
        }
        sb.append(M3UAOAMMessages.NEW_LINE);
        return sb.toString();
    }

    private String padRight(String s, int len) {
        return String.format("%-" + len + "s", s);
    }
    private String handleRemoveError(String[] commandArgs) {
        if (commandArgs.length != 4){
            return M3UAOAMMessages.INVALID_COMMAND;
        }
        try {
            int errorCode = Integer.parseInt(commandArgs[3]);
            ErrorRetryAction errorActionToDelete = null;
            // check if the error is defined.
            this.setDefaultValue();
            List<ErrorRetryAction> definedErrors = this.m3uaManagement.getErrorRetry();
            for (ErrorRetryAction errorRetry : definedErrors) {
                if (errorCode == errorRetry.getErrorCode()) {
                    errorActionToDelete = errorRetry;
                    break;
                }
            }
            if (errorActionToDelete == null) {
                return M3UAOAMMessages.ERROR_CODE_NOT_DEFINED;
            }
            this.m3uaManagement.removeErrorAction(errorActionToDelete.getErrorCode(), errorActionToDelete.getName(), errorActionToDelete.getRetryCount());

            return M3UAOAMMessages.ERROR_CODE_REMOVED_SUCCESS;
        } catch(NumberFormatException e) {
            logger.error(e.getMessage());
        }
        return M3UAOAMMessages.GENERIC_ERROR_CODE;
    }

    private String manageSet(String[] options) throws Exception {
        // Minimum 4 needed. Show
        // m3ua set <command> <value> stackname <stack-name>
        if (options.length < 4 || options.length > 6) {
            return M3UAOAMMessages.INVALID_COMMAND;
        }

        if (options.length > 4) {
            if (!options[4].equals("stackname")) {
                return M3UAOAMMessages.INVALID_COMMAND;
            }

            String m3uaStackName = options[5];
            M3UAManagementImpl m3uaManagementTmp = this.m3uaManagements.get(m3uaStackName);

            if (m3uaManagementTmp == null) {
                return String.format(M3UAOAMMessages.NO_M3UA_MANAGEMENT_BEAN_FOR_NAME, m3uaStackName);
            }

            this.m3uaManagement = m3uaManagementTmp;
        } else {
            this.setDefaultValue();
        }

        String parName = options[2].toLowerCase();

        switch (parName) {
            case "heartbeattime": {
                int val = Integer.parseInt(options[3]);
                this.m3uaManagement.setHeartbeatTime(val);
                break;
            }
            case "statisticsenabled": {
                boolean val = Boolean.parseBoolean(options[3]);
                this.m3uaManagement.setStatisticsEnabled(val);
                break;
            }
            case "statisticsdelay": {
                long statisticsDelay = Long.parseLong(options[3]);
                this.m3uaManagement.setStatisticsTaskDelay(statisticsDelay);
                break;
            }
            case "statisticsperiod": {
                long statisticsPeriod = Long.parseLong(options[3]);
                this.m3uaManagement.setStatisticsTaskPeriod(statisticsPeriod);
                break;
            }
            case "routingkeymanagementenabled": {
                boolean val = Boolean.parseBoolean(options[3]);
                this.m3uaManagement.setRoutingKeyManagementEnabled(val);
                break;
            }
            case "routinglabelformat":
                String vals = options[3];
                RoutingLabelFormat rlf = Enum.valueOf(RoutingLabelFormat.class, vals);
                this.m3uaManagement.setRoutingLabelFormat(rlf);
                break;
            case "uselsbforlinksetselection":
                boolean valBoolean = Boolean.parseBoolean(options[3]);
                this.m3uaManagement.setUseLsbForLinksetSelection(valBoolean);
                break;
            default:
                return M3UAOAMMessages.INVALID_COMMAND;
        }

        return String.format(M3UAOAMMessages.PARAMETER_SUCCESSFULLY_SET, this.m3uaManagement.getName());
    }

    private String manageGet(String[] options) throws Exception {
        // Minimum 2 needed. Show

        // m3ua get <command>
        if (options.length < 2) {
            return M3UAOAMMessages.INVALID_COMMAND;
        }

        if (options.length == 3) {
            this.setDefaultValue();

            String parName = options[2].toLowerCase();

            StringBuilder sb = new StringBuilder();
            sb.append("stack=").append(this.m3uaManagement.getName()).append(" ");
            sb.append(options[2]);
            sb.append(" = ");
            switch (parName) {
                case "maxsequencenumber":
                    sb.append(this.m3uaManagement.getMaxSequenceNumber());
                    break;
                case "maxasforroute":
                    sb.append(this.m3uaManagement.getMaxAsForRoute());
                    break;
                case "heartbeattime":
                    sb.append(this.m3uaManagement.getHeartbeatTime());
                    break;
                case "statisticsenabled":
                    sb.append(this.m3uaManagement.getStatisticsEnabled());
                    break;
                case "statisticsdelay":
                    sb.append(this.m3uaManagement.getStatisticsTaskDelay());
                    break;
                case "statisticsperiod":
                    sb.append(this.m3uaManagement.getStatisticsTaskPeriod());
                    break;
                case "routingkeymanagementenabled":
                    sb.append(this.m3uaManagement.getRoutingKeyManagementEnabled());
                    break;
                case "routinglabelformat":
                    sb.append(this.m3uaManagement.getRoutingLabelFormat());
                    break;
                case "uselsbforlinksetselection":
                    sb.append(this.m3uaManagement.isUseLsbForLinksetSelection());
                    break;
                case "deliverymessagethreadcount":
                    sb.append(this.m3uaManagement.getDeliveryMessageThreadCount());
                    break;
                default:
                    return M3UAOAMMessages.INVALID_COMMAND;
            }

            return sb.toString();
        } else {
            StringBuilder sb = new StringBuilder();
            for (FastMap.Entry<String, M3UAManagementImpl> e = this.m3uaManagements.head(), end = this.m3uaManagements.tail(); (e = e
                    .getNext()) != end;) {

                M3UAManagementImpl managementImplTmp = e.getValue();
                String stackname = e.getKey();

                sb.append("Properties for ");
                sb.append(stackname);
                sb.append("\n");
                sb.append("*******************");
                sb.append("\n");

                sb.append("maxsequencenumber = ");
                sb.append(managementImplTmp.getMaxSequenceNumber());
                sb.append("\n");

                sb.append("maxasforroute = ");
                sb.append(managementImplTmp.getMaxAsForRoute());
                sb.append("\n");

                sb.append("heartbeattime = ");
                sb.append(managementImplTmp.getHeartbeatTime());
                sb.append("\n");

                sb.append("statisticsenabled = ");
                sb.append(managementImplTmp.getStatisticsEnabled());
                sb.append("\n");

                sb.append("statisticsdelay = ");
                sb.append(managementImplTmp.getStatisticsTaskDelay());
                sb.append("\n");

                sb.append("statisticsperiod = ");
                sb.append(managementImplTmp.getStatisticsTaskPeriod());
                sb.append("\n");

                sb.append("routingkeymanagementenabled = ");
                sb.append(managementImplTmp.getRoutingKeyManagementEnabled());
                sb.append("\n");

                sb.append("routinglabelformat = ");
                sb.append(managementImplTmp.getRoutingLabelFormat());
                sb.append("\n");

                sb.append("uselsbforlinksetselection = ");
                sb.append(managementImplTmp.isUseLsbForLinksetSelection());
                sb.append("\n");

                sb.append("deliverymessagethreadcount = ");
                sb.append(managementImplTmp.getDeliveryMessageThreadCount());
                sb.append("\n");

                sb.append("*******************");
                sb.append("\n");
                sb.append("\n");
            }
            return sb.toString();
        }
    }

    public String execute(String[] args) {
        if (args[0].equals("m3ua")) {
            return this.executeM3UA(args);
        }
        return M3UAOAMMessages.INVALID_COMMAND;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.ss7.management.console.ShellExecutor#handles(java.lang. String)
     */
    @Override
    public boolean handles(String command) {
        return (command.startsWith("m3ua"));
    }

}
