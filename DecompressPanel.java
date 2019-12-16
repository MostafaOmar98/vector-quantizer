import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class DecompressPanel extends JPanel {

    private JButton loadBtn, decompressBtn;
    private LabelText filePath;
    private String compressedPath;
    DecompressPanel()
    {
        setLayout(new FlowLayout());

        loadBtn = new JButton("Load");
        add(loadBtn);

        filePath = new LabelText("File Path: ", 30);
        add(filePath);

        decompressBtn = new JButton("Decompress");
        add(decompressBtn);


        loadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser("C:\\Users\\MostafaOmar\\IdeaProjects\\vector-quantizer");
                int status = fc.showOpenDialog(fc.getParent());

                if (status == 0)
                    compressedPath = new String(fc.getSelectedFile().getPath());
                else
                    compressedPath = new String();

                filePath.setText(compressedPath);
                System.out.println(filePath.getText());
            }
        });

        decompressBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VectorQuantizerController.decompress(compressedPath, "D:\\mtest\\decompressed.jpg");
            }
        });

    }

}
