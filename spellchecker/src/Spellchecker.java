
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import word.Word;
import dictionary.Dictionary;
import dictionary.FileDictionary;
import dictionary.MemDictionary;
import document.Document;

/**
 * La clase Spellchecker es el punto de entrada a la aplicacion.
 * 
 * @author Jonathan David Mutal y Guillermo Luis incatasciato
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
	 * Consulta al usuario sobre que accion realizar (aceptar, ignorar o
	 * reemplazar) con la palabra w. Una vez que el usuario elige, 
	 * realiza la accion elegida.
	 * 
	 * @param word palabra a realizar accion
	 * @param dict diccionario principal donde se almacena palabras aceptadas
	 * @param dict_ignored diccionario ignorado donde se almacena palabras ignoradas
	 * @return palabra con la accion realizada
	 * @throws IOException
	 */
	
	public static Word consultUser(Word word, Dictionary dict, Dictionary dict_ignored) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String s;
		do {
			System.out.println("Palabra no  reconocida " + word.getWord() + " :");
			System.out.println("Aceptar (a) - Ignorar (i) - Reemplazar (r)");
			s = in.readLine();
		} while(!s.equals("a") && !s.equals("i") && !s.equals("r"));
		
		if(s.equals("a")) {
			dict.add(word);
		}
		if(s.equals("i")) {
			dict_ignored.add(word);
		}
		if(s.equals("r")) {
			System.out.println("Ingrese una palabra:");
			word.setWord(in.readLine());
		}
		return word;
	}
	
	/**
	 * Procesa el documento docIn, palabra por palabra, consultando al usuario 
	 * sobre la accion a realizar si la palabra no es conocida. Escribe las
	 * palabras conocidas e ignoradas en el documento de salida docOut.
	 * @param docIn documento de entrada donde procesa palabra por palabra
	 * @param docOut documento de salida
	 * @param dictIn diccionario principal de entrada
	 * @param dictIgnored diccionario con palabras ignoradas
	 * @throws EOFException final de archivo
	 * @throws FileNotFoundException intento de abrir el archivo denotado por 
	 * 								 una ruta de acceso especificada ha fallado
	 * @throws IOException se ha producido alguna excepcion I/O de algun tipo
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
			e.printStackTrace();
		}
	}
	
	/**
	 *  Punto de entrada principal. Abre el diccionario principal, procesa el
	 *  archivo especificado y guarda los cambios realizados en el diccionario
	 *  principal.
	 *  
	 * @param args (documento de entrada) (diccionario principal)
	 * @throws FileNotFoundException intento de abrir el archivo denotado por 
	 * 								 una ruta de acceso especificada ha fallado
	 */
	
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("nro de argumentos erroneo. Deben ser <documento> [<diccionario>].");
			return;
		}

		String path = (args.length >= 2) ? args[1] : "dict.txt";
		String text = args[0];
		FileDictionary dictMain;
		try {
			dictMain = new FileDictionary(path);
		} catch (FileNotFoundException e) {
			System.out.println("No existe el diccionario");
			return;
		} 
		
	   MemDictionary dictIgnored = new MemDictionary(); 
	   proccesDocument(text, "out.txt", dictMain, dictIgnored);
	   dictMain.save();
	}

}
