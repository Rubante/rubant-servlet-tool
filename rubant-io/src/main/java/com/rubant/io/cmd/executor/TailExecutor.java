package com.rubant.io.cmd.executor;

import com.rubant.io.cmd.TailCmd;
import com.rubant.io.file.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * 执行tail命令
 *
 * @author rubant
 * @date 2022/11/13 14:05
 */
public class TailExecutor implements Executor<TailCmd> {
    private static final Logger logger = LoggerFactory.getLogger(TailExecutor.class);

    @Override
    public String execute(TailCmd cmd) {

        if (cmd.getFile() == null || cmd.getFile().length() == 0) {
            throw new RuntimeException("file must not be null!");
        }

        String cmdFirst = String.format("tail %s %s", cmd.getOptions(), cmd.getFile());
        String[] cmdLine = new String[]{"sh", "-c", cmdFirst};

        Runtime runtime = Runtime.getRuntime();
        InputStream inputStream = null;
        try {
            Process process = runtime.exec(cmdLine);
            inputStream = process.getInputStream();
            return IOUtils.toString(inputStream, getCharset(cmd));
        } catch (IOException e) {
            logger.error("cmd error!" + cmd, e);
            return "request error！";
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }
}
