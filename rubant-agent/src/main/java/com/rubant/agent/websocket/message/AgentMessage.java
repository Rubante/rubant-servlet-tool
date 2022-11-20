package com.rubant.agent.websocket.message;

/**
 * agent 消息
 *
 * @author rubant
 * @date 2022/11/12 18:12
 */
public class AgentMessage {

    /**
     * app信息
     */
    private String appId;

    /**
     * 消息ID
     */
    private String messageId;

    /**
     * 消息类型
     */
    private String type;

    /**
     * 时间戳
     */
    private String timestamp;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public static class PingPong extends AgentMessage {
        @Override
        public String getType() {
            return MessageTypeEnum.PING_PONG.getValue();
        }

        @Override
        public String toString() {
            return "{\"type\":\"" + getType() + "\"}";
        }
    }
}
