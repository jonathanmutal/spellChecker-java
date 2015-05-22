package dictionary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileDictionary extends Dictionary{
	private String loadPath;

	public FileDictionary(){
		loadPath = "dict.txt";
	}
	
	public FileDictionary(String s){
		super();
		loadPath = s;
	}
	
	public void load(String path) throws FileNotFoundException {
		BufferedReader br = new BufferedReader(new FileReader(path));
		List<String> lista = new ArrayList<String>();
		String line;
		try {
			line = br.readLine();
			while(line != null){
				lista.add(line);
				line = br.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fromStringList(lista); 
	}
			
	
	public void save() {
		save(loadPath);
	}
	
	public void save(String path) {
		List<String> lista = new ArrayList<String>();
		lista = toListString();
		Iterator<String> itr = lista.iterator();
		BufferedWriter file;
		try {
			file = new BufferedWriter(new FileWriter(path));
			while(itr.hasNext()) {
				file.write(itr.next()+"\n");
			}	 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}