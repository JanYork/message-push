package net.zhaixing.push.support.core.account;

/**
 * 消息账户容器接口
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-28
 * @since 1.0.0
 */
public interface AccountContainer<K, V extends MessageAccount> {
    /**
     * 添加账户
     *
     * @param key     账户标识
     * @param account 账户
     */
    void put(K key, V account);

    /**
     * 获取账户
     *
     * @param key 账户标识
     * @return {@link V}    账户
     */
    V take(K key);

    /**
     * 移除账户
     *
     * @param key 账户标识
     */
    void remove(K key);
}
