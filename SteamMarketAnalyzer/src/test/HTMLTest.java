package test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import marketanalyzer.SteamMarketListing;
import marketanalyzer.SteamMarketUtility;

import com.google.appengine.labs.repackaged.org.json.JSONException;

public class HTMLTest {

	public static void main(String[] args) throws MalformedURLException, IOException, JSONException {
		
		
		
		//String queryResults = readWebPage(webpage);	
		
		/*
		JSONObject j = new JSONObject(queryResults);
		String resultsHTML = (String) j.get("results_html");
		System.out.println(resultsHTML);
		
		// Create the scraper object
		CleanerProperties props = new CleanerProperties();
		props.setTranslateSpecialEntities(true);
		props.setTransResCharsToNCR(true);
		props.setOmitComments(true);
		
		TagNode rootNode = new HtmlCleaner(props).clean(resultsHTML);
		rootNode.getEl*/
		List<SteamMarketListing> listings = new ArrayList<SteamMarketListing>();
		int itemCount = SteamMarketUtility.getItemCount();
		for (int i = 0; i < itemCount; i+=100){
			List<SteamMarketListing> newListings = SteamMarketUtility.getSteamMarketListings(i, 100);
			for (int j = 0; j < newListings.size(); j++){
				listings.add(newListings.get(j));
			}
			System.out.println(i);
		}
		
	}

	


}
