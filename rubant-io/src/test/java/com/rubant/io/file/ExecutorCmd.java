package com.rubant.io.file;

import com.rubant.io.cmd.GrepCmd;
import com.rubant.io.cmd.TailCmd;
import com.rubant.io.cmd.executor.Executor;
import com.rubant.io.cmd.executor.GrepExecutor;
import com.rubant.io.cmd.executor.TailExecutor;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 请描述功能
 *
 * @author rubant
 * @date 2022/11/12 20:59
 */
public class ExecutorCmd {
    private static Logger logger = LoggerFactory.getLogger(ExecutorCmd.class);

    @Test
    public void testGrep() {
        Executor<GrepCmd> executor = new GrepExecutor();
        GrepCmd cmd = new GrepCmd("dsas");
        String result = executor.execute(cmd);
        logger.info(result);
    }

    @Test
    public void testTail() {
        Executor<TailCmd> executor = new TailExecutor();

        TailCmd tailCmd = new TailCmd("D:\\export\\Logs\\edi\\wes-edi.log");
        String result = executor.execute(tailCmd);
        logger.info(result);
    }
}
