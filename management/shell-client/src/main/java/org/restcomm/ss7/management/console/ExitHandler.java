
package org.restcomm.ss7.management.console;

/**
 * @author amit bhayani
 *
 */
public class ExitHandler extends CommandHandlerWithHelp {

    static final Tree commandTree = new Tree("exit");

    /**
     *
     */
    public ExitHandler() {
        super(commandTree, DISCONNECT_MANDATORY_FLAG);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.ss7.management.console.CommandHandler#isAvailable(org.mobicents
     * .ss7.management.console.CommandContext)
     */
    @Override
    public boolean isAvailable(CommandContext commandContext) {
        // Available only in disconnected mode
        if (commandContext.isControllerConnected()) {
            commandContext.printLine("The command is not available in the current context. Please disconnnect first");
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
        String[] commands = commandLine.split(" ");
        if (commands.length != 1) {
            ctx.printLine("Invalid command.");
            return;
        }

        ctx.terminateSession();
    }

}
