package net.zhaixing.push.mail.core.enums;

/**
 * 邮件类型
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-02-06
 * @since 1.0.0
 */
public enum MailType {
    /**
     * 纯文本
     */
    SIMPLE,
    /**
     * HTML+附件
     */
    HTML,
    /**
     * 自动转换图片外链的HTML+附件
     */
    IMAGE_HTML,
    /**
     * 纯文本+附件
     */
    MULTI_PART
}
