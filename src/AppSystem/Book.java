package AppSystem;

public class Book extends Readable {
	
	private String genre,author,language,literature;
	
	
	public Book(String[] _args) {
		
		super(_args);
		
		genre = _args[5];
		author = _args[6];
		language = _args[7];
		literature = _args[8];
		
		type = 0;
	}
	
	public Book(String _name, String _publisher, int _pageCount, float _rate) {
		
		super(_name, _publisher, _pageCount, _rate);
		type = 0;
		
	}

	public static String getTitles() {

		String data = Readable.getTitles();
		
		data += ",Genre:";
		data += ",Author:";
		data += ",Language:";
		data += ",Literature:";

		return data;
	}
	
	@Override
	public String getData() {
		
		String data = super.getData();
		
		data += "," + genre;
		data += "," + author;
		data += "," + language;
		data += "," + literature;
		
		return data;
	}
}
