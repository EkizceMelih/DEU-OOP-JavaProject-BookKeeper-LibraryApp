package ScreenComponents;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class TextPrefab extends JLabel {

    public TextPrefab(String _text) {

        this(_text, Color.BLACK);
    }

    public TextPrefab(String _text, Font _font) {

        this(_text, _font, Color.BLACK);
    }

    public TextPrefab(String _text, Color _color) {

        this(_text, new Font("Consolas", Font.PLAIN, 15), Color.BLACK);
    }

    public TextPrefab(String _text, Font _font, Color _color) {
        super(_text);

        setFont(_font);
        setForeground(_color);

        UpdateSize();
    }

    public void UpdateSize() {

        setSize(getPreferredSize());
    }

    @Override
    public void setText(String _text) {

        String[] lines = _text.split("\n");
        StringBuilder htmlText = new StringBuilder();

        htmlText.append("<html>");
        for (String line : lines) {
            htmlText.append(line + "<br>");
        }
        htmlText.append("</html>");

        super.setText(htmlText.toString());

        UpdateSize();
    }
}
