package AppSystem;

enum ReadableType {

	Book,
	TestBook,
	Magazine
}

public abstract class Readable {

	protected int type;
	
	protected String name;
	protected String publisher;
	
	protected int pageCount;
	protected float rate;
	
	public Readable(String[] _args) {
		
		name = _args[1];
		publisher = _args[2];
		
		pageCount = Integer.parseInt(_args[3]);
		rate = Float.parseFloat(_args[4]);
	}
	
	public Readable(String _name, String _publisher, int _pageCount, float _rate) {
		
		name = _name;
		publisher = _publisher;
		
		pageCount = _pageCount;
		rate = _rate;
	}
	
	public String getName() {
		
		return name;
	}
	
	public String getTitlesByType() {
		
		switch (type) {
		
		case 0: {
			
			return Book.getTitles();
		}
		case 1: {
			
			return TestBook.getTitles();
		}
		case 2: {
			
			return Magazine.getTitles();
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + type);
		}
	}
	
	public static String getTitles() {

		String data = "";
		
		data += "Type:";
		data += ",Name:";
		data += ",Publisher:";
		data += ",Page Count:";
		data += ",Rate:";

		return data;
	}
	
	public String getData() {
		
		String data = "";
		
		var typeString = "";
		
		switch (type) {
		
		case 0: {
			typeString = "Book";
			break;
		}
		
		case 1: {
			typeString = "TestBook";
			break;
		}
		
		case 2: {
			typeString = "Magazine";
			break;
		}
		
		default:
			throw new IllegalArgumentException("Unexpected value: " + type);
		}
		
		data += typeString + ",";
		
		data += name + ",";
		data += publisher + ",";

		data += pageCount + ",";
		data += rate;
		
		return data;
	}
	
	public  void setRate(float newRate){
		this.rate=newRate;
	}
}
