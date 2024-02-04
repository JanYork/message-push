package net.zhaixing.push.support.core.sender;

import net.zhaixing.push.support.core.message.Message;

/**
 * 消息发送器接口
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-27
 * @since 1.0.0
 */
public interface MessageSender<T extends Message> {
    /**
     * 发送消息
     *
     * @param message 消息
     */
    void send(Message message);

    /**
     * 设置前置处理器
     *
     * @param preProcessor 前置处理器
     */
    void setPreProcessor(MessageProcessor<T> preProcessor);

    /**
     * 设置后置处理器
     *
     * @param postProcessor 后置处理器
     */
    void setPostProcessor(MessageProcessor<T> postProcessor);
}
