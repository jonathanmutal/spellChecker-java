package document;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import word.Word;

public class Document {
	private BufferedReader input;
	private BufferedWriter output;

	public Document(String in, String out) throws IOException {
		input = new BufferedReader(new FileReader(in));
		output = new BufferedWriter(new FileWriter(out));
	}
	
	public void close() throws IOException {
		input.close();
		output.close();
	}
	
	public Word getWord() throws IOException {
		char c;
		String string = "";
		while (!((c = (char) input.read()) != -1)) {
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
		Word word = new Word(string);
		return word;
	}
	
	
	public void putWord(Word word) throws IOException {
		output.write(word.getWord());
	}
}