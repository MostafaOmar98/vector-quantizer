import java.util.ArrayList;

public class Decoder {
    private String compressedPath;
    private int nxtIdx;
    Decoder(String compressedPath)
    {
        this.compressedPath = compressedPath;
    }

    public int[][] decode()
    {
        byte[] encoding = Utility.readBytes(compressedPath);

        int codeBookSize = Utility.getUnsignedByte(encoding[nxtIdx++]);
        int[] vectorDim = {Utility.getUnsignedByte(encoding[nxtIdx++]), Utility.getUnsignedByte(encoding[nxtIdx++])};

        ArrayList<ImageVector> codeBook = fetchCodeBook(encoding, codeBookSize, vectorDim[0], vectorDim[1]);

        Quantizer q = new Quantizer(codeBook);

        int n = Utility.getInt2Bytes(encoding, nxtIdx);
        nxtIdx += 2;
        int m = Utility.getInt2Bytes(encoding, nxtIdx);
        nxtIdx += 2;

        int numberOfVectors = n * m/(vectorDim[0] * vectorDim[1]);

        int[][] img = new int[n][m];

        int i = 0, j = 0;

        for (int k = 0; k < numberOfVectors; ++k)
        {
            int b = Utility.getUnsignedByte(encoding[nxtIdx++]);
            ImageVector v = q.dequantize(b);

            for (int ii = 0; ii < vectorDim[0]; ++ii)
            {
                for (int jj = 0; jj < vectorDim[1]; ++jj) {
                    img[i + ii][j + jj] = v.get(ii, jj);
                }
            }
            j += vectorDim[1];
            if (j == m)
            {
                j = 0;
                i += vectorDim[0];
            }
        }
//        ImageHandler.print(img);
        return img;

    }


    private ArrayList<ImageVector> fetchCodeBook(byte[] encoding, int codeBookSize, int n, int m)
    {
        ArrayList<ImageVector> codeBook = new ArrayList<>();
        for (int k = 0; k < codeBookSize; ++k)
        {
            int[][] data = new int[n][m];
            for (int i = 0; i < n; ++i)
            {
                for (int j = 0; j < m; ++j)
                    data[i][j] = Utility.getUnsignedByte(encoding[nxtIdx++]);
            }
            codeBook.add(new ImageVector(data));
        }
        return codeBook;

    }

}
