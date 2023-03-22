package test;

import java.io.*;

public class Uti {
    static final int SIZE = 10000;
    static final byte[] buffer = new byte[SIZE];

    public static void main(String args[]) throws IOException {
        copy(args[0], args[1]);
    }

    static void copy(String src, String dst) throws IOException {
        InputStream fis = null;
        OutputStream fos = null;
        try {
            fis = new FileInputStream(src);
            fos = new FileOutputStream(dst);
            while (true) {
                synchronized (buffer) {
                    if (fis.read(buffer) == -1) {
                        break;
                    }
                    fos.write(buffer);
                }
            }
        } finally {
            if (fis != null) {
                fis.close();
            }
            if (fos != null) {
                fos.close();
            }
        }
    }
}
