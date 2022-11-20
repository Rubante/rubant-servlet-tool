package com.rubant.io.file;

import com.rubant.io.Constants;

import java.io.*;
import java.nio.charset.Charset;

/**
 * 模仿tail -f命令
 *
 * @author rubant
 * @date 2022/11/13 0:21
 */
public class Tailer implements Runnable {

    /**
     * 延迟毫秒数：1秒
     */
    private static final int DEFAULT_DELAY_MILLIS = 1000;

    /**
     * 只读模式
     */
    private static final String RAF_MODE = "r";

    /**
     * 缓冲区
     */
    private static final int DEFAULT_BUF_SIZE = 1024 * 8;

    /**
     * 默认字符集
     */
    private static final Charset DEFAULT_CHARSET = Charset.defaultCharset();

    /**
     * 文件的顶部缓冲区.
     */
    private final byte[] inbuf;

    /**
     * 被tail的文件.
     */
    private final File file;

    /**
     * 读文件用的字符集
     */
    private final Charset charset;

    /**
     * 等待文件更新的时间.
     */
    private final long delayMillis;

    /**
     * 是从文件开头还是文件结尾tail
     */
    private final boolean end;

    /**
     * tail过程的事件监听器.
     */
    private final TailerListener listener;

    /**
     * 是否在等待更多输入时关闭及重写打开文件.
     */
    private final boolean reOpen;

    /**
     * 只要为true,则tailer将一直运行.
     */
    private volatile boolean run = true;

    /**
     * 用给定的文件创建一个tailer,从文件开头开始,默认延迟是1秒..
     */
    public Tailer(File file, TailerListener listener) {
        this(file, listener, DEFAULT_DELAY_MILLIS);
    }

    /**
     * 用给定的文件创建一个tailer,从文件头开始.
     */
    public Tailer(File file, TailerListener listener, long delayMillis) {
        this(file, listener, delayMillis, false);
    }

    /**
     * 用给定的文件创建一个tailer，可以设定延时时间.
     */
    public Tailer(File file, TailerListener listener, long delayMillis, boolean end) {
        this(file, listener, delayMillis, end, DEFAULT_BUF_SIZE);
    }

    /**
     * 用给定的文件创建一个tailer，可以设定延时时间.
     */
    public Tailer(File file, TailerListener listener, long delayMillis, boolean end, long offset) {
        this(file, listener, delayMillis, end, DEFAULT_BUF_SIZE);
    }

    /**
     * 用给定的文件创建一个tailer,设定延时时间,并指定是否重新打开文件.
     */
    public Tailer(File file, TailerListener listener, long delayMillis, boolean end,
                  boolean reOpen) {
        this(file, listener, delayMillis, end, reOpen, DEFAULT_BUF_SIZE);
    }

    /**
     * 用指定的文件创建tailer, 可以指定缓冲区.
     */
    public Tailer(File file, TailerListener listener, long delayMillis, boolean end,
                  int bufSize) {
        this(file, listener, delayMillis, end, false, bufSize);
    }

    /**
     * 用指定的文件创建tailer, 可以指定缓冲区.
     */
    public Tailer(File file, TailerListener listener, long delayMillis, boolean end,
                  boolean reOpen, int bufSize) {
        this(file, DEFAULT_CHARSET, listener, delayMillis, end, reOpen, bufSize);
    }

    /**
     * 用指定的文件创建tailer, 可以指定缓冲区, 设定字符集
     *
     * @param file        要tail的文件.
     * @param charset        读取文件用到的字符集
     * @param listener    监听器.
     * @param delayMillis 检查文件新内容的时间间隔, 毫秒计.
     * @param end         true的时候从文件末尾tail,false的时候从文件开头tail.
     * @param reOpen      如果为true,在读取文件块之间会关闭并重新打开文件
     * @param bufSize     缓冲区大小
     */
    public Tailer(File file, Charset charset, TailerListener listener, long delayMillis,
                  boolean end, boolean reOpen, int bufSize) {
        this.file = file;
        this.delayMillis = delayMillis;
        this.end = end;

        this.inbuf = new byte[bufSize];

        // 监听器初始化
        this.listener = listener;
        listener.init(this);
        this.reOpen = reOpen;
        this.charset = charset;
    }

    /**
     * 用指定的文件创建并启动一个tailer.
     */
    public static Tailer create(File file, TailerListener listener, long delayMillis,
                                boolean end, int bufSize) {
        return create(file, listener, delayMillis, end, false, bufSize);
    }

    /**
     * 用指定的文件创建并启动一个Tailer.
     */
    public static Tailer create(File file, TailerListener listener, long delayMillis,
                                boolean end, boolean reOpen,
                                int bufSize) {
        return create(file, DEFAULT_CHARSET, listener, delayMillis, end, reOpen, bufSize);
    }

    /**
     * 用指定的文件创建并启动一个tailer.
     */
    public static Tailer create(File file, Charset charset, TailerListener listener,
                                long delayMillis, boolean end, boolean reOpen
            , final int bufSize) {
        final Tailer tailer = new Tailer(file, charset, listener, delayMillis, end, reOpen, bufSize);
        final Thread thread = new Thread(tailer);
        thread.setDaemon(true);
        thread.start();
        return tailer;
    }

    /**
     * 用指定的文件创建并启动一个tailer.
     */
    public static Tailer create(File file, TailerListener listener, long delayMillis,
                                boolean end) {
        return create(file, listener, delayMillis, end, DEFAULT_BUF_SIZE);
    }

