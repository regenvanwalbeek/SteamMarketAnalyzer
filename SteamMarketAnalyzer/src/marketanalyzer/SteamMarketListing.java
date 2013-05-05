package marketanalyzer;

public class SteamMarketListing {

	private double startingPrice;

	private int quantity;

	private String gameName;

	private String itemName;

	private String link;

	public SteamMarketListing(double startingPrice, int quantity, String gameName,
			String itemName, String link) {
		this.startingPrice = startingPrice;
		this.quantity = quantity;
		this.gameName = gameName;
		this.itemName = itemName;
		this.link = link;
	}
	
	public double getStartingPrice(){
		return this.startingPrice;
	}
	
	public int getQuantity(){
		return this.quantity;
	}
	
	public String getGameName(){
		return this.gameName;
	}
	
	public String getItemName(){
		return this.itemName;
	}
	
	public String getLink(){
		return this.link;
	}

}
