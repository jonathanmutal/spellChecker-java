package document;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import word.Word;

/**
 * La clase Document representa el documento que se va a procesar.
 */

public class Document {
	
	/**
	 * Representa el archivo del entrada  que contiene el documento a procesar.
	 */
	
	private BufferedReader input;
	
	/**
	 * Representa el archivo destino donde se guardara el documento modificado.
	 */
	
	private BufferedWriter output;
	
	/**
	 * Construye un nuevo documento a procesar.
	 * @param in archivo de entrada
	 * @param out archivo de salida
	 */

	public Document(String in, String out) throws IOException  {
		input = new BufferedReader(new FileReader(in));
		output = new BufferedWriter(new FileWriter(out));
		
	}

	/**
	 * Cierra el documento.
	 *@throws Excepciones lanzadas por el metodo close
	 */
	
	public void close() throws IOException {
		input.close();
		output.close();
	}
	
	/**
	 * Lee el documento de entrada, palabra por palabra, copiando al documento
	 * de salida todos los caracteres no alfabeticos precedentes que encuentre.
	 * @return palabra leida del archivo
	 * @throws Señala que se llego al final del archivo
	 */
	
	public Word getWord() throws EOFException {
		char c;
		String string = "";
		int n = -1;
		try {
			while ((n = input.read()) != -1) {
				c = (char) n;
				if(!Character.isAlphabetic(c)) {
					if(string.equals("")){
						output.write(c);
					} else {
						input.reset();
						break;
					}
				} else {
					string += c;
					input.mark(0);
			    }
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(n == -1) {
			throw new EOFException();
		}
		Word word = new Word(string);
		return word;
	}
	
	/**
	 * Escribe una palabra en el archivo de salida.
	 * @param palabra a escribir
	 */
	
	public void putWord(Word word) {
		try {
			output.write(word.getWord());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}