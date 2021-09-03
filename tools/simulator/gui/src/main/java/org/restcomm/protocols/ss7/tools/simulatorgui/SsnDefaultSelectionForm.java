
package org.restcomm.protocols.ss7.tools.simulatorgui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class SsnDefaultSelectionForm extends JDialog {

    private static final long serialVersionUID = 2476545481583216386L;
    int selected = 0;

    private JRadioButton rbMap;
    private JRadioButton rbCap;
    private final ButtonGroup buttonGroup = new ButtonGroup();

    public SsnDefaultSelectionForm() {
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
        setModal(true);
        setResizable(false);
        setBounds(100, 100, 456, 217);

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        JLabel lblSelectATest = new JLabel("Select a test scenario for SSN selection");
        lblSelectATest.setBounds(10, 21, 383, 40);
        panel.add(lblSelectATest);

        rbMap = new JRadioButton("MAP VLR (fpr MAP USSD,SMS) - SSN=8");
        buttonGroup.add(rbMap);
        rbMap.setSelected(true);
        rbMap.setBounds(6, 68, 357, 23);
        panel.add(rbMap);

        rbCap = new JRadioButton("CAP  (fpr CAMEL tests) - SSN=146");
        buttonGroup.add(rbCap);
        rbCap.setBounds(6, 94, 357, 23);
        panel.add(rbCap);

        JButton btCancel = new JButton("Cancel");
        btCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                dispose();
            }
        });
        btCancel.setBounds(289, 144, 128, 23);
        panel.add(btCancel);

        JButton btOK = new JButton("OK");
        btOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (rbMap.isSelected())
                    selected = 1;
                else
                    selected = 2;
                dispose();
            }
        });
        btOK.setBounds(151, 144, 128, 23);
        panel.add(btOK);
    }

    public int getResult() {
        return selected;
    }
}
