
package org.restcomm.ss7.management.console.impl;

import org.restcomm.ss7.management.console.CommandContext;
import org.restcomm.ss7.management.console.CommandHandlerWithHelp;
import org.restcomm.ss7.management.console.Tree;
import org.restcomm.ss7.management.console.Tree.Node;

/**
 * @author amit bhayani
 *
 */
public class SccpExtCommandHandler extends CommandHandlerWithHelp {

    static final Tree commandTree = new Tree("sccp");
    static {
        Node parent = commandTree.getTopNode();

        Node rule = parent.addChild("rule");
        rule.addChild("create");
        rule.addChild("modify");
        rule.addChild("delete");
        rule.addChild("show");

        Node address = parent.addChild("address");
        address.addChild("create");
        address.addChild("modify");
        address.addChild("delete");
        address.addChild("show");

    };

    public SccpExtCommandHandler() {
        super(commandTree, CONNECT_MANDATORY_FLAG);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.ss7.management.console.CommandHandler#isValid(java.lang.String)
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
     * @see org.restcomm.ss7.management.console.CommandHandler#isAvailable(org.mobicents.ss7.management.console.CommandContext)
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
