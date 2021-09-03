
package org.restcomm.protocols.ss7.tools.simulatorgui;

import java.awt.EventQueue;

import org.restcomm.protocols.ss7.tools.simulator.TesterHostFactoryImpl;
import org.restcomm.protocols.ss7.tools.simulator.TesterHostFactoryInterface;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class MainGui implements Runnable {

    private final String appName;

    public MainGui(String appName) {
        this.appName = appName;
    }

    protected TesterHostFactoryInterface getTesterHostFactory() {
        return new TesterHostFactoryImpl();
    }

    public static void main(String[] args) {

        String appName = "main";
        if (args != null && args.length > 0) {
            appName = args[0];
        }

        EventQueue.invokeLater(new MainGui(appName));
    }

    protected ConnectionForm createConnectionForm() {
        return new ConnectionForm(this.getTesterHostFactory());
    }

    @Override
    public void run() {
        try {
            ConnectionForm frame = createConnectionForm();
            frame.setAppName(appName);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
