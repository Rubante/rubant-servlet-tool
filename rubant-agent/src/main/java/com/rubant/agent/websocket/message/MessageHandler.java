package com.rubant.agent.websocket.message;

/**
 * message处理器
 *
 * @author rubant
 * @date 2022/11/13 16:13
 */
public interface MessageHandler {

    /**
     * 消息处理
     *
     * @param message
     */
    void handle(String message);
}
