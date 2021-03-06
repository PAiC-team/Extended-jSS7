
package org.restcomm.ss7.management.console.impl;

import org.restcomm.ss7.management.console.CommandContext;
import org.restcomm.ss7.management.console.CommandHandlerWithHelp;
import org.restcomm.ss7.management.console.Tree;
import org.restcomm.ss7.management.console.Tree.Node;

/**
 * @author amit bhayani
 *
 */
public class TcapCommandHandler extends CommandHandlerWithHelp {

    static final Tree commandTree = new Tree("tcap");
    static {
        Node parent = commandTree.getTopNode();

        Node set = parent.addChild("set");
        set.addChild("dialogidletimeout");
        set.addChild("invoketimeout");
        set.addChild("maxdialogs");
        set.addChild("dialogidrangestart");
        set.addChild("dialogidrangeend");
//        set.addChild("previewmode");
        set.addChild("donotsendprotocolversion");
        set.addChild("statisticsenabled");
        set.addChild("swaptcapidbytes");
        set.addChild("ssn");
        set.addChild("executordelaythreshold_1");
        set.addChild("executordelaythreshold_2");
        set.addChild("executordelaythreshold_3");
        set.addChild("executorbacktonormaldelaythreshold_1");
        set.addChild("executorbacktonormaldelaythreshold_2");
        set.addChild("executorbacktonormaldelaythreshold_3");
        set.addChild("memorythreshold_1");
        set.addChild("memorythreshold_2");
        set.addChild("memorythreshold_3");
        set.addChild("backtonormalmemorythreshold_1");
        set.addChild("backtonormalmemorythreshold_2");
        set.addChild("backtonormalmemorythreshold_3");
        set.addChild("blockingincomingtcapmessages");
        set.addChild("slsrange");

        Node get = parent.addChild("get");
        get.addChild("dialogidletimeout");
        get.addChild("invoketimeout");
        get.addChild("maxdialogs");
        get.addChild("dialogidrangestart");
        get.addChild("dialogidrangeend");
        get.addChild("previewmode");
        get.addChild("donotsendprotocolversion");
        get.addChild("statisticsenabled");
        get.addChild("swaptcapidbytes");
        get.addChild("ssn");
        get.addChild("executordelaythreshold_1");
        get.addChild("executordelaythreshold_2");
        get.addChild("executordelaythreshold_3");
        get.addChild("executorbacktonormaldelaythreshold_1");
        get.addChild("executorbacktonormaldelaythreshold_2");
        get.addChild("executorbacktonormaldelaythreshold_3");
        get.addChild("memorythreshold_1");
        get.addChild("memorythreshold_2");
        get.addChild("memorythreshold_3");
        get.addChild("backtonormalmemorythreshold_1");
        get.addChild("backtonormalmemorythreshold_2");
        get.addChild("backtonormalmemorythreshold_3");
        get.addChild("blockingincomingtcapmessages");
        get.addChild("slsrange");

    };

    public TcapCommandHandler() {
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
