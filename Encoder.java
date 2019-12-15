import java.util.ArrayList;

public class Encoder {
    private ArrayList<ImageVector> codeBook;
    private Quantizer q;
    private int[] vectorDim;
    Encoder(ArrayList<ImageVector> codeBook)
    {
        this.codeBook = codeBook;
        vectorDim = new int[2];
        vectorDim[0] = codeBook.get(0).getNumberOfRows();
        vectorDim[1] = codeBook.get(0).getNumberOfColumns();
        q = new Quantizer(codeBook);
    }

    public void encode(int[][] img, String compressedPath)
    {
        /*
        Format of encoding:
        codeBook encoding
        1 byte: codeBook length
        1 byte: vectorDim[0]
        1 byte: vectorDim[1]
        for the next codeBook length 'chunks'
        each chunk is gonna be vectorDim[0] * vectorDim[1] bytes of values of that vector in the codebook

        Matrix Encoding:
        2 bytes: Number of Rows n
        2 bytes: Number of columns m
        for the next n * m/vectorDim[0] * vectorDim[1] (Number of vectors encoded) bytes
         each byte corresponds to an index in the codebook
         */
        byte[] codeBookEncoding = encodeCodeBook();
        byte[] imageEncoding = encodeImage(img);
        byte[] totalEncoding = Utility.concatArray(codeBookEncoding, imageEncoding);

        Utility.writeBytes(compressedPath, totalEncoding);
    }

    private void encodeCodeBookVectors(ArrayList<ImageVector> vectors, byte[] res, int nxtIdx)
    {
        for (ImageVector v : vectors)
        {
            for (int i = 0; i < v.getNumberOfRows(); ++i)
            {
                for (int j = 0; j < v.getNumberOfColumns(); ++j) {
                    res[nxtIdx++] = (byte) v.get(i, j);
                }
            }
        }
    }

    private void encodeImageVectors(ArrayList<ImageVector> vectors, byte[] res, int nxtIdx)
    {
        for (ImageVector v : vectors)
        {
            res[nxtIdx++] = (byte)q.quantize(v);
        }

    }

    private byte[] encodeImage(int[][] img)
    {
        int size = 2 + 2 + img.length * img[0].length/ (vectorDim[0] * vectorDim[1]);
        byte[] res = new byte[size];
        Utility.convertInt2Bytes(img.length, res, 0);
        Utility.convertInt2Bytes(img[0].length, res, 2);
        ArrayList<ImageVector> vectors = ImageHandler.getAllVectors(img, vectorDim[0], vectorDim[1]);
        encodeImageVectors(vectors, res, 4);
        return res;
    }

    private byte[] encodeCodeBook()
    {
        int size = 3 + codeBook.size() * vectorDim[0] * vectorDim[1];
        byte[] res = new byte[size];
        res[0] = (byte)codeBook.size();
        res[1] = (byte)vectorDim[0];
        res[2] = (byte)vectorDim[1];

        encodeCodeBookVectors(codeBook, res, 3);

        return res;
    }

}
