package com.rubant.agent.websocket;

import com.rubant.agent.websocket.message.MessageUtil;

import java.util.concurrent.*;

/**
 * webSocket监控器
 *
 * @author rubant
 * @date 2022/7/4 18:19
 */
public class WebSocketMonitor {

    /**
     * 独立建立的线程池
     */
    private static final ExecutorService executorService = new ThreadPoolExecutor(1, 1,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(), r -> {
        Thread thread = new Thread(r);
        thread.setName("websocket");
        return thread;
    });

    /**
     * 定时调度
     */
    private static final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    /**
     * 心跳调度器
     */
    private static ScheduledFuture scheduledFuture = null;

    /**
     * 重新创建连接
     */
    public static void reconnect() {
        executorService.submit(WebSocketFactory::buildSocketClient);
    }

    /**
     * 心跳消息
     */
    public static synchronized void pingPong() {
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
        }
        scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(MessageUtil::pingpong, 0, AgentClient.getConfig().getTickTime(), TimeUnit.MILLISECONDS);
    }
}