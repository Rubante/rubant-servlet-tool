package com.rubant.web.log;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.rubant.io.file.Tailer;
import com.rubant.io.file.TailerListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.File;
import java.nio.file.Paths;

/**
 * 日志消息
 *
 * @author rubant
 * @date 2022/11/13 19:54
 */
@Component
@ServerEndpoint("/log/ws")
public class LogWsEndpoint {
    /**
     * 用户会话
     */
    private Session session;

    private Tailer tailer;

    @Autowired
    private LogApiController apiController;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
    }

    @OnClose
    public void onClose() {
        tailer.stop();
    }

    @OnMessage
    public void onMessage(String text, Session session) {
        JSONObject json = JSON.parseObject(text);
        String type = json.getString("type");
        if (type != null && type.equalsIgnoreCase("tailf")) {
            String path = json.getString("path");
            String subPath = json.getString("subPath");
            String filename = json.getString("filename");

            File file = Paths.get(LogBaseController.BASE_LOG_DIR + "/" + path + "/" + subPath + "/" + filename).toFile();
            tailer = Tailer.create(file, new TailerListener() {
                @Override
                public void handle(String line) {
                    try {
                        session.getBasicRemote().sendText(line);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

        }
    }
}
