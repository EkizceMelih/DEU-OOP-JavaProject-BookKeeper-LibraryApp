package ScreenComponents;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

import AppSystem.Screen;

@SuppressWarnings("serial")
public class PasswordToggle extends JCheckBox {
	
	public PasswordToggle() {
		super();
		
		try {
			
			Image hiddenImage = ImageIO.read(new File("icons/hidden.png")).getScaledInstance(30, 30, Image.SCALE_SMOOTH);
			Image shownImage = ImageIO.read(new File("icons/shown.png")).getScaledInstance(30, 30, Image.SCALE_SMOOTH);
			
			ImageIcon hidden = new ImageIcon(hiddenImage);
			ImageIcon shown = new ImageIcon(shownImage);
			
			setDisabledIcon(hidden);
			setSelectedIcon(shown);
			
			setIcon(hidden);
		}
		catch (IOException e1) {

			e1.printStackTrace();
		}
		
		setBackground(Screen.instance.backgroundColor);
		
        setBounds(0, 0, 30, 30);

        addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
            	
            	var passwordField = Screen.instance.passwordField;
            	var passwordField2 = Screen.instance.passwordField2;
            	
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    passwordField.setEchoChar((char) 0); // Show Password
                    passwordField2.setEchoChar((char) 0); // Show Password
                }
                else {
                	passwordField.setEchoChar('*'); // Hide Password
                	passwordField2.setEchoChar('*'); // Hide Password
                }
            }
        });
	}
}
