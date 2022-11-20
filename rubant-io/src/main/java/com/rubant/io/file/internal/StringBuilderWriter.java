package com.rubant.io.file.internal;

import java.io.Serializable;
import java.io.Writer;

/**
 * String字符写入
 *
 * @author rubant
 * @date 2022/11/12 20:50
 */
public class StringBuilderWriter extends Writer implements Serializable {

    private final StringBuilder builder;

    /**
     * 默认构造函数
     */
    public StringBuilderWriter() {
        this.builder = new StringBuilder();
    }

    /**
     * 待容量的字符串构造器
     *
     * @param capacity
     */
    public StringBuilderWriter(int capacity) {
        this.builder = new StringBuilder(capacity);
    }

    /**
     * 自定义stringBuilder
     *
     * @param builder
     */
    public StringBuilderWriter(StringBuilder builder) {
        this.builder = builder != null ? builder : new StringBuilder();
    }

    @Override
    public Writer append(char value) {
        builder.append(value);
        return this;
    }

    @Override
    public Writer append(CharSequence value) {
        builder.append(value);
        return this;
    }

    @Override
    public Writer append(CharSequence value, int start, int end) {
        builder.append(value, start, end);
        return this;
    }

    @Override
    public void close() {
        // NOP
    }

    @Override
    public void flush() {
        // NOP
    }

    @Override
    public void write(final String value) {
        if (value != null) {
            builder.append(value);
        }
    }

    /**
     * 写入字符
     *
     * @param value
     * @param offset
     * @param length
     */
    @Override
    public void write(final char[] value, final int offset, final int length) {
        if (value != null) {
            builder.append(value, offset, length);
        }
    }

    public StringBuilder getBuilder() {
        return builder;
    }

    @Override
    public String toString() {
        return builder.toString();
    }
}