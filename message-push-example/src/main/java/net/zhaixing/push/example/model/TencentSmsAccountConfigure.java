package net.zhaixing.push.example.model;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import net.zhaixing.push.sms.tencent.core.account.TencentSmsAccountContainer;
import net.zhaixing.push.sms.tencent.core.model.TencentSmsAccount;
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
@ConfigurationProperties(prefix = "zx.message-push.sms.tencent")
@Configuration
@Data
public class TencentSmsAccountConfigure {
    /**
     * 邮件账户
     */
    private List<TencentSmsAccount> account;

    /**
     * 初始化
     */
    @PostConstruct
    public void init() {
        // 将邮件账户放入邮件账户对象容器池中
        account.forEach(account -> TencentSmsAccountContainer.getInstance().put(account.getFlag(), account));
    }
}
