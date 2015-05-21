package word;

import java.util.HashSet;
import java.util.Iterator;

public class WordSet {
	private HashSet<Word> set;

	public WordSet() {
		this.set = new HashSet<Word>(); 
	}

	public void add(Word word) {
		this.set.add(word);
	}
	
	public boolean contains(Word word) {
		return this.set.contains(word);
	}
	
	public void clear() {
		this.set.clear();
	}
	
	public Iterator<Word> iterator() {
		Iterator<Word> iterador = set.iterator();
		return iterador;
	}
	
	public int size() {
		return set.size();
	}
}
