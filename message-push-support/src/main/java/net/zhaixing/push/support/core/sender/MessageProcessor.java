package net.zhaixing.push.support.core.sender;

import net.zhaixing.push.support.core.message.Message;

/**
 * 消息处理器
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-28
 * @since 1.0.0
 */
public interface MessageProcessor<T extends Message> {
    /**
     * 处理消息
     *
     * @param message 消息
     */
    void process(T message);
}
