package com.intellisense.ebookstorev1.ultility;

import com.intellisense.ebookstorev1.store.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;

import java.util.Locale;

@Component
public class MailConstructor {
    @Autowired
    private Environment env;

    @Autowired
    private TemplateEngine templateEngine;

    public SimpleMailMessage constructResetTokenEmail(
            String contextPath, Locale locale, String token, User user, String password
    ) {
        String url = contextPath+"/newUser?token="+token;
        String message = "\nPlease click on this link to verify your email and edit your personal information. Your password is: \n"+password;
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(user.getEmail());
        email.setSubject("DeenBreed's Bookstore - New User Registration");
        email.setText(url+message);
        email.setFrom(env.getProperty("support.email"));
        return email;
    }

//    public MimeMessagePreparator constructOrderConfirmationEmail(User user, Order order, Locale locale) {
//        org.thymeleaf.context.Context context = new org.thymeleaf.context.Context();
//        context.setVariable("order", order);
//        context.setVariable("user", user);
//        context.setVariable("cartItemList", order.getCartItemList());
//        String text = templateEngine.process("orderConfirmationEmailTemplate", context);
//
//        return new MimeMessagePreparator() {
//            @Override
//            public void prepare(javax.mail.internet.MimeMessage mimeMessage) throws Exception {
//                MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
//                email.setText(user.getEmail());
//                email.setSubject("Order Confirmation - " + order.getId());
//                email.setText(text, true);
//                email.setFrom(new InternetAddress("deenbreed@gmail.com"));
//            }
//        };
//    }
}
