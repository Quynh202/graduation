package com.graduationproject.backend.backendwebsite.helper;

import com.graduationproject.backend.backendwebsite.model.MailRequestModel;
import com.graduationproject.backend.backendwebsite.model.MailResponseModel;
import com.graduationproject.backend.backendwebsite.model.OrderJasperModel;
import com.graduationproject.backend.backendwebsite.service.InvoiceService;
import com.graduationproject.backend.backendwebsite.service.SendMailService;

import java.io.IOException;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class SendMailHelper {

  @Autowired
  SendMailService sendMailService;

  @Autowired
  OrderHelper orderHelper;

  @Autowired
  InvoiceService invoiceService;

  /**
   * Send mail.
   *
   * @param mailRequestModel mailRequestModel
   * @return mail response
   */
  public Boolean sendMail(MailRequestModel mailRequestModel, Long orderId) throws IOException {
    OrderJasperModel orderJasperModel = orderHelper.getAllByOrderId(orderId);

    invoiceService.generateInvoiceFor(orderJasperModel, Locale.forLanguageTag("en"));

    Map<String, Object> model = new HashMap<>();
    model.put("Name", mailRequestModel.getMailName());
    model.put("location", "HaNoi - 2024");
    MailResponseModel response = sendMailService.sendMail(mailRequestModel, model, orderId);

    if (Boolean.FALSE.equals(response.getStatus())) {
      return Boolean.FALSE;
    }
    return Boolean.TRUE;
  }
}
