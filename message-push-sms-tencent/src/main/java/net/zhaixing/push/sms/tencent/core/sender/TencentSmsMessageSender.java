package net.zhaixing.push.sms.tencent.core.sender;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import net.zhaixing.push.sms.tencent.core.account.TencentSmsAccountContainer;
import net.zhaixing.push.sms.tencent.core.actuator.TencentSmsActuator;
import net.zhaixing.push.sms.tencent.core.message.TencentSmsMessage;
import net.zhaixing.push.sms.tencent.core.model.TencentSmsAccount;
import net.zhaixing.push.support.core.actuator.MessageActuatorPool;
import net.zhaixing.push.support.core.sender.AbstractMessageSender;
import org.checkerframework.checker.units.qual.A;
import org.jetbrains.annotations.NotNull;

/**
 * 腾讯短信消息发送器
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-02-08
 * @since 1.0.0
 */
public class TencentSmsMessageSender extends AbstractMessageSender<TencentSmsMessage, TencentSmsActuator> {
    public TencentSmsMessageSender(MessageActuatorPool<TencentSmsActuator> actuatorPool) {
        super(actuatorPool);
    }

    /**
     * 构建执行器
     *
     * @param key 执行器标识
     * @return {@link A}    执行器
     * @throws Exception 异常
     */
    @Override
    protected TencentSmsActuator buildActuator(String key) throws Exception {
        // 获取容器
        TencentSmsAccountContainer container = TencentSmsAccountContainer.getInstance();
        // 获取账号
        TencentSmsAccount account = container.take(key);
        // 构建凭证
        Credential cred = new Credential(account.getSecretId(), account.getSecretKey());
        // 获取请求配置
        HttpProfile httpProfile = getHttpProfile(account);
        // 构建客户端配置
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);
        clientProfile.setSignMethod(account.getSignMethod());
        //  构建客户端
        SmsClient client = new SmsClient(cred, account.getRegion(), clientProfile);
        // 返回执行器
        return new TencentSmsActuator(client);
    }

    /**
     * 获取HTTP配置
     *
     * @param account 账户信息
     * @return {@link HttpProfile}
     */
    @NotNull
    private static HttpProfile getHttpProfile(TencentSmsAccount account) {
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint(account.getEndpoint());

        // 如果存在自定义配置HTTP
        if (account.isEnabledHttpConfig()) {
            httpProfile.setConnTimeout(account.getConnTimeout());
            httpProfile.setReqMethod(account.getReqMethod());
        }

        // 如果存在代理
        if (account.isEnabledProxy()) {
            TencentSmsAccount.Proxy proxy = account.getProxy();
            httpProfile.setProxyHost(proxy.getProxyHost());
            httpProfile.setProxyPort(proxy.getProxyPort());
        }
        return httpProfile;
    }
}
