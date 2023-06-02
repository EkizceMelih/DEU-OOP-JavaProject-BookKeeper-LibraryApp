package AppSystem;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class GlobalLibrary extends Library {
    
   public GlobalLibrary() {
	   super();
   }
    
    @Override
    public void Save(String _fileName) {
    	
    	if (loadEnd) {
			
			try (PrintWriter writer = new PrintWriter(new File(_fileName))) {
				
				String nt = "\n";
				String data = "";
				
				for (var readable : readables) {
					
					data += nt + readable.getData();
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
		
		try {
			
			Scanner scanner = new Scanner(new File(_fileName));
			
			while (scanner.hasNextLine()) {
				
				String data = scanner.nextLine();
				
				if (data.equals("")) {
					
					continue;
				}
				
				String[] args = data.split(",");
				
				addNewReadable(args);
			}
		}
		catch (Exception e) {
			
			e.printStackTrace();
			System.out.println(_fileName + " file can not be found!");
		}
		
		super.Load(_fileName);
	}
    
    public void addNewReadable(String[] args) {
    	
    	Readable readable;
		
		switch(args[0]) {
		
			case "Book":
				
				readable = new Book(args);
				
				break;
			
			case "TestBook":
				
				readable = new TestBook(args);
				
				break;
			
			case "Magazine":
				
				readable = new Magazine(args);
				
				break;
			
			default:
				
				readable = null;
				break;
		}
		
		readables.add(readable);
    }
}
