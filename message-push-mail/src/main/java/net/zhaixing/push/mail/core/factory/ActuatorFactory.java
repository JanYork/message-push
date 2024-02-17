package net.zhaixing.push.mail.core.factory;

import net.zhaixing.push.mail.core.account.MailAccountContainer;
import net.zhaixing.push.mail.core.enums.MailType;
import net.zhaixing.push.mail.core.message.MailMessage;
import net.zhaixing.push.mail.core.model.MailAccount;
import org.apache.commons.mail.*;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;

import java.util.List;

/**
 * 邮件工厂类，用于根据不同的邮件类型创建相应的邮件执行器。
 * 支持的邮件类型包括简单邮件、HTML邮件、带图片的HTML邮件和多部分邮件。
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-02-06
 * @since 1.0.0
 */
public class ActuatorFactory {

    /**
     * 根据邮件消息创建相应的邮件执行器。
     *
     * @param message 包含邮件内容和配置的邮件消息对象
     * @return 配置完成的Email对象
     * @throws EmailException 如果在邮件处理过程中发生错误
     */
    public Email createActuator(MailMessage message) throws EmailException {
        MailType type = message.getMailType();
        MailAccount account = MailAccountContainer.getInstance().take(message.getActuatorFlag());

        // 根据邮件类型，创建不同类型的邮件执行器
        Email actuator = switch (type) {
            case SIMPLE -> createSimpleEmail(message);
            case HTML -> createHtmlEmail(message);
            case IMAGE_HTML -> createImageHtmlEmail(message);
            case MULTI_PART -> createMultiPartEmail(message);
        };
        // 配置并返回邮件执行器
        return configureEmail(actuator, account);
    }

    /**
     * 创建并配置一个简单邮件执行器。
     *
     * @param message 邮件消息对象
     * @return 配置好的SimpleEmail对象
     * @throws EmailException 如果邮件创建过程中出现问题
     */
    private SimpleEmail createSimpleEmail(MailMessage message) throws EmailException {
        SimpleEmail email = new SimpleEmail();
        configureBasicEmailDetails(email, message);
        return email;
    }

    /**
     * 创建并配置一个HTML邮件执行器。
     *
     * @param message 邮件消息对象
     * @return 配置好的HtmlEmail对象
     * @throws EmailException 如果邮件创建过程中出现问题
     */
    private HtmlEmail createHtmlEmail(MailMessage message) throws EmailException {
        HtmlEmail email = new HtmlEmail();
        configureHtmlEmailDetails(email, message);
        return email;
    }

    /**
     * 创建并配置一个带图片的HTML邮件执行器。
     *
     * @param message 邮件消息对象
     * @return 配置好的ImageHtmlEmail对象
     * @throws EmailException 如果邮件创建过程中出现问题
     */
    private ImageHtmlEmail createImageHtmlEmail(MailMessage message) throws EmailException {
        ImageHtmlEmail email = new ImageHtmlEmail();
        email.setDataSourceResolver(new DataSourceUrlResolver(message.getImageBaseUrl()));
        configureHtmlEmailDetails(email, message);
        return email;
    }

    /**
     * 创建并配置一个多部分邮件执行器。
     *
     * @param message 邮件消息对象
     * @return 配置好的MultiPartEmail对象
     * @throws EmailException 如果邮件创建过程中出现问题
     */
    private MultiPartEmail createMultiPartEmail(MailMessage message) throws EmailException {
        MultiPartEmail email = new MultiPartEmail();
        configureBasicEmailDetails(email, message);
        addAttachments(email, message.getAttachments());
        return email;
    }

    /**
     * 配置邮件的基本信息，如主题、内容和收件人。
     *
     * @param email 邮件执行器对象
     * @param message 邮件消息对象
     * @throws EmailException 如果配置过程中出现问题
     */
    private void configureBasicEmailDetails(Email email, MailMessage message) throws EmailException {
        email.setSubject(message.getSubject());
        email.setMsg(message.getMessage());
        email.addTo(message.getTo(), message.getToName());
    }

    /**
     * 配置HTML邮件的详细信息，包括基本信息、HTML内容、附件和内嵌图片。
     *
     * @param email HtmlEmail对象
     * @param message 邮件消息对象
     * @throws EmailException 如果配置过程中出现问题
     */
    private void configureHtmlEmailDetails(HtmlEmail email, MailMessage message) throws EmailException {
        configureBasicEmailDetails(email, message);
        email.setHtmlMsg(message.getMessage());
        addAttachments(email, message.getAttachments());
        addInlineImages(email, message);
    }

    /**
     * 向邮件中添加附件。
     *
     * @param email 邮件执行器对象
     * @param attachments 附件列表
     * @throws EmailException 如果添加附件过程中出现问题
     */
    private void addAttachments(MultiPartEmail email, List<EmailAttachment> attachments) throws EmailException {
        if (attachments != null) {
            for (EmailAttachment attachment : attachments) {
                email.attach(attachment);
            }
        }
    }

    /**
     * 向HTML邮件中添加内嵌图片。
     *
     * @param email HtmlEmail对象
     * @param message 邮件消息对象
     * @throws EmailException 如果添加图片过程中出现问题
     */
    private void addInlineImages(HtmlEmail email, MailMessage message) throws EmailException {
        if (message.getHasImage()) {
            List<MailMessage.Image> images = message.getImages();
            // 使用邮件消息中的原始HTML内容
            String html = message.getMessage();
            for (MailMessage.Image image : images) {
                // 将图片资源嵌入邮件中，并获取对应的Content-ID
                String cid = email.embed(image.getImageUrl(), image.getImageName());
                // 替换HTML内容中的占位符为实际的Content-ID
                html = html.replaceAll(image.getPlaceholder(), cid);
            }
            // 设置处理后的HTML内容到邮件中
            email.setHtmlMsg(html);
        }
    }


    /**
     * 配置邮件执行器的账户和连接信息。
     *
     * @param email 邮件执行器对象
     * @param account 邮件账户对象
     * @return 配置完成的Email对象
     * @throws EmailException 如果配置过程中出现问题
     */
    private Email configureEmail(Email email, MailAccount account) throws EmailException {
        email.setHostName(account.getHost());
        email.setSmtpPort(account.getPort());
        email.setAuthenticator(new DefaultAuthenticator(account.getAuth().getUsername(), account.getAuth().getPassword()));
        email.setSSLOnConnect(account.isSslEnable());
        email.setFrom(account.getFrom(), account.getFromName());
        return email;
    }
}
