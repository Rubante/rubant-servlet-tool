package com.rubant.io.cmd;

/**
 * tail 命令
 *
 * @author rubant
 * @date 2022/11/12 20:22
 */
public class TailCmd extends Cmd {

    /**
     * 查看的问题
     */
    private String file;

    /**
     * 命令项
     */
    private String options;

    public TailCmd() {
    }

    /**
     * 查看的文件
     */
    public TailCmd(String file) {
        this.setFile(file);
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getOptions() {
        return options == null ? "" : options;
    }

    public void setOptions(String options) {
        this.options = options;
    }
}
