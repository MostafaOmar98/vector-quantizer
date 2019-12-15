import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ImageHandler {

    // all arrays handle images as euclidean 2D space
    // x is horizontal (first dimesntion, width), y is vertical (second dimension, height)
    public static int[][] readImage(String imagePath)
    {
        File imgFile = new File(imagePath);
        BufferedImage buffImg = null;
        try {
            buffImg = ImageIO.read(imgFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int w = buffImg.getWidth(), h = buffImg.getHeight();
        int[][] img = new int[w][h];

        for (int x = 0; x < w; ++x)
        {
            for (int y = 0; y < h; ++y) {
                int blue = (buffImg.getRGB(x, y))&0xFF;
                img[x][y] = blue;
            }
        }

        return img;
    }

    public static void writeImg(int[][] img, String imagePath)
    {
        BufferedImage buffImg = new BufferedImage(img.length, img[0].length, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < img.length; ++x)
        {
            for (int y = 0; y < img[x].length; ++y)
                buffImg.setRGB(x, y, img[x][y]|(img[x][y]<<8)|(img[x][y]<<16));
        }

        File ImageFile = new File(imagePath);
        try {
            ImageIO.write(buffImg, "jpg", ImageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<ImageVector> getAllVectors(int[][] img, int n, int m)
    {
        ArrayList<ImageVector> allVectors = new ArrayList<>();
        for (int i = 0; i < img.length; i += n)
        {
            for (int j = 0; j < img[0].length; j += m)
            {
                int[][] data = new int[n][m];
                for (int ii = i; ii < i + n; ++ii)
                {
                    for (int jj = j; jj < j + m; ++jj)
                    {
                        data[ii - i][jj - j] = img[ii][jj];
                    }
                }
                ImageVector v = new ImageVector(data);
                allVectors.add(v);
            }
        }
        return allVectors;
    }

    public static void print(int[][] img)
    {
        for (int i = 0; i < 30; ++i)
        {
            for (int j = 0; j < 30; ++j)
                System.out.print(img[i][j] + " ");
            System.out.println();
        }
    }
}
