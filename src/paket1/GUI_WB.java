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
import javax.swing.table.DefaultTableModel;

import weka.associations.Apriori;
import weka.associations.AprioriItemSet;

import javax.swing.JButton;
import javax.swing.JPanel;
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

	private JCheckBox chckbxNista;
	private JCheckBox chckbxNista_1;
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
			
			retval += "6";
			status = true;
		}
		
		if (chckbxEventClearanceGroup.isSelected()) {
			
			if (!status) retval += "7";
			else retval += ", 7";
			status = true;
		}
		
		if (chckbxNewCheckBox_1.isSelected()) {
			
			if (!status) retval += "8";
			else retval += ", 8";
			status = true;
		}
		
		if (chckbxNewCheckBox_2.isSelected()) {
			
			if (!status) retval += "9";
			else retval += ", 9";
			status = true;
		}
		
		if (chckbxNewCheckBox_3.isSelected()) {
			
			if (!status) retval += "10";
			else retval += ", 10";
			status = true;
		}
		
		if (chckbxNewCheckBox_4.isSelected()) {
			
			if (!status) retval += "11";
			else retval += ", 11";
			status = true;
		}
		
		if (chckbxNewCheckBox_5.isSelected()) {
			
			if (!status) retval += "12";
			else retval += ", 12";
			status = true;
		}
		
		if (chckbxNewCheckBox_6.isSelected()) {
			
			if (!status) retval += "4";
			else retval += ", 4";
			status = true;
		}
		
		if (chckbxNista.isSelected()) {
			
			if (!status) retval += "15";
			else retval += ", 15";
			status = true;
		}
		
		if (chckbxNista_1.isSelected()) {
			
			if (!status) retval += "";
			else retval += ", ";
			status = true;
		}
		
		return retval;
	}
	
	public void actionPerformed (ActionEvent e) {
		
		DataLoader dl = null;
		
		if (e.getSource() == btnPotrai) {
			dl = new DataLoader();
			
			data_path = dl.odabir_datoteke();
			
			String tmp = data_path.substring(data_path.length()-4, data_path.length());
			//System.out.println(tmp);
			
			if(!tmp.equals("arff")) {
				JOptionPane.showMessageDialog(null, "Tip datoteke mora biti .arff, odaberite datoteku ponovno.", "Error", JOptionPane.ERROR_MESSAGE);
				dl = null;
				textField.setText("");
			} else { 
				textField.setText(data_path);
			}
			//System.out.println(data_path);
			
			//System.out.println(this.getIndices());
		}
		
		else if (e.getSource() == btnAnaliziraj) {
			
			//System.out.println("USO SAM");
			
				DataMiner dm = new DataMiner(this.data_path, this.getIndices());
				
				try {
					if(this.data_path==null || this.data_path=="") {
						JOptionPane.showMessageDialog(null, "Molimo odaberite datoteku", "Error", JOptionPane.ERROR_MESSAGE);
						dm = null;
						} else if(!chckbxEventClearanceGroup.isSelected()&&!chckbxNewCheckBox.isSelected()&&!chckbxNewCheckBox_1.isSelected()&&!chckbxNewCheckBox_2.isSelected()&&!chckbxNewCheckBox_3.isSelected()&&!chckbxNewCheckBox_4.isSelected()&&!chckbxNewCheckBox_5.isSelected()&&!chckbxNewCheckBox_6.isSelected()&&!chckbxNista.isSelected()&&!chckbxNista_1.isSelected()) {
							JOptionPane.showMessageDialog(null, "Molimo odaberite bar 2 chechbox-a", "Error", JOptionPane.ERROR_MESSAGE);
							dm = null;
						} else {
							dm.analiziraj();
							
	
							int l = dm.allTheRules.length;
							DefaultTableModel model = (DefaultTableModel) table.getModel();
							for(int i = 0; i < l - 2; i++) {
								AprioriItemSet tmp1 = (AprioriItemSet)dm.allTheRules[0].get(i);
			                    AprioriItemSet tmp2 = (AprioriItemSet)dm.allTheRules[1].get(i);
			                    String arg1 = tmp1.toString(dm.data)+ " ==> " + tmp2.toString(dm.data);
			                    String arg2 = String.format("%.2f", dm.allTheRules[2].get(i));
			                    String arg3 = String.format("%.2f", dm.allTheRules[3].get(i));
			                    String arg4 = String.format("%.2f", dm.allTheRules[4].get(i));
			                    
			                    
			                    model.addRow(new Object[]{arg1, arg4, arg2, arg3});	
							}
									
							
						}
						
					
					
				} catch (Exception excp) {
					System.out.println(excp);
				}
			
		}
	}
	
	private void initialize() {
		frmRess = new JFrame();
		frmRess.setTitle("RESS");
		frmRess.setBounds(100, 100, 1024, 600);
		frmRess.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRess.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JDesktopPane desktopPane = new JDesktopPane();
		frmRess.getContentPane().add(desktopPane);
		
		JLabel lblUitavanjeSetaPodataka = new JLabel("U�itavanje seta podataka");
		lblUitavanjeSetaPodataka.setBounds(24, 12, 235, 15);
		desktopPane.add(lblUitavanjeSetaPodataka);
		
		textField = new JTextField();
		textField.setBounds(24, 30, 195, 19);
		desktopPane.add(textField);
		textField.setColumns(10);
		
		btnPotrai = new JButton("Potra�i");
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
		
		chckbxNista = new JCheckBox("Nista");
		chckbxNista.setBounds(24, 273, 235, 23);
		desktopPane.add(chckbxNista);
		
		chckbxNista_1 = new JCheckBox("Nista");
		chckbxNista_1.setBounds(24, 300, 235, 23);
		desktopPane.add(chckbxNista_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(281, 64, 731, 323);
		desktopPane.add(scrollPane);
		
		table = new JTable(0, 4);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(500);
		
		table.getColumnModel().getColumn(0).setHeaderValue("Rule");
		table.getColumnModel().getColumn(1).setHeaderValue("Support");
		table.getColumnModel().getColumn(2).setHeaderValue("Confidence");
		table.getColumnModel().getColumn(3).setHeaderValue("Lift");
		
		
		scrollPane.setViewportView(table);
		
		btnAnaliziraj = new JButton("Analiziraj");
		btnAnaliziraj.setBounds(74, 331, 117, 25);
		desktopPane.add(btnAnaliziraj);
		
		btnAnaliziraj.addActionListener(this);
		
	}
}











