
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


public class Spellchecker {

	public Spellchecker() {
		
	}
	
	public static Word consultUser(Word word, Dictionary dict, Dictionary dict_ignored) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String s;
		do{
		System.out.println("Palabra no  reconocida " + word.getWord() + " :");
		System.out.println("Aceptar (a) - Ignorar (i) - Reemplazar (r)");
		s = in.readLine();
		}while(!s.equals("a") && !s.equals("i") && !s.equals("r"));
		
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
	
	public static void proccesDocument(String docIn, String docOut, Dictionary dictIn, Dictionary dictIgnored) {
		try {
			Document document = new Document(docIn, docOut);		
			Word word = new Word();
			boolean hayPalabra = true;
			while(hayPalabra){
				try {
					word = document.getWord();
					if(dictIn.contains(word) || dictIgnored.contains(word)) {
						document.putWord(word);
					} else {
						word = consultUser(word, dictIn, dictIgnored);
						document.putWord(word);
					}
				} catch (EOFException e) {
					hayPalabra = false;
				}
			}
			document.close();
			System.out.println("El documento "+ docIn + " ha sido procesado. Resultados en out.txt");
		} catch (FileNotFoundException e) {
			System.out.println("No se encontro el archivo "+ docIn);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
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
