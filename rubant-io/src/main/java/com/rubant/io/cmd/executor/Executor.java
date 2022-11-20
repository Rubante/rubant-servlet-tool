package com.rubant.io.cmd.executor;

import com.rubant.io.cmd.Cmd;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 命令执行器
 *
 * @author rubant
 * @date 2022/11/12 20:24
 */
public interface Executor<T extends Cmd> {

    /**
     * 执行命令
     *
     * @param cmd 操作系统命令
     * @return
     */
    String execute(T cmd);

    /**
     * 获取字符编码
     *
     * @param cmd
     * @return
     */
    default Charset getCharset(T cmd) {
        return cmd.getCharset() == null ? StandardCharsets.UTF_8 : Charset.forName(cmd.getCharset());
    }
}
