package net.zhaixing.push.sms.tencent.core.actuator;

import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import net.zhaixing.push.sms.tencent.core.message.TencentSmsMessage;
import net.zhaixing.push.support.core.actuator.AbstractActuator;

/**
 * 腾讯短信执行器
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-02-08
 * @since 1.0.0
 */
public class TencentSmsActuator extends AbstractActuator<TencentSmsMessage, SmsClient, SendSmsResponse> {
    public TencentSmsActuator(SmsClient client) {
        super(client);
    }

    /**
     * 执行处理
     *
     * @param message 消息
     * @throws Exception 异常
     */
    @Override
    public SendSmsResponse execute(TencentSmsMessage message) throws Exception {
        // 构建请求
        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumberSet(message.getPhoneNumberSet());
        request.setSmsSdkAppId(message.getAppId());
        request.setSignName(message.getSignName());
        request.setTemplateId(message.getTemplateId());
        request.setTemplateParamSet(message.getTemplateParamSet());

        if (message.getExtendCode() != null) {
            request.setSessionContext(message.getExtendCode());
        }

        if (message.getSenderId() != null) {
            request.setSenderId(message.getSenderId());
        }

        if (message.getSessionContext() != null) {
            request.setSessionContext(message.getSessionContext());
        }

        //  发送请求
        return client.SendSms(request);
    }
}
