package com.rubant.io.cmd.executor;

import com.rubant.io.cmd.GrepCmd;
import com.rubant.io.file.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * grep 命令
 *
 * @author rubant
 * @date 2022/11/12 20:25
 */
public class GrepExecutor implements Executor<GrepCmd> {

    private static final Logger logger = LoggerFactory.getLogger(GrepExecutor.class);

    @Override
    public String execute(GrepCmd cmd) {
        String options = cmd.getOptions();

        if (cmd.getContent() == null || cmd.getContent().length() == 0) {
            return "content must not be empty!";
        }
        String cmdFirst = String.format("grep %s %s %s", options, cmd.getContent(), cmd.getFile());

        if ("end".equals(cmd.getMode())) {
            String os = System.getProperty("os.name");
            if (os.toLowerCase().contains("mac")) {
                cmdFirst = String.format("tail -r %s |grep %s %s", cmd.getFile(), options, cmd.getContent());
            } else {
                cmdFirst = String.format("tac %s |grep %s %s", cmd.getFile(), options, cmd.getContent());
            }
        }

        if (cmd.getGrepV() != null && cmd.getGrepV().length() > 0) {
            cmdFirst = cmdFirst + " | grep -v " + cmd.getGrepV();
        }

        String[] cmdLine = new String[]{"sh", "-c", cmdFirst};

        logger.info("start grep : " + cmdLine[0] + " " + cmdLine[1] + " " + cmdLine[2]);

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
