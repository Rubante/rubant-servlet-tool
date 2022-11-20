package com.rubant.agent.websocket.message;

import java.util.ArrayList;
import java.util.List;

/**
 * 注册所有的消息处理器
 *
 * @author rubant
 * @date 2022/11/13 16:17
 */
public class MessageRegister {

    public static final List<MessageHandler> HANDLERS = new ArrayList<>();

    /**
     * 注册处理器
     *
     * @param handler
     */
    public static void register(MessageHandler handler) {
        HANDLERS.add(handler);
    }

    /**
     * 所有处理器
     *
     * @return
     */
    public static List<MessageHandler> getHandlers() {
        return HANDLERS;
    }
}
