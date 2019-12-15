import java.util.ArrayList;

public class ImageVector {
    private int n, m;
    private int[][] data;
    ImageVector()
    {
        n = m = 0;
        data = null;
    }
    ImageVector(int n, int m)
    {
        this.n = n;
        this.m = m;
        data = new int[n][m];
    }

    ImageVector(int[][] other)
    {
        if (other != null) {
            n = other.length;
            m = other[0].length;
            data = other;
        }
        else
        {
            n = m = 0;
            data = null;
        }
    }

    public int get(int i, int j)
    {
        return data[i][j];
    }

    public void set(int i, int j, int v)
    {
        data[i][j] = v;
    }

    public static ImageVector getAverage(ArrayList<ImageVector> vectors)
    {
        int n = vectors.get(0).n, m = vectors.get(0).m; // handle error
        int[][] data = new int[n][m];
        for (ImageVector v : vectors)
        {
            for (int i = 0; i < v.n; ++i)
            {
                for (int j = 0; j < v.m; ++j)
                {
                    data[i][j] += v.get(i, j);
                }
            }
        }
        for (int i = 0; i < n; ++i)
        {
            for (int j = 0; j < m; ++j) {
                data[i][j] = Math.round((float)data[i][j]/vectors.size());
            }
        }

        ImageVector ret = new ImageVector(data);
        return ret;
    }

    int getNumberOfRows()
    {
        return n;
    }
    int getNumberOfColumns()
    {
        return m;
    }

    double getDistance(ImageVector other)
    {
        /*
        square(value(i,j) - otherValue(i,j)) for all i, j
         */
        double ret = 0;
        for (int i = 0; i < n; ++i)
        {
            for (int j = 0; j < m; ++j)
            {
                int d = this.get(i, j) - other.get(i, j);
                ret += d * d;
            }
        }
        return Math.sqrt(ret);
    }

    public String toString()
    {
        String ret = new String();
        for (int i = 0; i < n; ++i)
        {
            for (int j = 0; j < m; ++j)
            {
                ret += "" + data[i][j];
                if (j != m - 1)
                    ret += " ";
            }
            if (i != n - 1)
                ret += "\n";
        }
        return ret;
    }

}
