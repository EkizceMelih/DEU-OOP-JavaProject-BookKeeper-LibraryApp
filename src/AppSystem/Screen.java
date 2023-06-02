package AppSystem;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.HeadlessException;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicButtonUI;

import ScreenComponents.*;

@SuppressWarnings("serial")
public class Screen extends JFrame {//using JFrame 
	public static Screen instance;
	
	public static int x = 1280;
	public static int y = 720;
	
	public Color backgroundColor;
	
	public Listeners listeners;

	public int currentReadableType;
	public Library currentLibrary;
	public Readable currentReadable;
	
	//	LoginSignup components
	public UsernameField usernameField;
	public PasswordField passwordField;
	public PasswordField passwordField2;
	public PasswordToggle passwordToggle;
	
	public ButtonPrefab loginButton;
	public ButtonPrefab signupButton;
	public ButtonPrefab loginScreenButton;
	public ButtonPrefab signupScreenButton;
	
	//	Main Page:
	public ButtonPrefab showProfileButton;
	public ButtonPrefab globalLibraryButton;
	public ButtonPrefab myLibraryButton;
	public ButtonPrefab otherUsersLibrariesButton;
	
	//	Profile Page:
	public TextPrefab detailsLabel;
	public TextPrefab nameLabel;
	public TextPrefab nameField;
	public TextPrefab rankLabel;
	public TextPrefab rankField;
	
	public TitledTextFieldPrefab favBookField;
	public TitledTextFieldPrefab favAuthorField;
	public TitledTextFieldPrefab favGenreField;
	
	public ButtonPrefab exitProfileButton;
	
	//	Library Page
	public ButtonPrefab searchScreenButton;
	public SearchField searchField;
	
	//	Library Search Box
	public ButtonPrefab searchBookButton;
	
	//	Book Visual Interface
	public TextPrefab readableTitleField;
	public TextPrefab readableDataField;
	public ButtonPrefab addToMyLibraryButton;
	public ButtonPrefab removeFromMyLibraryButton;
	
	//	Book Add Screen
	public ButtonPrefab readableChoiceBook;
	public ButtonPrefab readableChoiceTestBook;
	public ButtonPrefab readableChoiceMagazine;
	
	public TextPrefab titlesLabel;
	public TextFieldPrefab[] readableArgFields;
	
	public ButtonPrefab addReadableButton;
	
	//	Commons:
	public ButtonPrefab backToMainScreenButton;
	public ButtonPrefab goBackToLibraryButton;
	public ButtonPrefab goBackToUserListButton;
	public ButtonPrefab addReadableScreenButton;
	
