package marketanalyzer;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

import com.google.appengine.labs.repackaged.org.json.JSONException;

public class Test {

	public static void main(String[] args) throws IOException, JSONException{
//		CleanerProperties props = new CleanerProperties();
//		//props.setTranslateSpecialEntities(true);
//		//props.setTransResCharsToNCR(true);
//		//props.setOmitComments(true);
//		
//		TagNode rootNode = new HtmlCleaner(props).clean(new URL("http://steamcommunity.com/market/listings/440/Vintage%20Crit-a-Cola"));
//		
//		List priceTags = rootNode.getElementListByAttValue("class", "market_listing_price market_listing_price_with_fee", true, false);
//		
//		
//		// Get the one with the lowest USD. Wow exchange rates are dumb. This should be fixed
//		for (int i = 0; i < priceTags.size(); i++){
//			TagNode priceTag = (TagNode) priceTags.get(i);
//			String price = priceTag.getText().toString().trim();
//			// price contains $
//			if (price.contains("&#36;")){
//				System.out.println(price);
//				//break;
//			}
//		}

	List<SteamMarketListing> marketListings = SteamMarketUtility.getSteamMarketListings(0, 10);
	
	for (SteamMarketListing listing : marketListings){
		System.out.println(listing.getLink());
	}
	}
	
}
