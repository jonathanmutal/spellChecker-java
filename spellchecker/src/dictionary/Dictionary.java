package dictionary;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import word.Word;
import word.WordSet;

public abstract class Dictionary {
	protected WordSet set;

	public Dictionary() {
		set = new WordSet();
	}
	
	public void add(Word word) {
		set.add(word);
	}
	
	public boolean contains(Word word){
		return set.contains(word);
	}
	
	public void clear() {
		set.clear();
	}
	
	public void fromStringList(List<String> list) {
		Iterator<String> lista = list.iterator();
		for(int i = 0; i < list.size(); i ++) {
			Word word = new Word(lista.next());
			set.add(word);
		}
	}
	
	public List<String> toListString(){
		Iterator<Word> word = set.iterator();
		List<String> lista = new ArrayList<String>();
		for(int i = 1; i <= set.size(); i ++){
			lista.add(word.next().getWord());
		}
		return lista;
	}
	
	public int size() {
		return set.size();
	}
}
