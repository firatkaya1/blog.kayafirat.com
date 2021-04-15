package com.kayafirat.blog.util;

import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.UnsupportedEncodingException;

@Component
public class Template {

    /**
     * This is a verification email address.
     * When you register first time, this template will use.
     * Also User can trigger for exists user.
     *
     * @param emailAddress
     * @return
     */
    public MimeMessage verificationEmailTemplate(String emailAddress, Session session) {
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
                "<div style=\"font-size: 18px;\"> <p>Sevgili <b>" + emailAddress + " </b>,</p> <p>Başarılı bir şekilde kayıt işlemin tamamlandı. Lütfen aşağıdaki linke tıklayarak email adresini onaylayabilirsin. </p>" +
                " <p>Teşekkürler</p> " +
                "<p class=\"text-14\">Not : Hesabı aktive etme süren 30 dakikadır. Bu link 30 dakika sonra ömrünü tamamlayacaktır.Yeni bir doğrulama maili almak istiyorsan Ayarlar kısmından yeni bir şifre talep edebilirsin.</p> " +
                "</div> <div class=\"active\"> <a href=\"https://blog.kayafirat.com\"><button>Hesabı Aktive Et</button></a> </div> " +
                "<div class=\"error\"> Üsteki link çalışmıyorsa lütfen buna tıkla :" + emailAddress + " </div> <div class=\"unsubscribe\">" +
                " <p class=\"text-muted\"> Bu maili almak istemiyorsanız mail üyeliginizi sonlandırabilirsiniz. <a href=\"#\">Unsubscribe | Mail Üyeliginden çık</a> </p> </div> </div>" +
                " </body>" +
                "</html>";


        MimeMessage message = new MimeMessage(session);
        MimeMultipart multipart = new MimeMultipart();
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        MimeBodyPart mimeBodyPart2 = new MimeBodyPart();
        try {
            mimeBodyPart2.setContent("<p style=\"visibility:hidden;display:none;\">İşte doğrulama mailin ! Lütfen kendini bize bot olmadığını kanıtla!</p>", "text/html; charset=utf-8");
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
                "            <p>Sevgili <b>\"+emailAddress+\" </b>,</p>\n" +
                "            <p>Başarılı bir şekilde hesabın onaylandı. Artık benim için tamamen güvenilir bir kullanıcı oldun. Sana bunun için \n" +
                "                teşekkür ediyorum.  \n" +
                "            </p>\n" +
                "            <p>Bu adımda sonra kendi kişisel internet adresimde istediğin yazıyı okuyabilirsin.  Kendi bültenime kayıt olabilir ve yorum yapabilirsin.  \n" +
                "               Son olarak, bu açık kaynak kodlu bir projedir. Yani istediğin her kodu görebilmen mümkündür. İncelemek ya da destek olmak istersen \n" +
                "               beni mutlu edersin. <br><br>Kaynak kodlara <a href=\"https://github.com/firatkaya1\">buradan</a> ulaşabilirsin.\n" +
                "                <br><br>Sevgilerimle...\n" +
                "            </p>\n" +
                "        </div>\n" +
                "        <div class=\"active\"> <a href=\"https://blog.kayafirat.com\"><button>Siteye Git</button></a> </div>\n" +
                "        <div class=\"unsubscribe\">\n" +
                "            <p class=\"text-muted\"> Bu maili almak istemiyorsanız mail üyeliginizi sonlandırabilirsiniz. \n" +
                "                <a href=\"#\">&nbsp;Unsubscribe &nbsp;|&nbsp; Mail Üyeliginden çık&nbsp;</a> \n" +
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
            mimeBodyPart2.setContent("<p style=\"visibility:hidden;display:none;\">Harika ! Hesabını artık doğruladın, Teşekkürler...</p>", "text/html; charset=utf-8");
            mimeBodyPart.setContent(template, "text/html; charset=utf-8");
            multipart.addBodyPart(mimeBodyPart2);
            multipart.addBodyPart(mimeBodyPart);
            message.setFrom("kayafirat.com <noreply@kayafirat.com>");
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress, emailAddress.substring(0, emailAddress.indexOf("@"))));
            message.setSubject("Hesabın Onaylandı", "UTF-8");
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
                "<p>Bizlerden istediğin gibi şifreni sıfırlama bağlantısını getirdik. Aşağıdaki <b>Şifremi Sıfırla </b> bağlantısına tıklayarak açılacak yeni sayfadan kendine yeni bir sayfa oluşturabilirsin.</p> " +
                "<p>Teşekkürler</p> " +
                "<p class=\"text-14\">Not : Şifre sıfırlama süren 30 dakikadır. Bu link 30 dakika sonra ömrünü tamamlayacaktır.Yeni bir sıfırlama maili almak istiyorsan şifremi unuttun seçeneğime tıklayarak yeni bir link talep edebilirsin.</p>" +
                " </div> <div class=\"active\"> <a href=" + emailAddress + "" + "><button>Şifremi Sıfırla</button></a> </div> " +
                "<div class=\"error\"> Üsteki link çalışmıyorsa lütfen buna tıkla :" + emailAddress + " </div> " +
                "<div class=\"unsubscribe\"> <p class=\"text-muted\"> Bu maili almak istemiyorsanız mail üyeliginizi sonlandırabilirsiniz. <a href=\"#\">Unsubscribe | Mail Üyeliginden çık</a> </p> </div> </div> " +
                "</body>" +
                "</html>";
        MimeMessage message = new MimeMessage(session);
        MimeMultipart multipart = new MimeMultipart();
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        MimeBodyPart mimeBodyPart2 = new MimeBodyPart();
        try {
            mimeBodyPart2.setContent("<p style=\"visibility:hidden;display:none;\">Görünen o ki birileri balık hafızalı, işte sana hatırlaman için bir fırsat !</p>", "text/html; charset=utf-8");
            mimeBodyPart.setContent(template, "text/html; charset=utf-8");
            multipart.addBodyPart(mimeBodyPart2);
            multipart.addBodyPart(mimeBodyPart);
            message.setFrom("kayafirat.com <noreply@kayafirat.com>");
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress, emailAddress.substring(0, emailAddress.indexOf("@"))));
            message.setSubject("Şifre Sıfırlama İsteği", "UTF-8");
            message.setDescription("Bu bir şifre sıfırlama isteğidir.");
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
                "<p>Hesap şifren <span><b>" + emailAddress + "</b></span> tarihinde değiştirildi. Eğer bu sen değilsen lütfen bizimle iletişime geç ve hesabını güvene al.</p> " +
                "<p class=\"text-muted\">Ip Address: <span>" + emailAddress + "</span> </p> <p class=\"text-muted\">User-Agent: <span>" + emailAddress + "</span></p> </div> " +
                "<div class=\"active\"> <a href=\"https://blog.kayafirat.com\"><button>Bunu ben yapmadım.</button></a> </div> " +
                "<div class=\"error\"> Üsteki link çalışmıyorsa lütfen buna tıkla : </div> " +
                "<div class=\"unsubscribe\"> <p class=\"text-muted\"> Bu maili almak istemiyorsanız mail üyeliginizi sonlandırabilirsiniz. " +
                "<a href=\"#\">Unsubscribe | Mail Üyeliginden çık</a> </p> </div> </div> " +
                "</body></html>";
        MimeMessage message = new MimeMessage(session);
        MimeMultipart multipart = new MimeMultipart();
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        MimeBodyPart mimeBodyPart2 = new MimeBodyPart();
        try {
            mimeBodyPart2.setContent("<p style=\"visibility:hidden;display:none;\">Harika ! Artık unutabileceğin yeni bir şifreye sahipsin !</p>", "text/html; charset=utf-8");
            mimeBodyPart.setContent(template, "text/html; charset=utf-8");
            multipart.addBodyPart(mimeBodyPart2);
            multipart.addBodyPart(mimeBodyPart);
            message.setFrom("kayafirat.com <noreply@kayafirat.com>");
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress, emailAddress.substring(0, emailAddress.indexOf("@"))));
            message.setSubject("Şifren Yenilendi", "UTF-8");
            message.setDescription("Bu bir şifre yenileme mailidir.");
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
                "            <p>xxxx Tarihinde hesabına başarıyla giriş yaptın. Eğer bu bildirimleri almak istemiyorsan, blog.kayafirat.com adresinden giriş yaparak\n" +
                "                profil sekmesinde mail bildirimlerini kapatabilirsin. \n" +
                "            </p>\n" +
                "            <p>\n" +
                "                Giriş yapan sen değilsen lütfen aşağıdaki butona tıklayarak yeni bir şifre talep et. Bunu bizlere bildirebilirsin ve hesabını askıya alabiliriz.\n" +
                "            </p>\n" +
                "        </div>\n" +
                "        <div class=\"active\"> <a href=\"https://blog.kayafirat.com\"><button>Siteye Git</button></a> </div>\n" +
                "        <div class=\"unsubscribe\">\n" +
                "            <p class=\"text-muted\"> Bu maili almak istemiyorsanız mail üyeliginizi sonlandırabilirsiniz. \n" +
                "                <a href=\"#\">&nbsp;Unsubscribe &nbsp;|&nbsp; Mail Üyeliginden çık&nbsp;</a> \n" +
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
            mimeBodyPart2.setContent("<p style=\"visibility:hidden;display:none;\">Hey ! Bu girişi yapan sen misin ?!</p>", "text/html; charset=utf-8");
            mimeBodyPart.setContent(template, "text/html; charset=utf-8");
            multipart.addBodyPart(mimeBodyPart2);
            multipart.addBodyPart(mimeBodyPart);
            message.setFrom("kayafirat.com <noreply@kayafirat.com>");
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress, emailAddress.substring(0, emailAddress.indexOf("@"))));
            message.setSubject("Hesabına Giriş Yapıldı", "UTF-8");
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
                "                xxxx Tarihinde hesabına birden fazla deneme yapıldı. Sana ne yazık ki daha fazla bilgi veremiyoruz ama inanıyorum ki bunun üstesinden gelebiliriz.\n" +
                "            </p>\n" +
                "            <p>\n" +
                "                Bu denemeleri yapan sen değilsen en kısa zamanda şifreni değiştirmeni talep ediyoruz. Eğer dilersen senin için kontrolü ele alarak hesabını askıya alabiliriz.\n" +
                "            </p>\n" +
                "            <p>\n" +
                "                Güvenle kalman dileğiyle,\n" +
                "                Hoşcakal\n" +
                "            </p>\n" +
                "        </div>\n" +
                "        <div class=\"active\"> <a href=\"https://blog.kayafirat.com\"><button>Bunu ben yapmadım</button></a> </div>\n" +
                "        <div class=\"unsubscribe\">\n" +
                "            <p class=\"text-muted\"> Bu maili almak istemiyorsanız mail üyeliginizi sonlandırabilirsiniz. \n" +
                "                <a href=\"#\">&nbsp;Unsubscribe &nbsp;|&nbsp; Mail Üyeliginden çık&nbsp;</a> \n" +
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
            mimeBodyPart2.setContent("<p style=\"visibility:hidden;display:none;\">Hey ! Birileri hesabına girmeye çalışıyor !</p>", "text/html; charset=utf-8");
            mimeBodyPart.setContent(template, "text/html; charset=utf-8");
            multipart.addBodyPart(mimeBodyPart2);
            multipart.addBodyPart(mimeBodyPart);
            message.setFrom("kayafirat.com <noreply@kayafirat.com>");
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress, emailAddress.substring(0, emailAddress.indexOf("@"))));
            message.setSubject("Hesap Uyarısı", "UTF-8");
            message.setDescription("Bu bir hesap uyarısı bildirim mailidir.");
            message.setContent(multipart);
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return message;
    }


}
