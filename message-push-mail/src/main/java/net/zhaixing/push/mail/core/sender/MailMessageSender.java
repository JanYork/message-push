package net.zhaixing.push.mail.core.sender;

import net.zhaixing.push.mail.core.actuator.MailActuator;
import net.zhaixing.push.mail.core.actuator.MailActuatorPool;
import net.zhaixing.push.mail.core.factory.ActuatorFactory;
import net.zhaixing.push.mail.core.message.MailMessage;
import net.zhaixing.push.support.core.sender.AbstractMessageSender;
import org.apache.commons.mail.Email;

/**
 * 邮件消息发送器
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-28
 * @since 1.0.0
 */
public class MailMessageSender extends AbstractMessageSender<MailMessage, Void, MailActuator> {
    public MailMessageSender(MailActuatorPool actuatorPool) {
        super(actuatorPool);
    }

    /**
     * 构建执行器
     *
     * @param key 执行器标识
     * @return {@link MailActuator}   执行器
     * @throws Exception 异常
     */
    @Override
    protected MailActuator buildActuator(String key) throws Exception {
        // 邮件对象每一次都需要重构，不必构建其他配置项，丢入工具对象即可
        return new MailActuator(new ActuatorFactory());
    }
}
