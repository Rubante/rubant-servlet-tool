package com.rubant.agent.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * webSocket client的抓手
 *
 * @author rubant
 * @date 2022/7/4 13:38
 */
public class AgentClient {
    private static final Logger logger = LoggerFactory.getLogger(AgentClient.class);

    /**
     * 一个应用只存在一个socket，自动关闭时需要重新连接
     */
    private static AgentWebSocketClient INSTANCE;

    private static AgentConfig config;

    /**
     * 读写锁，保证单例状态
     */
    private static ReadWriteLock lock = new ReentrantReadWriteLock();

    /**
     * agent启动
     */
    public static void start() {
        config = new AgentConfig();

        Properties properties = new Properties();
        try {
            InputStream is = AgentClient.class.getResourceAsStream("/agent.properties");
            if (is == null) {
                throw new RuntimeException("agent.properties not exists!");
            }
            properties.load(is);
        } catch (IOException ex) {
            logger.error("agent.properties load error!", ex);
            throw new RuntimeException("agent config error!");
        } catch (Exception ex) {
            logger.error("agent.properties config error!", ex);
            throw new RuntimeException("agent config error!");
        }

        config.setAppId(properties.getProperty("appId"));
        config.setServerURL(properties.getProperty("serverURL"));
        config.setBaseLogDir(properties.getProperty("baseLogDir"));
        String tickTime = properties.getProperty("tickTime");
        if (tickTime != null) {
            config.setTickTime(Long.parseLong(tickTime));
        }
        String timeout = properties.getProperty("timeout");
        if (timeout != null) {
            config.setTimeout(Integer.parseInt(timeout));
        }
        String serverURL = properties.getProperty("serverURL");
        if (serverURL != null) {
            config.setServerURL(serverURL);
        } else {
            throw new RuntimeException("serverURL must not be null");
        }

        // 创建websocket
        WebSocketFactory.buildSocketClient();
    }

    /**
     * 设置socketClient
     *
     * @param agentWebSocketClient
     */
    public static void setProxyWebSocketClient(AgentWebSocketClient agentWebSocketClient) {
        lock.writeLock().lock();
        try {
            // 若存在未关闭的，将关闭原有的连接
            if (AgentClient.INSTANCE != null) {
                AgentClient.INSTANCE.close();
            }
            AgentClient.INSTANCE = agentWebSocketClient;
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * 获取配置
     *
     * @return
     */
    public static AgentConfig getConfig() {
        return AgentClient.config;
    }

    /**
     * 获取socketClient
     *
     * @return
     */
    public static AgentWebSocketClient getInstance() {
        lock.readLock().lock();
        try {
            return AgentClient.INSTANCE;
        } finally {
            lock.readLock().unlock();
        }
    }
}
