import javax.swing.*;

public class VectorQuantizerTabbedPane extends JTabbedPane {

    private CompressPanel compressPanel;
    private DecompressPanel decompressPanel;

    VectorQuantizerTabbedPane()
    {
        compressPanel = new CompressPanel();
        decompressPanel = new DecompressPanel();
        addTab("Compress", compressPanel);
        addTab("Decompress", decompressPanel);
    }

}
