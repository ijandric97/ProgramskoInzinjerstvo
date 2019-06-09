package paket1;
import java.util.*;
import weka.core.Instances;
import weka.core.Utils;
import weka.associations.ItemSet;
import weka.filters.*;
import weka.filters.unsupervised.attribute.NumericToNominal;
import weka.filters.unsupervised.attribute.Remove;

import java.io.FileReader;
import java.io.BufferedReader;
import weka.associations.Apriori;
import weka.associations.AprioriItemSet;

public class DataMiner {
	
	private String data_path;
	private String indices;
	public ArrayList[] allTheRules;
	public Instances data;
	
	public DataMiner(String dm, String ind) {
		
		data_path = dm;
		indices = ind;
		
	}
	
	public void analiziraj() throws Exception {
		
		//System.out.println("ulazim u ANAlizu heheeeee");
		
		 data = new Instances(new BufferedReader(new FileReader(data_path)));
		
		try {
			
			//System.out.println("KRECEM");
			
			//data = this.NumericToNominal(data);		//poziv metode za pretvorbu iz numeric u nominal
			
			//System.out.println("NumericToNominal rjeseno");
			
			data = this.selection(data);			//poziv metode za filtriranje
			
			//System.out.println("selection rjesen");
			
			//System.out.println(data);
			
		} catch (Exception e) {
			
			System.out.println(e);
		}
		
		//System.out.println(data_path);
		
		Apriori model = new Apriori();
		
		//System.out.println("Krecem sa asocijacijama");
		System.out.println("-------------------------------------------");
		
		model.buildAssociations(data);
		allTheRules = model.getAllTheRules();
                for(int i = 0; i < allTheRules.length-2; i++) {
                	AprioriItemSet arg1 = (AprioriItemSet)allTheRules[0].get(i);
                    AprioriItemSet arg2 = (AprioriItemSet)allTheRules[1].get(i);
                    
                    System.out.print(arg1.toString(data)+ " ==> " + arg2.toString(data) + " ");
                	
                    System.out.print(allTheRules[2].get(i) + " "+ allTheRules[3].get(i) + " "+ allTheRules[4].get(i) + " "+ allTheRules[5].get(i));
                	System.out.println();
                }
                
                
		//System.out.println("Gotovo!");
                System.out.println("---------------------------------------");
		
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
}
