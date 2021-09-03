package org.mobicents.ss7.management.console.impl;

import org.restcomm.ss7.management.console.CommandContext;
import org.restcomm.ss7.management.console.CommandHandlerWithHelp;
import org.restcomm.ss7.management.console.Tree;
import org.restcomm.ss7.management.console.Tree.Node;

/**
 * @author amit bhayani
 *
 */
public class LinksetCommandHandler extends CommandHandlerWithHelp {

    static final Tree commandTree = new Tree("linkset");
    static {
        Node parent = commandTree.getTopNode();
        Node create = parent.addChild("create");
        Node delete = parent.addChild("delete");
        Node activate = parent.addChild("activate");
        Node deactivate = parent.addChild("deactivate");
        Node show = parent.addChild("show");

        Node link = parent.addChild("link");
        link.addChild("create");
        link.addChild("delete");
        link.addChild("activate");
        link.addChild("deactivate");

    };

    public LinksetCommandHandler() {
        super(commandTree, CONNECT_MANDATORY_FLAG);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.ss7.management.console.CommandHandler#isValid(java.lang .String)
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
     * @see org.mobicents.ss7.management.console.CommandHandler#isAvailable(org.mobicents
     * .ss7.management.console.CommandContext)
     */
    @Override
    public boolean isAvailable(CommandContext ctx) {
        if (!ctx.isControllerConnected()) {
            ctx.printLine("The command is not available in the current context. Please connnect first");
            return false;
        }
        return true;
    }

}
