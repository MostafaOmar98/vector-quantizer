import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class Main {
/*
1 2 7 9 4 11
3 4 6 6 12 12
4 9 15 14 9 9
10 10 20 18 8 8
4 3 17 16 1 4
4 5 18 18 5 6
 */
/*
2 places to improve:
1- finding better vectors maybe? Idk if my algorithm is 100% correct
2- better saving in file...
 */
    public static void main(String[] args) {
//        ImageHandler h = new ImageHandler();
//        int[][] img = h.readImage("D:\\trash\\donkey.jpg");
//        h.writeImg(img, "donkey.jpg");

//        LBGAlgorithm alg = new LBGAlgorithm(16, vectorDim);
//        int img[][] = ImageHandler.readImage("D:\\mtest\\garden.jpg");

//        ImageHandler.print(img);

//        Scanner sc = new Scanner(System.in);
//        for (int i = 0; i < 6; ++i)
//        {
//            for (int j = 0; j < 6; ++j)
//                img[i][j] = sc.nextInt();
//        }
//        ArrayList<ImageVector> codeBook = alg.LBG(img);
//        for (ImageVector v : codeBook) {
//            System.out.println(v);
//            System.out.println();
//        }


//        System.out.println();
//        System.out.println();
//        int[] vectorDim = {10, 2};
//        VectorQuantizerController v = new VectorQuantizerController(16, vectorDim);
//        v.compress("D:\\mtest\\r2.jpg", "D:\\mtest\\compressed.bekh");
//        v.decompress("D:\\mtest\\compressed.bekh", "D:\\mtest\\output.jpg");

        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                new MainFrame("Vector Quantizer");
            }
        });


    }
}
