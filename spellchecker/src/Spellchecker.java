
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import word.Word;

import dictionary.Dictionary;
import document.Document;


public class Spellchecker {

	public Spellchecker() {
	}
	
	public static Word consultUser(Word word, Dictionary dict, Dictionary dict_ignored) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String s;
		System.out.println("Palabra no  reconocida" + word.getWord() + ":");
		System.out.println("Aceptar (a) - Ignorar (i) - Reemplazar (r)");
		s = in.readLine();
		if(s == "a") {
			dict.add(word);
		}
		if(s == "i") {
			dict_ignored.add(word);
		}
		if(s == "r") {
			System.out.println("Ingrese una palabra:");
			word.setWord(in.readLine());
		}
		return word;
	}
	
	public static void proccesDocument(String docIn, String docOut, Dictionary dictIn, Dictionary dictIgnored) {
		try {
			Document document = new Document(docIn, docOut);
			Word word = new Word(); 
			while((word = document.getWord()) != null){			
				if(dictIn.contains(word) || dictIgnored.contains(word)) {
					document.putWord(word);
				} else {
					word = consultUser(word, dictIn, dictIgnored);
					document.putWord(word);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
