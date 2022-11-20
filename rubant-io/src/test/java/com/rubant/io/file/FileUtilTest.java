package com.rubant.io.file;

import org.junit.Test;

/**
 * 请描述功能
 *
 * @author rubant
 * @date 2022/11/12 18:14
 */
public class FileUtilTest {

    @Test
    public void testGetFiles() {
        System.out.println(FileUtils.getFiles("D:\\"));

        System.out.println(FileUtils.getFiles("D:\\", ".txt"));

        System.out.println(FileUtils.getAllFiles("D:\\文档\\供应链解决方案部", ".docx"));

        System.out.println(FileUtils.getDictionarys("D:\\文档\\供应链解决方案部"));
    }
}
