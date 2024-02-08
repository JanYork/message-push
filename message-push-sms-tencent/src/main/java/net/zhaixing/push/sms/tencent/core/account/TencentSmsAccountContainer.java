package net.zhaixing.push.sms.tencent.core.account;

import net.zhaixing.push.sms.tencent.core.model.TencentSmsAccount;
import net.zhaixing.push.support.core.account.InMemoryAccountContainer;

/**
 * 腾讯云短信账户容器
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-02-08
 * @since 1.0.0
 */
public class TencentSmsAccountContainer extends InMemoryAccountContainer<TencentSmsAccount> {
    /**
     * 实例预先创建
     */
    private static final TencentSmsAccountContainer ACCOUNT_CONTAINER = new TencentSmsAccountContainer();

    /**
     * 私有化构造函数，防止外部通过new创建实例
     */
    private TencentSmsAccountContainer() {
    }

    /**
     * 提供一个公共的静态方法返回实例
     *
     * @return {@link TencentSmsAccountContainer}
     */
    public static TencentSmsAccountContainer getInstance() {
        return ACCOUNT_CONTAINER;
    }
}
