package com.kayafirat.blog.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.UnsupportedEncodingException;
import java.util.Date;

@Component
public class Template {

    @Autowired private JwtUtil jwtUtil;
    @Autowired private Environment environment;

    /**
     * This is a verification email address.
     * When you register first time, this template will use.
     * Also User can trigger for exists user.
     *
     * @param emailAddress
     * @return
     */
    public MimeMessage verificationEmailTemplate(String emailAddress, Session session) {
        String link = environment.getProperty("base.link")+"/verify?token="+jwtUtil.generateToken(emailAddress,1800000L);
        String unSubscribeLink = environment.getProperty("base.link")+"/unsubscribe?token="+jwtUtil.generateToken(emailAddress,1800000000L);

        String template = "<!DOCTYPE html>" +
                "<html lang=\"en\"> " +
                "<head> <meta charset=\"UTF-8\"> " +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> " +
                "<title>Verify Account</title> " +
                "<style> " +
                "html { background-color: #F8F9FA; } " +
                ".center { text-align: center; } " +
                ".h1 a{ text-align: center; text-decoration: none; color: #0069D9; } " +
                ".container { margin: auto; width: 40%; font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif; } " +
                ".text-muted { color: #747C84; font-size: 12px; } .active { text-align: center; margin-bottom: 40px; } " +
                ".active button { width: 200px; height: 50px; background-color:#4cad50; border:none; border-radius: 20px; color: #fff; outline: none; font-size: 18px; cursor: pointer;} " +
                ".active a { color: #fff; font-size: 18px; text-decoration: none; } " +
                ".active a:hover { cursor: pointer; background-color: #39823B; border:none; outline: none;} " +
                ".active a button:active { background-color: #4ab14d; outline: none; border:none; box-shadow: 5px 5px 15px #4ab14d; } " +
                ".text-18 { font-size: 16px; } .text-14 { font-size:14px } " +
                "@media only screen and (max-width: 600px) { .container { width: 100%; }}" +
                "</style> " +
                "</head> " +
                "<body> " +
                "<div class=\"container\">" +
                " <div class=\"header center\"> " +
                "<h1 class=\"h1\"> " +
                "<a href=\"https://kayafirat.com\">kayafirat.com</a> " +
                "</h1> " +
                "</div> " +
                "<div class=\"img center\"> " +
                "<img src=\"https://kayatech.me/img/padlock.png\" width=\"150px\" alt=\"verify-account\"> </div> " +
                "<div style=\"font-size: 18px;\"> <p>Sevgili <b>" + emailAddress + " </b>,</p> <p>Ba??ar??l?? bir ??ekilde kay??t i??lemin tamamland??. L??tfen a??a????daki linke t??klayarak email adresini onaylayabilirsin. </p>" +
                " <p>Te??ekk??rler</p> " +
                "<p class=\"text-14\">Not : Hesab?? aktive etme s??ren 30 dakikad??r. Bu link 30 dakika sonra ??mr??n?? tamamlayacakt??r.Yeni bir do??rulama maili almak istiyorsan Ayarlar k??sm??ndan yeni bir ??ifre talep edebilirsin.</p> " +
                "</div> <div class=\"active\"> <a href=\""+link+"\"><button>Hesab?? Aktive Et</button></a> </div> " +
                "<div class=\"error\"> ??steki link ??al????m??yorsa l??tfen buna t??kla :" + link + " </div> <div class=\"unsubscribe\">" +
                " <p class=\"text-muted\"> Bu maili almak istemiyorsan??z mail ??yeliginizi sonland??rabilirsiniz. <a href=\""+unSubscribeLink+"\">Unsubscribe | Mail ??yeliginden ????k</a> </p> </div> </div>" +
                " </body>" +
                "</html>";


        MimeMessage message = new MimeMessage(session);
        MimeMultipart multipart = new MimeMultipart();
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        MimeBodyPart mimeBodyPart2 = new MimeBodyPart();
        try {
            mimeBodyPart2.setContent("<p style=\"visibility:hidden;display:none;\">????te do??rulama mailin ! L??tfen kendini bize bot olmad??????n?? kan??tla!</p>", "text/html; charset=utf-8");
            mimeBodyPart.setContent(template, "text/html; charset=utf-8");
            multipart.addBodyPart(mimeBodyPart2);
            multipart.addBodyPart(mimeBodyPart);
            message.setFrom("kayafirat.com <noreply@kayafirat.com>");
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress, emailAddress.substring(0, emailAddress.indexOf("@"))));
            message.setSubject("Hesap Onaylama", "UTF-8");
            message.setDescription("Bu bir hesap onaylama mailidir.");
            message.setContent(multipart);
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return message;
    }

    /**
     * If User successfully verify own email address,
     * This template will use.
     *
     * @param emailAddress
     * @return
     */
    public MimeMessage verificationSuccessTemplate(String emailAddress, Session session) {
        String unSubscribeLink = environment.getProperty("base.link")+"/unsubscribe?token="+jwtUtil.generateToken(emailAddress,1800000000L);

        String template = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Verify Account</title>\n" +
                "    <style>\n" +
                "        html {background-color: #F8F9FA;}\n" +
                "        .center {text-align: center;}\n" +
                "        .h1 a {text-align: center;text-decoration: none;color: #0069D9;}\n" +
                "        .container {margin: auto;width: 40%;font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;}\n" +
                "        .text-muted {color: #747C84;font-size: 12px;}\n" +
                "        .active {text-align: center;margin-bottom: 40px;}\n" +
                "        .active button {width: 200px;height: 50px;background-color: #4cad50;border: none;border-radius: 20px;color: #fff;outline: none;font-size: 18px;cursor: pointer;}\n" +
                "        .active a {color: #fff;font-size: 18px;text-decoration: none;}\n" +
                "        .active a:hover {cursor: pointer;background-color: #39823B;border: none;outline: none;}\n" +
                "        .active a button:active {background-color: #4ab14d;outline: none;border: none;box-shadow: 5px 5px 15px #4ab14d;}\n" +
                "        .text-18 {font-size: 16px;}\n" +
                "        .text-14 {font-size: 14px}\n" +
                "        @media only screen and (max-width: 600px) {.container {width: 100%;}}\n" +
                "    </style>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <div class=\"header center\">\n" +
                "            <h1 class=\"h1\">\n" +
                "                <a href=\"https://kayafirat.com\">kayafirat.com</a>\n" +
                "            </h1>\n" +
                "        </div>\n" +
                "        <div class=\"img center\">\n" +
                "            <img src=\"https://image.kayafirat.com/images/1/verify.png\" width=\"150px\" alt=\"verify-account\">\n" +
                "        </div>\n" +
                "        <div style=\"font-size: 18px;\">\n" +
                "            <p>Sevgili <b>\""+emailAddress+"\" </b>,</p>\n" +
                "            <p>Ba??ar??l?? bir ??ekilde hesab??n onayland??. Art??k benim i??in tamamen g??venilir bir kullan??c?? oldun. Sana bunun i??in \n" +
                "                te??ekk??r ediyorum.  \n" +
                "            </p>\n" +
                "            <p>Bu ad??mda sonra kendi ki??isel internet adresimde istedi??in yaz??y?? okuyabilirsin.  Kendi b??ltenime kay??t olabilir ve yorum yapabilirsin.  \n" +
                "               Son olarak, bu a????k kaynak kodlu bir projedir. Yani istedi??in her kodu g??rebilmen m??mk??nd??r. ??ncelemek ya da destek olmak istersen \n" +
                "               beni mutlu edersin. <br><br>Kaynak kodlara <a href=\"https://github.com/firatkaya1\">buradan</a> ula??abilirsin.\n" +
                "                <br><br>Sevgilerimle...\n" +
                "            </p>\n" +
                "        </div>\n" +
                "        <div class=\"active\"> <a href=\"https://blog.kayafirat.com\"><button>Siteye Git</button></a> </div>\n" +
                "        <div class=\"unsubscribe\">\n" +
                "            <p class=\"text-muted\"> Bu maili almak istemiyorsan??z mail ??yeliginizi sonland??rabilirsiniz. \n" +
                "                <a href=\""+unSubscribeLink+"\">&nbsp;Unsubscribe &nbsp;|&nbsp; Mail ??yeliginden ????k&nbsp;</a> \n" +
                "            </p>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "\n" +
                "</html>";
        MimeMessage message = new MimeMessage(session);
        MimeMultipart multipart = new MimeMultipart();
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        MimeBodyPart mimeBodyPart2 = new MimeBodyPart();
        try {
            mimeBodyPart2.setContent("<p style=\"visibility:hidden;display:none;\">Harika ! Hesab??n?? art??k do??rulad??n, Te??ekk??rler...</p>", "text/html; charset=utf-8");
            mimeBodyPart.setContent(template, "text/html; charset=utf-8");
            multipart.addBodyPart(mimeBodyPart2);
            multipart.addBodyPart(mimeBodyPart);
            message.setFrom("kayafirat.com <noreply@kayafirat.com>");
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress, emailAddress.substring(0, emailAddress.indexOf("@"))));
            message.setSubject("Hesab??n Onayland??", "UTF-8");
            message.setDescription("Bu bir hesap onaylama bildirim mailidir.");
            message.setContent(multipart);
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return message;
    }

    /**
     * If user wants to reset own passwords,
     * this template will use.
     *
     * @param emailAddress
     * @return
     */
    public MimeMessage resetPasswordTemplate(String emailAddress, Session session) {
        String link = environment.getProperty("base.link")+"/resetpassword?code="+jwtUtil.generateToken(emailAddress,1800000L);
        String unSubscribeLink = environment.getProperty("base.link")+"/unsubscribe?token="+jwtUtil.generateToken(emailAddress,1800000000L);
        String template = "<!DOCTYPE html>" +
                "<html lang=\"en\"> " +
                "<head> " +
                "<meta charset=\"UTF-8\"> " +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> " +
                "<title>Verify Account</title> " +
                "<style> html { background-color: #F8F9FA; } " +
                ".center { text-align: center; } " +
                ".h1 a{ text-align: center; text-decoration: none; color: #0069D9; } " +
                ".container { margin: auto; width: 40%; font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif; } " +
                ".text-muted { color: #747C84; font-size: 12px; } " +
                ".active { text-align: center; margin-bottom: 40px; } " +
                ".active button { width: 200px; height: 50px; background-color:#4cad50; border:none; border-radius: 20px; color: #fff; outline: none; font-size: 18px; cursor: pointer;} " +
                ".active a { color: #fff; font-size: 18px; text-decoration: none; } " +
                ".active a:hover { cursor: pointer; background-color: #39823B; border:none; outline: none;}" +
                ".active a button:active { background-color: #4ab14d; outline: none; border:none; box-shadow: 5px 5px 15px #4ab14d; } " +
                ".text-18 { font-size: 16px; } .text-14 { font-size:14px } " +
                "@media only screen and (max-width: 600px) { .container { width: 100%; }}" +
                "</style> " +
                "</head>" +
                "<body> " +
                "<div class=\"container\"> " +
                "<div class=\"header center\"> " +
                "<h1 class=\"h1\"> " +
                "<a href=\"https://kayafirat.com\">kayafirat.com</a> </h1> " +
                "</div> " +
                "<div class=\"img center\"> " +
                "<img src=\"https://kayatech.me/img/synchronize.png\" width=\"150px\" alt=\"verify-account\"> </div> " +
                "<div style=\"font-size: 18px;\"> <p>Sevgili <b>" + emailAddress + "</b>,</p> " +
                "<p>Bizlerden istedi??in gibi ??ifreni s??f??rlama ba??lant??s??n?? getirdik. A??a????daki <b>??ifremi S??f??rla </b> ba??lant??s??na t??klayarak a????lacak yeni sayfadan kendine yeni bir sayfa olu??turabilirsin.</p> " +
                "<p>Te??ekk??rler</p> " +
                "<p class=\"text-14\">Not : ??ifre s??f??rlama s??ren 30 dakikad??r. Bu link 30 dakika sonra ??mr??n?? tamamlayacakt??r.Yeni bir s??f??rlama maili almak istiyorsan ??ifremi unuttun se??ene??ime t??klayarak yeni bir link talep edebilirsin.</p>" +
                " </div> <div class=\"active\"> <a href=" +link+"><button>??ifremi S??f??rla</button></a> </div> " +
                "<div class=\"error\"> ??steki link ??al????m??yorsa l??tfen buna t??kla :" + link + " </div> " +
                "<div class=\"unsubscribe\"> <p class=\"text-muted\"> Bu maili almak istemiyorsan??z mail ??yeliginizi sonland??rabilirsiniz. <a href=\""+unSubscribeLink+"\">Unsubscribe | Mail ??yeliginden ????k</a> </p> </div> </div> " +
                "</body>" +
                "</html>";
        MimeMessage message = new MimeMessage(session);
        MimeMultipart multipart = new MimeMultipart();
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        MimeBodyPart mimeBodyPart2 = new MimeBodyPart();
        try {
            mimeBodyPart2.setContent("<p style=\"visibility:hidden;display:none;\">G??r??nen o ki birileri bal??k haf??zal??, i??te sana hat??rlaman i??in bir f??rsat !</p>", "text/html; charset=utf-8");
            mimeBodyPart.setContent(template, "text/html; charset=utf-8");
            multipart.addBodyPart(mimeBodyPart2);
            multipart.addBodyPart(mimeBodyPart);
            message.setFrom("kayafirat.com <noreply@kayafirat.com>");
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress, emailAddress.substring(0, emailAddress.indexOf("@"))));
            message.setSubject("??ifre S??f??rlama ??ste??i", "UTF-8");
            message.setDescription("Bu bir ??ifre s??f??rlama iste??idir.");
            message.setContent(multipart);

            return message;
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return message;
    }

    /**
     * If User successfully changed own password,
     * This template will use.
     *
     * @param emailAddress
     * @return
     */
    public MimeMessage passwordChangedTemplate(String emailAddress, Session session) {
        String link = environment.getProperty("base.link")+"/forgotpassword";
        String unSubscribeLink = environment.getProperty("base.link")+"/unsubscribe?token="+jwtUtil.generateToken(emailAddress,1800000000L);

        String template = "<!DOCTYPE html><html lang=\"en\"> " +
                "<head> <meta charset=\"UTF-8\"> " +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> " +
                "<title>Password Changed</title> " +
                "<style> html { background-color: #F8F9FA; } " +
                ".center { text-align: center; } " +
                ".h1 a{ text-align: center; text-decoration: none; color: #0069D9; } " +
                ".container { margin: auto; width: 40%; font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif; } .text-muted { color: #747C84; font-size: 12px; } " +
                ".active { text-align: center; margin-bottom: 40px; } " +
                ".active button { width: 200px; height: 50px; background-color:#C93D32; border:none; border-radius: 20px; color: #fff; outline: none; font-size: 16px; cursor: pointer;} " +
                ".active a { color: #fff; font-size: 18px; text-decoration: none; } " +
                ".active a:hover { cursor: pointer; background-color:#C93D32; border:none; outline: none;} " +
                ".active a button:active { background-color: #C93D32; outline: none; border:none; box-shadow: 5px 5px 15px #C93D32; } " +
                ".text-18 { font-size: 16px; } .text-14 { font-size:14px } " +
                "@media only screen and (max-width: 600px) { .container { width: 100%; }}" +
                "</style> " +
                "</head> " +
                "<body> " +
                "<div class=\"container\"> " +
                "<div class=\"header center\"> " +
                "<h1 class=\"h1\"> <a href=\"https://kayafirat.com\">kayafirat.com</a>" +
                "</h1> </div> " +
                "<div class=\"img center\"> <img src=\"https://image.kayafirat.com/images/1/verify.png\" width=\"200\"   title=\"logo\" alt=\"logo\"> </div> " +
                "<div style=\"font-size: 16px;\"> <p>Sevgili <b>" + emailAddress + "</b>,</p> " +
                "<p>Hesap ??ifren <span><b>" + new Date() + "</b></span> tarihinde de??i??tirildi. E??er bu sen de??ilsen l??tfen bizimle ileti??ime ge?? ve hesab??n?? g??vene al.</p> " +
                "</div> " +
                "<div class=\"active\"> <a href=\""+link+"\"><button>Bunu ben yapmad??m.</button></a> </div> " +
                "<div class=\"error\"> ??steki link ??al????m??yorsa l??tfen buna t??kla : <a href=\""+link+"\">"+link+"</a></div> " +
                "<div class=\"unsubscribe\"> <p class=\"text-muted\"> Bu maili almak istemiyorsan??z mail ??yeliginizi sonland??rabilirsiniz. " +
                "<a href=\""+unSubscribeLink+"\">Unsubscribe | Mail ??yeliginden ????k</a> </p> </div> </div> " +
                "</body></html>";
        MimeMessage message = new MimeMessage(session);
        MimeMultipart multipart = new MimeMultipart();
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        MimeBodyPart mimeBodyPart2 = new MimeBodyPart();
        try {
            mimeBodyPart2.setContent("<p style=\"visibility:hidden;display:none;\">Harika ! Art??k unutabilece??in yeni bir ??ifreye sahipsin !</p>", "text/html; charset=utf-8");
            mimeBodyPart.setContent(template, "text/html; charset=utf-8");
            multipart.addBodyPart(mimeBodyPart2);
            multipart.addBodyPart(mimeBodyPart);
            message.setFrom("kayafirat.com <noreply@kayafirat.com>");
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress, emailAddress.substring(0, emailAddress.indexOf("@"))));
            message.setSubject("??ifren Yenilendi", "UTF-8");
            message.setDescription("Bu bir ??ifre yenileme mailidir.");
            message.setContent(multipart);

            return message;
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return message;

    }

    /**
     * If User successfully login,
     * This template will use.
     *
     * @param emailAddress
     * @return
     */
    public MimeMessage loginSuccessTemplate(String emailAddress, Session session) {
        String unSubscribeLink = environment.getProperty("base.link")+"/unsubscribe?token="+jwtUtil.generateToken(emailAddress,1800000000L);

        String template = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Verify Account</title>\n" +
                "    <style>\n" +
                "        html {background-color: #F8F9FA;}\n" +
                "        .center {text-align: center;}\n" +
                "        .h1 a {text-align: center;text-decoration: none;color: #0069D9;}\n" +
                "        .container {margin: auto;width: 40%;font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;}\n" +
                "        .text-muted {color: #747C84;font-size: 12px;}\n" +
                "        .active {text-align: center;margin-bottom: 40px;}\n" +
                "        .active button {width: 200px;height: 50px;background-color: #C93D32;border: none;border-radius: 20px;color: #fff;outline: none;font-size: 18px;cursor: pointer;}\n" +
                "        .active a {color: #fff;font-size: 18px;text-decoration: none;}\n" +
                "        .active a:hover {cursor: pointer;background-color: #C93D32;border: none;outline: none;}\n" +
                "        .active a button:active {background-color: #C93D32;outline: none;border: none;box-shadow: 5px 5px 15px #C93D32;}\n" +
                "        .text-18 {font-size: 16px;}\n" +
                "        .text-14 {font-size: 14px}\n" +
                "        @media only screen and (max-width: 600px) {.container {width: 100%;}}\n" +
                "    </style>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <div class=\"header center\">\n" +
                "            <h1 class=\"h1\">\n" +
                "                <a href=\"https://kayafirat.com\">kayafirat.com</a>\n" +
                "            </h1>\n" +
                "        </div>\n" +
                "        <div class=\"img center\">\n" +
                "            <img src=\"https://image.kayafirat.com/images/1/verify.png\" width=\"150px\" alt=\"verify-account\">\n" +
                "        </div>\n" +
                "        <div style=\"font-size: 18px;\">\n" +
                "            <p>Sevgili <b>\"+emailAddress+\" </b>,</p>\n" +
                "            <p>xxxx Tarihinde hesab??na ba??ar??yla giri?? yapt??n. E??er bu bildirimleri almak istemiyorsan, blog.kayafirat.com adresinden giri?? yaparak\n" +
                "                profil sekmesinde mail bildirimlerini kapatabilirsin. \n" +
                "            </p>\n" +
                "            <p>\n" +
                "                Giri?? yapan sen de??ilsen l??tfen a??a????daki butona t??klayarak yeni bir ??ifre talep et. Bunu bizlere bildirebilirsin ve hesab??n?? ask??ya alabiliriz.\n" +
                "            </p>\n" +
                "        </div>\n" +
                "        <div class=\"active\"> <a href=\"https://blog.kayafirat.com\"><button>Siteye Git</button></a> </div>\n" +
                "        <div class=\"unsubscribe\">\n" +
                "            <p class=\"text-muted\"> Bu maili almak istemiyorsan??z mail ??yeliginizi sonland??rabilirsiniz. \n" +
                "                <a href=\""+unSubscribeLink+"\">&nbsp;Unsubscribe &nbsp;|&nbsp; Mail ??yeliginden ????k&nbsp;</a> \n" +
                "            </p>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "\n" +
                "</html>";
        MimeMessage message = new MimeMessage(session);
        MimeMultipart multipart = new MimeMultipart();
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        MimeBodyPart mimeBodyPart2 = new MimeBodyPart();
        try {
            mimeBodyPart2.setContent("<p style=\"visibility:hidden;display:none;\">Hey ! Bu giri??i yapan sen misin ?!</p>", "text/html; charset=utf-8");
            mimeBodyPart.setContent(template, "text/html; charset=utf-8");
            multipart.addBodyPart(mimeBodyPart2);
            multipart.addBodyPart(mimeBodyPart);
            message.setFrom("kayafirat.com <noreply@kayafirat.com>");
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress, emailAddress.substring(0, emailAddress.indexOf("@"))));
            message.setSubject("Hesab??na Giri?? Yap??ld??", "UTF-8");
            message.setDescription("Bu bir hesap onaylama bildirim mailidir.");
            message.setContent(multipart);
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return message;
    }

    /**
     * If User try to login 3 times,
     * This template will use.
     *
     * @param emailAddress
     * @return
     */
    public MimeMessage loginAttemptTemplate(String emailAddress, Session session) {
        String unSubscribeLink = environment.getProperty("base.link")+"/unsubscribe?token="+jwtUtil.generateToken(emailAddress,1800000000L);

        String template = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Verify Account</title>\n" +
                "    <style>\n" +
                "        html {background-color: #F8F9FA;}\n" +
                "        .center {text-align: center;}\n" +
                "        .h1 a {text-align: center;text-decoration: none;color: #0069D9;}\n" +
                "        .container {margin: auto;width: 40%;font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;}\n" +
                "        .text-muted {color: #747C84;font-size: 12px;}\n" +
                "        .active {text-align: center;margin-bottom: 40px;}\n" +
                "        .active button {width: 200px;height: 50px;background-color: #C93D32;border: none;border-radius: 20px;color: #fff;outline: none;font-size: 18px;cursor: pointer;}\n" +
                "        .active a {color: #fff;font-size: 18px;text-decoration: none;}\n" +
                "        .active a:hover {cursor: pointer;background-color: #C93D32;border: none;outline: none;}\n" +
                "        .active a button:active {background-color: #C93D32;outline: none;border: none;box-shadow: 5px 5px 15px #C93D32;}\n" +
                "        .text-18 {font-size: 16px;}\n" +
                "        .text-14 {font-size: 14px}\n" +
                "        @media only screen and (max-width: 600px) {.container {width: 100%;}}\n" +
                "    </style>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <div class=\"header center\">\n" +
                "            <h1 class=\"h1\">\n" +
                "                <a href=\"https://kayafirat.com\">kayafirat.com</a>\n" +
                "            </h1>\n" +
                "        </div>\n" +
                "        <div class=\"img center\">\n" +
                "            <img src=\"https://image.kayafirat.com/images/1/verify.png\" width=\"150px\" alt=\"verify-account\">\n" +
                "        </div>\n" +
                "        <div style=\"font-size: 18px;\">\n" +
                "            <p>Sevgili <b>\"+emailAddress+\" </b>,</p>\n" +
                "            <p>\n" +
                "                xxxx Tarihinde hesab??na birden fazla deneme yap??ld??. Sana ne yaz??k ki daha fazla bilgi veremiyoruz ama inan??yorum ki bunun ??stesinden gelebiliriz.\n" +
                "            </p>\n" +
                "            <p>\n" +
                "                Bu denemeleri yapan sen de??ilsen en k??sa zamanda ??ifreni de??i??tirmeni talep ediyoruz. E??er dilersen senin i??in kontrol?? ele alarak hesab??n?? ask??ya alabiliriz.\n" +
                "            </p>\n" +
                "            <p>\n" +
                "                G??venle kalman dile??iyle,\n" +
                "                Ho??cakal\n" +
                "            </p>\n" +
                "        </div>\n" +
                "        <div class=\"active\"> <a href=\"https://blog.kayafirat.com\"><button>Bunu ben yapmad??m</button></a> </div>\n" +
                "        <div class=\"unsubscribe\">\n" +
                "            <p class=\"text-muted\"> Bu maili almak istemiyorsan??z mail ??yeliginizi sonland??rabilirsiniz. \n" +
                "                <a href=\""+unSubscribeLink+"\">&nbsp;Unsubscribe &nbsp;|&nbsp; Mail ??yeliginden ????k&nbsp;</a> \n" +
                "            </p>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "\n" +
                "</html> ";
        MimeMessage message = new MimeMessage(session);
        MimeMultipart multipart = new MimeMultipart();
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        MimeBodyPart mimeBodyPart2 = new MimeBodyPart();
        try {
            mimeBodyPart2.setContent("<p style=\"visibility:hidden;display:none;\">Hey ! Birileri hesab??na girmeye ??al??????yor !</p>", "text/html; charset=utf-8");
            mimeBodyPart.setContent(template, "text/html; charset=utf-8");
            multipart.addBodyPart(mimeBodyPart2);
            multipart.addBodyPart(mimeBodyPart);
            message.setFrom("kayafirat.com <noreply@kayafirat.com>");
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress, emailAddress.substring(0, emailAddress.indexOf("@"))));
            message.setSubject("Hesap Uyar??s??", "UTF-8");
            message.setDescription("Bu bir hesap uyar??s?? bildirim mailidir.");
            message.setContent(multipart);
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return message;
    }

    public MimeMessage other(String emailAddress, Session session,String title,String subtitle,String body) {

        MimeMessage message = new MimeMessage(session);
        MimeMultipart multipart = new MimeMultipart();
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        MimeBodyPart mimeBodyPart2 = new MimeBodyPart();
        try {
            mimeBodyPart2.setContent("<p style=\"visibility:hidden;display:none;\"> kayafirat.com  </p>", "text/html; charset=utf-8");
            mimeBodyPart.setContent(body, "text/html; charset=utf-8");
            multipart.addBodyPart(mimeBodyPart2);
            multipart.addBodyPart(mimeBodyPart);
            message.setFrom("kayafirat.com <noreply@kayafirat.com>");
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress, emailAddress.substring(0, emailAddress.indexOf("@"))));
            message.setSubject(title, "UTF-8");
            message.setDescription(subtitle);
            message.setContent(multipart);
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return message;
    }



}
