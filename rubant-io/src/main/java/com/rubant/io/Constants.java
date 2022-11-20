package com.rubant.io;

import com.rubant.io.file.FileResult;

import java.util.ArrayList;
import java.util.List;

/**
 * 常用的集合方法
 *
 * @author rubant
 * @date 2022/11/12 18:12
 */
public class Constants {

    /**
     * 空列表
     */
    public static final List<String> EMPTY_STRING_LIST = new ArrayList<>(0);

    /**
     * 空文件列表
     */
    public static final List<FileResult> EMPTY_FILE_RESULT_LIST = new ArrayList<>(0);

    /**
     * 文件大小单位
     */
    public static final String[] FILE_SIZE_UNIT = {"B", "KB", "MB", "GB", "TB", "PB"};

    /**
     * 文件末尾
     */
    public static final int EOF = -1;
}
