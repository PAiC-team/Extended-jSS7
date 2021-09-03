
package org.restcomm.ss7.management.console;

import java.util.List;

import org.restcomm.ss7.management.console.Tree.Node;

/**
 * @author amit bhayani
 *
 */
public class HistoryHandler extends CommandHandlerWithHelp {

    static final Tree commandTree = new Tree("history");
    static {
        Node parent = commandTree.getTopNode();
        parent.addChild("enable");
        parent.addChild("disable");
        parent.addChild("clear");
    };

    /**
     *
     */
    public HistoryHandler() {
        super(commandTree, DOESNT_CARE_CONNECT_DISCONNECT_FLAG);

    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.ss7.management.console.CommandHandler#isAvailable(org.mobicents
     * .ss7.management.console.CommandContext)
     */
    @Override
    public boolean isAvailable(CommandContext ctx) {
        return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.ss7.management.console.CommandHandler#isValid(java.lang .String)
     */
    @Override
    public void handle(CommandContext ctx, String commandLine) {

        if (commandLine.contains("--help")) {
            this.printHelp(commandLine, ctx);
            return;
        }

        String[] commands = commandLine.split(" ");

        if (commands.length == 1) {
            this.printHistory(ctx);
        } else if (commands.length == 2) {
            String argument = commands[1];
            if ("clear".equals(argument)) {
                ctx.getHistory().clear();
            } else if ("enable".equals(argument)) {
                ctx.getHistory().setUseHistory(true);
            } else if ("disable".equals(argument)) {
                ctx.getHistory().setUseHistory(false);
            } else {
                ctx.printLine("Invalid command.");
            }
        } else {
            ctx.printLine("Invalid command.");
        }
    }

    private static void printHistory(CommandContext ctx) {

        CommandHistory history = ctx.getHistory();
        List<String> list = history.asList();
        for (String cmd : list) {
            ctx.printLine(cmd);
        }
        ctx.printLine("(The history is currently " + (history.isUseHistory() ? "enabled)" : "disabled)"));
    }

}
