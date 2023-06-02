package AppSystem;

public class Magazine extends Readable {

	private String content,editor;
	
	public Magazine(String[] _args) {
		
		super(_args);
		
		content = _args[5];
		editor = _args[6];
		
		type = 2;
	}

	public Magazine(String _name, String _publisher, int _pageCount, float _rate) {
		
		super(_name, _publisher, _pageCount, _rate);
		type = 2;
		
	}

	public static String getTitles() {

		String data = Readable.getTitles();
		
		data += ",Content:";
		data += ",Editor:";

		return data;
	}
	
	@Override
	public String getData() {
		
		String data = super.getData();
		
		data += "," + content;
		data += "," + editor;
		
		return data;
	}
}
