package com.test.java;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.zip.Deflater;
import java.util.zip.InflaterInputStream;

class Encrypt {
    private static final int BUFFER_SIZE = 8192;
    private static ByteArrayOutputStream baos = null;
    private static byte[] buf = null;
    private static Deflater deflater = null;

    public static String decode(String paramString) throws IOException {
        if (buf == null) {
            buf = new byte[8192];
        }
        if (baos == null) {
            baos = new ByteArrayOutputStream(8192);
        }
        InflaterInputStream localInflaterInputStream = new InflaterInputStream(new ByteArrayInputStream(Base64.getDecoder().decode(paramString)));
        baos.reset();
        int i = localInflaterInputStream.read(buf);
        while (i != -1) {
            baos.write(buf, 0, i);
            int j = localInflaterInputStream.read(buf);
            i = j;
        }
        localInflaterInputStream.close();
        String str = new String(baos.toByteArray(), "UTF-8");
        return str;
    }
    public static void main(String args[]) throws IOException
    {
        System.out.println("Debugging, in main method");
        String encoded = "eJxLzs/VS8xLKcrPTNErS81LycxLBwBJFQdo";
        String decoded = decode(encoded);
        System.out.println(decoded);
    }

}