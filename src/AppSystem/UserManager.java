package AppSystem;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class UserManager {

	public static UserManager instance;
	
	private User currentUser;
	
	private String _fileName;
	
	private ArrayList<User> users;
	
	public UserManager() {
		
		instance = this;
		_fileName = "Users.csv";
		
		users = new ArrayList<User>();
	}
	
	public User getCurrentUser() {
		
		return currentUser;
	}
	
	public User getUser(String _userName) {
		
		for (var user : users) {
			
			if (user.userName.equals(_userName)) {
				
				return user;
			}
		}
		
		return null;
	}
	
	public void setCurrentUser(String _userName) {
		
		if (_userName.equals("")) {
			
			currentUser = null;
			return;
		}
		
		for (var user : users) {
			
			if (user.userName.equals(_userName)) {
				
				currentUser = user;
				break;
			}
		}
	}
	
	public ArrayList<User> getUsers() {
		
		return users;
	}
	
	public void addUser(User _user) {
		
		users.add(_user);
	}
	
	public void usersSave() {
		
        try (PrintWriter writer = new PrintWriter(new File(_fileName))) {

            String nt = "\n";
            String data = "";

            for (var user : users) {

                data += nt + user.getData();
            }

            writer.write(data);
        }
        catch (Exception e) {

            System.out.println(_fileName + " file can not be found!\n");

            e.printStackTrace();
        }
    }

    public void usersLoad() {

        try {

            Scanner scanner = new Scanner(new File(_fileName));

            while (scanner.hasNextLine()) {

                String data = scanner.nextLine();

                if (data.equals("")) {

                    continue;
                }

                String[] args = data.split(",");

                User user=new User(args[0], args[1], args[2], args[3], args[4], Integer.parseInt(args[5]));



                users.add(user);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println(_fileName + " file can not be found!");
        }
    }
}
