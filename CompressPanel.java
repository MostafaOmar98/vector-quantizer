import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CompressPanel extends JPanel {

    private JButton loadBtn, compressBtn;
    private LabelText qLevels, vWidth, vHeight, filePath;
    private String imagePath;
    CompressPanel()
    {
        setLayout(new FlowLayout());

        loadBtn = new JButton("Load");
        add(loadBtn);
        qLevels = new LabelText("Quantization Levels: ", 4);
        add(qLevels);

        vWidth = new LabelText("Vector Width: ", 4);
        add(vWidth);

        vHeight = new LabelText("Vector Height: ", 4);
        add(vHeight);

        filePath = new LabelText("Path: ", 30);
        add(filePath);

        compressBtn = new JButton("Compress");
        add(compressBtn);



        loadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser("D:\\mtest");
                int status = fc.showOpenDialog(fc.getParent());

                if (status == 0)
                    imagePath = new String(fc.getSelectedFile().getPath());
                else
                    imagePath = new String();

                filePath.setText(imagePath);
                System.out.println(filePath.getText());
            }
        });

        compressBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] vectorDim = {Integer.parseInt(vWidth.getText()), Integer.parseInt(vHeight.getText())};
                VectorQuantizerController controller = new VectorQuantizerController(Integer.parseInt(qLevels.getText()), vectorDim);
                controller.compress(imagePath, "compressed.bekh");
            }
        });

    }

}
