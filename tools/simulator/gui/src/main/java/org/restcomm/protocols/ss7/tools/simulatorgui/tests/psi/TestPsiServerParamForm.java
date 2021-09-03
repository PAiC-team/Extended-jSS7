package org.restcomm.protocols.ss7.tools.simulatorgui.tests.psi;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

import org.apache.log4j.Logger;
import org.restcomm.protocols.ss7.map.api.primitives.AddressNature;
import org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan;
import org.restcomm.protocols.ss7.tools.simulator.common.AddressNatureType;
import org.restcomm.protocols.ss7.tools.simulator.level3.NumberingPlanMapType;
import org.restcomm.protocols.ss7.tools.simulator.tests.psi.TestPsiServerManMBean;
import org.restcomm.protocols.ss7.tools.simulatorgui.M3uaForm;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class TestPsiServerParamForm extends JDialog {

  private static final long serialVersionUID = 5928271328162043272L;

  private static Logger logger = Logger.getLogger(TestPsiServerParamForm.class);

  private TestPsiServerManMBean testPsiServerManMBean;

  private JTextField cbAddress;
  private JTabbedPane tabbedPane;
  private JComboBox cbAddressNature;
  private JComboBox cbNumberingPlan;
  private JTextField tfMcc;
  private JTextField tfMnc;
  private JTextField tfLac;
  private JTextField tfCellId;
  private JTextField tfImsi;
  private JTextField tfImei;
  private JTextField tfNetworkNodeNumber;
  private JTextField tfLmsi;
  private JTextField tfAol;
  private JTextField tfIsSaiPresent;
  private JTextField tfGeographicLatitude;
  private JTextField tfGeographicLongitude;
  private JTextField tfGeographicalUncertainty;
  private JTextField tfcreeningAndPresentationIndicators;
  private JTextField tfGeodeticLatitude;
  private JTextField tfGeodeticLongitude;
  private JTextField tfGeodeticUncetainty;
  private JTextField tfGeodeticConfidence;
  private JTextField tfGCurrentLocationRetrieved;


  private JPanel createTab(JTabbedPane parent, String name) {
    JPanel tab = new JPanel();
    parent.addTab(name, null, tab, null);
    tab.setLayout(null);
    return tab;
  }

  private JPanel createSection(JPanel tab, String name, int y_pos, int height) {
    JPanel panel = new JPanel();
    panel.setLayout(null);
    panel.setBorder(new LineBorder(new Color(0, 0, 0)));
    panel.setBounds(26, y_pos, 511, height);
    tab.add(panel);

    JLabel label = new JLabel(name);
    label.setBounds(10, 0, 266, 14);
    panel.add(label);

    return panel;
  }

  private JComboBox createCombo(JPanel section, String name, int y_pos) {

    JLabel label = new JLabel(name);
    label.setBounds(10, y_pos, 174, 14);
    section.add(label);

    JComboBox comboBox = new JComboBox();
    comboBox.setBounds(194, y_pos, 307, 20);
    section.add(comboBox);

    return comboBox;
  }

  private JTextField createTextField(JPanel section, String name, int y_pos) {

    JLabel label = new JLabel(name);
    label.setBounds(10, y_pos, 174, 14);
    section.add(label);

    JTextField textField = new JTextField();
    textField.setBounds(194, y_pos, 307, 20);
    textField.setColumns(10);
    section.add(textField);

    return textField;
  }

  private JTextField createSmallTextField(JPanel section, String name, int x_pos, int y_pos) {

    int calculated_x = x_pos * 10 + (x_pos - 1) * 175;

    JLabel label = new JLabel(name);
    label.setBounds(calculated_x, y_pos, 75, 14);
    section.add(label);

    JTextField textField = new JTextField();
    textField.setBounds(calculated_x + 75, y_pos, 75, 20);
    textField.setColumns(10);
    section.add(textField);

    return textField;
  }

  private void createLabel(JPanel section, String name, int y_pos) {

    JLabel label = new JLabel(name);
    label.setBounds(10, y_pos, 450, 14);
    section.add(label);
  }

  private JCheckBox createCheckbox(JPanel section, String name, int y_pos) {

    JCheckBox checkBox = new JCheckBox(name);
    checkBox.setBounds(10, y_pos, 450, 20);
    section.add(checkBox);

    return checkBox;
  }

  public TestPsiServerParamForm(JFrame owner) {
    super(owner, true);

    int bottomOfPage = 800;
    int lineSeparation = 22;
    int sectionSeparation = 5;

    setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    setResizable(false);
    setTitle("MAP LCS test client settings");
    setBounds(100, 100, 640, bottomOfPage);
    getContentPane().setLayout(null);

    tabbedPane = new JTabbedPane(JTabbedPane.TOP);
    tabbedPane.setBounds(0, 0, 634, bottomOfPage - 100);
    getContentPane().add(tabbedPane);

    // General TAB
    JPanel panel_gen = createTab(tabbedPane, "General");

    JPanel panel = createSection(panel_gen, "Parameters for AddressString creation", 23, lineSeparation * 5);
    cbAddressNature = createCombo(panel, "AddressNature", lineSeparation);
    cbNumberingPlan = createCombo(panel, "NumberingPlanType", lineSeparation * 2);
    cbAddress = createTextField(panel, "Digits", lineSeparation * 3);

    // SRI Request TAB
    JPanel panel_sri = createTab(tabbedPane, "SRIforSM request");
    JPanel panelSriDetail = createSection(panel_sri, "SRIforSM request parameters", 23, lineSeparation * 2);
    createLabel(panelSriDetail, "AddressNature, NumberingPlan and NumberingPlanType from General tab", lineSeparation);

    // PSL Request TAB
    JPanel panel_psi = createTab(tabbedPane, "PSL Request");

    JPanel panel_psi_1 = createSection(panel_psi, "PSI request parameters", sectionSeparation, lineSeparation * 3);
    createLabel(panel_psi_1, "PSI params", lineSeparation * 2);


    JButton button = new JButton("Load default values for side A");
    button.setBounds(10, bottomOfPage - 90, 246, 23);
    getContentPane().add(button);

    JButton button_3 = new JButton("Load default values for side B");
    button_3.setBounds(266, bottomOfPage - 90, 255, 23);
    getContentPane().add(button_3);

    JButton button_4 = new JButton("Cancel");
    button_4.setBounds(404, bottomOfPage - 60, 117, 23);
    getContentPane().add(button_4);

    JButton button_2 = new JButton("Save");
    button_2.setBounds(180, bottomOfPage - 60, 117, 23);
    getContentPane().add(button_2);

    JButton button_1 = new JButton("Reload");
    button_1.setBounds(10, bottomOfPage - 60, 144, 23);
    getContentPane().add(button_1);
    button_1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        reloadData();
      }
    });
    button_2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (saveData()) {
          getJFrame().dispose();
        }
      }
    });
    button_4.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        getJFrame().dispose();
      }
    });
    button_3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        loadDataB();
      }
    });
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        loadDataA();
      }
    });

  }


  public void setData(TestPsiServerManMBean testPsiServerManMBean) {
    this.testPsiServerManMBean = testPsiServerManMBean;

    this.reloadData();
  }

  private JDialog getJFrame() {
    return this;
  }

  private void loadDataA() {
    M3uaForm.setEnumeratedBaseComboBox(cbAddressNature,
        new AddressNatureType(AddressNature.international_number.getIndicator()));
    M3uaForm.setEnumeratedBaseComboBox(cbNumberingPlan, new NumberingPlanMapType(NumberingPlan.ISDN.getIndicator()));
    cbAddress.setText("5980482910");
    this.tfImsi.setText("748010192837465");
    this.tfImei.setText("01171400466105");
    this.tfLmsi.setText("09876543");
    this.tfMcc.setText("748");
    this.tfMnc.setText("1");
    this.tfLac.setText("5");
    this.tfCellId.setText("3479");
    this.tfAol.setText("1");
    this.tfGeographicLatitude.setText("-23.291032");
    this.tfGeographicLongitude.setText("109.977810");
    this.tfGeodeticUncetainty.setText("50.0");
    this.tfGeodeticLatitude.setText("-24.010010");
    this.tfGeodeticLongitude.setText("110.00987");
    this.tfGeodeticUncetainty.setText("100.0");
    this.tfGeodeticConfidence.setText("1");
    this.tfcreeningAndPresentationIndicators.setText("3");
    this.tfIsSaiPresent.setText("false");
    this.tfGCurrentLocationRetrieved.setText("true");

  }

  private void reloadData() {
    M3uaForm.setEnumeratedBaseComboBox(cbAddressNature, this.testPsiServerManMBean.getAddressNature());
    M3uaForm.setEnumeratedBaseComboBox(cbNumberingPlan, this.testPsiServerManMBean.getNumberingPlanType());

    cbAddress.setText(this.testPsiServerManMBean.getNumberingPlan());
    this.tfImsi.setText(this.testPsiServerManMBean.getImsi());
    this.tfNetworkNodeNumber.setText(this.testPsiServerManMBean.getNetworkNodeNumber());
    this.tfImei.setText(this.testPsiServerManMBean.getImei());
    this.tfLmsi.setText(this.testPsiServerManMBean.getLmsi());
    this.tfMcc.setText(String.valueOf(this.testPsiServerManMBean.getMcc()));
    this.tfMnc.setText(String.valueOf(this.testPsiServerManMBean.getMnc()));
    this.tfLac.setText(String.valueOf(this.testPsiServerManMBean.getLac()));
    this.tfCellId.setText(String.valueOf(this.testPsiServerManMBean.getCi()));
    this.tfAol.setText(String.valueOf(this.testPsiServerManMBean.getAol()));
    this.tfGeographicLatitude.setText(String.valueOf(this.testPsiServerManMBean.getGeographicalLatitude()));
    this.tfGeographicLongitude.setText(String.valueOf(this.testPsiServerManMBean.getGeographicalLongitude()));
    this.tfGeographicalUncertainty.setText(String.valueOf(this.testPsiServerManMBean.getGeographicalUncertainty()));
    this.tfGeodeticLatitude.setText(String.valueOf(this.testPsiServerManMBean.getGeodeticLatitude()));
    this.tfGeodeticLongitude.setText(String.valueOf(this.testPsiServerManMBean.getGeodeticLongitude()));
    this.tfGeodeticUncetainty.setText(String.valueOf(this.testPsiServerManMBean.getGeodeticUncertainty()));
    this.tfGeodeticConfidence.setText(String.valueOf(this.testPsiServerManMBean.getGeodeticConfidence()));
    this.tfcreeningAndPresentationIndicators.setText(String.valueOf(this.testPsiServerManMBean.getScreeningAndPresentationIndicators()));
    this.tfIsSaiPresent.setText(String.valueOf(this.testPsiServerManMBean.isSaiPresent()));
    this.tfGCurrentLocationRetrieved.setText(String.valueOf(this.testPsiServerManMBean.isCurrentLocationRetrieved()));

  }

  private void loadDataB() {
    loadDataA();
  }

  private boolean saveData() {

    this.testPsiServerManMBean.setAddressNature((AddressNatureType) cbAddressNature.getSelectedItem());
    this.testPsiServerManMBean.setNumberingPlanType((NumberingPlanMapType) cbNumberingPlan.getSelectedItem());
    this.testPsiServerManMBean.setNumberingPlan(cbAddress.getText());
    this.testPsiServerManMBean.setImsi(this.tfImsi.getText());
    this.testPsiServerManMBean.setImei(this.tfImei.getText());
    this.testPsiServerManMBean.setLmsi(this.tfLmsi.getText());
    this.testPsiServerManMBean.setNetworkNodeNumber(this.tfNetworkNodeNumber.getText());
    this.testPsiServerManMBean.setMcc(Integer.valueOf(this.tfMcc.getText()));
    this.testPsiServerManMBean.setMnc(Integer.valueOf(this.tfMnc.getText()));
    this.testPsiServerManMBean.setLac(Integer.valueOf(this.tfLac.getText()));
    this.testPsiServerManMBean.setCi(Integer.valueOf(this.tfCellId.getText()));
    this.testPsiServerManMBean.setGeographicalLatitude(Double.valueOf(this.tfGeographicLatitude.getText()));
    this.testPsiServerManMBean.setGeographicalLongitude(Double.valueOf(this.tfGeographicLongitude.getText()));
    this.testPsiServerManMBean.setGeographicalUncertainty(Double.valueOf(this.tfGeographicalUncertainty.getText()));
    this.testPsiServerManMBean.setGeodeticLatitude(Double.valueOf(this.tfGeodeticLatitude.getText()));
    this.testPsiServerManMBean.setGeodeticLongitude(Double.valueOf(this.tfGeodeticLongitude.getText()));
    this.testPsiServerManMBean.setGeodeticUncertainty(Double.valueOf(this.tfGeodeticUncetainty.getText()));
    this.testPsiServerManMBean.setGeodeticConfidence(Integer.valueOf(this.tfGeodeticConfidence.getText()));

    return true;
  }

}