    /**
     * 用指定的文件创建并启动一个tailer.
     */
    public static Tailer create(File file, TailerListener listener, long delayMillis,
                                boolean end, boolean reOpen) {
        return create(file, listener, delayMillis, end, reOpen, DEFAULT_BUF_SIZE);
    }

    /**
     * 用指定的文件创建并启动一个tailer.
     */
    public static Tailer create(File file, TailerListener listener, long delayMillis) {
        return create(file, listener, delayMillis, false);
    }

    /**
     * 用指定的文件创建并启动一个tailer.
     */
    public static Tailer create(File file, TailerListener listener) {
        return create(file, listener, DEFAULT_DELAY_MILLIS, false);
    }

    /**
     * 获取tail的文件.
     *
     * @return tail文件
     */
    public File getFile() {
        return file;
    }

    /**
     * 是否正在运行.
     *
     * @return 是否正在运行.
     * @since 2.5
     */
    protected boolean getRun() {
        return run;
    }

    /**
     * 返回延迟的毫秒数.
     *
     * @return 延迟毫秒数.
     */
    public long getDelay() {
        return delayMillis;
    }

    /**
     * 跟踪文件变化，为每一行调用TailerListener的handle方法
     */
    @Override
    public void run() {
        RandomAccessFile reader = null;
        try {
            // 上次检查文件变更的时间
            long last = 0;
            // 文件中的位置
            long position = 0;
            // 打开文件
            while (getRun() && reader == null) {
                try {
                    reader = new RandomAccessFile(file, RAF_MODE);
                } catch (final FileNotFoundException e) {
                    listener.fileNotFound();
                }
                if (reader == null) {
                    Thread.sleep(delayMillis);
                } else {
                    // 文件的当前位置
                    position = end ? file.length() : 0;
                    position = position < 0 ? 0 : position;
                    last = file.lastModified();
                    reader.seek(position);
                }
            }
            while (getRun()) {
                boolean newer = FileUtils.isNewerFile(file, last);
                // 检查文件长度以确定文件是否被轮转
                long length = file.length();
                if (length < position) {
                    // 文件被轮转
                    listener.fileRotated();
                    // 轮转后需要重新被打开
                    try {
                        // 确保关闭旧文件，否则无法重新打开
                        RandomAccessFile save = reader;
                        reader = new RandomAccessFile(file, RAF_MODE);
                        // 旧文件已经被转走，开始旧文件中的新内容
                        try {
                            readLines(save);
                        } catch (IOException ioe) {
                            listener.handle(ioe);
                        }
                        position = 0;
                        // 显示关闭旧文件
                        IOUtils.closeQuietly(save);
                    } catch (final FileNotFoundException e) {
                        // 发生异常继续使用之前的reader和position
                        listener.fileNotFound();
                    }
                    continue;
                } else {
                    // 检查文件是否有新内容
                    if (length > position) {
                        // 文件的内容比上次多了
                        position = readLines(reader);
                        last = file.lastModified();
                    } else if (newer) {
                        /*
                         * 如果文件被截断或者被相同长度的内容覆盖，则需要重置当前位置
                         */
                        position = 0;
                        reader.seek(position);

                        // 读取新的行
                        position = readLines(reader);
                        last = file.lastModified();
                    }
                }
                if (reOpen) {
                    IOUtils.closeQuietly(reader);
                }
                Thread.sleep(delayMillis);
                if (getRun() && reOpen) {
                    reader = new RandomAccessFile(file, RAF_MODE);
                    reader.seek(position);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            stop(e);
        } catch (Exception e) {
            stop(e);
        } finally {
            IOUtils.closeQuietly(reader);
        }
    }

    /**
     * 异常时停止tailer
     *
     * @param e 异常
     */
    private void stop(Exception e) {
        listener.handle(e);
        stop();
    }

    /**
     * 让tailer完成本次循环后返回.
     */
    public void stop() {
        this.run = false;
    }

    /**
     * 读取新行.
     *
     * @param reader 待读取的文件
     * @return 读取后的新位置
     * @throws java.io.IOException IO异常
     */
    private long readLines(RandomAccessFile reader) throws IOException {
        ByteArrayOutputStream lineBuf = new ByteArrayOutputStream(64);
        long pos = reader.getFilePointer();
        // 重新读取的位置
        long rePos = pos;
        int num;
        boolean seenCRLF = false;
        while (getRun() && ((num = reader.read(inbuf)) != Constants.EOF)) {
            for (int i = 0; i < num; i++) {
                final byte ch = inbuf[i];
                switch (ch) {
                    case '\n':
                        // 删掉LF的CR
                        seenCRLF = false;
                        listener.handle(new String(lineBuf.toByteArray(), charset));
                        lineBuf.reset();
                        rePos = pos + i + 1;
                        break;
                    case '\r':
                        if (seenCRLF) {
                            lineBuf.write('\r');
                        }
                        seenCRLF = true;
                        break;
                    default:
                        if (seenCRLF) {
                            // 删掉最后的CR
                            seenCRLF = false;
                            listener.handle(new String(lineBuf.toByteArray(), charset));
                            lineBuf.reset();
                            rePos = pos + i + 1;
                        }
                        lineBuf.write(ch);
                }
            }
            pos = reader.getFilePointer();
        }
        IOUtils.closeQuietly(lineBuf);
        reader.seek(rePos);
        listener.endOfFileReached();

        return rePos;
    }
}
