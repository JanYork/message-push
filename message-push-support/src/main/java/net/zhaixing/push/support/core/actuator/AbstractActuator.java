package net.zhaixing.push.support.core.actuator;

import lombok.Getter;
import net.zhaixing.push.support.core.message.Message;

/**
 * 消息执行器抽象类
 *
 * @param <T> 消息类型
 * @param <S> 客户端类型
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-28
 * @since 1.0.0
 */
@Getter
public abstract class AbstractActuator<T extends Message, S, R> implements Actuator<T, R> {
    /**
     * 客户端
     */
    protected final S client;

    public AbstractActuator(S client) {
        this.client = client;
    }

    /**
     * 执行处理
     *
     * @param message 消息
     * @throws Exception 异常
     */
    @Override
    public abstract R execute(T message) throws Exception;
}
