
package org.restcomm.protocols.ss7.tools.traceparser;

/**
*
* @author sergey vetyutnev
*
*/
public class MainGui implements Runnable {

    private final Ss7ParseParameters par;
    private final String persistDir;

    public MainGui(String persistDir, Ss7ParseParameters par) {
        this.persistDir = persistDir;
        this.par = par;
    }

//    public static void main(String[] args) {
//
//        String appName = "main";
//        if (args != null && args.length > 0) {
//            appName = args[0];
//        }
//
//        EventQueue.invokeLater(new MainGui(appName));
//    }

    @Override
    public void run() {
        try {
            TraceParserForm frame = new TraceParserForm(par);
            frame.setPersistDir(persistDir);
            frame.frmSsTraceParser.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
