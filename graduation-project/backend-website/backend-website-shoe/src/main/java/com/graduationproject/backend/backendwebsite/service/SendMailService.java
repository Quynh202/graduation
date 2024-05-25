package com.graduationproject.backend.backendwebsite.service;

import com.graduationproject.backend.backendwebsite.common.Constant;
import com.graduationproject.backend.backendwebsite.helper.OrderHelper;
import com.graduationproject.backend.backendwebsite.model.MailRequestModel;
import com.graduationproject.backend.backendwebsite.model.MailResponseModel;
import com.graduationproject.backend.backendwebsite.model.OrderJasperModel;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

@Service
public class SendMailService {

  private static final String ORDER_PREFIX = "order";

  private static final String SUFFIX = ".pdf";

  @Autowired
  JavaMailSender javaMailSender;

  @Autowired
  Configuration config;

  @Autowired
  OrderHelper orderHelper;

  /**
   * Send mail.
   *
   * @param mailRequestModel mailRequestModel
   * @param model            model
   * @param orderId          orderId
   * @return mail response
   */
  public MailResponseModel sendMail(MailRequestModel mailRequestModel, Map<String, Object> model,
                                    Long orderId) {
    MailResponseModel response = new MailResponseModel();
    MimeMessage message = javaMailSender.createMimeMessage();

    try {
      // set mediaType
      MimeMessageHelper helper =
          new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
              StandardCharsets.UTF_8.name());
      // add attachment
      OrderJasperModel order = orderHelper.getAllByOrderId(orderId);
      String fileAttachment = ORDER_PREFIX + Constant.UNDERSCORE + order.getOrderCode() + SUFFIX;
      helper.addAttachment(fileAttachment,
          new FileSystemResource("E:\\Graduation\\export-pdf\\" + fileAttachment));

      Template template = config.getTemplate("email-template.ftl");
      String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

      helper.setTo(mailRequestModel.getMailTo());
      helper.setText(html, true);
      helper.setSubject(mailRequestModel.getMailSubject());
      helper.setFrom(mailRequestModel.getMailFrom());
      javaMailSender.send(message);

      response.setMessage("Mail send to : " + mailRequestModel.getMailTo());
      response.setStatus(Boolean.TRUE);

    } catch (IOException | TemplateException | MessagingException e) {
      response.setMessage("Mail Sending failure : " + e.getMessage());
      response.setStatus(Boolean.FALSE);
    }

    return response;
  }

}
