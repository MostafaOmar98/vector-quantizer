import java.util.ArrayList;

public class Cluster {
    private ImageVector repVector;
    private ArrayList<ImageVector> vectors;
    private int n, m;
    Cluster(ArrayList<ImageVector> vectors)
    {
        this.vectors = vectors;
        repVector = ImageVector.getAverage(vectors);
        this.n = repVector.getNumberOfRows();
        this.m = repVector.getNumberOfColumns();
    }

    Cluster(int n, int m)
    {
        vectors = new ArrayList<ImageVector>();
        repVector = new ImageVector(n, m);
        this.n = repVector.getNumberOfRows();
        this.m = repVector.getNumberOfColumns();
    }

    public int get(int i, int j)
    {
        return repVector.get(i, j);
    }

    public ArrayList<ImageVector> getVectors()
    {
        return vectors;
    }

    public void addVector(ImageVector v)
    {
        vectors.add(v); // handle error
    }

    public void calcRep()
    {
        repVector = ImageVector.getAverage(vectors);
    }

    public double getMeanSquareError()
    {
        double ret = 0;
        for (ImageVector v : vectors)
            ret += repVector.getDistance(v);
        ret /= vectors.size();
        return ret;
    }

    public void clearVectors()
    {
        vectors.clear();
    }

    public int getNumberOfRows()
    {
        return n;
    }
    public int getNumberOfColumns()
    {
        return m;
    }

    public ImageVector getRepVector()
    {
        return repVector;
    }
}
