package ScreenComponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import AppSystem.Screen;

@SuppressWarnings("serial")
public class TitledTextFieldPrefab extends JPanel implements KeyListener {

    private JLabel titleLabel;
    private AutoResizeTextFieldPrefab textField;
    
    public AutoResizeTextFieldPrefab getTextField() {
    	
    	return textField;
    }
    
    public TitledTextFieldPrefab(AutoResizeTextFieldPrefab _textField, int _posX, int _posY) {

		setLayout(null);
    	
		setBackground(Screen.instance.backgroundColor);
    	
    	textField = _textField;
    	textField.setLocation(_posX, _posY);
    	textField.addKeyListener(this);
		
    	textField.setBackground(Screen.instance.backgroundColor);
    	textField.setBorder(null);
    	textField.UpdateSize();

    	titleLabel = new JLabel("");
    	titleLabel.setLocation(0, 0);
    	titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
    	titleLabel.setVerticalAlignment(SwingConstants.CENTER);
    	setTitleLabel("", new Font("Consolas", Font.BOLD, 20), Color.DARK_GRAY);

    	textField.setVisible(true);
    	titleLabel.setVisible(true);
    	
    	add(textField);
    	add(titleLabel);
    	
    	UpdateSize();
    }
    
    public void setTitleLabel(String _text, Font _font, Color _color) {
    	
    	titleLabel.setText(_text);
    	titleLabel.setFont(_font);
    	titleLabel.setForeground(_color);
    	titleLabel.setSize(titleLabel.getPreferredSize().width, titleLabel.getPreferredSize().height);
    	
    	UpdateSize();
    }
    
    private void UpdateSize() {

    	setSize(titleLabel.getSize().width + textField.getLocation().x + textField.getSize().width + 15, Math.max(titleLabel.getSize().height, textField.getSize().height));
    }

	@Override
	public void keyTyped(KeyEvent e) {

		
	}

	@Override
	public void keyPressed(KeyEvent e) {

		UpdateSize();
	}

	@Override
	public void keyReleased(KeyEvent e) {

		UpdateSize();
	}
}
