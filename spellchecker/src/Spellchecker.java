
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import word.Word;
import dictionary.Dictionary;
import dictionary.FileDictionary;
import dictionary.MemDictionary;
import document.Document;

/**
 * La clase Spellchecker, es un corrector ortografico
 *  es el punto de entrada a la aplicacion.
 * 
 * @author Jonathan David Mutal y Guillermo Luis Incatasciato
 * @version 1.0
 * 
 */

public class Spellchecker {

	/**
	 * Constructor de spellchecker. Convenciones de nomeclatura.
	 */
	
	public Spellchecker() {
		
	}
	
	/**
	 * Propone tres acciones para las palabras desconocidas: aceptar, ignorar,
	 * remplazar. En el caso de aceptar la palabra, la incorpora a dict
	 * y devuelve la misma palabra. En caso de ignorar, la incorpora a 
	 * dict_ignored y devuelve la misma palabra. En el caso de reemplazar
	 * la palabra se pedira una palabra al usuario y delvolvera dicha palabra.
	 * @param word palabra por la que se consultara al usuario
	 * @param dict diccionario al que se agregara la palabra  si es aceptada
	 * @param dictIgnored diccionario al que se agregara la palabra si esta se
	 * ignora
	 * @return palabra resultante de la decision del usuario
	 */
	
	public static Word consultUser(Word word, Dictionary dict, Dictionary dictIgnored) {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String s;
		try {
			do{
			System.out.println("Palabra no  reconocida " + word.getWord() + " :");
			System.out.println("Aceptar (a) - Ignorar (i) - Reemplazar (r)");
			s = in.readLine();
			}while(!s.equals("a") && !s.equals("i") && !s.equals("r"));
			
			if(s.equals("a")) {
				dict.add(word);
			}
			if(s.equals("i")) {
				dictIgnored.add(word);
			}
			if(s.equals("r")) {
				System.out.println("Ingrese una palabra:");
				word.setWord(in.readLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return word;
	}
	
	/**
	 * Realiza la correcion ortografica del documento.
	 * @param docIn archivo a procesar
	 * @param docOut archivo donde se guardara el resultado de la correcion
	 * @param dictIn diccionario de palabras conocidas
	 * @param dictIgnored diccionario de palabras ignoradas
	 * 
	 */
	
	public static void proccesDocument(String docIn, String docOut, Dictionary dictIn, Dictionary dictIgnored) {
		try {
			Document document = new Document(docIn, docOut);		
			Word word = new Word();
				try {
					while(true){
						word = document.getWord();
						if(dictIn.contains(word) || dictIgnored.contains(word)) {
							document.putWord(word);
						} else {
							word = consultUser(word, dictIn, dictIgnored);
							document.putWord(word);
						}
					}
				} catch (EOFException e) {
					document.close();
					System.out.println("El documento "+ docIn + " ha sido procesado. Resultados en out.txt");
				}
		} catch (FileNotFoundException e) {
			System.out.println("No se encontro el archivo "+ docIn);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 *  Punto de entrada principal. Abre el diccionario principal, procesa el
	 *  archivo especificado y guarda los cambios realizados en el diccionario
	 *  principal.
	 *  
	 * @param args (documento de entrada) (diccionario principal)
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		if (args.length < 1 || args.length > 2) {
			System.out.println("Nro de argumentos erroneo. Deben ser <documento> [<diccionario>].");
			return;
		}

		String path = (args.length >= 2) ? args[1] : "dict.txt";
		
		if(path.equals("dict.txt")) {
			File f = new File("dict.txt");
			if(!f.exists()){
				try {
					f.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		String text = args[0];
		FileDictionary dictMain;
		try {
			dictMain = new FileDictionary(path);
		} catch (FileNotFoundException e) {
			System.out.println("No se encontro el archivo del diccionario: " + path);
			return;
		} 
		
	   MemDictionary dictIgnored = new MemDictionary(); 
	   proccesDocument(text, "out.txt", dictMain, dictIgnored);
	   dictMain.save();
	}
}