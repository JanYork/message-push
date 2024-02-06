package net.zhaixing.push.mail.core.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import net.zhaixing.push.mail.core.enums.MailType;
import net.zhaixing.push.support.core.message.Message;
import org.apache.commons.mail.EmailAttachment;
import org.checkerframework.checker.units.qual.N;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.List;

/**
 * 邮件消息 TODO：需要去掉部分无需使用的字段
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-28
 * @since 1.0.0
 */
@Getter
public class MailMessage extends Message {
    /**
     * 主题
     */
    private final String subject;
    /**
     * 消息
     */
    private final String message;
    /**
     * 收件人
     */
    private final String to;

    /**
     * 收件人名称
     */
    private final String toName;

    /**
     * 附件
     */
    private final List<EmailAttachment> attachments;

    /**
     * 图片嵌入文件
     */
    private final List<Image> images;

    /**
     * 图片基地址
     */
    private final URL imageBaseUrl;

    /**
     * 是否是html
     */
    private final Boolean isHtml;

    /**
     * 是否包含附件
     */
    private final Boolean hasAttachment;

    /**
     * 是否包含图片
     */
    private final Boolean hasImage;

    /**
     * 邮件类型
     */
    private final MailType mailType;


    @Data
    @lombok.Builder
    @AllArgsConstructor
    public static class Image {
        /**
         * 占位字符串
         */
        String placeholder;
        /**
         * 文件
         */
        URL imageUrl;
        /**
         * 图片名称
         */
        String imageName;
    }

    /**
     * 构造函数
     *
     * @param builder 构建器
     */
    protected MailMessage(EmailMessageBuilder builder) {
        super(builder);
        this.subject = builder.subject;
        this.message = builder.message;
        this.to = builder.to;
        this.attachments = builder.attachments;
        this.images = builder.images;
        this.isHtml = builder.isHtml;
        this.hasAttachment = builder.hasAttachment;
        this.hasImage = builder.hasImage;
        this.mailType = builder.mailType;
        this.toName = builder.toName;
        this.imageBaseUrl = builder.imageBaseUrl;
    }

    /**
     * 消息构建器
     *
     * @author JanYork
     * @version 1.0.0
     * @date 2024-01-28
     * @since 1.0.0
     */
    public static class EmailMessageBuilder extends Builder<EmailMessageBuilder> {
        private String subject;
        private String message;
        private String to;
        private String toName;
        private URL imageBaseUrl;
        private Boolean isHtml = false;
        private Boolean hasAttachment = false;
        private Boolean hasImage = false;
        private List<EmailAttachment> attachments;
        private List<Image> images;
        private MailType mailType;

        public EmailMessageBuilder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public EmailMessageBuilder message(String message) {
            this.message = message;
            return this;
        }

        public EmailMessageBuilder to(String to) {
            this.to = to;
            return this;
        }

        public EmailMessageBuilder toName(String toName) {
            this.toName = toName;
            return this;
        }

        public EmailMessageBuilder attachments(List<EmailAttachment> attachments) {
            this.attachments = attachments;
            this.hasAttachment = true;
            return this;
        }

        public EmailMessageBuilder images(List<Image> images) {
            this.images = images;
            this.hasImage = true;
            return this;
        }

        public EmailMessageBuilder imageBaseUrl(URL imageBaseUrl) {
            this.imageBaseUrl = imageBaseUrl;
            return this;
        }

        public EmailMessageBuilder isHtml(boolean isHtml) {
            this.isHtml = isHtml;
            return this;
        }

        public EmailMessageBuilder mailType(MailType mailType) {
            this.mailType = mailType;
            return this;
        }

        @Override
        public MailMessage build() {
            validate();
            return new MailMessage(this);
        }

        @Override
        protected EmailMessageBuilder self() {
            return this;
        }

        @Override
        protected void validate() {
            super.validate();
            // 可以添加更多关于 subject 和 body 的验证逻辑
        }
    }
}
