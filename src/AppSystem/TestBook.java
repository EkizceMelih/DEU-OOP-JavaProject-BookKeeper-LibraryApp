package AppSystem;

public class TestBook extends Readable {
	
	private String type_of_lesson,author;
	private int question_num,true_answer_num,false_answer_num;
	public TestBook(String[] _args) {
		
		super(_args);
		
		type_of_lesson = _args[5];
		author = _args[6];
		
		question_num = Integer.parseInt(_args[7]);
		true_answer_num = Integer.parseInt(_args[8]);
		false_answer_num = Integer.parseInt(_args[9]);
		
		type = 1;
	}

	public TestBook(String _name, String _publisher, int _pageCount, float _rate) {
		
		super(_name, _publisher, _pageCount, _rate);
		type = 1;
		
	}
	public float calculateScore(){
		float score=(float)(true_answer_num-(0.25*false_answer_num));
		return score;
	}

	public static String getTitles() {

		String data = Readable.getTitles();
		
		data += ",Type of Lesson:";
		data += ",Author:";
		data += ",Question Count:";
		data += ",True Answer Count:";
		data += ",False Answer Count:";
		data += ",Score:";

		return data;
	}
	
	@Override
	public String getData() {
		
		String data = super.getData();
		
		data += "," + type_of_lesson;
		data += "," + author;
		data += "," + question_num;
		data += "," + true_answer_num;
		data += "," + false_answer_num;
		data += "," + calculateScore();
		
		return data;
	}
}
