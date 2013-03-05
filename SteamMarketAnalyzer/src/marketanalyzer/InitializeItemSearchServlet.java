package marketanalyzer;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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

public class InitializeItemSearchServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		ArrayList<String> urls = getURLs();

		// Add any new market history items that have been found
		PersistenceManager pm = PMF.get().getPersistenceManager();
		ArrayList<MarketHistoryJDO> marketHistoryItems = new ArrayList<MarketHistoryJDO>();

		Query q = pm.newQuery(MarketHistoryJDO.class);
		q.setFilter("url == urlParam");
		q.declareParameters("String urlParam");

		for (String url : urls) {
			List<MarketHistoryJDO> results = (List<MarketHistoryJDO>) q
					.execute(url);
			if (results.isEmpty()) {
				marketHistoryItems.add(new MarketHistoryJDO(url));
			}
		}

		pm.makePersistentAll(marketHistoryItems);

		pm.close();

		response.setContentType("text/plain");
		response.getWriter().println("finished");

	}

	private static ArrayList<String> getURLs() {
		// initialize the urls. In the future, do something smart like scrape
		// the urls. Until then I'm going to target items

		ArrayList<String> urls = new ArrayList<String>();

		// vintages
		urls.add("http://steamcommunity.com/market/listings/440/Vintage%20Crit-a-Cola"); // crit-a-cola
		urls.add("http://steamcommunity.com/market/listings/440/Vintage%20Gunslinger"); // gunslinger
		urls.add("http://steamcommunity.com/market/listings/440/Vintage%20Stockbroker%27s%20Scarf"); // stockbroker's
																										// scarf

		/** GENUINES **/
		// Anger
		urls.add("http://steamcommunity.com/market/listings/440/Genuine%20Anger");
		// AWP-er Hand
		urls.add("http://steamcommunity.com/market/listings/440/Genuine%20AWPer%20Hand");
		// Distinguished Rogue
		urls.add("http://steamcommunity.com/market/listings/440/Genuine%20Distinguished%20Rogue");
		// Foppish Physician
		urls.add("http://steamcommunity.com/market/listings/440/Genuine%20Foppish%20Physician");
		// Pip-boy
		urls.add("http://steamcommunity.com/market/listings/440/Genuine%20Pip-Boy");
		// Menpo
		urls.add("http://steamcommunity.com/market/listings/440/Genuine%20Menpo");
		// Companion Cube Pin
		urls.add("http://steamcommunity.com/market/listings/440/Genuine%20Companion%20Cube%20Pin");
		// Diamondback
		urls.add("http://steamcommunity.com/market/listings/440/Genuine%20Diamondback");
		
		return urls;
	}

	private static ArrayList<String> scrapeURLs() throws MalformedURLException, IOException {
		ArrayList<String> urls = new ArrayList<String>();

		// Create the scraper object
		CleanerProperties props = new CleanerProperties();
		props.setTranslateSpecialEntities(true);
		props.setTransResCharsToNCR(true);
		props.setOmitComments(true);
		
		TagNode rootNode = new HtmlCleaner(props).clean(new URL("http://steamcommunity.com/market/search?q=appid%3A440"));
		
		// TODO being recursive may contribute to load...not sure
		List linkTags = rootNode.getElementListByAttValue("class",
				"market_listing_row_link",
				true, false);
		
		for (int i = 0; i < linkTags.size(); i++){
			TagNode linkTag = (TagNode) linkTags.get(i);
			String link = ""; //linkTag.
			urls.add(link);

		}
		
		return urls;
	}

}
