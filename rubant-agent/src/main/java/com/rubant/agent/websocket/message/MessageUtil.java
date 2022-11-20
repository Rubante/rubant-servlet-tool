package com.rubant.agent.websocket.message;

import com.rubant.agent.websocket.AgentClient;

/**
 * 收到服务器的消息
 *
 * @author rubant
 * @date 2022/11/13 15:41
 */
public class MessageUtil {

    public static final AgentMessage.PingPong PING_PONG = new AgentMessage.PingPong();

    /**
     * 收到消息
     *
     * @param message
     */
    public static void receive(String message) {
        for (MessageHandler handler : MessageRegister.getHandlers()) {
            handler.handle(message);
        }
    }

    /**
     * 发送消息
     *
     * @param agentMessage
     */
    public static void send(AgentMessage agentMessage) {
        AgentClient.getInstance().send(agentMessage.toString());
    }

    /**
     * 发送消息
     *
     * @param message
     */
    public static void send(String message) {
        AgentClient.getInstance().send(message);
    }

    /**
     * 心跳
     */
    public static void pingpong() {
        MessageUtil.send(PING_PONG);
    }

    /**
     * 注册处理器器
     *
     * @param handler
     */
    public static void registe(MessageHandler handler) {
        MessageRegister.register(handler);
    }
}
