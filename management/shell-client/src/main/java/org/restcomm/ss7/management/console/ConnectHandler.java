
package org.restcomm.ss7.management.console;

/**
 * @author amit bhayani
 *
 */
public class ConnectHandler extends CommandHandlerWithHelp {

    static final Tree commandTree = new Tree("connect");

    /**
     *
     */
    public ConnectHandler() {
        super(commandTree, DISCONNECT_MANDATORY_FLAG);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.ss7.management.console.CommandHandler#isAvailable(org.mobicents
     * .ss7.management.console.CommandContext)
     */
    @Override
    public boolean isAvailable(CommandContext ctx) {
        if (ctx.isControllerConnected()) {
            ctx.printLine("Already connected");
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
        // TODO Validate command

        if (commandLine.contains("--help")) {
            this.printHelp(commandLine, ctx);
            return;
        }

        String[] commands = commandLine.split(" ");

        if (commands.length == 1) {
            ctx.connectController(null, -1);
        } else if (commands.length == 2) {
            if (commandLine.contains("--help")) {
                this.printHelp("help/connect.txt", ctx);
                return;
            }
        } else if (commands.length == 3) {
            String host = commands[1];
            try {
                int port = Integer.parseInt(commands[2]);
                ctx.connectController(host, port);
            } catch (NumberFormatException ne) {
                ctx.printLine("Port must be from 1 to 65535");
            }

        } else {
            ctx.printLine("Invalid command.");
        }

    }
}
