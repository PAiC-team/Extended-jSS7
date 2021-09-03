
package org.restcomm.ss7.management.console;

/**
 * <p>
 * This class represents the Command Line Interface for managing the Mobicents SS7 stack.
 * </p>
 *
 *
 * @author amit bhayani
 *
 */
public class Shell {

    Version version = Version.instance;

    public final String WELCOME_MESSAGE = version.toString();

    public static final String CONNECTED_MESSAGE = "Connected to %s currently running on %s";

    public final String prefix;
    public static final String CLI_POSTFIX = ">";

    private void showCliHelp() {
        System.out.println(version.toString());
        System.out.println("Usage: SS7 [OPTIONS]");
        System.out.println("Valid Options");
        System.out.println("-v           Display version number and exit");
        System.out.println("-h           This help screen");
    }

    public Shell() {
        prefix = version.getProperty("prefix");
    }

    public static void main(String[] args) throws Exception {
        Shell shell = new Shell();
        shell.start(args);
    }

    private void start(String[] args) throws Exception {

        // Take care of Cmd Line arguments
        if (args != null && args.length > 0) {

            String cmd = args[0];

            if (cmd.compareTo("-v") == 0) {
                System.out.println(version.toString());
                System.exit(0);
            }

            if (cmd.compareTo("-h") == 0) {
                this.showCliHelp();
                System.exit(0);
            }

        }

        System.out.println(WELCOME_MESSAGE);

        CommandContextImpl commandContext = new CommandContextImpl();
        commandContext.setPrefix(this.prefix);

        // consoleListener = new ConsoleListenerImpl(this.prefix,
        // commandContext);
        // console = new ConsoleImpl(commandContext);
        // consoleListener.setConsole(console);
        // console.start();
        String line;
        // console.pushToConsole(ANSIColors.GREEN_TEXT());
        while ((!commandContext.isTerminated() && ((line = commandContext.readLine()) != null))) {
            line = line.trim();

            if (line.equals("")) {
                continue;
            }
            // if (line.equalsIgnoreCase("password")) {
            // line = console.read("password: ", Character.valueOf((char) 0));
            // console.pushToConsole("password typed:" + line + "\n");
            //
            // }
            if (line.equals("clear") || line.equals("cls")) {
                commandContext.clearScreen();
                continue;
            }

            // this.consoleListener.commandEntered(line);

            CommandHandler commandHandler = null;
            for (CommandHandler commandHandlerTemp : ConsoleImpl.commandHandlerList) {
                if (commandHandlerTemp.handles(line)) {
                    commandHandler = commandHandlerTemp;
                    break;
                }
            }

            if (commandHandler != null) {

                if (!commandHandler.isAvailable(commandContext)) {
                    continue;
                }

                commandHandler.handle(commandContext, line);
            } else {
                commandContext.printLine("Unexpected command \"" + line + "\"");
            }

        }
    }
}
