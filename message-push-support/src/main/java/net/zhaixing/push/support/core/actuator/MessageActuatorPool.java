package net.zhaixing.push.support.core.actuator;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Cache;

import java.util.concurrent.TimeUnit;

/**
 * 消息执行器池
 *
 * @param <Actuator> 消息执行器类型
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-28
 * @since 1.0.0
 */
public abstract class MessageActuatorPool<Actuator> {
    /**
     * 缓存池
     */
    private final Cache<String, Actuator> cache;

    public MessageActuatorPool() {
        cache = Caffeine.newBuilder()
                .maximumSize(15)
                .expireAfterAccess(12, TimeUnit.HOURS)
                .recordStats()
                .build();
    }

    public MessageActuatorPool(long maxSize, long expireAfterAccessHours) {
        cache = Caffeine.newBuilder()
                .maximumSize(maxSize)
                .expireAfterAccess(expireAfterAccessHours, TimeUnit.HOURS)
                .recordStats()
                .build();
    }


    /**
     * 获取实例方法
     *
     * @param key 缓存key
     * @return {@link Actuator}
     */
    public Actuator getInstance(String key) {
        return cache.getIfPresent(key);
    }

    /**
     * 写入实例方法
     *
     * @param key      缓存key
     * @param instance 实例对象
     */
    public void putInstance(String key, Actuator instance) {
        cache.put(key, instance);
    }

    /**
     * 清除特定缓存项
     *
     * @param key 缓存key
     */
    public void invalidate(String key) {
        cache.invalidate(key);
    }

    /**
     * 清除所有缓存项
     */
    public void invalidateAll() {
        cache.invalidateAll();
    }
}
