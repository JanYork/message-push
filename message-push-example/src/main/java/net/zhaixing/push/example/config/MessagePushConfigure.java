package net.zhaixing.push.example.config;

import net.zhaixing.push.mail.core.actuator.MailActuatorPool;
import net.zhaixing.push.mail.core.sender.MailMessageSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息推送配置
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-28
 * @since 1.0.0
 */
@Configuration
public class MessagePushConfigure {
    /**
     * 构建邮件消息发送器
     *
     * @return {@link MailMessageSender}
     */
    @Bean
    public MailMessageSender buildMailMessageSender() {
        // 创建邮件执行器池
        MailActuatorPool pool = new MailActuatorPool();
        // 创建邮件执行器
        return new MailMessageSender(pool);
    }
}
