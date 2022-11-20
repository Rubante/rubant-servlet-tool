package com.rubant.io.file;

/**
 * tail中的监听器
 *
 * @author rubant
 * @date 2022/11/13 0:28
 */
public interface TailerListener {

    /**
     * Tailer在构建过程中调用该方法,使listener能够停止对应的Tailer
     */
    default void init(Tailer tailer) {
    }

    /**
     * 如果没有找到tail的文件, 则调用该方法.
     */
    default void fileNotFound() {
    }

    /**
     * 如果检测到文件轮转则调用.
     * 文件被重新打开前调用该方法, 如果新文件没有创建则调用fileNotFound方法
     */
    default void fileRotated() {
    }

    /**
     * 处理Tailer读取到的行内容.
     */
    default void handle(String line) {
    }

    /**
     * 处理异常
     */
    default void handle(Exception ex) {
    }

    /**
     * 当到达末尾时调用
     */
    default void endOfFileReached() {
    }
}
