
package org.restcomm.ss7.management.console.impl;

import org.restcomm.ss7.management.console.CommandContext;
import org.restcomm.ss7.management.console.CommandHandlerWithHelp;
import org.restcomm.ss7.management.console.Tree;
import org.restcomm.ss7.management.console.Tree.Node;

/**
 * @author amit bhayani
 *
 */
public class SctpCommandHandler extends CommandHandlerWithHelp {

    static final Tree commandTree = new Tree("sctp");
    static {
        Node parent = commandTree.getTopNode();
        Node server = parent.addChild("server");
        server.addChild("create");
        server.addChild("modify");
        server.addChild("destroy");
        server.addChild("start");
        server.addChild("stop");
        server.addChild("show");

        Node association = parent.addChild("association");
        association.addChild("create");
        association.addChild("modify");
        association.addChild("destroy");
        association.addChild("show");

        Node set = parent.addChild("set");
        set.addChild("connectdelay");
        set.addChild("cc_delaythreshold_1");
        set.addChild("cc_delaythreshold_2");
        set.addChild("cc_delaythreshold_3");
        set.addChild("cc_backtonormal_delaythreshold_1");
        set.addChild("cc_backtonormal_delaythreshold_2");
        set.addChild("cc_backtonormal_delaythreshold_3");

        Node get = parent.addChild("get");
        get.addChild("connectdelay");
        get.addChild("cc_delaythreshold_1");
        get.addChild("cc_delaythreshold_2");
        get.addChild("cc_delaythreshold_3");
        get.addChild("cc_backtonormal_delaythreshold_1");
        get.addChild("cc_backtonormal_delaythreshold_2");
        get.addChild("cc_backtonormal_delaythreshold_3");

    };

    public SctpCommandHandler() {
        super(commandTree, CONNECT_MANDATORY_FLAG);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.ss7.management.console.CommandHandler#isValid(java.lang .String)
     */
    @Override
    public void handle(CommandContext ctx, String commandLine) {
        // TODO Validate command

        if (commandLine.contains("--help")) {
            this.printHelp(commandLine, ctx);
            return;
        }

        ctx.sendMessage(commandLine);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.ss7.management.console.CommandHandler#isAvailable(org.mobicents
     * .ss7.management.console.CommandContext)
     */
    @Override
    public boolean isAvailable(CommandContext ctx) {
        if (!ctx.isControllerConnected()) {
            ctx.printLine("The command is not available in the current context. Please connect first");
            return false;
        }
        return true;
    }

}
