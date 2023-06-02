package ScreenComponents;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import AppSystem.Screen;

@SuppressWarnings("serial")
public class UsernameField extends JTextField implements KeyListener {

    private JLabel headerLabel;

    public UsernameField() {
    	
    	addKeyListener(this);
    	
        setFont(new Font("Consolas", Font.PLAIN, 25));
        setTransferHandler(null);
        setSize(500, 40);

        headerLabel = new JLabel("enter username...");
        headerLabel.setFont(new Font("Consolas", Font.ITALIC, 25));
        headerLabel.setForeground(Color.GRAY);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setBounds(0, 4, 500, 40);
        add(headerLabel);

        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (getText().isEmpty()) {
                    headerLabel.setVisible(false);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getText().isEmpty()) {
                    headerLabel.setVisible(true);
                }
            }
        });
    }
	
	public void CheckText(KeyEvent e) {
		
		char c = e.getKeyChar();
        if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9') || c == '_' || c == ' ' || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE ) {
            
        	if (c == ' ' && !isSpacePlacable()) {
        		e.consume();
        	}
        	else {
        		
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
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {

		UpdateText();
	}

    @Override
    public void setText(String text) {
        super.setText(text);
        if (text.isEmpty()) {
            headerLabel.setVisible(true);
        } else {
            headerLabel.setVisible(false);
        }
    }
}
