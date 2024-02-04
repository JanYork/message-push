package net.zhaixing.push.support.core.sender;

import net.zhaixing.push.support.core.actuator.MessageActuatorPool;
import net.zhaixing.push.support.core.actuator.Actuator;
import net.zhaixing.push.support.core.message.Message;

/**
 * 消息发送器抽象类
 *
 * @param <T> 消息类型
 * @param <A> 执行器类型
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-28
 * @since 1.0.0
 */
public abstract class AbstractMessageSender<T extends Message, A extends Actuator<T>> implements MessageSender<T> {
    /**
     * 前置处理器
     */
    private MessageProcessor<T> preProcessor;

    /**
     * 后置处理器
     */
    private MessageProcessor<T> postProcessor;

    /**
     * 执行器池
     */
    private final MessageActuatorPool<A> actuatorPool;

    public AbstractMessageSender(MessageActuatorPool<A> actuatorPool) {
        this.actuatorPool = actuatorPool;
    }

    /**
     * 获取执行器
     *
     * @param key 执行器标识
     * @return {@link A}    执行器
     * @throws Exception 异常
     */
    protected A takeActuator(String key) throws Exception {
        // 从池中获取执行器
        A actuator = actuatorPool.getInstance(key);
        if (actuator == null) {
            // 构建执行器
            actuator = buildActuator(key);
            // 放入池中
            actuatorPool.putInstance(key, actuator);
        }
        return actuator;
    }

    /**
     * 构建执行器
     *
     * @param key 执行器标识
     * @return {@link A}    执行器
     * @throws Exception 异常
     */
    protected abstract A buildActuator(String key) throws Exception;

    /**
     * 发送消息
     *
     * @param message 消息
     */
    @Override
    public void send(Message message) {
        if (preProcessor != null) {
            preProcessor.process((T) message);
        }
        try {
            A actuator = takeActuator(message.getActuatorFlag());
            actuator.execute((T) message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (postProcessor != null) {
            postProcessor.process((T) message);
        }
    }

    @Override
    public void setPreProcessor(MessageProcessor<T> preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void setPostProcessor(MessageProcessor<T> postProcessor) {
        this.postProcessor = postProcessor;
    }
}
