package net.zhaixing.push.mail.core.account;

import net.zhaixing.push.mail.core.model.MailAccount;
import net.zhaixing.push.support.core.account.InMemoryAccountContainer;

/**
 * 邮件账户容器
 * <p>
 *  TODO：需要进行单例模式改造
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-28
 * @since 1.0.0
 */
public class MailAccountContainer extends InMemoryAccountContainer<MailAccount> {
    public static final MailAccountContainer ACCOUNT_CONTAINER = new MailAccountContainer();
}
