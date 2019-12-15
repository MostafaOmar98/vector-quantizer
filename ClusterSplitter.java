public class ClusterSplitter {
    private Cluster father, lowChild, highChild;
    private int n, m;
    ClusterSplitter(Cluster c)
    {
        father = c;
        n = father.getNumberOfRows();
        m = father.getNumberOfColumns();
    }

    public void split()
    {
        lowChild = new Cluster(n, m);
        highChild = new Cluster(n, m);
        ImageVector low = new ImageVector(n, m);
        ImageVector high = new ImageVector(n, m);
        for (int i = 0; i < n; ++i)
        {
            for (int j = 0; j < m; ++j)
            {
                low.set(i,j, Math.max(0, father.get(i, j) - 1));
                high.set(i, j, Math.min(255, father.get(i, j) + 1));
            }
        }

        for (ImageVector v : father.getVectors())
        {
            if (low.getDistance(v) < high.getDistance(v))
                lowChild.addVector(v);
            else
                highChild.addVector(v);
        }
        lowChild.calcRep();
        highChild.calcRep();
    }

    public Cluster getLowChild()
    {
        return lowChild;
    }

    public Cluster getHighChild()
    {
        return highChild;
    }

}
