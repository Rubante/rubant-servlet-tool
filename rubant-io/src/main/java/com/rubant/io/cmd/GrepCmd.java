package com.rubant.io.cmd;

/**
 * grep命令
 *
 * @author rubant
 * @date 2022/11/12 20:21
 */
public class GrepCmd extends Cmd {

    /**
     * 待搜索的文件
     */
    private String file;

    /**
     * 待查找的内容
     */
    private String content;

    /**
     * 选项
     */
    private String options;

    /**
     * 查找模式
     */
    private String mode;

    /**
     * grep v 操作
     */
    private String grepV;

    public GrepCmd() {
    }

    /**
     * 搜索的路径
     */
    public GrepCmd(String file) {
        this.setFile(file);
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOptions() {
        return options == null ? "" : options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getGrepV() {
        return grepV;
    }

    public void setGrepV(String grepV) {
        this.grepV = grepV;
    }
}
