
package org.restcomm.ss7.management.console.impl;

import org.restcomm.ss7.management.console.CommandContext;
import org.restcomm.ss7.management.console.CommandHandlerWithHelp;
import org.restcomm.ss7.management.console.Tree;
import org.restcomm.ss7.management.console.Tree.Node;

/**
 * @author amit bhayani
 *
 */
public class M3UACommandHandler extends CommandHandlerWithHelp {

    static final Tree commandTree = new Tree("m3ua");
    static {
        Node parent = commandTree.getTopNode();
        Node as = parent.addChild("as");
        as.addChild("create");
        as.addChild("destroy");
        as.addChild("add");
        as.addChild("remove");
        as.addChild("show");

        Node asp = parent.addChild("asp");
        asp.addChild("create");
        asp.addChild("destroy");
        asp.addChild("start");
        asp.addChild("stop");
        asp.addChild("show");

        Node route = parent.addChild("route");
        route.addChild("add");
        route.addChild("remove");
        route.addChild("show");

        Node set = parent.addChild("set");
        set.addChild("heartbeattime");
        set.addChild("statisticsenabled");
        set.addChild("statisticsdelay");
        set.addChild("statisticsperiod");
        set.addChild("routingkeymanagementenabled");
        set.addChild("uselsbforlinksetselection");

        Node get = parent.addChild("get");
        get.addChild("heartbeattime");
        get.addChild("statisticsenabled");
        get.addChild("statisticsdelay");
        get.addChild("statisticsperiod");
        get.addChild("routingkeymanagementenabled");
        get.addChild("uselsbforlinksetselection");
        get.addChild("maxsequencenumber");
        get.addChild("maxasforroute");
        get.addChild("routinglabelformat");
        get.addChild("deliverymessagethreadcount");

        // add the error management handling
        Node errorHandler = parent.addChild("error");
        errorHandler.addChild("show");
        errorHandler.addChild("remove");
        errorHandler.addChild("add");

    };

    public M3UACommandHandler() {
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
