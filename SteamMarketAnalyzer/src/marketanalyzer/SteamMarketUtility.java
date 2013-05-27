package marketanalyzer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class SteamMarketUtility {

	/**
	 * Gets the number of items listed on the steam market
	 * 
	 * @return
	 * @throws IOException
	 * @throws JSONException
	 */
	private static int getItemCount() throws IOException, JSONException {
		// Get the JSON for an empty query
		String query = getQueryString("", 0, 0);
		String queryResults = readWebPage(query);
		JSONObject j = new JSONObject(queryResults);
		boolean success = (Boolean) j.get("success");
		if (!success){
			throw new IOException();
		}
		
		// Get the number of items available
		return (Integer) j.get("total_count");
		
	}
	
	public static List<SteamMarketListing> getSteamMarketListings(){
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
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		return listings;
	}

	/**
	 * Gets the Steam Market Listings starting at a given index and continuing
	 * for the given number of indices
	 * 
	 * @param start
	 *            Index to start scraping at
	 * @param count
	 *            Number of listings to scrape
	 * @return Scraped market listings
	 * @throws IOException
	 *             When there is an error retrieving the data
	 * @throws JSONException
	 *             When there is an error retrieving the data
	 */
	private static List<SteamMarketListing> getSteamMarketListings(int start, int count)
			throws IOException, JSONException {
		// Get the JSON for count listings starting at start
		String query = getQueryString("", start, count);
		String queryResults = readWebPage(query);
		JSONObject j = new JSONObject(queryResults);
		boolean success = (Boolean) j.get("success");
		if (!success){
			throw new IOException();
		}
		
		// If start + count is more than the number of items available, adjust
		// the count to be correct
		int totalCount = (Integer) j.get("total_count");
		if (start + count > totalCount){
			count = totalCount - start;
		}
		
		// Get the results HTML
		String resultsHTML = (String) j.get("results_html");
		
		// Create the scraper object
		CleanerProperties props = new CleanerProperties();
		props.setTranslateSpecialEntities(true);
		props.setTransResCharsToNCR(true);
		props.setOmitComments(true);
		TagNode rootNode = new HtmlCleaner(props).clean(resultsHTML);
		
		// Get all the item page links
		List linkNodes = rootNode.getElementListByAttValue("class", "market_listing_row_link", true, false);
		
		// Get the Steam Listing for each result
		List<SteamMarketListing> listings = new ArrayList<SteamMarketListing>();
		for (int i = 0; i < count; i++) {
			TagNode resultDiv = (TagNode) rootNode.getElementListByAttValue(
					"id", "result_" + i, true, false).get(0);
			
			// Parse quantity
			String quantityStr = ((TagNode) resultDiv.getElementListByAttValue(
					"class", "market_listing_num_listings_qty", true, false)
					.get(0)).getText().toString();
			int quantity = Integer.parseInt(quantityStr.replace(",", ""));
			
			// Parse game name
			String gameName = ((TagNode) resultDiv.getElementListByAttValue(
					"class", "market_listing_game_name", true, false).get(0))
					.getText().toString();
			
			// Parse item name
			String itemName = ((TagNode) resultDiv.getElementListByAttValue(
					"class", "market_listing_item_name", true, false).get(0))
					.getText().toString();
			
			// Parse link
			String link = ((TagNode) linkNodes.get(i * 4))
					.getAttributeByName("href");
			
			// Parse starting price
			String startingPriceStr = ((TagNode) resultDiv
					.getElementListByAttValue(
							"class",
							"market_listing_right_cell market_listing_num_listings",
							true, false).get(0)).getText().toString();
			startingPriceStr = startingPriceStr.substring(
					startingPriceStr.indexOf("&#36;") + 5).trim();
			double startingPrice = Double.parseDouble(startingPriceStr);

			// Create the listing
			SteamMarketListing listing = new SteamMarketListing(startingPrice,
					quantity, gameName, itemName, link);
			listings.add(listing);
		}
		return listings;
	}

	/**
	 * Reads an HTTP web page
	 * 
	 * @param webpage
	 *            String URL of page to read
	 * @return Response String from page
	 * @throws IOException
	 *             Thrown if failure retrieving web page
	 */
	private static String readWebPage(String webpage) throws IOException {
		URL url = new URL(webpage);
		BufferedReader in = new BufferedReader(new InputStreamReader(
				url.openStream()));
		String inputLine;
		String result = "";
		while ((inputLine = in.readLine()) != null) {
			result += inputLine + "\n";
		}
		in.close();
		return result;
	}

	/**
	 * The web page for querying the database.
	 * 
	 * The web page will return a JSON object with the following result
	 * parameters: 
	 * 'success' is a boolean of whether the query was a success or not 
	 * 'start' is the start value given as integer in query 
	 * 'pagesize' is the number of results returned 
	 * 'total_count' is the number of total results available 
	 * 'results_html' is the HTML string
	 * 
	 * @param query
	 *            The search query. If empty, returns everything in the
	 *            database. Empty string for no query.
	 * @param start
	 *            The offset from the beginning of the results.
	 * @param count
	 *            How many results are returned.
	 * @return The URL of the query string
	 */
	private static String getQueryString(String query, int start, int count) {
		return "http://steamcommunity.com/market/search/render/?query=" + query
				+ "&start=" + start + "&count=" + count;
	}
	
}
