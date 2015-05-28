package word;

/**
 * Esta clase representa una palabra del diccionario.
 * La clase Word representa una palabra y es un wrapper sobre el tipo de datos String.
 */

public class Word {

	/**
	 * Este atributo representa la palabra mediante un String.
	 */

	private String word;

	/**
	 * Primer constructor de la clase.
	 */

	public Word(){
		word = new String("");
	}

	/**
	 * Segundo constructor de la clase.
	 * @param s string que definira el word.
	 */
	
	public Word(String s){
		word = new String(s);
	}

	/**
	 * Setea un Word con un String.
	 * @param s Un string para setear el word.
	 * @return void
	 */

	public void setWord(String s){
		word = s;
	}

	/**
	 * Obtiene un String del Word.
	 * @return El string de la palabra(Word).
	 */

	public String getWord() {
		return word;
	}

	/**
	 * Método sobre-escrito, asocia un objeto Word con un código hash.
	 * @return El código hash asociado a la palabra.
	 */

	@Override
	public int hashCode() {
		return  word.hashCode();
	}

	/**
	 * Método sobre-escrito, indica cuando algún objeto es igual a este.
	 * @param obj La referencia de un objeto que se quiere comparar.
	 * @return true si el objeto es el mismo que el argumento, false si no.
	 */

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Word && word.equals(((Word) obj).word);
	}
}