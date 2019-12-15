import java.awt.*;
import java.util.ArrayList;

import static java.lang.System.exit;

public class LBGAlgorithm {
    private int QuantizationLevels;
    private int vectorDim[];

    LBGAlgorithm(int QuantizationLevels, int[] vectorDim)
    {
        this.QuantizationLevels = QuantizationLevels;
        this.vectorDim = vectorDim;
    }

    public ArrayList<ImageVector> LBG(int[][] img)
    {
        if (img.length%vectorDim[0] != 0 || img[0].length%vectorDim[1] != 0) // cant split to these vector dimensions
        {
            System.out.println("DIMENSIONS WRONG");
            exit(1);
        }
        ArrayList<ImageVector> allVectors = ImageHandler.getAllVectors(img, vectorDim[0], vectorDim[1]);
        ArrayList<Cluster> initialClusters = getInitialClusters(allVectors);
        ArrayList<ImageVector> codeBook = refineClusters(initialClusters, allVectors);
        return codeBook;
    }

    private ArrayList<ImageVector> refineClusters(ArrayList<Cluster> clusters, ArrayList<ImageVector> allVectors)
    {
        for (int k = 0; k < 50; ++k)
        {
            for (Cluster c : clusters)
                c.clearVectors();

            for (ImageVector v : allVectors)
            {
                int pick = 0;
                double minDist = clusters.get(0).getRepVector().getDistance(v);
                for (int i = 1; i < clusters.size(); ++i)
                {
                    double dist = clusters.get(i).getRepVector().getDistance(v);
                    if (dist < minDist)
                    {
                        pick = i;
                        minDist = dist;
                    }
                }

                clusters.get(pick).addVector(v);
            }

            for (Cluster c : clusters)
                c.calcRep();
        }
        ArrayList<ImageVector> codeBook = new ArrayList<>();
        for (Cluster c : clusters)
            codeBook.add(c.getRepVector());
        return codeBook;
    }

    private ArrayList<Cluster> getInitialClusters(ArrayList<ImageVector> allVectors)
    {
        ArrayList<Cluster> allClusters = new ArrayList<Cluster>();
        allClusters.add(new Cluster(allVectors));

        while (allClusters.size() < QuantizationLevels)
        {
            ArrayList<Cluster> newAllClusters = new ArrayList<Cluster>();
            for (Cluster c : allClusters)
            {
                ClusterSplitter splitter = new ClusterSplitter(c);
                splitter.split();
                newAllClusters.add(splitter.getLowChild());
                newAllClusters.add(splitter.getHighChild());
            }
            allClusters = newAllClusters;
        }

        return allClusters;
    }



}
