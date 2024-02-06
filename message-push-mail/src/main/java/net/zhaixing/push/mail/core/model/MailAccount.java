package net.zhaixing.push.mail.core.model;

import lombok.Data;
import net.zhaixing.push.support.core.account.MessageAccount;

import javax.mail.internet.InternetAddress;
import java.util.List;
import java.util.Map;

/**
 * 邮件账户
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-28
 * @since 1.0.0
 */
@Data
public class MailAccount implements MessageAccount {
    /**
     * SMTP服务器地址
     */
    private String host;
    /**
     * SMTP服务器地址
     */
    private int port;
    /**
     * 发件人邮箱地址
     */
    private String from;
    /**
     * 发件人名称
     */
    private String fromName;
    /**
     * 是否启用SSL
     */
    private boolean sslEnable;
    /**
     * 是否启用认证
     */
    private boolean authEnable;
    /**
     * SMTP认证信息
     */
    private Authentication auth;
    /**
     * 套接字配置
     */
    private Socket socket;
    /**
     * 是否启用STARTTLS
     */
    private boolean startTlsEnabled;
    /**
     * 是否强制要求STARTTLS
     */
    private boolean startTlsRequired;
    /**
     * 是否在SMTP之前使用POP3验证
     */
    private boolean popBeforeSmtp;
    /**
     * POP3账户信息
     */
    private PopAccount popAccount;
    /**
     * 是否启用调试模式
     */
    private boolean debug;
    /**
     * 邮件账户标识
     */
    private String flag;

    /**
     * 认证信息
     *
     * @author JanYork
     * @version 1.0.0
     * @date 2024-01-28
     * @since 1.0.0
     */
    @Data
    public static class Authentication {
        private String username;
        private String password;
    }

    /**
     * 套接字配置
     *
     * @author JanYork
     * @version 1.0.0
     * @date 2024-01-28
     * @since 1.0.0
     */
    @Data
    public static class Socket {
        private int timeout;
        private int connectionTimeout;
    }

    /**
     * POP账户信息
     *
     * @author JanYork
     * @version 1.0.0
     * @date 2024-01-28
     * @since 1.0.0
     */
    @Data
    public static class PopAccount {
        private String popHost;
        private String popUsername;
        private String popPassword;
    }

    @Override
    public String getFlag() {
        return flag;
    }

    /**
     * 邮件账户校验
     */
    @Override
    public void validate() {
        MessageAccount.super.validate();
        if (host == null || host.isEmpty()) {
            throw new IllegalArgumentException("host is empty");
        }
        if (port <= 0) {
            throw new IllegalArgumentException("port is invalid");
        }
        if (from == null || from.isEmpty()) {
            throw new IllegalArgumentException("from is empty");
        }
    }
}
