package marketanalyzer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.*;

import marketanalyzer.JDO.MarketHistoryJDO;
import marketanalyzer.JDO.PMF;

/**
 * A servlet which analyzes the information in the datastore and produces a report on cheap items. 
 * 
 * @author Regen Van Walbeek
 */
@SuppressWarnings("serial")
public class SteamMarketAnalyzerServlet extends HttpServlet {
	
	// The fee valve charges when selling
	private static final double FEE = .13;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/plain");

		PersistenceManager pm = PMF.get().getPersistenceManager();
		ArrayList<MarketHistoryJDO> marketHistoryItems = new ArrayList<MarketHistoryJDO>();
		
		Query q = pm.newQuery(MarketHistoryJDO.class);
		
		for (MarketHistoryJDO history : marketHistoryItems){
			double average = history.averagePrice();
			double mostRecent = history.mostRecent();
			
			if (mostRecent < (1 - FEE) * average){
				response.getWriter().write("Item:" + history.getUrl() + "\n");
				response.getWriter().write("Current: " + mostRecent + "\n");
				response.getWriter().write("Average: " + average + "\n");
				response.getWriter().write("\n");
			}
			
		}
		
		response.getWriter().write("end report");
		pm.close();
	}
}
