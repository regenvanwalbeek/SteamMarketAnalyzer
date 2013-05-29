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

	private static final int MAX_CAPACITY = 168;

	/**
	 * Sum of the prices in the history - used to save read-write ops when
	 * calculating averages.
	 */
	@Persistent
	private Double priceSum;

	public MarketHistoryJDO(String url) {
		this.url = url;
		priceHistory = new ArrayList<Double>();
		priceSum = 0.0;
	}

	public String getUrl() {
		return this.url;
	}

	public List<Double> getPriceHistory() {
		return this.priceHistory;
	}

	public void addPrice(double price) {
		// Remove any excess prices.
		while (priceHistory.size() >= MAX_CAPACITY) {
			priceHistory.remove(0);
			priceSum -= price;
		}

		// Add the new price
		priceHistory.add(price);
		priceSum += price;
	}

	public double averagePrice() {
		return priceSum / priceHistory.size();
	}

	public double mostRecent() {
		return priceHistory.get(priceHistory.size() - 1);
	}

}
