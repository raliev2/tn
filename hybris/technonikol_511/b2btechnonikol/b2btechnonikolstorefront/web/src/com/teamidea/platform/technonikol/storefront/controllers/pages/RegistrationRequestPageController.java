package com.teamidea.platform.technonikol.storefront.controllers.pages;

import com.teamidea.platform.technonikol.storefront.controllers.ControllerConstants;
import com.teamidea.platform.technonikol.storefront.controllers.util.GlobalMessages;
import com.teamidea.platform.technonikol.storefront.forms.RegistrationRequestForm;
import de.hybris.platform.acceleratorservices.email.EmailService;
import de.hybris.platform.acceleratorservices.model.email.EmailAddressModel;
import de.hybris.platform.acceleratorservices.model.email.EmailAttachmentModel;
import de.hybris.platform.acceleratorservices.model.email.EmailMessageModel;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

@Controller
@Scope("tenant")
@RequestMapping(value = "/register")
public class RegistrationRequestPageController extends DefaultPageController
{
    protected static final Logger LOG = Logger.getLogger(RegistrationRequestPageController.class);

    @Resource(name="emailService")
    private EmailService emailService;

    @Override
    @RequestMapping(method = RequestMethod.GET)
    public String get(final Model model, final HttpServletRequest request, final HttpServletResponse response) throws CMSItemNotFoundException
    {
        final RegistrationRequestForm form = new RegistrationRequestForm();
        model.addAttribute(form);
        super.get(model, request, response);
        return ControllerConstants.Views.Pages.Form.registrationRequestForm;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String sendRegistrationRequest(@Valid final RegistrationRequestForm form, final BindingResult bindingResult, final Model model,
                                          final HttpServletRequest request, final HttpServletResponse response,
                                          final RedirectAttributes redirectModel) throws CMSItemNotFoundException
    {
        loadPageDataInModel(model, request);
        if (bindingResult.hasErrors()) {
            GlobalMessages.addErrorMessage(model, "form.global.error");
            return ControllerConstants.Views.Pages.Form.registrationRequestForm;
        }
        else {
            sendRegistrationRequestEmailTemporaryVersion(form);
            return ControllerConstants.Views.Pages.Form.registrationRequestFormProcessed;
        }

    }

    private void sendRegistrationRequestEmailTemporaryVersion(RegistrationRequestForm form) {
        // Recipient's email ID needs to be mentioned.
        String to = "erkin.babadjanov@tstn.ru";

        // Sender's email ID needs to be mentioned
        String from = "1plt@tn.ru";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.from", "1plt@tn.ru");
        properties.setProperty("mail.replyto", "1plt@tn.ru");

        properties.setProperty("mail.smtp.host", "mail.evozon.com");
        properties.setProperty("mail.smtp.server", "mail.evozon.com");
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.user", "devhybris@evozon.com");
        properties.setProperty("mail.smtp.password", ".8/Vaekjd9");
        properties.setProperty("mail.use.tls", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try{
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));
            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress("ogorodnikov@teamidea.ru"));
            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress("erkin.babadjanov@tstn.ru"));
            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress("aliev@teamidea.ru"));

            // Set CC: header field of the header.
            message.addRecipient(Message.RecipientType.CC,
                    new InternetAddress("anikanovalex@gmail.com"));

            // Set Subject: header field
            message.setSubject("Заявка на регистрацию");

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Fill the message
            messageBodyPart.setText(createMailBody(form));

            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart );

            // Send message
            Transport transport = session.getTransport("smtp");
            //transport.connect("xmail.teamidea.ru", 25, "rivegauche@teamidea.local", "ZAQ!2wsx");
            transport.connect("mail.evozon.com", 587, "devhybris@evozon.com", ".8/Vaekjd9");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    private String createMailBody(RegistrationRequestForm form) {
        return "Имя: "      + form.getFirstName() + "\n" +
                "Фамилия: "  + form.getLastName() + "\n" +
                "Отчество: " + form.getSurName() + "\n" +
                "Компания: " + form.getCompany() + "\n" +
                "ИНН: "      + form.getInn() + "\n" +
                "Телефон: "  + form.getPhone() + "\n" +
                "Эл.почта: " + form.getEmail() + "\n" +
                "Подписаться на СМС и email рассылки: " + (form.isSubscribedToSmsAndEmailNotification() ? "да" : "нет") ;
    }

    private void sendRegistrationRequestEmail(RegistrationRequestForm form) {
        List<EmailAddressModel> toAddresses = new ArrayList<EmailAddressModel>();
        EmailAddressModel toAddress = emailService.getOrCreateEmailAddressForEmail("anikanovalex@gmail.com", "");
        toAddresses.add(toAddress);

        List<EmailAddressModel> ccAddresses = new ArrayList<EmailAddressModel>();
        EmailAddressModel ccAddress = emailService.getOrCreateEmailAddressForEmail("anikanovalex@gmail.com", "Администрация");
        ccAddresses.add(ccAddress);

        List<EmailAddressModel> bccAddresses = Collections.EMPTY_LIST;
        EmailAddressModel fromAddress = emailService.getOrCreateEmailAddressForEmail("anikanovalex@gmail.com", "");
        String replyToAddress = "anikanovalex@gmail.com";
        String subject = "Запрос на регистрацию";
        String body = "test Email Registration Form";
        List<EmailAttachmentModel> attachments = Collections.EMPTY_LIST;

        EmailMessageModel emailMessage = emailService.createEmailMessage(
                toAddresses, ccAddresses, bccAddresses, fromAddress, replyToAddress,
                subject,
                body,
                attachments
        );
        boolean result = emailService.send(emailMessage);

        LOG.info("The result of sending registration request email: " + result);
    }

    private void loadPageDataInModel(Model model, HttpServletRequest request) {
        final ContentPageModel page = getContentPageForRequest(request);
        storeCmsPageInModel(model, page);
        setUpMetaDataForContentPage(model, page);
    }

}
