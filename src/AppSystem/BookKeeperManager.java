package AppSystem;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import ScreenComponents.ButtonPrefab;

@SuppressWarnings("serial")
public class BookKeeperManager extends JPanel implements KeyListener, ActionListener {

	public static BookKeeperManager instance;
	
	public GlobalLibrary globalLibrary;
	
	private ArrayList<ButtonPrefab> readableButtons;
	private ArrayList<ButtonPrefab> userButtons;
	
	public BookKeeperManager() {
		
		instance = this;
		
		globalLibrary = new GlobalLibrary();
		globalLibrary.Load("GlobalLibrary.csv");
		
		readableButtons = new ArrayList<ButtonPrefab>();
		userButtons = new ArrayList<ButtonPrefab>();
		
		setBackground(Screen.instance.backgroundColor);

		Runtime.getRuntime().addShutdownHook(new Thread()
		{
		    @Override
		    public void run()
		    {
		        globalLibrary.Save("GlobalLibrary.csv");
		        UserManager.instance.usersSave();
		    }
		});
		
		requestFocus();

        setFocusable(true);

        setFocusTraversalKeysEnabled(false);

		setLayout(null);
		
		/*globalLibrary.AddReadable(new Book("Utopia", "Thomas More", 112, 10));
		globalLibrary.AddReadable(new Book("xd", "dx", 50, 4));
		
		globalLibrary.Save("GlobalLibrary.txt");*/
		
		UserManager.instance.usersLoad();
	}
	
	public void clearReadableButtons() {
		
		for (var button : readableButtons) {
			
			remove(button);
		}
		
		readableButtons.clear();
	}
	
	public void addReadableButton(ButtonPrefab _button) {
		
		readableButtons.add(_button);
		add(_button);
	}
	
	public boolean isReadableButton(Object _obj) {
		
		if (_obj.getClass() != ButtonPrefab.class) {
			
			return false;
		}
		
		var button = (ButtonPrefab)_obj;
		
		for (var readableButton : readableButtons) {
			
			if (readableButton.equals(button)) {
				
				return true;
			}
		}
		
		return false;
	}
	
	public void clearUserButtons() {
		
		for (var button : userButtons) {
			
			remove(button);
		}
		
		userButtons.clear();
	}
	
	public void addUserButton(ButtonPrefab _button) {
		
		userButtons.add(_button);
		add(_button);
	}
	
	public boolean isUserButton(Object _obj) {
		
		if (_obj.getClass() != ButtonPrefab.class) {
			
			return false;
		}
		
		var button = (ButtonPrefab)_obj;
		
		for (var userButton : userButtons) {
			
			if (userButton.equals(button)) {
				
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public void repaint() {
		super.repaint();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		g.setColor(Color.BLACK);
		
		var screen = Screen.instance;
		
		var components = getComponents();
		
		for (var component : components) {
			
			component.repaint();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
