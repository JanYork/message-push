package net.zhaixing.push.example.api;

import jakarta.annotation.Resource;
import net.zhaixing.push.mail.core.enums.MailType;
import net.zhaixing.push.mail.core.message.MailMessage;
import net.zhaixing.push.mail.core.sender.MailMessageSender;
import org.apache.commons.mail.EmailAttachment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 邮件API测试
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-02-06
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/example/")
public class MailExampleApi {
    @Resource
    private MailMessageSender mailMessageSender;

    /**
     * 纯文本邮件
     */
    @RequestMapping("mail/text")
    public void testSendText() {
        // 纯文本邮件
        MailMessage message = new MailMessage.EmailMessageBuilder()
                .message("你好，世界！Hello World！")
                .subject("测试：纯文本邮件")
                .to("747945307@qq.com")
                .actuatorFlag("mail-1001")
                .mailType(MailType.SIMPLE)
                .build();
        mailMessageSender.send(message);
    }

    /**
     * 带附件的纯文本邮件
     *
     * @throws MalformedURLException 异常
     */
    @RequestMapping("mail/textAttachment")
    public void testSendTextWithAttachment() throws MalformedURLException {
        // 纯文本邮件 + 附件
        List<EmailAttachment> attachments = new ArrayList<>();
        attachments.add(createEmailAttachment());

        MailMessage message = new MailMessage.EmailMessageBuilder()
                .message("你好，世界！Hello World！")
                .subject("测试：纯文本并携带附件邮件")
                .to("747945307@qq.com")
                .actuatorFlag("mail-1001")
                .attachments(attachments)
                .mailType(MailType.MULTI_PART)
                .build();
        mailMessageSender.send(message);
    }

    /**
     * HTML邮件
     */
    @RequestMapping("mail/html")
    public void testSendHtml() {
        // HTML邮件
        MailMessage message = new MailMessage.EmailMessageBuilder()
                .message("<html><body><h1>你好，世界！Hello World！</h1></body></html>")
                .subject("测试：HTML内容邮件")
                .to("747945307@qq.com")
                .actuatorFlag("mail-1001")
                .mailType(MailType.HTML)
                .build();
        mailMessageSender.send(message);
    }

    /**
     * 带附件的HTML邮件
     *
     * @throws MalformedURLException 错误
     */
    @RequestMapping("mail/htmlAttachment")
    public void testSendHtmlWithAttachment() throws MalformedURLException {
        // HTML邮件 + 附件
        List<EmailAttachment> attachments = new ArrayList<>();
        attachments.add(createEmailAttachment());

        MailMessage message = new MailMessage.EmailMessageBuilder()
                .message("<html><body><h1>你好，世界！Hello World！</h1></body></html>")
                .subject("测试：HTML内容并携带附件邮件")
                .to("747945307@qq.com")
                .actuatorFlag("mail-1001")
                .attachments(attachments)
                .mailType(MailType.HTML)
                .build();
        mailMessageSender.send(message);
    }

    /**
     * 嵌入图片并携带附件的HTML邮件
     *
     * @throws MalformedURLException 异常
     */
    @RequestMapping("mail/htmlAttachmentImage")
    public void testSendHtmlWithAttachmentAndImage() throws MalformedURLException {
        // HTML邮件 + 附件 + 嵌入图片
        List<EmailAttachment> attachments = new ArrayList<>();
        attachments.add(createEmailAttachment());

        MailMessage message = new MailMessage.EmailMessageBuilder()
                .message("<html><body><h1>你好，世界！Hello World！</h1>"
                        + "<img src='https://oss.zhaixing.net/JanYork/202401291437591.png'></body></html>")
                .subject("测试：HTML内容并携带附件同时携带嵌入图片邮件")
                .to("747945307@qq.com")
                .actuatorFlag("mail-1001")
                .attachments(attachments)
                .mailType(MailType.IMAGE_HTML)
                .build();
        mailMessageSender.send(message);
    }

    /**
     * 存在图片外链的HTML邮件
     */
    @RequestMapping("mail/htmlImageUrl")
    public void testSendHtmlWithImageUrl() {
        // HTML邮件 + 图片
        MailMessage message = new MailMessage.EmailMessageBuilder()
                .message("<html><body><h1>你好，世界！Hello World！</h1>"
                        + "<img src='https://oss.zhaixing.net/JanYork/202401291437591.png'></body></html>")
                .subject("subject: 测试：HTML内容并存在图片外链邮件")
                .to("747945307@qq.com")
                .actuatorFlag("mail-1001")
                .mailType(MailType.HTML)
                .build();
        mailMessageSender.send(message);
    }

    /**
     * 自动将HTML中的图片转化为嵌入图片的HTML邮件
     *
     * @throws IOException 异常
     */
    @RequestMapping("mail/htmlImage")
    public void testSendHtmlWithImage() throws IOException {
        // HTML邮件 + 嵌入图片
        MailMessage message = new MailMessage.EmailMessageBuilder()
                .message("<html><body><h1>你好，世界！Hello World！</h1>"
                        + "<img src='https://oss.zhaixing.net/JanYork/202401291437591.png'></body></html>")
                .subject("subject: 测试：HTML内容并转化其中外链为嵌入图邮件")
                .to("747945307@qq.com")
                .actuatorFlag("mail-1001")
                .imageBaseUrl(new URL("https://oss.zhaixing.net"))
                .mailType(MailType.IMAGE_HTML)
                .build();
        mailMessageSender.send(message);
    }

    private EmailAttachment createEmailAttachment() throws MalformedURLException {
        EmailAttachment attachment = new EmailAttachment();
//        attachment.setPath("path/to/attachment"); // 替换为附件的实际路径
        // 或者使用URL
        attachment.setURL(new URL("https://oss.zhaixing.net/JanYork/202401291437591.png"));
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription("Attachment Description");
        attachment.setName("logo.png");
        return attachment;
    }
}
