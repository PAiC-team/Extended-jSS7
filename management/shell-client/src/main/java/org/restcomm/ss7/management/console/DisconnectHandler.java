
package org.restcomm.ss7.management.console;

/**
 * @author abhayani
 *
 */
public class DisconnectHandler extends CommandHandlerWithHelp {

    static final Tree commandTree = new Tree("disconnect");

    /**
     *
     */
    public DisconnectHandler() {
        super(commandTree, CONNECT_MANDATORY_FLAG);
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
            ctx.printLine("Already disconnected");
            return false;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.ss7.management.console.CommandHandler#handle(org.mobicents .ss7.management.console.CommandContext,
     * java.lang.String)
     */
    @Override
    public void handle(CommandContext ctx, String commandLine) {
        if (commandLine.contains("--help")) {
            this.printHelp(commandLine, ctx);
            return;
        }

        String[] commands = commandLine.split(" ");

        if (commands.length == 1) {
            ctx.sendMessage("disconnect");
            ctx.disconnectController();
        } else if (commands.length == 2) {
            if (commandLine.contains("--help")) {
                this.printHelp("help/disconnect.txt", ctx);
                return;
            }
        } else {
            ctx.printLine("Invalid command.");
        }

    }
}
