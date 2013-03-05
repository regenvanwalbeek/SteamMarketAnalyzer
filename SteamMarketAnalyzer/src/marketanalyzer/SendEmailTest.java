package marketanalyzer;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SendEmailTest extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response){
		try {
			Properties props = new Properties();
			Session session = Session.getDefaultInstance(props, null);
			
			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress("analyzer@steammarketanalyzer.appspotmail.com",
					"Steam Market Analyzer"));
			InternetAddress[] recipients = {
					new InternetAddress("regenvanwalbeek@gmail.com",
							"Regen"),
					new InternetAddress("jesse.roose@gmail.com",
							"Jesse") };
			message.addRecipients(Message.RecipientType.TO, recipients);
			message.setSubject("Steam Market Sales Found");
			message.setText("This is a test of sending an email message with GAE");
			Transport.send(message);
		} catch (AddressException e) {

		} catch (MessagingException e) {

		} catch (UnsupportedEncodingException e) {

		}
	}
}
