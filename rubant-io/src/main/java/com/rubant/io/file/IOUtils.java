package com.rubant.io.file;

import com.rubant.io.Constants;
import com.rubant.io.file.internal.StringBuilderWriter;

import java.io.*;
import java.nio.charset.Charset;

/**
 * io 操作工具类
 *
 * @author rubant
 * @date 2022/11/12 20:48
 */
public class IOUtils {

    /**
     * 默认一次读取的数据字节数
     */
    private static final int DEFAULT_BUFFER_SIZE = 1024 * 8;

    /**
     * 将输入流转变为字符串
     */
    public static String toString(InputStream input, Charset encoding) throws IOException {
        StringBuilderWriter sw = new StringBuilderWriter();
        copy(input, sw, encoding);
        return sw.toString();
    }

    /**
     * 将输入流的输入写入写入器
     */
    public static void copy(InputStream input, Writer output, Charset inputEncoding)
            throws IOException {
        final InputStreamReader in = new InputStreamReader(input, inputEncoding);
        copy(in, output);
    }

    /**
     * 读写数据
     */
    public static int copy(Reader input, Writer output) throws IOException {
        final long count = copyLarge(input, output);
        if (count > Integer.MAX_VALUE) {
            return -1;
        }
        return (int) count;
    }

    /**
     * 复制大块数据
     */
    public static long copyLarge(Reader input, Writer output) throws IOException {
        return copyLarge(input, output, new char[DEFAULT_BUFFER_SIZE]);
    }

    /**
     * 以缓冲区大小进行读写
     */
    public static long copyLarge(Reader input, Writer output, char[] buffer) throws IOException {
        long count = 0;
        int n;
        while (Constants.EOF != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        }
        return count;
    }

    /**
     * 静默关闭
     */
    public static void closeQuietly(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException ex) {
            // NOP
        }
    }
}
