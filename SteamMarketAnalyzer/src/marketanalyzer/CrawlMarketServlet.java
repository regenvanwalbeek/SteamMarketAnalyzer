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

public class CrawlMarketServlet extends HttpServlet {

	// TODO this will need to be a longer task

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		// Get all the market history items
		Query q = pm.newQuery(MarketHistoryJDO.class);
		List<MarketHistoryJDO> results = (List<MarketHistoryJDO>) q.execute();
		ArrayList<MarketHistoryJDO> marketHistories = new ArrayList<MarketHistoryJDO>();

		// Create the scraper object
		CleanerProperties props = new CleanerProperties();
		props.setTranslateSpecialEntities(true);
		props.setTransResCharsToNCR(true);
		props.setOmitComments(true);

		// scrape each url to gather the current market on each item
		for (MarketHistoryJDO marketHistory : results) {
			try {
				TagNode rootNode = new HtmlCleaner(props).clean(new URL(marketHistory
						.getUrl()));

				List priceTags = rootNode.getElementListByAttValue("class",
						"market_listing_price market_listing_price_with_fee",
						true, false);

				// Get the one with the lowest USD. Wow exchange rates are dumb.
				// This should be fixed if valve does an api TODO
				String lowestPrice = null;
				for (int i = 0; i < priceTags.size(); i++) {
					TagNode priceTag = (TagNode) priceTags.get(i);
					String price = priceTag.getText().toString().trim();

					if (price.contains("&#36;")) {
						lowestPrice = price;
						break;
					}
				}

				lowestPrice = lowestPrice.substring(5);
				marketHistory.addPrice(Double.parseDouble(lowestPrice));

				// store the adjusted market history objects
				pm.makePersistent(marketHistory);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		pm.close();
		
		SteamMarketAnalyzerEmailer.sendEmails();
	}
}
