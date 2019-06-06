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

public class GUI_WB implements ActionListener{

	JFrame frmRess;
	private JTextField textField;
	private JButton btnPotrai;

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
		frmRess.setBounds(100, 100, 809, 645);
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
	}

}











