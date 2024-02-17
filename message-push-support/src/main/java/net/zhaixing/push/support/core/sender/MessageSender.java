package net.zhaixing.push.support.core.sender;

import net.zhaixing.push.support.core.message.Message;
import net.zhaixing.push.support.core.result.PushResult;

/**
 * 消息发送器接口
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-27
 * @since 1.0.0
 */
public interface MessageSender<T extends Message, R> {
    /**
     * 发送消息
     *
     * @param message 消息
     */
    PushResult<R> send(Message message);

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
