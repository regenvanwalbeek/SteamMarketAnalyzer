package page;

import java.io.IOException;
import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import marketanalyzer.SteamMarketListing;
import marketanalyzer.SteamMarketUtility;
import marketanalyzer.JDO.MarketHistoryJDO;
import marketanalyzer.JDO.PMF;
import auth.Authenticator;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class MonitorPageServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		if (!Authenticator.isAuthenticated()){
			response.sendRedirect("/");
			return;
		}
		
		// Get the user account
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		request.setAttribute("user", user);
		
		// Get the logout URL
		String logoutURL = userService.createLogoutURL(request.getRequestURI());
		request.setAttribute("logoutURL", logoutURL);
		
		// Get the login URL
		String loginURL = userService.createLoginURL(request.getRequestURI());
		request.setAttribute("loginURL", loginURL);
		
		request.getRequestDispatcher("monitor.jsp").forward(request, response);
		
	}
	
	private static double PERCENT = .3;
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// Find listings under a percentage
		List<SteamMarketListing> listings = SteamMarketUtility.getSteamMarketListings();
	
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String messageBody = "";
		for (int i = 0; i < listings.size(); i++) {
			SteamMarketListing listing = listings.get(i);
			MarketHistoryJDO history;
			try {
				history = (MarketHistoryJDO) pm.getObjectById(MarketHistoryJDO.class, listing
						.getLink());
				double average = history.averagePrice();
				double startingPrice = listing.getStartingPrice();
				if (startingPrice < (1 - PERCENT) * average){
					messageBody += "Item:" + history.getUrl() + "<br />";
					messageBody += "Current: " + startingPrice + "<br />";
					messageBody += "Average: " + average + "<br />";
					messageBody += "<br />";
				}
			} catch (JDOObjectNotFoundException e) {
				// Item not found - listed since last crawl. Just wait for the item to be crawled.
			}
		}
		response.getWriter().write(messageBody);

		pm.close();
	}
}
