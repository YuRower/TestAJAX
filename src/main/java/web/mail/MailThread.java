package web.mail;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class MailThread extends Thread {
    private static final Logger LOGGER = LogManager.getLogger(MailThread.class);

    private static final String TYPE = "text/html";

    private MimeMessage message;
    private String to;
    private String from;
    private String subject;
	private String body;
    private Properties properties;

    public MailThread(String to, String from, String subject, String body, Properties properties) {
        this.to = to;
        this.from = from;
        this.subject = subject;
        this.body=body;
        this.properties = properties;
    }

 
	@Override
    public void run() {
        init();
        try {
            Transport.send(message);
        } catch (MessagingException e) {
            LOGGER.log(Level.ERROR,"error of sending", e);
        }
    }

    private void init() {
        SessionCreator sessionCreator = new SessionCreator(properties);
        Session mailSession = sessionCreator.createSession();
        mailSession.setDebug(true);
        message = new MimeMessage(mailSession);
        try {
        	
              //�� ����
            //  message.setFrom(new InternetAddress(username));
              //����
              message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(from));
              //���� ���������
              message.setSubject(subject);
              //�����
              message.setText(body);
              
            //message.setSubject(subject);
            //message.setContent(mailText, TYPE);
       //     message.setRecipient(Message.RecipientType.TO, new InternetAddress(sendToEmail));
        } catch (AddressException e) {
            LOGGER.log(Level.ERROR,"incorrect email:" + from, e);
        } catch (MessagingException e) {
            LOGGER.log(Level.ERROR,"error of formed message", e);
        }
    }
}