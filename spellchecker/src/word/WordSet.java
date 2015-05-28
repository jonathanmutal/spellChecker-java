package word;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * La clase WordSet representa un conjunto de palabras, el cual se implementa interna-mente con una
 * coleccion que implemente la interfaz Set
 */

public class WordSet {

	/**
	 * Representa un conjunto de palabras.
	 */
	
	private Set<Word> set;

	/**
	 * Construye un nuevo conjunto vacío de palabras.
	 */

	public WordSet() {
		this.set = new HashSet<Word>(); 
	}

	/**
	 * Agrega el elemento word, si es que no se encuentra en el conjunto.
	 * @param word elemento que sera agregado al conjunto de palabras
	 */
	
	public void add(Word word) {
		this.set.add(word);
	}
	
	/**
	 * Devuelve true si este conjunto contiene el elemento especificado.
	 * @param word palabra cuya presencia en este conjunto se va a probar
	 * @return true si este conjunto contiene la palabra especificada, caso contrario false
	 */
	
	public boolean contains(Word word) {
		return this.set.contains(word);
	}
	
	/**
	 * Elimina todos los elementos de este conjunto.
	 */
	
	public void clear() {
		this.set.clear();
	}
	
	/**
	 * Devuelve una referencia a un iterator correspondiente a la implementacion interna del conjunto.
	 * @return una referencia a un iterator correspondiente a la implementacion interna del conjunto
	 */
	
	public Iterator<Word> iterator() {
		Iterator<Word> iterador = set.iterator();
		return iterador;
	}
	
	/**\
	 * Retorna el numero de elementos en el conjunto de palabras.
	 * @return el numero de elementos en el conjunto de palabras
	 */
	
	public int size() {
		return set.size();
	}
}
