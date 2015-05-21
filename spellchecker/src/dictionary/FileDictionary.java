package dictionary;

public class FileDictionary extends Dictionary{
	private String loadPath;

	public FileDictionary(){
		super();
		loadPath = "dict.txt";
	}
	
	public FileDictionary(String s){
		super();
		loadPath = s;
	}
	
	public void load(String s) {
		File file_load = new File(s);
		Dictionary dict = new Dictionary();
	}
}