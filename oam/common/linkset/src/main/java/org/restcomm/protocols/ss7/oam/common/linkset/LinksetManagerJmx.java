
package org.restcomm.protocols.ss7.oam.common.linkset;

import javolution.util.FastMap;

import org.mobicents.ss7.linkset.oam.Layer4;
import org.mobicents.ss7.linkset.oam.Linkset;
import org.mobicents.ss7.linkset.oam.LinksetFactoryFactory;
import org.mobicents.ss7.linkset.oam.LinksetManager;

import org.restcomm.protocols.ss7.oam.common.jmx.MBeanHost;
import org.restcomm.protocols.ss7.oam.common.jmxss7.Ss7Layer;

/**
 * @author Lasith Waruna Perera
 *
 */
public class LinksetManagerJmx implements LinksetManagerJmxMBean {

    private final MBeanHost ss7Management;
    private final LinksetManager wrappedLinksetManager;

    public LinksetManagerJmx(MBeanHost ss7Management, LinksetManager wrappedLinksetManager) {
        this.ss7Management = ss7Management;
        this.wrappedLinksetManager = wrappedLinksetManager;
    }

    @Override
    public String activateLink(String[] arg0) throws Exception {
        return this.wrappedLinksetManager.activateLink(arg0);
    }

    @Override
    public String activateLinkset(String[] arg0) throws Exception {
        return this.wrappedLinksetManager.activateLinkset(arg0);
    }

    @Override
    public String createLink(String[] arg0) throws Exception {
        return this.wrappedLinksetManager.createLink(arg0);
    }

    @Override
    public String createLinkset(String[] arg0) throws Exception {
        return this.wrappedLinksetManager.createLinkset(arg0);
    }

    @Override
    public String deactivateLink(String[] arg0) throws Exception {
        return this.wrappedLinksetManager.deactivateLink(arg0);
    }

    @Override
    public String deactivateLinkset(String[] arg0) throws Exception {
        return this.wrappedLinksetManager.deactivateLinkset(arg0);
    }

    @Override
    public String deleteLink(String[] arg0) throws Exception {
        return this.wrappedLinksetManager.deleteLink(arg0);
    }

    @Override
    public String deleteLinkset(String[] arg0) throws Exception {
        return this.wrappedLinksetManager.deleteLinkset(arg0);
    }

    @Override
    public Layer4 getLayer4() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public LinksetFactoryFactory getLinksetFactoryFactory() {
        return this.wrappedLinksetManager.getLinksetFactoryFactory();
    }

    @Override
    public FastMap<String, Linkset> getLinksets() {
        return this.wrappedLinksetManager.getLinksets();
    }

    @Override
    public String getName() {
        return this.wrappedLinksetManager.getName();
    }

    @Override
    public String getPersistDir() {
        return this.wrappedLinksetManager.getPersistDir();
    }

    @Override
    public void setLayer4(Layer4 arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setLinksetFactoryFactory(LinksetFactoryFactory arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setPersistDir(String arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public String showLinkset(String[] arg0) throws Exception {
        return this.wrappedLinksetManager.showLinkset(arg0);
    }

    @Override
    public void start() {
        this.ss7Management.registerMBean(Ss7Layer.LINKSET, LinksetManagementType.MANAGEMENT, this.getName(), this);
    }

    @Override
    public void stop() {
        // TODO Auto-generated method stub

    }

}
