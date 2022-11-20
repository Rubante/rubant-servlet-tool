package com.rubant.io.cmd;

/**
 * linux命令行的一个抽象
 *
 * @author rubant
 * @date 2022/11/12 20:21
 */
public abstract class Cmd {

    /**
     * 字符编码
     */
    private String charset;

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }
}
