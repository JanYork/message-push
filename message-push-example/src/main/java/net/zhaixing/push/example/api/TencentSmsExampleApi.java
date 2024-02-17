package net.zhaixing.push.example.api;

import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import jakarta.annotation.Resource;
import net.zhaixing.push.sms.tencent.core.message.TencentSmsMessage;
import net.zhaixing.push.sms.tencent.core.sender.TencentSmsMessageSender;
import net.zhaixing.push.support.core.message.Message;
import net.zhaixing.push.support.core.result.PushResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/example/")
public class TencentSmsExampleApi {
    @Resource
    private TencentSmsMessageSender tencentSmsMessageSender;


    @RequestMapping("tencent/sms")
    public void sendSms() {
        Message message = new TencentSmsMessage.TencentSmsMessageBuilder()
                .appId("1400889118")
                .signName("择星科技")
                .templateId("1986896")
                .templateParamSet(new String[]{"123456"})
                .phoneNumberSet(new String[]{"16670880818"})
                .actuatorFlag("sms-1001")
                .build();
        PushResult<SendSmsResponse> result = tencentSmsMessageSender.send(message);
        // 处理结果
        System.out.println(result);
    }
}
