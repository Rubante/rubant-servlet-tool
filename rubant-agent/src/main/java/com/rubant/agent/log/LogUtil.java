package com.rubant.agent.log;

import com.rubant.agent.websocket.AgentClient;
import com.rubant.io.file.FileResult;
import com.rubant.io.file.FileUtils;

import java.util.List;

/**
 * 获取宿主相关日志信息
 *
 * @author rubant
 * @date 2022/11/13 16:03
 */
public class LogUtil {

    public List<FileResult> getLogsDir() {
        String baseDir = AgentClient.getConfig().getBaseLogDir();

        return FileUtils.getAllFiles(baseDir);
    }
}
