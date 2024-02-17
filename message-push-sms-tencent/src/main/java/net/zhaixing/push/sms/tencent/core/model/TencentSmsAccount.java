package net.zhaixing.push.sms.tencent.core.model;

import lombok.Data;
import net.zhaixing.push.support.core.account.MessageAccount;

/**
 * 腾讯短信账户
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-02-08
 * @since 1.0.0
 */
@Data
public class TencentSmsAccount implements MessageAccount {
    /**
     * 腾讯短信账户
     */
    private String secretId;

    /**
     * 腾讯短信账户密钥
     */
    private String secretKey;

    /**
     * 是否启用代理
     */
    private boolean enabledProxy = false;

    /**
     * 代理
     */
    private Proxy proxy;

    /**
     * 是否自定义配置HTTP
     */
    private boolean enabledHttpConfig = false;

    /**
     * 连接超时时间
     */
    private Integer connTimeout = 60;

    /**
     * 请求方法
     */
    private String reqMethod = "POST";

    /**
     * 短信服务域名
     */
    private String endpoint = "sms.tencentcloudapi.com";

    /**
     * 签名方法
     */
    private String signMethod = "HmacSHA256";

    /**
     * 地域
     */
    private String region = "ap-guangzhou";

    /**
     * 账户标识
     */
    private String flag;

    /**
     * 代理配置
     *
     * @author JanYork
     * @version 1.0.0
     * @date 2024-02-08
     * @since 1.0.0
     */
    @Data
    public static class Proxy {
        /**
         * 代理主机
         */
        private String proxyHost;
        /**
         * 代理端口
         */
        private Integer proxyPort;
    }

    /**
     * 获取账户标识
     *
     * @return {@link String}
     */
    @Override
    public String getFlag() {
        return flag;
    }

    /**
     * 验证账户信息
     */
    @Override
    public void validate() {
        MessageAccount.super.validate();
    }
}
