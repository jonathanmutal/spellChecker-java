package word;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 
 * La clase WordSet representa un conjunto de palabras, el cual se implementa interna-mente con una
 * coleccion que implemente la interfaz Set
 *
 */

public class WordSet {

	/**
	 * Representa un conjunto de palabras, un set.
	 */
	
	private Set<Word> set;

	/**
	 * Construye un nuevo conjunto vacío
	 */

	public WordSet() {
		this.set = new HashSet<Word>(); 
	}

	/**
	 * Agrega el elemento word, si es que no se encuentra en el conjunto.
	 * @param word elemento que sera agregado al conjunto.
	 */
	
	public void add(Word word) {
		this.set.add(word);
	}
	
	public boolean contains(Word word) {
		return this.set.contains(word);
	}
	
	public void clear() {
		this.set.clear();
	}
	
	/**
	 * Devuelve una referencia a un iterator correspondiente a la implementacion interna del conjunto.
	 * @return una referencia a un iterator correspondiente a la implementacion interna del conjunto.
	 */
	
	public Iterator<Word> iterator() {
		Iterator<Word> iterador = set.iterator();
		return iterador;
	}
	
	public int size() {
		return set.size();
	}
}
