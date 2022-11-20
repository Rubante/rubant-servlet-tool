package com.rubant.agent.websocket;

/**
 * agent配置
 *
 * @author rubant
 * @date 2022/11/12 18:12
 */
public class AgentConfig {

    /**
     * app标识
     */
    private String appId;

    /**
     * 获取心跳时间
     */
    private Long tickTime;

    /**
     * 服务器端地址
     */
    private String serverURL;

    /**
     * 日志根目录
     */
    private String baseLogDir;

    /**
     * 请求超时时间
     */
    private int timeout;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Long getTickTime() {
        return tickTime == null ? 50000 : tickTime;
    }

    public void setTickTime(Long tickTime) {
        this.tickTime = tickTime;
    }

    public String getServerURL() {
        return serverURL;
    }

    public void setServerURL(String serverURL) {
        this.serverURL = serverURL;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getBaseLogDir() {
        return baseLogDir;
    }

    public void setBaseLogDir(String baseLogDir) {
        this.baseLogDir = baseLogDir;
    }
}