	public Screen(String _title) throws HeadlessException {
		super(_title);
		
		instance = this;
		
		backgroundColor = new Color(200, 200, 255, 255);
		
		setResizable(false);
		setFocusable(false);
		
		setSize(x + 12, y + 32);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		new UserManager();
		
		BookKeeperManager manager = new BookKeeperManager();
		
		listeners = new Listeners();
		
		
		//	Login / Signup Screen
		usernameField = new UsernameField();
		passwordField = new PasswordField("enter password...");
		passwordField2 = new PasswordField("enter password again...");
		passwordToggle = new PasswordToggle();
		
		loginButton = new ButtonPrefab("Login");
		signupButton = new ButtonPrefab("Sign Up");
		loginScreenButton = new ButtonPrefab("Back to Login", 150, 40);
		signupScreenButton = new ButtonPrefab("Sign Up");
		
		//	Main Screen
		showProfileButton = new ButtonPrefab("", 100, 100);
		
		showProfileButton.setUI(new BasicButtonUI() {
			@Override
			public void paint(Graphics g, JComponent c) {
		        super.paint(g, c);
		    }
		});
		
		showProfileButton.setBackground(backgroundColor);
		showProfileButton.setBorder(null);
		showProfileButton.setIcon("icons/profile_icon.png");
		//creating buttons
		globalLibraryButton = new ButtonPrefab("Global Library", 350, 60, SwingConstants.CENTER, SwingConstants.BOTTOM, new Font("Consolas", Font.BOLD, 30));
		myLibraryButton = new ButtonPrefab("My Library", 350, 60, SwingConstants.CENTER, SwingConstants.BOTTOM, new Font("Consolas", Font.BOLD, 30));
		otherUsersLibrariesButton = new ButtonPrefab("Other Users Library", 360, 60, SwingConstants.CENTER, SwingConstants.BOTTOM, new Font("Consolas", Font.BOLD, 30));
		
		//	Profile Screen
		detailsLabel = new TextPrefab("Profile Details:", new Font("Consolas", Font.BOLD, 30), Color.DARK_GRAY);
		nameLabel = new TextPrefab("Name:", new Font("Consolas", Font.BOLD, 20), Color.DARK_GRAY);
		nameField = new TextPrefab("Ali_Cuvit", new Font("Consolas", Font.BOLD, 20), Color.BLACK);
		
		rankLabel = new TextPrefab("Rank:", new Font("Consolas", Font.BOLD, 20), Color.DARK_GRAY);
		rankField = new TextPrefab("Warrior", new Font("Consolas", Font.BOLD, 20), Color.BLACK);

		favBookField = new TitledTextFieldPrefab(new AutoResizeTextFieldPrefab("The Art of War", new Font("Consolas", Font.BOLD, 20), Color.BLACK), 200, 0);
		favBookField.setTitleLabel("Favorite Book: ", new Font("Consolas", Font.BOLD, 20), Color.DARK_GRAY);

		favAuthorField = new TitledTextFieldPrefab(new AutoResizeTextFieldPrefab("Sun Tzu", new Font("Consolas", Font.BOLD, 20), Color.BLACK), 200, 0);
		favAuthorField.setTitleLabel("Favorite Author: ", new Font("Consolas", Font.BOLD, 20), Color.DARK_GRAY);

		favGenreField = new TitledTextFieldPrefab(new AutoResizeTextFieldPrefab("Treatise", new Font("Consolas", Font.BOLD, 20), Color.BLACK), 200, 0);
		favGenreField.setTitleLabel("Favorite Genre: ", new Font("Consolas", Font.BOLD, 20), Color.DARK_GRAY);
		
		exitProfileButton = new ButtonPrefab("Sign Out", 100, 40);
		
		// search
		searchField=new SearchField();
		searchScreenButton=new ButtonPrefab("Search Book", 150, 40, new Font("Consolas", Font.BOLD, 15) );
		
		searchBookButton = new ButtonPrefab("Search", 150, 40, new Font("Consolas", Font.BOLD, 15) );
		
		//	Book Visual Interface
		readableTitleField = new TextPrefab("Name:", new Font("Consolas", Font.BOLD, 20));
		readableDataField = new TextPrefab("Utopia", new Font("Consolas", Font.BOLD, 20));
		addToMyLibraryButton = new ButtonPrefab("Add to My Library", 220, 40, new Font("Consolas", Font.BOLD, 15));
		removeFromMyLibraryButton = new ButtonPrefab("Remove From My Library", 220, 40, new Font("Consolas", Font.BOLD, 15));
		
		//	Book Add Screen
		readableChoiceBook = new ButtonPrefab("Book", 120, 40, new Font("Consolas", Font.BOLD, 15));
		readableChoiceTestBook = new ButtonPrefab("Test Book", 120, 40, new Font("Consolas", Font.BOLD, 15));
		readableChoiceMagazine = new ButtonPrefab("Magazine", 120, 40, new Font("Consolas", Font.BOLD, 15));
		
		titlesLabel = new TextPrefab("Type:", new Font("Consolas", Font.PLAIN, 35), Color.BLACK);
		readableArgFields = new TextFieldPrefab[9];
		
		for (int i = 0; i < 9; i++) {
			
			readableArgFields[i] = new TextFieldPrefab(new Font("Consolas", Font.PLAIN, 15), Color.BLACK, 600, 40);
		}
		
		addReadableButton = new ButtonPrefab("Add", 70, 40);
		
		//	Commons
		backToMainScreenButton = new ButtonPrefab("Back to Main", 150, 40, new Font("Consolas", Font.BOLD, 15));
		goBackToLibraryButton = new ButtonPrefab("Back", 150, 40, new Font("Consolas", Font.BOLD, 15) );
		goBackToUserListButton = new ButtonPrefab("Back", 150, 40, new Font("Consolas", Font.BOLD, 15) );
		addReadableScreenButton = new ButtonPrefab("Add New Readable", 200, 40, new Font("Consolas", Font.BOLD, 15) );
		
		///////////////////
		
		manager.addMouseListener(listeners);
		
		//	login / signup
		manager.add(usernameField);
		manager.add(passwordField);
		manager.add(passwordField2);
		manager.add(passwordToggle);
		
		manager.add(loginButton);
		manager.add(signupButton);
		manager.add(loginScreenButton);
		manager.add(signupScreenButton);
		
		//	main
		manager.add(showProfileButton);
		manager.add(globalLibraryButton);
		manager.add(myLibraryButton);
		manager.add(otherUsersLibrariesButton);
		
		//	profile
		manager.add(detailsLabel);
		manager.add(nameLabel);
		manager.add(nameField);
		manager.add(rankLabel);
		manager.add(rankField);
		
		manager.add(favBookField);
		manager.add(favAuthorField);
		manager.add(favGenreField);
		
		manager.add(exitProfileButton);
		
		// library
		manager.add(searchField);
		manager.add(searchScreenButton);
		manager.add(searchBookButton);
		
		// book visual interface
		manager.add(readableTitleField);
		manager.add(readableDataField);
		manager.add(addToMyLibraryButton);
		manager.add(removeFromMyLibraryButton);
		
		// book add screen
		manager.add(readableChoiceBook);
		manager.add(readableChoiceTestBook);
		manager.add(titlesLabel);
		manager.add(readableChoiceMagazine);
		
		for (int i = 0; i < 9; i++) {
			
			manager.add(readableArgFields[i]);
		}
		
		manager.add(addReadableButton);
		
		//	common
		manager.add(backToMainScreenButton);
		manager.add(goBackToLibraryButton);
		manager.add(goBackToUserListButton);
		manager.add(addReadableScreenButton);

		add(manager);
		
		CloseAll();
		
		setVisible(true);
		
		LoginScreen();
	}
	
