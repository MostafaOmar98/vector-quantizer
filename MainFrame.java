import javax.swing.*;

public class MainFrame extends JFrame {

    private VectorQuantizerTabbedPane tabbedPane;

    MainFrame(String title)
    {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        tabbedPane = new VectorQuantizerTabbedPane();
        add(tabbedPane);

        pack();
        setSize(800, 400);
        setVisible(true);
    }

}
