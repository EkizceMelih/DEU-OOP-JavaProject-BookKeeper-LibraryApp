package AppSystem;

public class User {
	String userName;
	String password;
	String favBookName;
	String favAuthor;
	String favGenre;
	UserLibrary library;
	int rank;
	
	public User(String userName, String password, String favBookName, String favAuthor, String favGenre,  int rank) {
		
		super();
		
		library = new UserLibrary(this);
		
		this.userName = userName;
		this.password = password;
		this.favBookName = favBookName;
		this.favAuthor = favAuthor;
		this.favGenre = favGenre;
		
		this.rank = rank;
		
		String fileName= userName+"Library.csv";
		
		library.Load(fileName);
	}
	
	
	public User(String userName, String password) {
		
		super();
		
		library = new UserLibrary(this);
		
		this.userName = userName;
		this.password = password;
		this.favBookName = "null";
		this.favAuthor = "null";
		this.favGenre = "null";
		
		this.rank = 0;
		
		String fileName= userName+"Library.csv";
		
		library.Load(fileName);
	}
	
	public void addToUserLib(Readable _readable){
		
		GlobalLibrary globalLibrary = BookKeeperManager.instance.globalLibrary;
		boolean flag = false;
		
		for (int i = 0; i < globalLibrary.readables.size(); i++) {
			
			var readable = globalLibrary.readables.get(i);
			
			if (_readable.name.equals(readable.name)) {
				library.AddReadable(readable);
				System.out.println(readable.name+" user librarye eklendi");
				flag=true;
				String fileName= userName+"Library.csv";
				library.Save(fileName);
				break;
			}
		}
		
		if (!flag) {
			System.out.println(_readable.name+" user librarye eklenemedi");
		}
		
	}
	
	public void removeFromUserLib(Readable _readable){
		
		GlobalLibrary globalLibrary = BookKeeperManager.instance.globalLibrary;
		boolean flag = false;
		
		if (library.isContains(_readable)) {
			
			library.RemoveReadable(_readable);
			System.out.println(_readable.name+" user libraryden silindi");
			String fileName= userName+"Library.csv";
			library.Save(fileName);
		}
		else {
			System.out.println(_readable.name+" user librarye eklenemedi");
		}
		
	}
	
	public String getData() {
			
		String data = "";
		
		data += userName + ",";			
		data += password + ",";
		data += favBookName + ",";
		data += favAuthor + ",";
		data += favGenre + ",";
		data += rank ;

		
		return data;
	}
	
	public String getRank() {
		
		return "Warrior";
	}
	
	public void LoadProfileData() {
		
		var screen = Screen.instance;
		
		screen.nameField.setText(userName);
		screen.rankField.setText(getRank());
		screen.favBookField.getTextField().setText(favBookName);
		screen.favAuthorField.getTextField().setText(favAuthor);
		screen.favGenreField.getTextField().setText(favGenre);
	}
	
	public void SaveProfileData() {
		
		var screen = Screen.instance;
		
		favBookName = screen.favBookField.getTextField().getText();
		favAuthor = screen.favAuthorField.getTextField().getText();
		favGenre = screen.favGenreField.getTextField().getText();
	}
}