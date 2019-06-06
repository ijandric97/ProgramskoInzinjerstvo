package paket1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
//import java.awt.event.ActionEvent;
import java.awt.event.*;

import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class GUI_WB implements ActionListener{

	JFrame frmRess;
	private JTextField textField;
	private JButton btnPotrai;
	private JCheckBox chckbxNewCheckBox;
	private JCheckBox checkBox;
	private JCheckBox chckbxNewCheckBox_1;
	private JCheckBox chckbxNewCheckBox_2;
	private JCheckBox chckbxNewCheckBox_3;
	private JCheckBox chckbxNewCheckBox_4;
	private JCheckBox chckbxNewCheckBox_5;
	private JCheckBox chckbxNewCheckBox_6;
	private JTable table;
	
	private String[] columnNames = {"Rule", "Support", "Confidence", "Lift"};
	private JCheckBox checkBox_1;
	private JCheckBox checkBox_2;

	/**
	 * Create the application.
	 */
	public GUI_WB() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	public void actionPerformed (ActionEvent e) {
		
		DataLoader dl = null;
		
		if (e.getSource() == btnPotrai) {
			dl = new DataLoader();
			
		}
		textField.setText(dl.odabir_datoteke());
		//System.out.println(dl.odabir_datoteke());
	}
	
	private void initialize() {
		frmRess = new JFrame();
		frmRess.setTitle("RESS");
		frmRess.setBounds(100, 100, 1024, 600);
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
		
		chckbxNewCheckBox = new JCheckBox("Event clearance Group");
		chckbxNewCheckBox.setBounds(24, 57, 208, 23);
		desktopPane.add(chckbxNewCheckBox);
		
		checkBox = new JCheckBox("New check box");
		checkBox.setBounds(24, 84, 208, 23);
		desktopPane.add(checkBox);
		
		chckbxNewCheckBox_1 = new JCheckBox("New check box");
		chckbxNewCheckBox_1.setBounds(24, 111, 208, 23);
		desktopPane.add(chckbxNewCheckBox_1);
		
		chckbxNewCheckBox_2 = new JCheckBox("New check box");
		chckbxNewCheckBox_2.setBounds(24, 138, 208, 23);
		desktopPane.add(chckbxNewCheckBox_2);
		
		chckbxNewCheckBox_3 = new JCheckBox("New check box");
		chckbxNewCheckBox_3.setBounds(24, 165, 208, 23);
		desktopPane.add(chckbxNewCheckBox_3);
		
		chckbxNewCheckBox_4 = new JCheckBox("New check box");
		chckbxNewCheckBox_4.setBounds(24, 192, 208, 23);
		desktopPane.add(chckbxNewCheckBox_4);
		
		chckbxNewCheckBox_5 = new JCheckBox("New check box");
		chckbxNewCheckBox_5.setBounds(24, 219, 208, 23);
		desktopPane.add(chckbxNewCheckBox_5);
		
		chckbxNewCheckBox_6 = new JCheckBox("New check box");
		chckbxNewCheckBox_6.setBounds(24, 246, 208, 23);
		desktopPane.add(chckbxNewCheckBox_6);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(256, 64, 756, 323);
		desktopPane.add(scrollPane);
		
		//table = new JTable()
		table = new JTable(0, 4);
		
		table.getColumnModel().getColumn(0).setHeaderValue("Rule");
		//table.getColumnModel().getColumn(0).setPreferredWidth(100);
		
		table.getColumnModel().getColumn(1).setHeaderValue("Support");
		table.getColumnModel().getColumn(2).setHeaderValue("Confidence");
		table.getColumnModel().getColumn(3).setHeaderValue("Lift");
		
		//table.setRo
		
		scrollPane.setViewportView(table);
		
		checkBox_1 = new JCheckBox("New check box");
		checkBox_1.setBounds(24, 273, 208, 23);
		desktopPane.add(checkBox_1);
		
		checkBox_2 = new JCheckBox("New check box");
		checkBox_2.setBounds(24, 300, 208, 23);
		desktopPane.add(checkBox_2);
		
	}
}











