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

/**
 * Representa un diccionario que se carga desde un archivo de texto.
 * Es una subclase de Dictionary.
 */

public class FileDictionary extends Dictionary{
	
	/**
	 * Representa el path del archivo de un diccionario.
	 */
	
	private String loadPath;

	/**
	 * Carga el diccionario desde una ruta por defecto.
	 * @throws FileNotFoundException intento de abrir el archivo denotado por 
	 * 								 una ruta de acceso especificada ha fallado
	 */

	public FileDictionary() throws FileNotFoundException{
		loadPath = "dict.txt";
		load(loadPath);
	}
	
	/**
	 * Recibe un path al archivo, y retorna un diccionario que contiene las
	 * palabras en dicho archivo.
	 * @param s path del archivo
	 * @throws FileNotFoundException intento de abrir el archivo denotado por 
	 * 								 una ruta de acceso especificada ha fallado
	 */
	
	public FileDictionary(String s) throws FileNotFoundException {
		super();
		loadPath = s;
		load(s);
	}
	
	/**
	 * Carga el diccionario desde un archivo.
	 * @param path ruta del archivo.
	 * @throws FileNotFoundException intento de abrir el archivo denotado por 
	 * 								 una ruta de acceso especificada ha fallado
	 * @throws IOException se ha producido alguna excepcion I/O de algun tipo
	 */
	
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
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fromStringList(lista); 
	}
			
	/**
	 * Guarda en el diccionario en el mismo archivo desde el cual se cargo.
	 * @param path ruta donde se guarda el diccionario
	 * @throws IOException se ha producido alguna excepcion I/O de algun tipo
	 */
	
	public void save() {
		save(loadPath);
	}
	
	/**
	 * Guarda el diccionario en la ruta que recibe.
	 * @param path ruta donde se guarda el diccionario
	 * @throws IOException se ha producido alguna excepcion I/O de algun tipo
	 */
	
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
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}