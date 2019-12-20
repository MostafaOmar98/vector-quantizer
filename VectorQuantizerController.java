import java.util.ArrayList;

public class VectorQuantizerController {
    private int QuantizationLevels;
    private int[] vectorDim;
    VectorQuantizerController( int QuantizationLevels, int[] vectorDim)
    {
        this.QuantizationLevels = QuantizationLevels;
        this.vectorDim = vectorDim;
    }

    public void compress(String imagePath, String compressedPath)
    {
        int[][] img =  ImageHandler.readImage(imagePath);

        LBGAlgorithm LBG = new LBGAlgorithm(QuantizationLevels, vectorDim);
        ArrayList<ImageVector> codeBook = LBG.LBG(img);

        Encoder enc = new Encoder(codeBook);
        enc.encode(img, compressedPath);
    }

    public void decompress(String compressedPath, String imagePath)
    {
        Decoder dec = new Decoder(compressedPath);
        int[][] img = dec.decode();

        ImageHandler.writeImg(img, imagePath);
    }

}
