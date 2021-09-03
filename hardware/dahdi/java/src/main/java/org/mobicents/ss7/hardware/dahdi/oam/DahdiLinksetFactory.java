package org.mobicents.ss7.hardware.dahdi.oam;

import org.restcomm.protocols.ss7.scheduler.Scheduler;
import org.mobicents.ss7.linkset.oam.LinkOAMMessages;
import org.mobicents.ss7.linkset.oam.Linkset;
import org.mobicents.ss7.linkset.oam.LinksetFactory;

/**
 * <p>
 * Factory for creating <tt>dahdi</tt> based {@link Linkset}
 * </p>
 *
 * @author amit bhayani
 *
 */
public class DahdiLinksetFactory extends LinksetFactory {

    private static final String NAME = "dahdi";
    private static final String LINKSET_NAME = "dahdilinkset";
    private static final String LINK_NAME = "dahdilink";
    private Scheduler scheduler;

    public DahdiLinksetFactory() {
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public Linkset createLinkset(String[] options) throws Exception {

        // the expected command is "linkset create dahdi opc 1 apc 2 ni 3
        // linkset1". We know length is 10
        if (options.length != 10) {
            throw new Exception(LinkOAMMessages.INVALID_COMMAND);
        }

        String option = options[3];

        // If first option is not OPC
        if (option.compareTo("opc") != 0) {
            return null;
        }

        int opc = Integer.parseInt(options[4]);

        if (options[5].compareTo("apc") != 0) {
            throw new Exception(LinkOAMMessages.INVALID_COMMAND);
        }

        int dpc = Integer.parseInt(options[6]);

        if (options[7].compareTo("ni") != 0) {
            throw new Exception(LinkOAMMessages.INVALID_COMMAND);
        }

        int ni = Integer.parseInt(options[8]);

        String name = options[9];

        if (name == null) {
            throw new Exception(LinkOAMMessages.INVALID_COMMAND);
        }

        DahdiLinkset currLinkSet = new DahdiLinkset(name, opc, dpc, ni);
        currLinkSet.setScheduler(scheduler);
        return currLinkSet;
    }

    /**
     * Get linkset name
     *
     * @return
     */
    public String getLinksetName() {
        return LINKSET_NAME;
    }

    /**
     * Get linkset class
     *
     * @return
     */
    public Class getLinksetClass() {
        return DahdiLinkset.class;
    }

    /**
     * Get link name
     *
     * @return
     */
    public String getLinkName() {
        return LINK_NAME;
    }

    /**
     * Get link class
     *
     * @return
     */
    public Class getLinkClass() {
        return DahdiLink.class;
    }
}
