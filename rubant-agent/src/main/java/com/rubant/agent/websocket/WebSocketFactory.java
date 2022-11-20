package com.rubant.agent.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

/**
 * 创建websocket
 *
 * @author rubant
 * @date 2022/11/12 18:12
 */
public class WebSocketFactory {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketFactory.class);

    /**
     * 构建socket client
     */
    public static void buildSocketClient() {
        try {
            String serverURL = AgentClient.getConfig().getServerURL();
            if (serverURL.contains("&")) {
                serverURL += "&appId=" + AgentClient.getConfig().getAppId();
            } else {
                serverURL += "?appId=" + AgentClient.getConfig().getAppId();
            }

            AgentWebSocketClient client = new AgentWebSocketClient(new URI(serverURL));
            client.setTcpNoDelay(true);
            client.connectBlocking();
        } catch (Exception ex) {
            logger.error("websocket client build error", ex);
            throw new RuntimeException(ex);
        }
    }
}
