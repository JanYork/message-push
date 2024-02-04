package net.zhaixing.push.support.core.account;

/**
 * 消息账户实体接口
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-28
 * @since 1.0.0
 */
public interface MessageAccount {
    /**
     * 获取账户标识
     *
     * @return {@link String}
     */
    String getFlag();

    /**
     * 验证账户信息
     */
    default void validate() {
    }
}
