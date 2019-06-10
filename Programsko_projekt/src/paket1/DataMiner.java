package paket1;
import java.util.*;
import weka.core.Instances;
import weka.filters.*;
import weka.filters.unsupervised.attribute.Remove;

import java.io.FileReader;
import java.io.BufferedReader;
import weka.associations.Apriori;

public class DataMiner {
	
	private String data_path;
	private String indices;
	public ArrayList[] allTheRules;
	private Instances data;
	
	public DataMiner(String dm, String ind) {
		
		data_path = dm;
		indices = ind;
		
	}
	
	public void analiziraj() throws Exception {
		
		 data = new Instances(new BufferedReader(new FileReader(data_path)));
		
		try {
			
			data = this.selection(data);			//poziv metode za filtriranje
			
		} catch (Exception e) {
			
			System.out.println(e);
		}
		
		Apriori model = new Apriori();
		model.buildAssociations(data);
		
		try {
		
			allTheRules = model.getAllTheRules();
		
		}
		catch (Exception e) {
			
			System.out.println(e);
			
		}
		
		System.out.println(model);
		
	}
	
	public Instances selection(Instances rawData) throws Exception {	//filtriranje željenih atributa
		
		Instances data = rawData;
		
		Remove remove = new Remove();
		
		remove.setAttributeIndices(this.indices);
		
		remove.setInvertSelection(true);
		
		remove.setInputFormat(data);
		
		Instances filtered = Filter.useFilter(data, remove); // apply filter
		
		return filtered;
		
	}
	
	public ArrayList[] getAllTheRules() {
		
		return this.allTheRules;
	}
	
	public Instances getData() {
		
		return this.data;
	}
}
