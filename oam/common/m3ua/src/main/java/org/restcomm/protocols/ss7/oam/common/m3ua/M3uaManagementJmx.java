
package org.restcomm.protocols.ss7.oam.common.m3ua;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javolution.util.FastList;
import javolution.util.FastMap;
import org.apache.log4j.Logger;
import org.restcomm.protocols.ss7.m3ua.As;
import org.restcomm.protocols.ss7.m3ua.Asp;
import org.restcomm.protocols.ss7.m3ua.AspFactory;
import org.restcomm.protocols.ss7.m3ua.ExchangeType;
import org.restcomm.protocols.ss7.m3ua.ErrorRetryAction;
import org.restcomm.protocols.ss7.m3ua.Functionality;
import org.restcomm.protocols.ss7.m3ua.IPSPType;
import org.restcomm.protocols.ss7.m3ua.M3UACounterProvider;
import org.restcomm.protocols.ss7.m3ua.M3UAManagement;
import org.restcomm.protocols.ss7.m3ua.M3UAManagementEventListener;
import org.restcomm.protocols.ss7.m3ua.RouteAs;
import org.restcomm.protocols.ss7.m3ua.State;
import org.restcomm.protocols.ss7.m3ua.impl.ErrorRetryActionImpl;
import org.restcomm.protocols.ss7.m3ua.impl.M3UAErrorManagementState;
import org.restcomm.protocols.ss7.m3ua.impl.parameter.ParameterFactoryImpl;
import org.restcomm.protocols.ss7.m3ua.parameter.NetworkAppearance;
import org.restcomm.protocols.ss7.m3ua.parameter.ParameterFactory;
import org.restcomm.protocols.ss7.m3ua.parameter.RoutingContext;
import org.restcomm.protocols.ss7.m3ua.parameter.TrafficModeType;
import org.restcomm.protocols.ss7.oam.common.alarm.AlarmListener;
import org.restcomm.protocols.ss7.oam.common.alarm.AlarmListenerCollection;
import org.restcomm.protocols.ss7.oam.common.alarm.AlarmMediator;
import org.restcomm.protocols.ss7.oam.common.alarm.AlarmMessage;
import org.restcomm.protocols.ss7.oam.common.alarm.AlarmMessageImpl;
import org.restcomm.protocols.ss7.oam.common.alarm.AlarmSeverity;
import org.restcomm.protocols.ss7.oam.common.alarm.CurrentAlarmList;
import org.restcomm.protocols.ss7.oam.common.alarm.CurrentAlarmListImpl;
import org.restcomm.protocols.ss7.oam.common.jmx.MBeanHost;
import org.restcomm.protocols.ss7.oam.common.jmxss7.Ss7Layer;
import org.restcomm.protocols.ss7.oam.common.statistics.ComplexValueImpl;
import org.restcomm.protocols.ss7.oam.common.statistics.CounterDefImpl;
import org.restcomm.protocols.ss7.oam.common.statistics.CounterDefSetImpl;
import org.restcomm.protocols.ss7.oam.common.statistics.SourceValueCounterImpl;
import org.restcomm.protocols.ss7.oam.common.statistics.SourceValueObjectImpl;
import org.restcomm.protocols.ss7.oam.common.statistics.SourceValueSetImpl;
import org.restcomm.protocols.ss7.oam.common.statistics.api.ComplexValue;
import org.restcomm.protocols.ss7.oam.common.statistics.api.CounterDef;
import org.restcomm.protocols.ss7.oam.common.statistics.api.CounterDefSet;
import org.restcomm.protocols.ss7.oam.common.statistics.api.CounterMediator;
import org.restcomm.protocols.ss7.oam.common.statistics.api.CounterType;
import org.restcomm.protocols.ss7.oam.common.statistics.api.SourceValueSet;
import org.restcomm.protocols.ss7.statistics.api.LongValue;

