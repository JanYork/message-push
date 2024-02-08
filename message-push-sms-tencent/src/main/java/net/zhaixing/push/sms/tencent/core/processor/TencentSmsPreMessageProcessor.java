package net.zhaixing.push.sms.tencent.core.processor;

import net.zhaixing.push.sms.tencent.core.message.TencentSmsMessage;
import net.zhaixing.push.support.core.sender.MessageProcessor;

/**
 * 腾讯短信发送消息处理器
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-02-08
 * @since 1.0.0
 */
public class TencentSmsPreMessageProcessor implements MessageProcessor<TencentSmsMessage> {
    /**
     * 处理消息
     *
     * @param message 消息
     */
    @Override
    public void process(TencentSmsMessage message) {
        // 前置处理器
    }
}
