package ScreenComponents;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class PasswordField extends JPasswordField implements KeyListener {

    private JLabel headerLabel;
    private String header;
    
    public void setLabelTempText(String _tempText) {
    	
    	headerLabel.setText(_tempText);
    }

    public PasswordField(String _header) {
    	
    	header = _header;
    	
        setFont(new Font("Consolas", Font.PLAIN, 25));
        addKeyListener(this);
        setTransferHandler(null);
        setSize(500, 40);

        headerLabel = new JLabel(header);
        headerLabel.setFont(new Font("Consolas", Font.ITALIC, 25));
        headerLabel.setForeground(Color.GRAY);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setBounds(0, 4, 500, 40);
        add(headerLabel);

        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (getPassword().length == 0) {
                    headerLabel.setVisible(false);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getPassword().length == 0) {
                	
                	headerLabel.setText(header);
                    headerLabel.setVisible(true);
                }
            }
        });
    }
	
	public void CheckText(KeyEvent e) {
		
		char c = e.getKeyChar();
        if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9') || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE ) {
            
        	if (c == ' ') {
        		e.consume();
        	}
        }
        else {
        	e.consume();
        }
	}
	
	public void UpdateText() {

		String text = new String(getPassword());
		String updated = text.replaceAll("( )+", "").stripLeading();
		
		if (!text.equals(updated)) {

			int caret = getCaretPosition();
			
			setText(updated);
			
			if (caret <= text.length()) {
				setCaretPosition(caret);
			}
			else {
				setCaretPosition(text.length());
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
