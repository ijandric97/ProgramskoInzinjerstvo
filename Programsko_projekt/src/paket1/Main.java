package paket1;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		//GUI sucelje = new GUI();
		//sucelje.prikazi();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_WB window = new GUI_WB();
					window.frmRess.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

}
