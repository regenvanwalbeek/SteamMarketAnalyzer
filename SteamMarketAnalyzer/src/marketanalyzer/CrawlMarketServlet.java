package marketanalyzer;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import marketanalyzer.JDO.MarketHistoryJDO;
import marketanalyzer.JDO.PMF;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

import com.google.appengine.labs.repackaged.org.json.JSONException;

public class CrawlMarketServlet extends HttpServlet {

	// TODO this will need to be a longer task

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		// Scrape the steam market prices.
		List<SteamMarketListing> listings = new ArrayList<SteamMarketListing>();
		try {
			int itemCount = SteamMarketUtility.getItemCount();
			for (int i = 0; i < itemCount; i += 100) {
				List<SteamMarketListing> newListings = SteamMarketUtility
						.getSteamMarketListings(i, 100);
				for (int j = 0; j < newListings.size(); j++) {
					listings.add(newListings.get(j));
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			return;
		} catch (JSONException e1) {
			e1.printStackTrace();
			return;
		}
		
		// Set the new prices in the history.
		PersistenceManager pm = PMF.get().getPersistenceManager();
		for (int i = 0; i < listings.size(); i++) {
			MarketHistoryJDO history;
			try {
				history = (MarketHistoryJDO) pm.getObjectById(listings.get(i)
						.getLink());
			} catch (JDOObjectNotFoundException e) {
				history = new MarketHistoryJDO(listings.get(i).getLink());
			}
			history.addPrice(listings.get(i).getStartingPrice());
			pm.makePersistent(history);
		}

		pm.close();

		SteamMarketAnalyzerEmailer.sendEmails();
	}
}
