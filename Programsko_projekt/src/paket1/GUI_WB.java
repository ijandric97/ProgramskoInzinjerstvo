package paket1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
//import java.awt.event.ActionEvent;
import java.awt.event.*;

import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ProgressMonitor;
import javax.swing.table.DefaultTableModel;

import weka.associations.Apriori;
import weka.associations.AprioriItemSet;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class GUI_WB implements ActionListener {

	JFrame frmRess;
	private JTextField textField;
	private JButton btnPotrai;
	private JCheckBox chckbxNewCheckBox;
	private JCheckBox chckbxEventClearanceGroup;
	private JCheckBox chckbxNewCheckBox_1;
	private JCheckBox chckbxNewCheckBox_2;
	private JCheckBox chckbxNewCheckBox_3;
	private JCheckBox chckbxNewCheckBox_4;
	private JCheckBox chckbxNewCheckBox_5;
	private JCheckBox chckbxNewCheckBox_6;
	private JTable table;
	private JButton btnAnaliziraj;
	
	private String data_path;
	/**
	 * Create the application.
	 */
	public GUI_WB() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	public String getIndices () {		//upisivanje željenih atributa u string
		
		String retval = "";
		
		boolean status = false;
		
		if (chckbxNewCheckBox.isSelected()) {
			
			retval += "2";
			status = true;
		}
		
		if (chckbxEventClearanceGroup.isSelected()) {
			
			if (!status) retval += "3";
			else retval += ", 3";
			status = true;
		}
		
		if (chckbxNewCheckBox_1.isSelected()) {
			
			if (!status) retval += "4";
			else retval += ", 4";
			status = true;
		}
		
		if (chckbxNewCheckBox_2.isSelected()) {
			
			if (!status) retval += "5";
			else retval += ", 5";
			status = true;
		}
		
		if (chckbxNewCheckBox_3.isSelected()) {
			
			if (!status) retval += "6";
			else retval += ", 6";
			status = true;
		}
		
		if (chckbxNewCheckBox_4.isSelected()) {
			
			if (!status) retval += "7";
			else retval += ", 7";
			status = true;
		}
		
		if (chckbxNewCheckBox_5.isSelected()) {
			
			if (!status) retval += "8";
			else retval += ", 8";
			status = true;
		}
		
		if (chckbxNewCheckBox_6.isSelected()) {
			
			if (!status) retval += "1";
			else retval += ", 1";
			status = true;
		}
		
		return retval;
	}
	
	public int checkCheckboxes() {
		
		int retval = 0;
		
		if (chckbxNewCheckBox.isSelected()) retval++;
		if (chckbxEventClearanceGroup.isSelected()) retval++;
		if (chckbxNewCheckBox_1.isSelected()) retval++;
		if (chckbxNewCheckBox_2.isSelected()) retval++;
		if (chckbxNewCheckBox_3.isSelected()) retval++;
		if (chckbxNewCheckBox_4.isSelected()) retval++;
		if (chckbxNewCheckBox_5.isSelected()) retval++;
		if (chckbxNewCheckBox_6.isSelected()) retval++;
			
		return retval;
	}
	
	public void actionPerformed (ActionEvent e) {
		
		DataLoader dl = null;
		
		if (e.getSource() == btnPotrai) {
			dl = new DataLoader();
			
			data_path = dl.odabir_datoteke();
			
			String tmp = data_path.substring(data_path.length() - 4, data_path.length());
			
			if(!tmp.equals("arff")) {
				
				JOptionPane.showMessageDialog(null, "Tip datoteke mora biti .arff, odaberite datoteku ponovno.", "Pogrešan format datoteke.", JOptionPane.ERROR_MESSAGE);
				
				dl = null;
				textField.setText("");
				data_path = "";
				
			}
			
			else textField.setText(data_path);
		}
		
		else if (e.getSource() == btnAnaliziraj) {
			
			DataMiner dm = new DataMiner(this.data_path, this.getIndices());
			
			try {
				
				if(this.data_path == null || this.data_path == "") {
					
					JOptionPane.showMessageDialog(null, "Molimo odaberite datoteku.", "Datoteka nije odabrana.", JOptionPane.ERROR_MESSAGE);
					
					dm = null;
					
				}
				else if(this.checkCheckboxes() < 2) {
					
					JOptionPane.showMessageDialog(null, "Molimo odaberite barem dva atributa.", "Premalo atributa.", JOptionPane.ERROR_MESSAGE);
					
					dm = null;
				}
				
				else {
					
					dm.analiziraj();
					
					int l = dm.getAllTheRules().length;
					
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					
					try {
					
						for(int i = 0; i < l - 2; i++) {
							
							AprioriItemSet tmp1 = (AprioriItemSet)dm.getAllTheRules()[0].get(i);
		                    AprioriItemSet tmp2 = (AprioriItemSet)dm.getAllTheRules()[1].get(i);
		                    
		                    String arg1 = tmp1.toString(dm.getData())+ " ==> " + tmp2.toString(dm.getData());
		                    String arg2 = String.format("%.2f", dm.getAllTheRules()[2].get(i));
		                    String arg3 = String.format("%.2f", dm.getAllTheRules()[3].get(i));
		                    String arg4 = String.format("%.2f", dm.getAllTheRules()[4].get(i));
		                    
		                    model.addRow(new Object[]{arg1, arg4, arg2, arg3});
						}
					}
					catch (Exception excp) {
						
						System.out.println(excp);
						JOptionPane.showMessageDialog(null, "Nije pronađeno niti jedno pravilo.", "Nema pravila.", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			
			catch (Exception excp) {
				
				System.out.println(excp);
			}
		}
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		frmRess = new JFrame();
		frmRess.setTitle("RESS");
		frmRess.setBounds(100, 100, 1450, 600);
		frmRess.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRess.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JDesktopPane desktopPane = new JDesktopPane();
		frmRess.getContentPane().add(desktopPane);
		
		JLabel lblUitavanjeSetaPodataka = new JLabel("Učitavanje seta podataka");
		lblUitavanjeSetaPodataka.setBounds(24, 12, 235, 15);
		desktopPane.add(lblUitavanjeSetaPodataka);
		
		textField = new JTextField();
		textField.setBounds(24, 30, 195, 19);
		desktopPane.add(textField);
		textField.setColumns(10);
		
		btnPotrai = new JButton("Potraži");
		btnPotrai.setBounds(231, 27, 117, 25);
		desktopPane.add(btnPotrai);
		
		btnPotrai.addActionListener(this);
		
		chckbxNewCheckBox = new JCheckBox("Event Clearance Subgroup");
		chckbxNewCheckBox.setBounds(24, 57, 235, 23);
		desktopPane.add(chckbxNewCheckBox);
		
		chckbxEventClearanceGroup = new JCheckBox("Event Clearance Group");
		chckbxEventClearanceGroup.setBounds(24, 84, 235, 23);
		desktopPane.add(chckbxEventClearanceGroup);
		
		chckbxNewCheckBox_1 = new JCheckBox("Event Clearance Date");
		chckbxNewCheckBox_1.setBounds(24, 111, 235, 23);
		desktopPane.add(chckbxNewCheckBox_1);
		
		chckbxNewCheckBox_2 = new JCheckBox("Hundred Block Location");
		chckbxNewCheckBox_2.setBounds(24, 138, 235, 23);
		desktopPane.add(chckbxNewCheckBox_2);
		
		chckbxNewCheckBox_3 = new JCheckBox("District / Sector");
		chckbxNewCheckBox_3.setBounds(24, 165, 235, 23);
		desktopPane.add(chckbxNewCheckBox_3);
		
		chckbxNewCheckBox_4 = new JCheckBox("Zone / Beat");
		chckbxNewCheckBox_4.setBounds(24, 192, 235, 23);
		desktopPane.add(chckbxNewCheckBox_4);
		
		chckbxNewCheckBox_5 = new JCheckBox("Census / Tract");
		chckbxNewCheckBox_5.setBounds(24, 219, 235, 23);
		desktopPane.add(chckbxNewCheckBox_5);
		
		chckbxNewCheckBox_6 = new JCheckBox("Event Clearance Code");
		chckbxNewCheckBox_6.setBounds(24, 246, 235, 23);
		desktopPane.add(chckbxNewCheckBox_6);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(281, 64, 1150, 323);
		desktopPane.add(scrollPane);
		
		table = new JTable(0, 4);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(900);
		
		table.getColumnModel().getColumn(0).setHeaderValue("Rule");
		table.getColumnModel().getColumn(1).setHeaderValue("Support");
		table.getColumnModel().getColumn(2).setHeaderValue("Confidence");
		table.getColumnModel().getColumn(3).setHeaderValue("Lift");
			
		scrollPane.setViewportView(table);
		
		btnAnaliziraj = new JButton("Analiziraj");
		btnAnaliziraj.setBounds(74, 275, 117, 25);
		desktopPane.add(btnAnaliziraj);
		
		btnAnaliziraj.addActionListener(this);
		
	}
}











