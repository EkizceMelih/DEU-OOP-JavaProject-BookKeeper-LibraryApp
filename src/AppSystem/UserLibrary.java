package AppSystem;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class UserLibrary extends Library {

	private User user;
	
	public UserLibrary(User _user) {
		super();
		
		user = _user;
	}
    
    @Override
    public void Save(String _fileName) {
    	
    	if (loadEnd) {
			
			try (PrintWriter writer = new PrintWriter(new File(_fileName))) {
				
				String nt = "\n";
				String data = "";
				
				for (var readable : readables) {
					
					data += nt + readable.getName();
				}
				
				writer.write(data);
			}
			catch (Exception e) {
				
				System.out.println(_fileName + " file can not be found!\n");
				
				e.printStackTrace();
			}
		}
    }
    

	@Override
	public void Load(String _fileName) {
		
		var file = new File(_fileName);
		
		if (!file.exists()) {
			
			Save(_fileName);
		}
		else {
			
			try (Scanner scanner = new Scanner(file)) {
				
				while (scanner.hasNextLine()) {
					
					String data = scanner.nextLine();
					
					Readable readable = BookKeeperManager.instance.globalLibrary.getReadable(data);
					
					if (readable == null) {
						
						continue;
					}
					
					user.addToUserLib(readable);
				}
			}
			catch (Exception e) {
				
				e.printStackTrace();
				System.out.println(_fileName + " file can not be found!");
			}
		}
		
		loadEnd = true;
	}
}