	public void CloseAll() {//close all Buttons of the screen
		
		var components = BookKeeperManager.instance.getComponents();
		
		for (var component : components) {
			
			component.setVisible(false);
		}
		
		BookKeeperManager.instance.clearReadableButtons();
		BookKeeperManager.instance.clearUserButtons();
		currentReadable = null;
	}
	
	public void LoginScreen() {//loginScreen page 
		
		CloseAll();
		
		usernameField.setText("");
		passwordField.setText("");
		
		usernameField.setVisible(true);
		passwordField.setVisible(true);
		passwordToggle.setVisible(true);
		loginButton.setVisible(true);
		signupScreenButton.setVisible(true);

		signupScreenButton.setLocation((x - signupScreenButton.getSize().width) / 2, 200);
		usernameField.setLocation((x - usernameField.getSize().width) / 2, 300);
		passwordField.setLocation((x - passwordField.getSize().width) / 2, 350);
		loginButton.setLocation((x - loginButton.getSize().width) / 2, 400);
		
		passwordToggle.setLocation((x - passwordToggle.getSize().width + passwordField.getSize().width) / 2 + 20, 354);
	}
	
	public void SignupScreen() {
		
		CloseAll();
		
		usernameField.setVisible(true);
		passwordField.setVisible(true);
		passwordField2.setVisible(true);
		passwordToggle.setVisible(true);
		signupButton.setVisible(true);
		loginScreenButton.setVisible(true);

		loginScreenButton.setLocation((x - loginScreenButton.getSize().width) / 2, 200);
		usernameField.setLocation((x - usernameField.getSize().width) / 2, 300);
		passwordField.setLocation((x - passwordField.getSize().width) / 2, 350);
		passwordField2.setLocation((x - passwordField2.getSize().width) / 2, 400);
		signupButton.setLocation((x - signupButton.getSize().width) / 2, 450);
		
		passwordToggle.setLocation((x - passwordToggle.getSize().width + passwordField.getSize().width) / 2 + 20, 404);
	}
	
	public void MainScreen() {
		
		CloseAll();
		
		showProfileButton.setVisible(true);
		globalLibraryButton.setVisible(true);
		myLibraryButton.setVisible(true);
		otherUsersLibrariesButton.setVisible(true);
		
		showProfileButton.setLocation(x - showProfileButton.getSize().width - 20, 20);

		globalLibraryButton.setLocation((x - globalLibraryButton.getSize().width) / 2, 200);
		myLibraryButton.setLocation((x - myLibraryButton.getSize().width) / 2, 300);
		otherUsersLibrariesButton.setLocation((x - otherUsersLibrariesButton.getSize().width) / 2, 400);
	}
	
