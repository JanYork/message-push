package net.zhaixing.push.sms.tencent.core.message;

import lombok.Getter;
import net.zhaixing.push.support.core.message.Message;

/**
 * 腾讯短信消息
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-02-08
 * @since 1.0.0
 */
@Getter
public class TencentSmsMessage extends Message {
    /**
     * 短信应用ID
     */
    private final String appId;
    /**
     * 签名名称
     */
    private final String signName;
    /**
     * 模板ID
     */
    private final String templateId;
    /**
     * 模板参数
     */
    private final String[] templateParamSet;
    /**
     * 手机号码
     */
    private final String[] phoneNumberSet;
    /**
     * 会话上下文
     */
    private final String sessionContext;
    /**
     * 扩展码
     */
    private final String extendCode;
    /**
     * 发送方ID
     */
    private final String senderId;

    /**
     * 构造函数
     *
     * @param builder 构建器
     */
    protected TencentSmsMessage(TencentSmsMessageBuilder builder) {
        super(builder);
        this.appId = builder.appId;
        this.signName = builder.signName;
        this.templateId = builder.templateId;
        this.templateParamSet = builder.templateParamSet;
        this.phoneNumberSet = builder.phoneNumberSet;
        this.sessionContext = builder.sessionContext;
        this.extendCode = builder.extendCode;
        this.senderId = builder.senderId;
    }

    /**
     * 腾讯短信消息构建器
     *
     * @author JanYork
     * @version 1.0.0
     * @date 2024-02-08
     * @since 1.0.0
     */
    public static class TencentSmsMessageBuilder extends Builder<TencentSmsMessageBuilder> {
        /**
         * 短信应用ID
         */
        private String appId;

        /**
         * 短信签名
         */
        private String signName;

        /**
         * 模板ID
         */
        private String templateId;

        /**
         * 模板参数
         */
        private String[] templateParamSet;

        /**
         * 手机号码
         */
        private String[] phoneNumberSet;

        /**
         * 会话上下文
         */
        private String sessionContext;

        /**
         * 扩展码
         */
        private String extendCode;

        /**
         * 发送方ID
         */
        private String senderId;

        /**
         * 设置短信应用ID
         *
         * @param appId 短信应用ID
         * @return {@link TencentSmsMessageBuilder}
         */
        public TencentSmsMessageBuilder appId(String appId) {
            this.appId = appId;
            return this;
        }

        /**
         * 设置短信签名
         *
         * @param signName 短信签名
         * @return {@link TencentSmsMessageBuilder}
         */
        public TencentSmsMessageBuilder signName(String signName) {
            this.signName = signName;
            return this;
        }

        /**
         * 设置模板ID
         *
         * @param templateId 模板ID
         * @return {@link TencentSmsMessageBuilder}
         */
        public TencentSmsMessageBuilder templateId(String templateId) {
            this.templateId = templateId;
            return this;
        }

        /**
         * 设置模板参数
         *
         * @param templateParamSet 模板参数
         * @return {@link TencentSmsMessageBuilder}
         */
        public TencentSmsMessageBuilder templateParamSet(String[] templateParamSet) {
            this.templateParamSet = templateParamSet;
            return this;
        }

        /**
         * 设置手机号码
         *
         * @param phoneNumberSet 手机号码
         * @return {@link TencentSmsMessageBuilder}
         */
        public TencentSmsMessageBuilder phoneNumberSet(String[] phoneNumberSet) {
            this.phoneNumberSet = phoneNumberSet;
            return this;
        }

        /**
         * 设置会话上下文
         *
         * @param sessionContext 会话上下文
         * @return {@link TencentSmsMessageBuilder}
         */
        public TencentSmsMessageBuilder sessionContext(String sessionContext) {
            this.sessionContext = sessionContext;
            return this;
        }

        /**
         * 设置扩展码
         *
         * @param extendCode 扩展码
         * @return {@link TencentSmsMessageBuilder}
         */
        public TencentSmsMessageBuilder extendCode(String extendCode) {
            this.extendCode = extendCode;
            return this;
        }

        /**
         * 设置国际短信发送方ID
         *
         * @param senderId 发送方ID
         * @return {@link TencentSmsMessageBuilder}
         */
        public TencentSmsMessageBuilder senderId(String senderId) {
            this.senderId = senderId;
            return this;
        }

        /**
         * 提供具体的构建方法
         *
         * @return {@link Message}  消息对象
         */
        @Override
        public Message build() {
            validate();
            return new TencentSmsMessage(this);
        }

        /**
         * 强制子类实现此方法，以返回具体的builder类型
         *
         * @return {@link TencentSmsMessageBuilder}
         */
        @Override
        protected TencentSmsMessageBuilder self() {
            return this;
        }

        @Override
        protected void validate() {
            super.validate();
            // 可以添加更多的校验逻辑
        }
    }
}
