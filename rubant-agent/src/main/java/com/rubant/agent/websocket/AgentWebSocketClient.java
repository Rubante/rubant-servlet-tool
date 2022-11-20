package com.rubant.agent.websocket;

import com.rubant.agent.websocket.message.MessageUtil;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

/**
 * webSocket client端，每个端只保持一个，并确保断开后能够重连
 *
 * @author rubant
 * @date 2022/11/12 18:12
 */
public class AgentWebSocketClient extends WebSocketClient {

    private static final Logger logger = LoggerFactory.getLogger(AgentWebSocketClient.class);

    /**
     * 是否连接
     */
    private volatile boolean isConnected = false;

    /**
     * 有参构造函数
     *
     * @param serverUri
     */
    public AgentWebSocketClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        this.isConnected = true;
        AgentClient.setProxyWebSocketClient(this);
        WebSocketMonitor.pingPong();
        logger.info("websocket connected server!");
    }

    @Override
    public void onMessage(String message) {
        MessageUtil.receive(message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        logger.error("connection closed {}", reason);
        this.isConnected = false;

        // 重新连接
        WebSocketMonitor.reconnect();
    }

    @Override
    public void onError(Exception ex) {
        logger.error("client error!", ex);
    }

    /**
     * 是否已经建立连接
     *
     * @return
     */
    public boolean isConnected() {
        return isConnected;
    }
}
