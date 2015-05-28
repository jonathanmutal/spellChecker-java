package dictionary;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import word.Word;
import word.WordSet;

/**
 * Es una clase abstracta que representa un diccionario de palabras.
 */

public abstract class Dictionary {
	
	/**
	 * Atributo que representa el conjunto de palabras.
	 */
	
	protected WordSet set;

	/**
	 * Construye un nuevo diccionario vacio.
	 */
	
	public Dictionary() {
		set = new WordSet();
	}
	
	/**
	 * Agrega el elemento word al diccionario, si es que no se encuentra en el diccionario.
	 * @param word elemento que sera agregado al conjunto de palabras
	 */
	
	public void add(Word word) {
		set.add(word);
	}
	
	/**
	 * Devuelve true si este diccionario contiene la palabra especificada.
	 * @param word palabra cuya presencia en este diccionario se va a probar
	 * @return true si este diccionario contiene la palabra especificada, caso contraio false.
	 */
	
	public boolean contains(Word word){
		return set.contains(word);
	}
	
	/**
	 * Elimina todos los elementos de este diccionario.
	 */
	
	public void clear() {
		this.set.clear();
	}
	
	/**
	 * Inserta en el diccionario todos los elementos pertenecientes a la lista pasada como argumento.
	 * @param list representa una lista de palabras 
	 */
	
	public void fromStringList(List<String> list) {
		Iterator<String> itr = list.iterator();
		while(itr.hasNext()) {
			Word word = new Word(itr.next());
			set.add(word);
		}
	}
	
	/**
	 * Retorna en una lista de strings, todas las palabras que pertenecen al diccionario.
	 * @return una lista de String, que son las palabras que pertenecen al diccionario
	 */
	
	public List<String> toListString(){
		Iterator<Word> itr = set.iterator();
		List<String> lista = new ArrayList<String>();
		while(itr.hasNext()){
			lista.add(itr.next().getWord());
		}
		return lista;
	}
	
	/**\
	 * Retorna el numero de elementos en el diccionario.
	 * @return el numero de elementos en el diccionario
	 */

	public int size() {
		return set.size();
	}
}
