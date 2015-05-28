package document;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import word.Word;

public class Document {
	private BufferedReader input;
	private BufferedWriter output;

	public Document(String in, String out) throws IOException  {
		input = new BufferedReader(new FileReader(in));
		output = new BufferedWriter(new FileWriter(out));
		
	}
	
	public void close() throws IOException {
		input.close();
		output.close();
	}
	
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
	
	
	public void putWord(Word word) {
		try {
			output.write(word.getWord());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}