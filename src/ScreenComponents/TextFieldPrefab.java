package ScreenComponents;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

@SuppressWarnings("serial")
public class TextFieldPrefab extends JTextField implements KeyListener {
    
    public TextFieldPrefab() {
		
		this(Color.BLACK);
	}
	
	public TextFieldPrefab(Font _font) {
		
		this(_font, Color.BLACK);
	}
	
	public TextFieldPrefab(Color _color) {
		
		this(new Font("Consolas", Font.PLAIN, 15), Color.BLACK);
	}

    public TextFieldPrefab(Font _font, Color _color) {
    	
    	addKeyListener(this);
    	
        setFont(_font);
		setForeground(_color);
        setTransferHandler(null);
    }

    public TextFieldPrefab(Font _font, Color _color, int _width, int _heigth) {
    	
    	addKeyListener(this);
    	
        setFont(_font);
		setForeground(_color);
        setTransferHandler(null);
        
        setSize(_width, _heigth);
    }
	
	public void CheckText(KeyEvent e) {
		
		char c = e.getKeyChar();
        if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9') || c == '_' || c == ' ' || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE ) {
            
        	if (c == ' ' && !isSpacePlacable()) {
        		e.consume();
        	}
        }
        else {
        	e.consume();
        }
	}
	
	private boolean isSpacePlacable() {

		int caret = getCaretPosition();
		String text = getText();
		boolean flag = caret != 0 && text.charAt(caret - 1) != ' ' && !(caret < text.length() && text.charAt(caret) == ' ');
		
		return flag;
	}
	
	public void UpdateText() {

		String updated = getText().replaceAll("( )+", " ").stripLeading();
		
		if (!getText().equals(updated)) {

			int caret = getCaretPosition();
			
			setText(updated);
			
			if (caret <= getText().length()) {
				setCaretPosition(caret);
			}
			else {
				setCaretPosition(getText().length());
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		CheckText(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {

		UpdateText();
	}

	@Override
	public void keyReleased(KeyEvent e) {

		UpdateText();
	}
}
