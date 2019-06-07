package paket1;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

public class DataLoader {
	
	private JFileChooser jfc;
	
	private String indices;
	
	public DataLoader() {
		
	}
	
	public String odabir_datoteke() {
		
		jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		
		File selectedFile = null;
		int returnValue = this.jfc.showOpenDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			
			selectedFile = jfc.getSelectedFile();
		}
		
		return selectedFile.getAbsolutePath();
	}
	
	//public String getIndices() {}
}
