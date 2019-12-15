import java.io.*;

public class Utility {
    public static byte[] concatArray(byte[] a, byte[] b)
    {
        byte[] res = new byte[a.length + b.length];
        System.arraycopy(a, 0, res, 0, a.length);
        System.arraycopy(b, 0, res, a.length, b.length);
        return res;
    }

    public static void convertInt2Bytes(int v, byte[] res, int idx)
    {
        res[idx] = (byte)((v>>8)&0xFF);
        res[idx + 1] = (byte)(v&0xFF);
    }

    public static int getInt2Bytes(byte[] arr, int idx)
    {
        int ret = arr[idx];
        ret <<= 8;
        ret |= getUnsignedByte(arr[idx + 1]);
        return ret;

    }

    public static void writeBytes(String path, byte[] arr)
    {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(path, false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            out.write(arr);
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public static byte[] readBytes(String path)
    {
        File f = new File(path);
        FileInputStream inp = null;
        try{
            inp = new FileInputStream(f);
        }catch(IOException e)
        {
            e.printStackTrace();
        }

        byte[] res = new byte[(int)f.length()];
        try {
            inp.read(res);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static int getUnsignedByte(byte b)
    {
        return (b&0xFF);
    }
}
