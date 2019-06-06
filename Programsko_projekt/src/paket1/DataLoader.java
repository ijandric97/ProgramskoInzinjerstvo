package paket1;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

public class DataLoader {
	
	private JFileChooser jfc;
	
	public DataLoader() {
		
		jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		
	}
	
	public String odabir_datoteke() {
		
		File selectedFile = null;
		int returnValue = this.jfc.showOpenDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			
			selectedFile = jfc.getSelectedFile();
		}
		
		return selectedFile.getAbsolutePath();
	}
}
