package com.volunteer.commonweal.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GzipUtils {
    public static byte[] compress(String str, String encoding) {
        if (str == null || str.length() == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream(); //创建字节输出流
        GZIPOutputStream gzip; //创建GZIP输出流
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(str.getBytes(encoding)); //进行gzip压缩
            gzip.close();
        } catch ( Exception e) {
            e.printStackTrace();
        }
        return out.toByteArray(); //输出Gzip信息
    }
    public static String uncompress(byte[] bytes, String coding) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        try {
            GZIPInputStream ungzip = new GZIPInputStream(in);
            byte[] buffer = new byte[256];
            int n;
            while ((n = ungzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uncompressToString(out.toByteArray(), coding);
    }
    public static String uncompressToString(byte[] bytes, String coding) {
        return uncompressToString(bytes, coding);
    }
}
