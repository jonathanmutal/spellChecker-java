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
		this.set.clear();
	}
	
	public void fromStringList(List<String> list) {
		Iterator<String> itr = list.iterator();
		while(itr.hasNext()) {
			Word word = new Word(itr.next());
			set.add(word);
		}
	}
	
	public List<String> toListString(){
		Iterator<Word> itr = set.iterator();
		List<String> lista = new ArrayList<String>();
		while(itr.hasNext()){
			lista.add(itr.next().getWord());
		}
		return lista;
	}
	
	public int size() {
		return set.size();
	}
}
