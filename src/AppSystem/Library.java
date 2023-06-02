package AppSystem;
import java.util.ArrayList;

public abstract class Library {
	
	public boolean loadEnd;

	protected ArrayList<Readable> readables;
	
	public Library() {
		
		loadEnd = false;
		readables = new ArrayList<Readable>();
	}
	
	public ArrayList<Readable> Readables() {
		
		return readables;
	}

    public ArrayList<Readable> getFilteredReadables(String _name){
    	
    	_name = _name.toLowerCase();
    	
       ArrayList<Readable> filtered_readables= new ArrayList<Readable>();
       
        for (Readable readable : readables) {
            if(readable.name.toLowerCase().contains(_name)){
                filtered_readables.add(readable);
            }
        }
        return filtered_readables;
    }
    
    public Readable getReadable(String _name) {
    	
    	_name = _name.toLowerCase();
		
    	for (Readable readable : readables) {
            if(readable.name.toLowerCase().equals(_name)){
                return readable;
            }
        }
    	
    	return null;
	}
	
	public void AddReadable(Readable _readable) {
		
		readables.add(_readable);
	}
	
	public void RemoveReadable(Readable _readable) {
		
		readables.remove(_readable);
	}
	
	public void Save(String _fileName) {
		
		
	}
	
	public void Load(String _fileName) {
		
		loadEnd = true;
	}
	
	public boolean isContains(Readable _readable) {
		
		return isContains(_readable.getName());
	}
	
	public boolean isContains(String _readableName) {
		
		for (var readable : readables) {
			
			if (readable.getName().equalsIgnoreCase(_readableName)) {
				
				//System.out.println("TRUE !!!!!");
				return true;
			}
		}
		
		//System.out.println("FALSE !!!!!");
		
		return false;
	}
}
