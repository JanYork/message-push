package net.zhaixing.push.support.core.account;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 消息账户容器抽象类
 *
 * @param <T> 消息账户类型
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-28
 * @since 1.0.0
 */
public abstract class InMemoryAccountContainer<T extends MessageAccount> implements AccountContainer<String, T> {
    /**
     * 账户容器
     */
    private final ConcurrentHashMap<String, T> accounts = new ConcurrentHashMap<>();

    @Override
    public void put(String key, T account) {
        accounts.put(key, account);
    }

    @Override
    public T take(String key) {
        return accounts.get(key);
    }

    @Override
    public void remove(String key) {
        accounts.remove(key);
    }

    public void clear() {
        accounts.clear();
    }

    public int size() {
        return accounts.size();
    }
}
