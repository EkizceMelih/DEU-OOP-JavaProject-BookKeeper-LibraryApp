package ScreenComponents;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import AppSystem.Screen;

@SuppressWarnings("serial")
public class ButtonPrefab extends JButton {

	public ButtonPrefab(String _text) {
		
		this(_text, 90, 40);
	}

	public ButtonPrefab(String _text, int _width, int _heigth) {
		
		this(_text, _width, _heigth, new Font("Consolas", Font.PLAIN, 15));
	}

	public ButtonPrefab(String _text, int _width, int _heigth, Font _font) {
		
		this(_text, _width, _heigth, SwingConstants.CENTER, SwingConstants.CENTER, _font);
	}
	
	public ButtonPrefab(String _text, int _width, int _heigth, int _align_horizontal, int _align_vertical) {
		
		this(_text, _width, _heigth, _align_horizontal, _align_vertical, new Font("Consolas", Font.PLAIN, 15));
	}

	public ButtonPrefab(String _text, int _width, int _heigth, int _align_horizontal, int _align_vertical, Font _font) {
		super(_text);

		setFont(_font);
		setHorizontalAlignment(_align_horizontal);
		setVerticalAlignment(_align_vertical);
		addActionListener(Screen.instance.listeners);
		addMouseListener(Screen.instance.listeners);
		setFocusable(false);
		setSize(_width, _heigth);
	}
	
	public void setIcon(String _filePath) {
		
		setIcon(_filePath, getSize().width, getSize().height);
	}
	
	public void setIcon(String _filePath, int _iconWidth, int _iconHeight) {
		
		try {
			setIcon(new ImageIcon(ImageIO.read(new File(_filePath)).getScaledInstance(_iconWidth, _iconHeight, Image.SCALE_SMOOTH)));
		}
		catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}
