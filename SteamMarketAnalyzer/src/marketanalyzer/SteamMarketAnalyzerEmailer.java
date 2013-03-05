package marketanalyzer;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Properties;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import marketanalyzer.JDO.MarketHistoryJDO;
import marketanalyzer.JDO.PMF;

public class SteamMarketAnalyzerEmailer {

	// The fee valve charges when selling
	private static final double FEE = .13;

	public static void sendEmails() {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		Query q = pm.newQuery(MarketHistoryJDO.class);
		Collection<MarketHistoryJDO> marketHistoryItems = (Collection<MarketHistoryJDO>) q
				.execute();

		String messageBody = "";

		for (MarketHistoryJDO history : marketHistoryItems) {
			double average = history.averagePrice();
			double mostRecent = history.mostRecent();

			if (mostRecent < (1 - FEE) * average) {
				messageBody += "Item:" + history.getUrl() + "\n";
				messageBody += "Current: " + mostRecent + "\n";
				messageBody += "Average: " + average + "\n";
				messageBody += "\n";
			}

		}
		pm.close();

		// Send the email
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		if (!messageBody.equals("")) {
			try {
				Message message = new MimeMessage(session);

				message.setFrom(new InternetAddress("testemail@steammarketanalyzer.appspot.com",
						"Steam Market Analyzer"));
				InternetAddress[] recipients = {
						new InternetAddress("regenvanwalbeek@gmail.com",
								"Regen"),
						new InternetAddress("jesse.roose@gmail.com",
								"Jesse") };
				message.addRecipients(Message.RecipientType.TO, recipients);
				message.setSubject("Steam Market Sales Found");
				message.setText(messageBody);
				Transport.send(message);
			} catch (AddressException e) {

			} catch (MessagingException e) {

			} catch (UnsupportedEncodingException e) {

			}
		}

	}
}