	public void ProfileScreen() {
		
		CloseAll();
		
		UserManager.instance.getCurrentUser().LoadProfileData();//current User 
		
		backToMainScreenButton.setVisible(true);
		
		detailsLabel.setVisible(true);
		nameLabel.setVisible(true);
		nameField.setVisible(true);
		rankLabel.setVisible(true);
		rankField.setVisible(true);
		favBookField.setVisible(true);
		favAuthorField.setVisible(true);
		favGenreField.setVisible(true);
		
		favBookField.getTextField().UpdateSize();
		favAuthorField.getTextField().UpdateSize();
		favGenreField.getTextField().UpdateSize();
		
		exitProfileButton.setVisible(true);
		
		backToMainScreenButton.setLocation(x - backToMainScreenButton.getSize().width - 20, 20);
		
		detailsLabel.setLocation(50, 70);
		
		int label_y = 3;

		nameLabel.setLocation(50, 50 * label_y);
		nameField.setLocation(250, 50 * label_y++);
		rankLabel.setLocation(50, 50 * label_y);
		rankField.setLocation(250, 50 * label_y++);
		favBookField.setLocation(50, 50 * label_y++);
		favAuthorField.setLocation(50, 50 * label_y++);
		favGenreField.setLocation(50, 50 * label_y++);
		
		exitProfileButton.setLocation(x - exitProfileButton.getSize().width - 20, y - exitProfileButton.getSize().height - 20);
	}
	
	public void LibraryScreen(boolean _isOtherUsersLibrary) {
		
		CloseAll();
		
		if (_isOtherUsersLibrary) {
			
			goBackToUserListButton.setVisible(true);
			goBackToUserListButton.setLocation((x - goBackToUserListButton.getSize().width) / 2, 20);
		}
		else {
			
			addReadableScreenButton.setVisible(true);
			addReadableScreenButton.setLocation((x - addReadableScreenButton.getSize().width) / 2, 20);
		}
		
		backToMainScreenButton.setVisible(true);
		searchScreenButton.setVisible(true);
		
		
		backToMainScreenButton.setLocation(x - backToMainScreenButton.getSize().width - 20, 20);
		searchScreenButton.setLocation(20, 20);
		

		
		var readables = currentLibrary.Readables();
		
		int index = 0;
		
		for (var readable : readables) {
			
			var newBookButton = new ButtonPrefab(readable.getName(), 120, 40, new Font("Consolas", Font.BOLD, 12));
			
			newBookButton.setLocation((index % 10) * 125 + 15, (index / 10) * 45 + 120);
			
			BookKeeperManager.instance.addReadableButton(newBookButton);
			newBookButton.setVisible(true);
			
			index++;
		}
	}
	
	public void CurrentLibrarySearchScreen() {
		
		CloseAll();
		
		backToMainScreenButton.setVisible(true);
		searchField.setVisible(true);
		
		goBackToLibraryButton.setVisible(true);
		searchBookButton.setVisible(true);

		goBackToLibraryButton.setLocation(20, 20);
		backToMainScreenButton.setLocation(Screen.x - backToMainScreenButton.getSize().width - 20, 20);
		searchField.setLocation(50,150);

		searchBookButton.setLocation(550,150);
	}
	
	public void ReadableVisualInterfaceScreen(String _readableName) {
		
		CloseAll();
		
		currentReadable = BookKeeperManager.instance.globalLibrary.getReadable(_readableName);
		readableTitleField.setText(currentReadable.getTitlesByType().replaceAll(",", "\n"));
		readableDataField.setText(currentReadable.getData().replaceAll(",", "\n"));

		backToMainScreenButton.setVisible(true);
		goBackToLibraryButton.setVisible(true);
		
		readableTitleField.setVisible(true);
		readableDataField.setVisible(true);
		ToggleReadableAddRemoveButtons();

		readableTitleField.setLocation(20, 80);
		readableDataField.setLocation(readableTitleField.getSize().width + 30, 80);
		
		backToMainScreenButton.setLocation(Screen.x - backToMainScreenButton.getSize().width - 20, 20);
		goBackToLibraryButton.setLocation(20, 20);
		
		addToMyLibraryButton.setLocation(Screen.x - addToMyLibraryButton.getSize().width - 20, Screen.y - addToMyLibraryButton.getSize().width - 20);
		removeFromMyLibraryButton.setLocation(Screen.x - removeFromMyLibraryButton.getSize().width - 20, Screen.y - removeFromMyLibraryButton.getSize().width - 20);
	}
	
