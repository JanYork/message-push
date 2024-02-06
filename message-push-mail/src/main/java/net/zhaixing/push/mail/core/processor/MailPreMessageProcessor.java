package net.zhaixing.push.mail.core.processor;

import net.zhaixing.push.mail.core.message.MailMessage;
import net.zhaixing.push.support.core.sender.MessageProcessor;

public class MailPreMessageProcessor implements MessageProcessor<MailMessage> {
    /**
     * 处理消息
     *
     * @param message 消息
     */
    @Override
    public void process(MailMessage message) {
       // 前置处理器
    }
}
