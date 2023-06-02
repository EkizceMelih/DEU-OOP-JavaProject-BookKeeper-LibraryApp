package AppSystem;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import ScreenComponents.ButtonPrefab;

public class Listeners implements ActionListener, MouseListener {

	@Override
	public void actionPerformed(ActionEvent e) {	//	calls when button pressed
		
		var eventSource = e.getSource();
		
		var screen = Screen.instance;
		
		if (eventSource == screen.loginButton) {
			
			String name = screen.usernameField.getText();
			String password = new String(screen.passwordField.getPassword());
			
			UserLogin.login(name, password);
		}
		else if (eventSource == screen.signupButton) {
			
			String name = screen.usernameField.getText();
			String password = new String(screen.passwordField.getPassword());
			String password2 = new String(screen.passwordField2.getPassword());
			
			if (password.equals(password2)) {
				UserLogin.signUp(name, password);
			}
			else {
				
				screen.passwordField2.setText("");
				screen.passwordField2.setLabelTempText("passwords not match!");
			}
		}
		else if (eventSource == screen.signupScreenButton) {

			screen.usernameField.setText("");
			screen.passwordField.setText("");
			screen.passwordField2.setText("");
			
			screen.SignupScreen();
		}
		else if (eventSource == screen.loginScreenButton) {

			screen.usernameField.setText("");
			screen.passwordField.setText("");
			screen.passwordField2.setText("");
			
			screen.LoginScreen();
		}
		else if (eventSource == screen.backToMainScreenButton) {
			
			if (screen.favBookField.isVisible()) {
				
				UserManager.instance.getCurrentUser().SaveProfileData();
			}
			
			screen.MainScreen();
		}
		else if (eventSource == screen.exitProfileButton) {
			
			UserManager.instance.getCurrentUser().SaveProfileData();
			UserManager.instance.setCurrentUser("");
			screen.LoginScreen();
		}
		else if (eventSource == screen.showProfileButton) {
			
			screen.ProfileScreen();
		}
		else if (eventSource == screen.globalLibraryButton) {
			
			screen.currentLibrary = BookKeeperManager.instance.globalLibrary;
			screen.LibraryScreen(false);
		}
		else if (eventSource == screen.myLibraryButton) {
			
			screen.currentLibrary = UserManager.instance.getCurrentUser().library;
			screen.LibraryScreen(false);
		}
		else if (eventSource == screen.otherUsersLibrariesButton) {
			
			screen.UserListScreen();
		}
		else if(eventSource == screen.searchScreenButton) {
			
			screen.CurrentLibrarySearchScreen();
		}
		else if(eventSource == screen.goBackToLibraryButton) {
			
			boolean isOtherUsersLibrary = !(screen.currentLibrary == BookKeeperManager.instance.globalLibrary || screen.currentLibrary == UserManager.instance.getCurrentUser().library);
			
			screen.LibraryScreen(isOtherUsersLibrary);
		}
		else if(eventSource == screen.goBackToUserListButton) {
			
			screen.UserListScreen();
		}
		else if(eventSource == screen.searchBookButton) {
			
			screen.CurrentLibrarySearchScreen();
	    	
	        String userInput = screen.searchField.getText();
	        var readables = screen.currentLibrary.getFilteredReadables(userInput);
			
			int index = 0;
			
			for (var readable : readables) {
				
				var newBookButton = new ButtonPrefab(readable.getName(), 120, 40, new Font("Consolas", Font.BOLD, 12));
				
				BookKeeperManager.instance.addReadableButton(newBookButton);
				
				newBookButton.setLocation((index % 10) * 125 + 15, (index / 10) * 45 + 250);
				
				newBookButton.setVisible(true);
				index++;
			}
		}
		else if (eventSource == screen.addToMyLibraryButton) {
			
			var user = UserManager.instance.getCurrentUser();
			
			user.addToUserLib(screen.currentReadable);
			
			screen.ToggleReadableAddRemoveButtons();
		}
		else if (eventSource == screen.removeFromMyLibraryButton) {
			
			var user = UserManager.instance.getCurrentUser();
			
			user.removeFromUserLib(screen.currentReadable);
			
			screen.ToggleReadableAddRemoveButtons();
		}
		else if (eventSource == screen.addReadableScreenButton) {
			
			screen.AddReadableScreen();
		}
		else if (eventSource == screen.readableChoiceBook) {
			
			screen.AddReadableArgsScreen(0);
		}
		else if (eventSource == screen.readableChoiceTestBook) {
			
			screen.AddReadableArgsScreen(1);
		}
		else if (eventSource == screen.readableChoiceMagazine) {
			
			screen.AddReadableArgsScreen(2);
		}
		else if (eventSource == screen.addReadableButton) {
			
			String[] args = new String[10];
			
			switch (screen.currentReadableType) {
			case 0: {
				
				args[0] = "Book";
				break;
			}
			case 1: {
				
				args[0] = "TestBook";
				break;
			}
			case 2: {
				
				args[0] = "Magazine";
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + screen.currentReadableType);
			}
			
			for (int i = 0; i < 9; i++) {
				
				args[i + 1] = screen.readableArgFields[i].getText();
				
				screen.readableArgFields[i].setText("");
			}
			
			var globalLibrary = BookKeeperManager.instance.globalLibrary;
			
			globalLibrary.addNewReadable(args);

			var user = UserManager.instance.getCurrentUser();
			
			if (screen.currentLibrary == user.library) {
				
				user.addToUserLib(globalLibrary.getReadable(args[1]));
			}
			
			screen.LibraryScreen(false);
		}
		else if (BookKeeperManager.instance.isReadableButton(eventSource)) {
			
			var button = (ButtonPrefab)eventSource;
			
			screen.ReadableVisualInterfaceScreen(button.getText());
		}
		else if (BookKeeperManager.instance.isUserButton(eventSource)) {
			
			var button = (ButtonPrefab)eventSource;
			var user = UserManager.instance.getUser(button.getText());
			
			screen.currentLibrary = user.library;
			screen.LibraryScreen(true);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {

		BookKeeperManager.instance.requestFocus();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
}
