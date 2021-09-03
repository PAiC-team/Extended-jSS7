
package org.restcomm.protocols.ss7.tools.traceparser;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Rectangle;
import javax.swing.JScrollPane;

/**
*
* @author sergey vetyutnev
*
*/
public class StatisticResultFm extends JDialog {

    public StatisticResultFm(JFrame owner, String data) {
        super(owner, true);

        setBounds(new Rectangle(150, 150, 450, 350));

        JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        scrollPane.setViewportView(textArea);

        textArea.setText(data);
    }

}
