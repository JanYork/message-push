package net.zhaixing.push.mail.core.actuator;

import net.zhaixing.push.mail.core.factory.ActuatorFactory;
import net.zhaixing.push.mail.core.message.MailMessage;
import net.zhaixing.push.mail.core.processor.MailPostMessageProcessor;
import net.zhaixing.push.mail.core.processor.MailPreMessageProcessor;
import net.zhaixing.push.support.core.actuator.AbstractActuator;
import org.apache.commons.mail.Email;

/**
 * 邮件执行器
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-28
 * @since 1.0.0
 */
public class MailActuator extends AbstractActuator<MailMessage, ActuatorFactory, Void> {
    public MailActuator(ActuatorFactory client) {
        super(client);
    }

    /**
     * 执行处理
     *
     * @param message 消息
     * @throws Exception 异常
     */
    @Override
    public Void execute(MailMessage message) throws Exception {
        Email mail = client.createActuator(message);
        MailPreMessageProcessor preProcessor = new MailPreMessageProcessor();
        preProcessor.process(message);
        mail.send();
        MailPostMessageProcessor postProcessor = new MailPostMessageProcessor();
        postProcessor.process(message);
        return null;
    }
}
