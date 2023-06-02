package AppSystem;
import java.util.ArrayList;

public class UserLogin {
	
	public static void login(String userName, String password) {
		
		ArrayList<User> users=UserManager.instance.getUsers();
		boolean flag=false;
		for (int i = 0; i < users.size(); i++) {
			
			if (userName.equals(users.get(i).userName)) {
				
				if (password.equals(users.get(i).password)) {
					
					System.out.println("Welcome "+userName);
					flag=true;
					
					Screen.instance.MainScreen();
					UserManager.instance.setCurrentUser(userName);
					break;
					
				}else {
					
					System.out.println("Password is not correct");
					flag=true;
					break;
				}
			}
		}
		if (!flag) {
			System.out.println("Can not found user");
		}
	}
	
	public static void signUp(String userName, String password) {
		ArrayList<User> users=UserManager.instance.getUsers();
		boolean flag=false;
		for (int i = 0; i < users.size(); i++) {
			if (userName.equals(users.get(i).userName)) {
				System.out.println(userName+" username is already used");
				flag=true;
				break;
			}
		}
		if (!flag) {
			User user = new User(userName, password);
			UserManager.instance.addUser(user);
			System.out.println("user kaydedildi");
			
			Screen.instance.LoginScreen();
		}
		
	}
		
		
	
}