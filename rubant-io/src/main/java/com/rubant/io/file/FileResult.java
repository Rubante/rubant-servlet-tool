package com.rubant.io.file;

import com.rubant.io.Constants;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 文件属性：文件名、更新时间、文件大小
 *
 * @author rubant
 * @date 2022/11/12 19:21
 */
public class FileResult {

    /**
     * 文件路径
     */
    private String path;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 文件大小
     */
    private long size;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getHumanSize() {
        int unit = 0;
        double s = size;
        while (s > 1024) {
            s /= 1024;
            unit++;
        }

        String result;
        if (unit != 0) {
            result = BigDecimal.valueOf(s).setScale(2, RoundingMode.CEILING).toString();
        } else {
            result = String.valueOf(size);
        }

        return result + Constants.FILE_SIZE_UNIT[unit];
    }

    @Override
    public String toString() {
        return "FileResult{" +
                "fileName='" + fileName + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", size=" + getHumanSize() +
                '}';
    }
}