	public void ToggleReadableAddRemoveButtons() {
		
		var userLib = UserManager.instance.getCurrentUser().library;
		
		var currentReadableExistInLib = userLib.isContains(currentReadable);
		
		addToMyLibraryButton.setVisible(!currentReadableExistInLib);
		removeFromMyLibraryButton.setVisible(currentReadableExistInLib);
	}
	
	public void UserListScreen() {
		
		CloseAll();
		
		backToMainScreenButton.setVisible(true);
		
		backToMainScreenButton.setLocation(Screen.x - backToMainScreenButton.getSize().width - 20, 20);
		

		
		var users = UserManager.instance.getUsers();
		
		int index = 0;
		
		for (var user : users) {
			
			if (user == UserManager.instance.getCurrentUser()) {
				
				continue;
			}
			
			var newUserButton = new ButtonPrefab(user.userName, 120, 40, new Font("Consolas", Font.BOLD, 12));
			
			newUserButton.setLocation((index % 10) * 125 + 15, (index / 10) * 45 + 120);
			
			BookKeeperManager.instance.addUserButton(newUserButton);
			newUserButton.setVisible(true);
			
			index++;
		}
	}
	
	public void AddReadableScreen() {
		
		CloseAll();
		
		backToMainScreenButton.setVisible(true);
		
		readableChoiceBook.setVisible(true);
		readableChoiceTestBook.setVisible(true);
		readableChoiceMagazine.setVisible(true);
		
		backToMainScreenButton.setLocation(Screen.x - backToMainScreenButton.getSize().width - 20, 20);
		
		readableChoiceBook.setLocation((x - readableChoiceBook.getSize().width) / 2, y / 2 - readableChoiceBook.getSize().height - 5);
		readableChoiceTestBook.setLocation((x - readableChoiceTestBook.getSize().width) / 2, y / 2);
		readableChoiceMagazine.setLocation((x - readableChoiceMagazine.getSize().width) / 2, y / 2 + readableChoiceMagazine.getSize().height + 5);
	}
	
	public void AddReadableArgsScreen(int _type) {
		
		CloseAll();
		
		currentReadableType = _type;
		backToMainScreenButton.setVisible(true);
		titlesLabel.setVisible(true);
		addReadableButton.setVisible(true);

		backToMainScreenButton.setLocation(Screen.x - backToMainScreenButton.getSize().width - 20, 20);
		titlesLabel.setLocation(20, 80);
		addReadableButton.setLocation(20, 20);
		
		int argCount = 0;
		
		String titles = "";
		
		switch (_type) {
		case 0: {
			
			argCount = 8;
			titles = Book.getTitles();
			break;
		}
		case 1: {
			
			argCount = 9;
			titles = TestBook.getTitles();

			int lastCommaIndex = titles.lastIndexOf(',');
			titles = titles.substring(0, lastCommaIndex);
			break;
		}
		case 2: {
			
			argCount = 6;
			titles = Magazine.getTitles();
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + _type);
		}

		int firstCommaIndex = titles.indexOf(',');
		titles = titles.substring(firstCommaIndex + 1, titles.length());
		
		titles = titles.replaceAll(",", "\n");
		
		titlesLabel.setText(titles);
		
		var unitSize = titlesLabel.getSize().height / argCount;
		System.out.println(unitSize);
		
		for (int i = 0; i < argCount; i++) {
			
			var field = readableArgFields[i];
			
			field.setVisible(true);
			field.setLocation(titlesLabel.getSize().width + 30, i * unitSize + 82);
		}
	}
	
	public static void main(String[] args) {

		Screen screen = new Screen("BookKeeper");
		
		
		System.out.println("-------------------------------------");
		
		//UserManager.instance.getUsers().get(0).addToUserLib(BookKeeperManager.instance.globalLibrary.getReadable("Ulysses"));
		
		/*User u = UserManager.instance.getUsers().get(0);
		
		u.addToUserLib(BookKeeperManager.instance.globalLibrary.Readables().get(0));*/
	}

}
