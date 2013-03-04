package marketanalyzer.JDO;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class MarketHistoryJDO {

	@PrimaryKey
	private String url;
	
	/**
	 * List of prices
	 */
	@Persistent
	private List<Double> priceHistory;
	
	private static final int MAX_CAPACITY = 84;
	
	public MarketHistoryJDO(String url){
		this.url = url;
		priceHistory = new ArrayList<Double>();
	}
	
	public String getUrl(){
		return this.url;
	}
	
	public List<Double> getPriceHistory(){
		return this.priceHistory;
	}
	
	public void addPrice(double price){
		if (priceHistory.size() == MAX_CAPACITY){
			priceHistory.remove(0);
		}
		priceHistory.add(price);
	}
	
	public double averagePrice(){
		double sum = 0;
		for (Double d : priceHistory){
			sum += d;
		}
		
		double average = sum / priceHistory.size();
		return average;
	}
	
	public double mostRecent(){
		return priceHistory.get(priceHistory.size() - 1);
	}
	

}
