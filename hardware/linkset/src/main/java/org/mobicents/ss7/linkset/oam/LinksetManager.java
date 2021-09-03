package org.mobicents.ss7.linkset.oam;

import javolution.util.FastMap;

public interface LinksetManager {
    LinksetFactoryFactory getLinksetFactoryFactory();

    void setLinksetFactoryFactory(LinksetFactoryFactory linksetFactoryFactory);

    FastMap<String, Linkset> getLinksets();

    String getPersistDir();

    void setPersistDir(String persistDir);

    Layer4 getLayer4();

    void setLayer4(Layer4 layer4);

    void start();

    void stop();

    String getName();

    String showLinkset(String[] options) throws Exception;

    String createLinkset(String[] options) throws Exception;

    String deleteLinkset(String[] options) throws Exception;

    String activateLinkset(String[] options) throws Exception;

    String deactivateLinkset(String[] options) throws Exception;

    String createLink(String[] options) throws Exception;

    String deleteLink(String[] options) throws Exception;

    String activateLink(String[] options) throws Exception;

    String deactivateLink(String[] options) throws Exception;
}