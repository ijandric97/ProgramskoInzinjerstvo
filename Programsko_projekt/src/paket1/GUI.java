package paket1;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.awt.event.*; 

public class GUI extends JFrame implements ActionListener {
	
	private JFrame frame;
	private JButton butt;
	private JLabel labela;
	
	private JFileChooser jfc;
	
	public GUI () {
		frame = new JFrame("Prozor");
		butt = new JButton("KLIKNI MATORE!");
		labela = new JLabel("LABELA");
		jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
	}
	
	public void odabir_datoteke() {
		
		int returnValue = this.jfc.showOpenDialog(null);
		//int returnValue = jfc.showSaveDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			
			File selectedFile = jfc.getSelectedFile();
			System.out.println(selectedFile.getAbsolutePath());
		}
	}
	public void prikazi() {
		
		butt.addActionListener(this);
		this.frame.getContentPane().add(butt);
		
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.pack();
		this.frame.setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e) {	//implementirano od actionListener
        this.odabir_datoteke();
    }
}
