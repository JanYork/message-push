package net.zhaixing.push.support.core.message;

import lombok.Getter;

/**
 * 消息构建器抽象
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-27
 * @since 1.0.0
 */
@Getter
public abstract class Message {
    /**
     * 执行器标识
     */
    private final String actuatorFlag;

    /**
     * 构造函数
     *
     * @param builder 构建器
     */
    protected Message(Builder<?> builder) {
        this.actuatorFlag = builder.actuatorFlag;
    }

    /**
     * 消息构建器抽象
     *
     * @author JanYork
     * @version 1.0.0
     * @date 2024-01-27
     * @since 1.0.0
     */
    public abstract static class Builder<T extends Builder<T>> {
        /**
         * 执行器标识
         */
        private String actuatorFlag;

        /**
         * 基本属性的设置方法
         *
         * @param actuatorFlag 执行器标识
         * @return {@link T}    具体的builder类型
         */
        public T actuatorFlag(String actuatorFlag) {
            this.actuatorFlag = actuatorFlag;
            return self();
        }

        /**
         * 提供具体的构建方法
         *
         * @return {@link Message}  消息对象
         */
        public abstract Message build();

        /**
         * 强制子类实现此方法，以返回具体的builder类型
         *
         * @return {@link T}    具体的builder类型
         */
        protected abstract T self();

        /**
         * 校验字段内容
         */
        protected void validate() {
            if (actuatorFlag == null || actuatorFlag.trim().isEmpty()) {
                throw new IllegalStateException("Actuator flag must not be null or empty");
            }
        }
    }
}
