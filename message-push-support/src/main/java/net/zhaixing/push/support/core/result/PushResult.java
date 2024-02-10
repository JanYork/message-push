package net.zhaixing.push.support.core.result;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 推送结果
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-02-10
 * @since 1.0.0
 */
@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PushResult<R> {
    private final boolean success;
    private final R result;
    private final String message;

    /**
     * 成功时的工厂方法
     *
     * @param result the result
     * @return {@link PushResult}<{@link R}>
     */
    public static <R> PushResult<R> success(R result) {
        return new PushResult<>(true, result, null);
    }

    /**
     * 错误时的工厂方法
     *
     * @param message 消息
     * @return {@link PushResult}<{@link R}>
     */
    public static <R> PushResult<R> failure(String message) {
        return new PushResult<>(false, null, message);
    }

    /**
     * 获取结果数据，调用时应检查success确保安全访问
     *
     * @return {@link R}
     */
    public R getResult() {
        if (!success) {
            throw new IllegalStateException("Attempted to get result from a failed operation");
        }
        return result;
    }
}
