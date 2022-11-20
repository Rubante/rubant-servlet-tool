package com.rubant.io.file;

import java.nio.file.Paths;

/**
 * 请描述功能
 *
 * @author rubant
 * @date 2022/11/13 12:07
 */
public class TailerTest {

    public static void main(String[] args) {
        Tailer tailer = Tailer.create(Paths.get("D:\\export\\Logs\\edi\\wes-edi.log").toFile(), new TailerListener() {
            @Override
            public void handle(String line) {
                System.out.println(line);
            }
        }, 1000, true);

        try {
            Thread.sleep(100000);
        } catch (Exception ex) {
            ex.printStackTrace();
            ;
        }
    }
}
