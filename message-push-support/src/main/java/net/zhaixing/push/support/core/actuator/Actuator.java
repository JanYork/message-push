package net.zhaixing.push.support.core.actuator;

import net.zhaixing.push.support.core.message.Message;

/**
 * 消息执行器
 *
 * @param <T> 消息类型
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-28
 * @since 1.0.0
 */
public interface Actuator<T extends Message> {
    /**
     * 执行方法
     *
     * @param message 消息
     * @throws Exception 异常
     */
    void execute(T message) throws Exception;
}
