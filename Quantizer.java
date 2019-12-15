import java.util.ArrayList;

public class Quantizer {
    ArrayList<ImageVector> codeBook;
    Quantizer(ArrayList<ImageVector> codeBook)
    {
        this.codeBook = codeBook;
    }

    public int quantize(ImageVector v)
    {
        int pick = 0;
        double minDist = codeBook.get(0).getDistance(v);
        for (int i = 1; i < codeBook.size(); ++i)
        {
            double dist = codeBook.get(i).getDistance(v);
            if (dist < minDist)
            {
                minDist = dist;
                pick = i;
            }
        }
        return pick;
    }

    public ImageVector dequantize(int i)
    {
        return codeBook.get(i);
    }

}
