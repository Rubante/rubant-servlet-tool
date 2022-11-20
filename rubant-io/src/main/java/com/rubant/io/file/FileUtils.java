package com.rubant.io.file;

import com.rubant.io.Constants;
import com.rubant.io.file.internal.SuffixSimpleFileVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 文件目录工具类型
 *
 * @author rubant
 * @date 2022/11/12 17:58
 */
public class FileUtils {
    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    /**
     * 获取当前目录下所有的问题夹
     *
     * @param dir 当前目录
     * @return 当前目录的文件列表
     */
    public static List<FileResult> getDictionarys(String dir) {
        try {
            return Files.list(Paths.get(dir))
                    .filter(path -> path.toFile().isDirectory()).map(FileUtils::getFileResult).collect(Collectors.toList());
        } catch (IOException ex) {
            logger.error("dir:" + dir, ex);
        }
        return Constants.EMPTY_FILE_RESULT_LIST;
    }

    /**
     * 获取当前目录下的文件及目录
     *
     * @param dir 当前目录
     * @return 当前目录的文件列表
     */
    public static List<FileResult> getFiles(String dir) {
        return getFiles(dir, null);
    }

    /**
     * 获取所有带后缀的文件列表
     *
     * @param dir    当前目录
     * @param suffix 后缀名
     * @return 文件列表
     */
    public static List<FileResult> getFiles(String dir, String suffix) {
        try {
            return Files.list(Paths.get(dir))
                    .filter(path -> suffix == null || path.toString().endsWith(suffix)).map(FileUtils::getFileResult).collect(Collectors.toList());
        } catch (IOException ex) {
            logger.error("dir:" + dir + ",suffix:" + suffix, ex);
        }
        return Constants.EMPTY_FILE_RESULT_LIST;
    }

    /**
     * 获取当前目录及子目录的所有文件
     *
     * @param dir 当前目录
     * @return
     */
    public static List<FileResult> getAllFiles(String dir) {
        return getAllFiles(dir, null);
    }

    /**
     * 获取所有文件
     *
     * @param dir    当前目录
     * @param suffix 后缀
     * @return
     */
    public static List<FileResult> getAllFiles(String dir, String suffix) {
        SuffixSimpleFileVisitor visitor = new SuffixSimpleFileVisitor();
        visitor.setSuffix(suffix);
        try {
            Files.walkFileTree(Paths.get(dir), visitor);
            return visitor.getResult();
        } catch (IOException ex) {
            logger.error("dir:" + dir, ex);
        }
        return Constants.EMPTY_FILE_RESULT_LIST;
    }

    /**
     * 获取最后更新时间
     *
     * @param file 文件
     * @return
     */
    public static String getLastModifiedTime(File file) {
        if (file != null && file.exists()) {
            Date date = new Date(file.lastModified());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return dateFormat.format(date);
        }

        return "";
    }

    /**
     * 由path获取文件信息
     *
     * @param path 文件路径
     * @return 文件信息
     */
    public static FileResult getFileResult(Path path) {
        FileResult fileResult = new FileResult();
        File file = path.toFile();
        if (file != null) {
            fileResult.setFileName(file.getName());
        }

        fileResult.setPath(path.toString());
        fileResult.setUpdateTime(getLastModifiedTime(file));
        fileResult.setSize(file.length());
        return fileResult;
    }

    /**
     * 是否是一个新文件
     */
    public static boolean isNewerFile(File file, File reference) {
        if (reference == null) {
            throw new IllegalArgumentException("No specified reference file");
        }
        if (!reference.exists()) {
            throw new IllegalArgumentException("The reference file '"
                    + reference + "' doesn't exist");
        }
        return isNewerFile(file, reference.lastModified());
    }

    /**
     * 通过最后修改时间比较文件新旧
     */
    public static boolean isNewerFile(File file, long timeMillis) {
        if (file == null) {
            throw new IllegalArgumentException("No specified file");
        }
        if (!file.exists()) {
            return false;
        }
        return file.lastModified() > timeMillis;
    }
}
