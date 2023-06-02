package ScreenComponents;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

@SuppressWarnings("serial")
public class AutoResizeTextFieldPrefab extends JTextField implements KeyListener {
    
    public AutoResizeTextFieldPrefab(String _text) {
		
		this(_text, Color.BLACK);
	}
	
	public AutoResizeTextFieldPrefab(String _text, Font _font) {
		
		this(_text, _font, Color.BLACK);
	}
	
	public AutoResizeTextFieldPrefab(String _text, Color _color) {
		
		this(_text, new Font("Consolas", Font.PLAIN, 15), Color.BLACK);
	}

    public AutoResizeTextFieldPrefab(String _text, Font _font, Color _color) {
    	
    	addKeyListener(this);
    	
    	setText(_text);
        setFont(_font);
		setForeground(_color);
        setTransferHandler(null);
        
        UpdateSize();
    }
    
    public void UpdateSize() {
    	
    	setSize(getPreferredSize().width + 15, getPreferredSize().height);
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
		UpdateSize();
	}

	@Override
	public void keyReleased(KeyEvent e) {

		UpdateText();
		UpdateSize();
	}
}