/**
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public class M3uaManagementJmx implements M3uaManagementJmxMBean, M3UAManagementEventListener, AlarmMediator, CounterMediator {

    protected final Logger logger;

    private final MBeanHost ss7Management;
    private final M3UAManagement wrappedM3UAManagement;

    protected FastList<As> appServers = new FastList<As>();
    protected FastList<AspFactory> aspFactories = new FastList<AspFactory>();
    private AlarmListenerCollection alc = new AlarmListenerCollection();

    private static final ParameterFactory parameterFactory = new ParameterFactoryImpl();

    private FastMap<String, CounterDefSet> lstCounters = new FastMap<String, CounterDefSet>();


    public M3uaManagementJmx(MBeanHost ss7Management, M3UAManagement wrappedM3UAManagement) {
        this.ss7Management = ss7Management;
        this.wrappedM3UAManagement = wrappedM3UAManagement;
        this.logger = Logger.getLogger(M3uaManagementJmx.class.getCanonicalName() + "-" + wrappedM3UAManagement.getName());
    }

    /**
     * methods - bean life-cycle
     */

    public void start() {

        synchronized (this.appServers) {
            appServers.clear();
        }

        synchronized (this.aspFactories) {
            aspFactories.clear();
        }

        setupCounterList();

        this.ss7Management.registerMBean(Ss7Layer.M3UA, M3UAManagementType.MANAGEMENT, this.getName(), this);
        this.wrappedM3UAManagement.addM3UAManagementEventListener(this);

        List<AspFactory> aspFactories = this.wrappedM3UAManagement.getAspfactories();
        for (AspFactory aspFactory : aspFactories) {
            this.addAspFactoryToManagement(aspFactory);
        }

        List<As> appServers = this.wrappedM3UAManagement.getAppServers();
        for (As as : appServers) {
            this.addAsToManagement(as);

            List<Asp> asps = as.getAspList();

            for (Asp asp : asps) {
                this.onAspAssignedToAs(as, asp);
            }
        }
    }

    public void stop() {
        this.removeAllResourcesFromManagement();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.protocols.ss7.oam.common.m3ua.M3uaManagementJmxMBean#getName ()
     */
    @Override
    public String getName() {
        return this.wrappedM3UAManagement.getName();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.protocols.ss7.oam.common.m3ua.M3uaManagementJmxMBean# getPersistDir()
     */
    @Override
    public String getPersistDir() {
        return this.wrappedM3UAManagement.getPersistDir();
    }

    @Override
    public void setPersistDir(String persistDir) {
        this.wrappedM3UAManagement.setPersistDir(persistDir);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.protocols.ss7.oam.common.m3ua.M3uaManagementJmxMBean# getMaxSequenceNumber()
     */
    @Override
    public int getMaxSequenceNumber() {
        return this.wrappedM3UAManagement.getMaxSequenceNumber();
    }

    @Override
    public void setMaxSequenceNumber(int maxSequenceNumber) throws Exception {
        this.wrappedM3UAManagement.setMaxSequenceNumber(maxSequenceNumber);
    }

    @Override
    public boolean isSctpLibNettySupport() {
        return this.wrappedM3UAManagement.isSctpLibNettySupport();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.protocols.ss7.oam.common.m3ua.M3uaManagementJmxMBean# getMaxAsForRoute()
     */
    @Override
    public int getMaxAsForRoute() {
        return this.wrappedM3UAManagement.getMaxAsForRoute();
    }

    @Override
    public void setMaxAsForRoute(int maxAsForRoute) throws Exception {
        this.wrappedM3UAManagement.setMaxAsForRoute(maxAsForRoute);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.protocols.ss7.oam.common.m3ua.M3uaManagementJmxMBean# getAppServers()
     */
    @Override
    public List<As> getAppServers() {
        return this.appServers.unmodifiable();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.protocols.ss7.oam.common.m3ua.M3uaManagementJmxMBean# getAspfactories()
     */
    @Override
    public List<AspFactory> getAspfactories() {
        return this.aspFactories.unmodifiable();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.protocols.ss7.oam.common.m3ua.M3uaManagementJmxMBean#createAs (java.lang.String,
     * org.mobicents.protocols.ss7.m3ua.Functionality, org.mobicents.protocols.ss7.m3ua.ExchangeType,
     * org.mobicents.protocols.ss7.m3ua.IPSPType, org.mobicents.protocols.ss7.m3ua.parameter.RoutingContext,
     * org.mobicents.protocols.ss7.m3ua.parameter.TrafficModeType, org.mobicents.protocols.ss7.m3ua.parameter.NetworkAppearance)
     */
    @Override
    public As createAs(String asName, Functionality functionality, ExchangeType exchangeType, IPSPType ipspType,
            RoutingContext rc, TrafficModeType trafficMode, int minAspActive, NetworkAppearance na) throws Exception {
        As as = this.wrappedM3UAManagement.createAs(asName, functionality, exchangeType, ipspType, rc, trafficMode,
                minAspActive, na);
        return null;
    }

    @Override
    public As createAppServer(String asName, String functionality, String exchangeType, String ipspType,
            String rc, int trafficMode, int minAspActive, String na) throws Exception {

        NetworkAppearance networkAppearance = null;
        if (na != null && !na.trim().equals("")) {
            long naLong = Long.parseLong(na);
            networkAppearance = parameterFactory.createNetworkAppearance(naLong);
        }

        RoutingContext routingContext = null;
        if (rc != null && !rc.trim().equals("")) {
            long rcLong = Long.parseLong(rc.trim());
            routingContext = parameterFactory.createRoutingContext(new long[] { rcLong });
        }

        TrafficModeType trafficModeType = parameterFactory.createTrafficModeType(trafficMode);
        As as = this.wrappedM3UAManagement.createAs(asName, Functionality.valueOf(functionality.toUpperCase()),
                ExchangeType.valueOf(exchangeType.toUpperCase()), IPSPType.valueOf(ipspType.toUpperCase()), routingContext,
                trafficModeType, minAspActive, networkAppearance);

        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.protocols.ss7.oam.common.m3ua.M3uaManagementJmxMBean#destroyAs (java.lang.String)
     */
    @Override
    public As destroyAs(String asName) throws Exception {
        this.wrappedM3UAManagement.destroyAs(asName);
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.protocols.ss7.oam.common.m3ua.M3uaManagementJmxMBean# createAspFactory(java.lang.String,
     * java.lang.String)
     */
    @Override
    public AspFactory createAspFactory(String aspName, String associationName) throws Exception {
        this.wrappedM3UAManagement.createAspFactory(aspName, associationName);
        return null;
    }

    @Override
    public AspFactory createAspFactory(String aspName, String associationName, boolean isHeartBeatEnabled) throws Exception {
        this.wrappedM3UAManagement.createAspFactory(aspName, associationName, isHeartBeatEnabled);
        return null;
    }

    @Override
    public int getHeartbeatTime() {
        return this.wrappedM3UAManagement.getHeartbeatTime();
    }

    @Override
    public void setHeartbeatTime(int timeBetweenHeartbeat) throws Exception {
        this.wrappedM3UAManagement.setHeartbeatTime(timeBetweenHeartbeat);
    }

    @Override
    public boolean isUseLsbForLinksetSelection() {
        return this.wrappedM3UAManagement.isUseLsbForLinksetSelection();
    }

    @Override
    public void setUseLsbForLinksetSelection(boolean useLsbForLinksetSelection) throws Exception {
        this.wrappedM3UAManagement.setUseLsbForLinksetSelection(useLsbForLinksetSelection);
    }

    @Override
    public int getDeliveryMessageThreadCount() {
        return this.wrappedM3UAManagement.getDeliveryMessageThreadCount();
    }

    @Override
    public String getRoutingLabelFormatStr() {
        return this.wrappedM3UAManagement.getRoutingLabelFormatStr();
    }

    @Override
    public boolean isStarted() {
        return this.wrappedM3UAManagement.isStarted();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.protocols.ss7.oam.common.m3ua.M3uaManagementJmxMBean# createAspFactory(java.lang.String,
     * java.lang.String, long)
     */
    @Override
    public AspFactory createAspFactory(String aspName, String associationName, long aspid, boolean isHeartBeatEnabled)
            throws Exception {
        this.wrappedM3UAManagement.createAspFactory(aspName, associationName, aspid, isHeartBeatEnabled);
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.protocols.ss7.oam.common.m3ua.M3uaManagementJmxMBean# destroyAspFactory(java.lang.String)
     */
    @Override
    public AspFactory destroyAspFactory(String aspName) throws Exception {
        this.wrappedM3UAManagement.destroyAspFactory(aspName);
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.protocols.ss7.oam.common.m3ua.M3uaManagementJmxMBean# assignAspToAs(java.lang.String, java.lang.String)
     */
    @Override
    public Asp assignAspToAs(String asName, String aspName) throws Exception {
        this.wrappedM3UAManagement.assignAspToAs(asName, aspName);
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.protocols.ss7.oam.common.m3ua.M3uaManagementJmxMBean# unassignAspFromAs(java.lang.String,
     * java.lang.String)
     */
    @Override
    public Asp unassignAspFromAs(String asName, String aspName) throws Exception {
        this.wrappedM3UAManagement.unassignAspFromAs(asName, aspName);
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.protocols.ss7.oam.common.m3ua.M3uaManagementJmxMBean#startAsp (java.lang.String)
     */
    @Override
    public void startAsp(String aspName) throws Exception {
        this.wrappedM3UAManagement.startAsp(aspName);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.protocols.ss7.oam.common.m3ua.M3uaManagementJmxMBean#stopAsp (java.lang.String)
     */
    @Override
    public void stopAsp(String aspName) throws Exception {
        this.wrappedM3UAManagement.stopAsp(aspName);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.protocols.ss7.oam.common.m3ua.M3uaManagementJmxMBean#addRoute (int, int, int, java.lang.String)
     */
    @Override
    @Deprecated
    public void addRoute(int dpc, int opc, int si, String asName) throws Exception {
        this.wrappedM3UAManagement.addRoute(dpc, opc, si, asName);
    }

    @Override
    public void addRoute(int dpc, int opc, int si, String asName, int trafficModeType) throws Exception {
        this.wrappedM3UAManagement.addRoute(dpc, opc, si, asName, trafficModeType);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.protocols.ss7.oam.common.m3ua.M3uaManagementJmxMBean#removeRoute (int, int, int, java.lang.String)
     */
    @Override
    public void removeRoute(int dpc, int opc, int si, String asName) throws Exception {
        this.wrappedM3UAManagement.removeRoute(dpc, opc, si, asName);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.protocols.ss7.oam.common.m3ua.M3uaManagementJmxMBean#removeAllResources()
     */
    @Override
    public void removeAllResources() throws Exception {
        // TODO Auto-generated method stub

    }

    /**
     * M3UAManagementEventListener callbacks
     */

    @Override
    public void onAsCreated(As as) {
        this.addAsToManagement(as);

        if (wrappedM3UAManagement.isStarted()) {
            if (!as.isUp()) {
                AlarmMessage alm = this.generateAsAlarm(as, false);
                this.alc.onAlarm(alm);
            }
        }
    }

    @Override
    public void onAsDestroyed(As as) {
        this.removeAsFromManagement(as);

        if (wrappedM3UAManagement.isStarted()) {
            if (!as.isUp()) {
                AlarmMessage alm = this.generateAsAlarm(as, true);
                this.alc.onAlarm(alm);
            }
        }
    }

    @Override
    public void onAsDown(As as, State oldState) {
        if (wrappedM3UAManagement.isStarted()) {
            if(oldState.equals(State.STATE_ACTIVE)) {
                AlarmMessage alm = this.generateAsAlarm(as, false);
                this.alc.onAlarm(alm);
            }
        }
    }

    @Override
    public void onAsInactive(As as, State oldState) {
        if (wrappedM3UAManagement.isStarted()) {
            if(oldState.equals(State.STATE_ACTIVE)) {
                AlarmMessage alm = this.generateAsAlarm(as, false);
                this.alc.onAlarm(alm);
            }
        }
    }

    @Override
    public void onAsActive(As as, State oldState) {
        if (wrappedM3UAManagement.isStarted()) {
            AlarmMessage alm = this.generateAsAlarm(as, true);
            this.alc.onAlarm(alm);
        }
    }

    @Override
    public void onAsPending(As as, State oldState) {
        if (wrappedM3UAManagement.isStarted()) {
            if(oldState.equals(State.STATE_ACTIVE)) {
                AlarmMessage alm = this.generateAsAlarm(as, false);
                this.alc.onAlarm(alm);
            }
        }
    }

    @Override
    public void onAspInactive(Asp asp, State oldState) {
        if (wrappedM3UAManagement.isStarted()) {
           AspFactory aspFact = asp.getAspFactory();
           if (aspFact.getStatus()) {
             if(oldState.equals(State.STATE_ACTIVE)) {
                 AlarmMessage alm = this.generateAspAlarm(asp, false);
                 this.alc.onAlarm(alm);
              }
           }
        }
    }

    @Override
    public void onAspActive(Asp asp, State oldState) {
        if (wrappedM3UAManagement.isStarted()) {
            AspFactory aspFact = asp.getAspFactory();
            if (aspFact.getStatus()) {
                AlarmMessage alm = this.generateAspAlarm(asp, true);
                this.alc.onAlarm(alm);
            }
        }
    }

    @Override
    public void onAspDown(Asp asp, State oldState) {
        if (wrappedM3UAManagement.isStarted()) {
            AspFactory aspFact = asp.getAspFactory();
            if (aspFact.getStatus()) {
                if(oldState.equals(State.STATE_ACTIVE)) {
                    AlarmMessage alm = this.generateAspAlarm(asp, false);
                    this.alc.onAlarm(alm);
                }
            }
        }
    }

    // @Override
    public void onStarted() {
        CurrentAlarmListImpl all = this.checkAllAlarms(false);
        for (AlarmMessage alm : all.getCurrentAlarmList()) {
            this.alc.onAlarm(alm);
        }

        AlarmMessage alm2 = generateStoppedAlarm(true);
        this.alc.onAlarm(alm2);
    }

    // @Override
    public void onStopped() {
        CurrentAlarmListImpl all = this.checkAllAlarms(true);
        for (AlarmMessage alm : all.getCurrentAlarmList()) {
            this.alc.onAlarm(alm);
        }

        AlarmMessage alm2 = generateStoppedAlarm(false);
        this.alc.onAlarm(alm2);
    }

    @Override
    public void onAspFactoryCreated(AspFactory aspFactory) {
        this.addAspFactoryToManagement(aspFactory);
    }

    @Override
    public void onAspFactoryDestroyed(AspFactory aspFactory) {
        this.removeAspFactoryFromManagement(aspFactory);
    }

    @Override
    public void onAspFactoryStarted(AspFactory aspFact) {
        if (wrappedM3UAManagement.isStarted()) {
          List<Asp> lstAsp = aspFact.getAspList();
          for (Asp asp : lstAsp) {
            if (!asp.isUp()) {
                AlarmMessage alm = this.generateAspAlarm(asp, false);
                this.alc.onAlarm(alm);
            }
          }
        }
    }

    @Override
    public void onAspFactoryStopped(AspFactory aspFact) {
        if (wrappedM3UAManagement.isStarted()) {
            List<Asp> lstAsp = aspFact.getAspList();
            for (Asp asp : lstAsp) {
                if (!asp.isUp()) {
                    AlarmMessage alm = this.generateAspAlarm(asp, true);
                    this.alc.onAlarm(alm);
                }
            }
        }
    }

    @Override
    public void onAspAssignedToAs(As as, Asp asp) {
        AsJmx asJmx = null;
        AspFactoryJmx aspFactoryJmx = null;

        synchronized (this.appServers) {
            for (FastList.Node<As> n = this.appServers.head(), end = this.appServers.tail(); (n = n.getNext()) != end;) {
                As asTemp = n.getValue();
                if (asTemp.getName().equals(as.getName())) {
                    asJmx = (AsJmx) asTemp;
                    break;
                }
            }// for loop
        }

        synchronized (this.aspFactories) {
            for (FastList.Node<AspFactory> n = this.aspFactories.head(), end = this.aspFactories.tail(); (n = n.getNext()) != end;) {
                AspFactory aspFactoryJmxTmp = n.getValue();
                if (aspFactoryJmxTmp.getName().equals(asp.getName())) {
                    aspFactoryJmx = (AspFactoryJmx) aspFactoryJmxTmp;
                    break;
                }
            }// for loop
        }

        this.addAspToManagement(asJmx, aspFactoryJmx, asp);
    }

    @Override
    public void onAspUnassignedFromAs(As as, Asp asp) {
        AsJmx asJmx = null;
        AspFactoryJmx aspFactoryJmx = null;

        synchronized (this.appServers) {
            for (FastList.Node<As> n = this.appServers.head(), end = this.appServers.tail(); (n = n.getNext()) != end;) {
                As asTemp = n.getValue();
                if (asTemp.getName().equals(as.getName())) {
                    asJmx = (AsJmx) asTemp;
                    break;
                }
            }// for loop
        }

        synchronized (this.aspFactories) {
            for (FastList.Node<AspFactory> n = this.aspFactories.head(), end = this.aspFactories.tail(); (n = n.getNext()) != end;) {
                AspFactory aspFactoryJmxTmp = n.getValue();
                if (aspFactoryJmxTmp.getName().equals(asp.getName())) {
                    aspFactoryJmx = (AspFactoryJmx) aspFactoryJmxTmp;
                    break;
                }
            }// for loop
        }

        this.removeAspFromManagement(asJmx, aspFactoryJmx, asp);
    }

    @Override
    public void onRemoveAllResources() {
        this.removeAllResourcesFromManagement();
    }

    /**
     * methods - private
     */
    private void addAsToManagement(As as) {
        synchronized (this.appServers) {
            AsJmx asJmx = new AsJmx(as);
            this.ss7Management.registerMBean(Ss7Layer.M3UA, M3UAManagementType.AS, as.getName(), asJmx);
            this.appServers.add(asJmx);
        }
    }

    private void removeAsFromManagement(As as) {
        synchronized (this.appServers) {
            As asJmx = null;
            for (FastList.Node<As> n = this.appServers.head(), end = this.appServers.tail(); (n = n.getNext()) != end;) {
                As asTemp = n.getValue();
                if (asTemp.getName().equals(as.getName())) {
                    asJmx = asTemp;
                    break;
                }
            }// for

            this.appServers.remove(asJmx);

            this.ss7Management.unregisterMBean(Ss7Layer.M3UA, M3UAManagementType.AS, as.getName());
        }
    }

    private void addAspFactoryToManagement(AspFactory aspFactory) {
        synchronized (this.aspFactories) {
            AspFactoryJmx aspFactoryJmx = new AspFactoryJmx(aspFactory);
            this.ss7Management.registerMBean(Ss7Layer.M3UA, M3UAManagementType.ASPFACTORY, aspFactory.getName(), aspFactoryJmx);
            this.aspFactories.add(aspFactoryJmx);
        }
    }

    private void removeAspFactoryFromManagement(AspFactory aspFactory) {
        synchronized (this.aspFactories) {
            AspFactory aspFactoryJmx = null;
            for (FastList.Node<AspFactory> n = this.aspFactories.head(), end = this.aspFactories.tail(); (n = n.getNext()) != end;) {
                AspFactory aspFactoryJmxTmp = n.getValue();
                if (aspFactoryJmxTmp.getName().equals(aspFactory.getName())) {
                    aspFactoryJmx = aspFactoryJmxTmp;
                    break;
                }
            }

            this.aspFactories.remove(aspFactoryJmx);

            this.ss7Management.unregisterMBean(Ss7Layer.M3UA, M3UAManagementType.ASPFACTORY, aspFactoryJmx.getName());
        }
    }

    private void addAspToManagement(AsJmx asJmx, AspFactoryJmx aspFactoryJmx, Asp asp) {
        AspJmx aspJmx = new AspJmx(asp, asJmx, aspFactoryJmx);

        asJmx.addAppServerProcess(aspJmx);
        aspFactoryJmx.addAppServerProcess(aspJmx);

        this.ss7Management.registerMBean(Ss7Layer.M3UA, M3UAManagementType.ASP, aspJmx.getName(), aspJmx);
    }

    private void removeAspFromManagement(AsJmx asJmx, AspFactoryJmx aspFactoryJmx, Asp asp) {
        AspJmx aspJmx = asJmx.removeAppServerProcess(asp.getName());
        aspFactoryJmx.removeAppServerProcess(aspJmx);

        this.ss7Management.unregisterMBean(Ss7Layer.M3UA, M3UAManagementType.ASP, asp.getName());
    }

    private void removeAllResourcesFromManagement() {

        List<As> appServers = this.wrappedM3UAManagement.getAppServers();
        for (As as : appServers) {

            List<Asp> asps = as.getAspList();

            for (Asp asp : asps) {
                this.onAspUnassignedFromAs(as, asp);
            }

            this.removeAsFromManagement(as);
        }

        List<AspFactory> aspFactories = this.wrappedM3UAManagement.getAspfactories();
        for (AspFactory aspFactory : aspFactories) {
            this.removeAspFactoryFromManagement(aspFactory);
        }
    }

    @Override
    public String getAlarmProviderObjectPath() {
        return this.alc.getAlarmProviderObjectPath();
    }

    @Override
    public CurrentAlarmList getCurrentAlarmList() {
        CurrentAlarmListImpl all = new CurrentAlarmListImpl();

        if (wrappedM3UAManagement.isStarted()) {
            all = this.checkAllAlarms(false);
        } else {
            AlarmMessage alm = generateStoppedAlarm(false);
            all.addAlarm(alm);
        }

        return all;
    }

    @Override
    public void registerAlarmListener(AlarmListener al) {
        this.alc.registerAlarmListener(al);
    }

    @Override
    public void setAlarmProviderObjectPath(String value) {
        this.alc.setAlarmProviderObjectPath(value);
    }

    @Override
    public void unregisterAlarmListener(AlarmListener al) {
        this.alc.unregisterAlarmListener(al);
    }

    private AlarmMessage generateAsAlarm(As ass, boolean isCleared) {
        AlarmMessageImpl alm = new AlarmMessageImpl();

        alm.setIsCleared(isCleared);
        alm.setAlarmSeverity(AlarmSeverity.major);
        alm.setAlarmSource("SS7_M3UA_" + this.getName());
        alm.setObjectName("AS: " + ass.getName());
        alm.setObjectPath("/M3ua:" + this.getName() + "/Ass/As:" + ass.getName());
        alm.setProblemName("AS state is not active");
        alm.setTimeAlarm(Calendar.getInstance());

        return alm;
    }

    private AlarmMessage generateAspAlarm(Asp asp, boolean isCleared) {
        AlarmMessageImpl alm = new AlarmMessageImpl();

        alm.setIsCleared(isCleared);
        alm.setAlarmSeverity(AlarmSeverity.minor);
        alm.setAlarmSource("SS7_M3UA_" + this.getName());
        alm.setObjectName("ASP: " + asp.getName());
        alm.setObjectPath("/M3ua:" + this.getName() + "/Asps/Asp:" + asp.getName());
        alm.setProblemName("ASP state is not active");
        alm.setTimeAlarm(Calendar.getInstance());
        return alm;
    }

    private AlarmMessage generateStoppedAlarm(boolean isCleared) {
        AlarmMessageImpl alm = new AlarmMessageImpl();

        alm.setIsCleared(isCleared);
        alm.setAlarmSeverity(AlarmSeverity.critical);
        alm.setAlarmSource("SS7_M3UA_" + this.getName());
        alm.setObjectName("M3UA");
        alm.setObjectPath("/M3ua:" + this.getName());
        alm.setProblemName("M3UA server is stopped");
        alm.setTimeAlarm(Calendar.getInstance());

        return alm;
    }

    private CurrentAlarmListImpl checkAllAlarms(boolean isCleared) {
        CurrentAlarmListImpl all = new CurrentAlarmListImpl();

        List<AspFactory> lstAspFact = this.wrappedM3UAManagement.getAspfactories();
        for (AspFactory aspFact : lstAspFact) {
            if (aspFact.getStatus()) {
                List<Asp> lstAsp = aspFact.getAspList();
                for (Asp asp : lstAsp) {
                    if (!asp.isUp()) {
                        AlarmMessage alm = this.generateAspAlarm(asp, isCleared);
                        all.addAlarm(alm);
                    }
                }
            }
        }
        List<As> lstAs = this.wrappedM3UAManagement.getAppServers();
        for (As as : lstAs) {
            if (!as.isUp()) {
                AlarmMessage alm = this.generateAsAlarm(as, isCleared);
                all.addAlarm(alm);
            }
        }

        return all;
    }

    @Override
    public void onServiceStarted() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onServiceStopped() {
        // TODO Auto-generated method stub

    }

    @Override
    public void addM3UAManagementEventListener(M3UAManagementEventListener arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeM3UAManagementEventListener(M3UAManagementEventListener arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public Map<String, RouteAs> getRoute() {
        return this.wrappedM3UAManagement.getRoute();
    }

    @Override
    public M3UACounterProvider getCounterProvider() {
        return this.wrappedM3UAManagement.getCounterProvider();
    }

    @Override
    public void setStatisticsEnabled(boolean val) throws Exception {
        this.wrappedM3UAManagement.setStatisticsEnabled(val);
    }

    @Override
    public long getStatisticsTaskDelay() {
        return this.wrappedM3UAManagement.getStatisticsTaskDelay();
    }

    @Override
    public void setStatisticsTaskDelay(long statisticsTaskDelay) throws Exception {
        this.wrappedM3UAManagement.setStatisticsTaskDelay(statisticsTaskDelay);
    }

    @Override
    public long getStatisticsTaskPeriod() {
        return this.wrappedM3UAManagement.getStatisticsTaskPeriod();
    }

    @Override
    public void setStatisticsTaskPeriod(long statisticsTaskPeriod) throws Exception {
        this.wrappedM3UAManagement.setStatisticsTaskPeriod(statisticsTaskPeriod);
    }

    @Override
    public boolean getStatisticsEnabled() {
        return this.wrappedM3UAManagement.getStatisticsEnabled();
    }

    @Override
    public boolean getRoutingKeyManagementEnabled() {
        return this.wrappedM3UAManagement.getRoutingKeyManagementEnabled();
    }

    @Override
    public void setRoutingKeyManagementEnabled(boolean routingKeyManagementEnabled) {
        this.wrappedM3UAManagement.setRoutingKeyManagementEnabled(routingKeyManagementEnabled);
    }

    @Override
    public void setErrorRetryAction(int errorCode, String name, int retryCount) {
        M3UAErrorManagementState.getInstance().addErrorAction(new ErrorRetryActionImpl(errorCode, name, retryCount));
    }

    @Override
    public List<ErrorRetryAction> getErrorRetry() {
        return M3UAErrorManagementState.getInstance().getErrorRetry();

    }

    @Override
    public void removeErrorAction(int errorCode, String name, int retryCount) {
        M3UAErrorManagementState.getInstance().removeErrorAction(new ErrorRetryActionImpl(errorCode, name, retryCount));
    }

    @Override
    public CounterDefSet getCounterDefSet(String counterDefSetName) {
        return lstCounters.get(counterDefSetName);
    }

    @Override
    public String[] getCounterDefSetList() {
        String[] res = new String[lstCounters.size()];
        lstCounters.keySet().toArray(res);
        return res;
    }

    @Override
    public String getCounterMediatorName() {
        return "M3ua-" + this.getName();
    }

    @Override
    public SourceValueSet getSourceValueSet(String counterDefSetName, String campaignName, int durationInSeconds) {
        if (durationInSeconds >= 60)
            logger.info("getSourceValueSet() - starting - campaignName=" + campaignName);
        else
            logger.debug("getSourceValueSet() - starting - campaignName=" + campaignName);

        SourceValueSetImpl svs;
        try {
            if (!this.wrappedM3UAManagement.isStarted())
                return null;

            M3UACounterProvider cp = this.wrappedM3UAManagement.getCounterProvider();
            if (cp == null)
                return null;

            String[] csl = this.getCounterDefSetList();
            if (!csl[0].equals(counterDefSetName))
                return null;

            svs = new SourceValueSetImpl(cp.getSessionId());

            CounterDefSet cds = getCounterDefSet(counterDefSetName);
            for (CounterDef cd : cds.getCounterDefs()) {
                SourceValueCounterImpl scs = new SourceValueCounterImpl(cd);
                SourceValueObjectImpl svo = null;
                switch (cd.getCounterName()) {
                    case "PacketsPerAssociationTx" :
                        svo = createComplexValue(cp.getPacketsPerAssTx(campaignName));
                        break;
                    case "AspUpPerAssociationTx" :
                        svo = createComplexValue(cp.getAspUpPerAssTx(campaignName));
                        break;
                    case "AspUpAckPerAssociationTx" :
                        svo = createComplexValue(cp.getAspUpAckPerAssTx(campaignName));
                        break;
                    case "AspDownPerAssociationTx" :
                        svo = createComplexValue(cp.getAspDownPerAssTx(campaignName));
                        break;
                    case "AspDownAckPerAssociationTx" :
                        svo = createComplexValue(cp.getAspDownAckPerAssTx(campaignName));
                        break;
                    case "AspActivePerAssociationTx" :
                        svo = createComplexValue(cp.getAspActivePerAssTx(campaignName));
                        break;
                    case "AspActiveAckPerAssociationTx" :
                        svo = createComplexValue(cp.getAspActiveAckPerAssTx(campaignName));
                        break;
                    case "AspInactivePerAssociationTx" :
                        svo = createComplexValue(cp.getAspInactivePerAssTx(campaignName));
                        break;
                    case "AspInactiveAckPerAssociationTx" :
                        svo = createComplexValue(cp.getAspInactiveAckPerAssTx(campaignName));
                        break;
                    case "ErrorPerAssociationTx" :
                        svo = createComplexValue(cp.getErrorPerAssTx(campaignName));
                        break;
                    case "NotifyPerAssociationTx" :
                        svo = createComplexValue(cp.getNotifyPerAssTx(campaignName));
                        break;
                    case "DunaPerAssociationTx" :
                        svo = createComplexValue(cp.getDunaPerAssTx(campaignName));
                        break;
                    case "DavaPerAssociationTx" :
                        svo = createComplexValue(cp.getDavaPerAssTx(campaignName));
                        break;
                    case "DaudPerAssociationTx" :
                        svo = createComplexValue(cp.getDaudPerAssTx(campaignName));
                        break;
                    case "SconPerAssociationTx" :
                        svo = createComplexValue(cp.getSconPerAssTx(campaignName));
                        break;
                    case "DupuPerAssociationTx" :
                        svo = createComplexValue(cp.getDupuPerAssTx(campaignName));
                        break;
                    case "DrstPerAssociationTx" :
                        svo = createComplexValue(cp.getDrstPerAssTx(campaignName));
                        break;
                    case "HeartbeatPerAssociationTx" :
                        svo = createComplexValue(cp.getBeatPerAssTx(campaignName));
                        break;
                    case "HeartbeatAckPerAssociationTx" :
                        svo = createComplexValue(cp.getBeatAckPerAssTx(campaignName));
                        break;
                    case "PacketsPerAssociationRx" :
                        svo = createComplexValue(cp.getPacketsPerAssRx(campaignName));
                        break;
                    case "AspUpPerAssociationRx" :
                        svo = createComplexValue(cp.getAspUpPerAssRx(campaignName));
                        break;
                    case "AspUpAckPerAssociationRx" :
                        svo = createComplexValue(cp.getAspUpAckPerAssRx(campaignName));
                        break;
                    case "AspDownPerAssociationRx" :
                        svo = createComplexValue(cp.getAspDownPerAssRx(campaignName));
                        break;
                    case "AspDownAckPerAssociationRx" :
                        svo = createComplexValue(cp.getAspDownAckPerAssRx(campaignName));
                        break;
                    case "AspActivePerAssociationRx" :
                        svo = createComplexValue(cp.getAspActivePerAssRx(campaignName));
                        break;
                    case "AspActiveAckPerAssociationRx" :
                        svo = createComplexValue(cp.getAspActiveAckPerAssRx(campaignName));
                        break;
                    case "AspInactivePerAssociationRx" :
                        svo = createComplexValue(cp.getAspInactivePerAssRx(campaignName));
                        break;
                    case "AspInactiveAckPerAssociationRx" :
                        svo = createComplexValue(cp.getAspInactiveAckPerAssRx(campaignName));
                        break;
                    case "ErrorPerAssociationRx" :
                        svo = createComplexValue(cp.getErrorPerAssRx(campaignName));
                        break;
                    case "NotifyPerAssociationRx" :
                        svo = createComplexValue(cp.getNotifyPerAssRx(campaignName));
                        break;
                    case "DunaPerAssociationRx" :
                        svo = createComplexValue(cp.getDunaPerAssRx(campaignName));
                        break;
                    case "DavaPerAssociationRx" :
                        svo = createComplexValue(cp.getDavaPerAssRx(campaignName));
                        break;
                    case "DaudPerAssociationRx" :
                        svo = createComplexValue(cp.getDaudPerAssRx(campaignName));
                        break;
                    case "SconPerAssociationRx" :
                        svo = createComplexValue(cp.getSconPerAssRx(campaignName));
                        break;
                    case "DupuPerAssociationRx" :
                        svo = createComplexValue(cp.getDupuPerAssRx(campaignName));
                        break;
                    case "DrstPerAssociationRx" :
                        svo = createComplexValue(cp.getDrstPerAssRx(campaignName));
                        break;
                    case "HeartbeatPerAssociationRx" :
                        svo = createComplexValue(cp.getBeatPerAssRx(campaignName));
                        break;
                    case "HeartbeatAckPerAssociationRx" :
                        svo = createComplexValue(cp.getBeatAckPerAssRx(campaignName));
                        break;
                }

                if (svo != null)
                    scs.addObject(svo);
                svs.addCounter(scs);
            }
        } catch (Throwable e) {
            logger.info("Exception when getSourceValueSet() - campaignName=" + campaignName + " - " + e.getMessage(), e);
            return null;
        }

        if (durationInSeconds >= 60)
            logger.info("getSourceValueSet() - return value - campaignName=" + campaignName);
        else
            logger.debug("getSourceValueSet() - return value - campaignName=" + campaignName);

        return svs;
    }

    private void setupCounterList() {
        FastMap<String, CounterDefSet> lst = new FastMap<String, CounterDefSet>();

        CounterDefSetImpl cds = new CounterDefSetImpl(this.getCounterMediatorName() + "-Main");
        lst.put(cds.getName(), cds);

        //TX counters
        CounterDef cd = new CounterDefImpl(CounterType.ComplexValue, "PacketsPerAssociationTx", "Number of data packets transmitted per association");
        cds.addCounterDef(cd);
        cd = new CounterDefImpl(CounterType.ComplexValue, "AspUpPerAssociationTx", "Number of ASP UP messages transmitted per association");
        cds.addCounterDef(cd);
        cd = new CounterDefImpl(CounterType.ComplexValue, "AspUpAckPerAssociationTx", "Number of ASP UP ACK messages transmitted per association");
        cds.addCounterDef(cd);
        cd = new CounterDefImpl(CounterType.ComplexValue, "AspDownPerAssociationTx", "Number of ASP DOWN messages transmitted per association");
        cds.addCounterDef(cd);
        cd = new CounterDefImpl(CounterType.ComplexValue, "AspDownAckPerAssociationTx", "Number of ASP DOWN ACK messages transmitted per association");
        cds.addCounterDef(cd);
        cd = new CounterDefImpl(CounterType.ComplexValue, "AspActivePerAssociationTx", "Number of ASP ACTIVE messages transmitted per association");
        cds.addCounterDef(cd);
        cd = new CounterDefImpl(CounterType.ComplexValue, "AspActiveAckPerAssociationTx", "Number of ASP ACTIVE ACK messages transmitted per association");
        cds.addCounterDef(cd);
        cd = new CounterDefImpl(CounterType.ComplexValue, "AspInactivePerAssociationTx", "Number of ASP INACTIVE messages transmitted per association");
        cds.addCounterDef(cd);
        cd = new CounterDefImpl(CounterType.ComplexValue, "AspInactiveAckPerAssociationTx", "Number of ASP INACTIVE ACK transmitted per association");
        cds.addCounterDef(cd);
        cd = new CounterDefImpl(CounterType.ComplexValue, "ErrorPerAssociationTx", "Number of ERROR messages transmitted per association");
        cds.addCounterDef(cd);
        cd = new CounterDefImpl(CounterType.ComplexValue, "NotifyPerAssociationTx", "Number of NOTIFY messages transmitted per association");
        cds.addCounterDef(cd);
        cd = new CounterDefImpl(CounterType.ComplexValue, "DunaPerAssociationTx", "Number of DUNA messages transmitted per association");
        cds.addCounterDef(cd);
        cd = new CounterDefImpl(CounterType.ComplexValue, "DavaPerAssociationTx", "Number of DAVA messages transmitted per association");
        cds.addCounterDef(cd);
        cd = new CounterDefImpl(CounterType.ComplexValue, "DaudPerAssociationTx", "Number of DAUD messages transmitted per association");
        cds.addCounterDef(cd);
        cd = new CounterDefImpl(CounterType.ComplexValue, "SconPerAssociationTx", "Number of SCON messages transmitted per association");
        cds.addCounterDef(cd);
        cd = new CounterDefImpl(CounterType.ComplexValue, "DupuPerAssociationTx", "Number of DUPU messages transmitted per association");
        cds.addCounterDef(cd);
        cd = new CounterDefImpl(CounterType.ComplexValue, "DrstPerAssociationTx", "Number of DRST messages transmitted per association");
        cds.addCounterDef(cd);
        cd = new CounterDefImpl(CounterType.ComplexValue, "HeartbeatPerAssociationTx", "Number of HEARTBEAT messages transmitted per association");
        cds.addCounterDef(cd);
        cd = new CounterDefImpl(CounterType.ComplexValue, "HeartbeatAckPerAssociationTx", "Number of HEARTBEAT ACK messages transmitted per association");
        cds.addCounterDef(cd);

        //RX counters
        cd = new CounterDefImpl(CounterType.ComplexValue, "PacketsPerAssociationRx", "Number of data packets received per association");
        cds.addCounterDef(cd);
        cd = new CounterDefImpl(CounterType.ComplexValue, "AspUpPerAssociationRx", "Number of ASP UP messages received per association");
        cds.addCounterDef(cd);
        cd = new CounterDefImpl(CounterType.ComplexValue, "AspUpAckPerAssociationRx", "Number of ASP UP ACK messages received per association");
        cds.addCounterDef(cd);
        cd = new CounterDefImpl(CounterType.ComplexValue, "AspDownPerAssociationRx", "Number of ASP DOWN messages received per association");
        cds.addCounterDef(cd);
        cd = new CounterDefImpl(CounterType.ComplexValue, "AspDownAckPerAssociationRx", "Number of ASP DOWN ACK messages received per association");
        cds.addCounterDef(cd);
        cd = new CounterDefImpl(CounterType.ComplexValue, "AspActivePerAssociationRx", "Number of ASP ACTIVE messages received per association");
        cds.addCounterDef(cd);
        cd = new CounterDefImpl(CounterType.ComplexValue, "AspActiveAckPerAssociationRx", "Number of ASP ACTIVE ACK messages received per association");
        cds.addCounterDef(cd);
        cd = new CounterDefImpl(CounterType.ComplexValue, "AspInactivePerAssociationRx", "Number of ASP INACTIVE messages received per association");
        cds.addCounterDef(cd);
        cd = new CounterDefImpl(CounterType.ComplexValue, "AspInactiveAckPerAssociationRx", "Number of ASP INACTIVE ACK received per association");
        cds.addCounterDef(cd);
        cd = new CounterDefImpl(CounterType.ComplexValue, "ErrorPerAssociationRx", "Number of ERROR messages received per association");
        cds.addCounterDef(cd);
        cd = new CounterDefImpl(CounterType.ComplexValue, "NotifyPerAssociationRx", "Number of NOTIFY messages received per association");
        cds.addCounterDef(cd);
        cd = new CounterDefImpl(CounterType.ComplexValue, "DunaPerAssociationRx", "Number of DUNA messages received per association");
        cds.addCounterDef(cd);
        cd = new CounterDefImpl(CounterType.ComplexValue, "DavaPerAssociationRx", "Number of DAVA messages received per association");
        cds.addCounterDef(cd);
        cd = new CounterDefImpl(CounterType.ComplexValue, "DaudPerAssociationRx", "Number of DAUD messages received per association");
        cds.addCounterDef(cd);
        cd = new CounterDefImpl(CounterType.ComplexValue, "SconPerAssociationRx", "Number of SCON messages received per association");
        cds.addCounterDef(cd);
        cd = new CounterDefImpl(CounterType.ComplexValue, "DupuPerAssociationRx", "Number of DUPU messages received per association");
        cds.addCounterDef(cd);
        cd = new CounterDefImpl(CounterType.ComplexValue, "DrstPerAssociationRx", "Number of DRST messages received per association");
        cds.addCounterDef(cd);
        cd = new CounterDefImpl(CounterType.ComplexValue, "HeartbeatPerAssociationRx", "Number of HEARTBEAT messages received per association");
        cds.addCounterDef(cd);
        cd = new CounterDefImpl(CounterType.ComplexValue, "HeartbeatAckPerAssociationRx", "Number of HEARTBEAT ACK messages received per association");
        cds.addCounterDef(cd);

        lstCounters = lst;
    }

    private SourceValueObjectImpl createComplexValue(Map<String, LongValue> vv) {
        SourceValueObjectImpl svo = null;
        if (vv != null) {
            svo = new SourceValueObjectImpl(this.getName(), 0);
            ComplexValue[] vvv = new ComplexValue[vv.size()];
            int i1 = 0;
            for (String s : vv.keySet()) {
                LongValue lv = vv.get(s);
                vvv[i1++] = new ComplexValueImpl(s, lv.getValue());
            }
            svo.setComplexValue(vvv);
        }
        return svo;
    }

}
