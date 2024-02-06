package net.zhaixing.push.example.model;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import net.zhaixing.push.mail.core.account.MailAccountContainer;
import net.zhaixing.push.mail.core.model.MailAccount;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 邮件账户配置
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-28
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = "zx.message-push.mail")
@Configuration
@Data
public class MailAccountConfigure {
    /**
     * 邮件账户
     */
    private List<MailAccount> account;

    /**
     * 初始化
     */
    @PostConstruct
    public void init() {
        // 将邮件账户放入邮件账户对象容器池中
        account.forEach(account -> {
            MailAccountContainer.ACCOUNT_CONTAINER.put(account.getFlag(), account);
        });
    }
}
