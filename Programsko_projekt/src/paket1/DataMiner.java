package paket1;

import weka.core.Instances;
import java.io.FileReader;
import java.io.BufferedReader;
import weka.associations.Apriori;

public class DataMiner {
	
	private String data_path;
	
	public DataMiner(String dm) {
		
		data_path = dm;
	}
	
	public void analiziraj() throws Exception {
		
		Instances data = new Instances(new BufferedReader(new FileReader(data_path)));
		
		//System.out.println(data_path);
		
		Apriori model = new Apriori();
		
		model.buildAssociations(data);
		
		System.out.println(model);
	}

}
