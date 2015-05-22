package word;

public class Word {
	private String word;

	public Word(){
		word = new String("");
	}

	public Word(String s){
		word = new String(s);
	}

	public void setWord(String s){
		word = s;
	}

	public String getWord() {
		return word;
	}

	@Override
	public int hashCode() {
		return  word.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Word && word.equals(((Word) obj).word);
	}
}