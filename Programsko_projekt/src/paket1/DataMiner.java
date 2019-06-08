package paket1;

import weka.core.Instances;
import weka.filters.*;
import weka.filters.unsupervised.attribute.NumericToNominal;
import weka.filters.unsupervised.attribute.Remove;

import java.io.FileReader;
import java.io.BufferedReader;
import weka.associations.Apriori;

public class DataMiner extends Apriori{
	
	private String data_path;
	private String indices;
	
	public DataMiner(String dm, String ind) {
		
		data_path = dm;
		indices = ind;
		
	}
	
	public void analiziraj() throws Exception {
		
		Instances data = new Instances(new BufferedReader(new FileReader(data_path)));
		
		try {
			
			data = this.NumericToNominal(data);		//poziv metode za pretvorbu iz numeric u nominal
			data = this.selection(data);			//poziv metode za filtriranje
			
			//System.out.println(data);
			
		} catch (Exception e) {
			
			System.out.println(e);
		}
		
		//System.out.println(data_path);
		
		Apriori model = new Apriori();
		
		model.buildAssociations(data);
		
		System.out.println(model);
	}
	
	public Instances NumericToNominal(Instances dataProcessed) throws Exception {
		
		NumericToNominal convert = new NumericToNominal();
		
		String[] options = new String[2];
		
		options[0] = "-R";
		options[1] = "2, 3, 4, 12, 13, 14";		//redni brojevi stupaca koji su numeric
		
		convert.setOptions(options);
		convert.setInputFormat(dataProcessed);
		
		Instances filterData = Filter.useFilter(dataProcessed, convert);
		
		return filterData;
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
	
	public String toString() {
		return "jebiga";
	}
}
