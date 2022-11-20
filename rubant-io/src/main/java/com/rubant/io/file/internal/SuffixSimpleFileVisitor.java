package com.rubant.io.file.internal;

import com.rubant.io.file.FileResult;
import com.rubant.io.file.FileUtils;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * 根据后缀过滤文件列表
 *
 * @author rubant
 * @date 2022/11/12 18:19
 */
public class SuffixSimpleFileVisitor extends SimpleFileVisitor<Path> {

    /**
     * 后缀
     */
    private String suffix;

    /**
     * 过滤后的文件列表
     */
    private final List<FileResult> result = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
        String filename = path.toString();
        if (suffix == null || filename.endsWith(suffix.toLowerCase()) || filename.endsWith(suffix.toUpperCase())) {
            result.add(FileUtils.getFileResult(path));
        }
        return super.visitFile(path, attrs);
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public List<FileResult> getResult() {
        return result;
    }
}
