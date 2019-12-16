import javax.swing.*;
import java.awt.*;

public class LabelText extends JPanel {
    private JLabel label;
    private JTextField txt;

    LabelText(String labelText, int textSize)
    {
        setLayout(new FlowLayout());

        label = new JLabel(labelText);
        txt = new JTextField(textSize);

        add(label);
        add(txt);
    }

    public String getText()
    {
        return txt.getText();
    }

    public void setText(String s)
    {
        txt.setText(s);
    }
}
