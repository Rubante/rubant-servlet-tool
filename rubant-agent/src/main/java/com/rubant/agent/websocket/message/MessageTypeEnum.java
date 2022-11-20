package com.rubant.agent.websocket.message;

/**
 * 消息类型
 *
 * @author rubant
 * @date 2022/11/12 18:12
 */
public enum MessageTypeEnum {
    // 心跳消息
    PING_PONG("PING_PONG"),
    // 应答消息
    ACK("ACK"),
    // 错误消息
    ERROR("ERROR");

    // value
    private String value;

    /**
     * 消息类型
     *
     * @param value 类型值，全部大写
     */
    MessageTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    /**
     * 是否相等
     *
     * @param value
     * @return
     */
    public boolean equals(String value) {
        return this.getValue().equals(value);
    }
}
