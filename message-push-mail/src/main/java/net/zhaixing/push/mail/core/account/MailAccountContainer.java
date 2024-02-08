package net.zhaixing.push.mail.core.account;

import net.zhaixing.push.mail.core.model.MailAccount;
import net.zhaixing.push.support.core.account.InMemoryAccountContainer;

/**
 * 邮件账户容器
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-28
 * @since 1.0.0
 */
public class MailAccountContainer extends InMemoryAccountContainer<MailAccount> {
    /**
     * 实例预先创建
     */
    private static final MailAccountContainer ACCOUNT_CONTAINER = new MailAccountContainer();

    /**
     * 私有化构造函数，防止外部通过new创建实例
     */
    private MailAccountContainer() {
    }

    /**
     * 提供一个公共的静态方法返回实例
     *
     * @return {@link MailAccountContainer}
     */
    public static MailAccountContainer getInstance() {
        return ACCOUNT_CONTAINER;
    }
}
