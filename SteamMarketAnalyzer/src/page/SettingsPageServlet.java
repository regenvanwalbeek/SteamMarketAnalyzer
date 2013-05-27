package page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.Authenticator;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class SettingsPageServlet extends HttpServlet{

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
		
		request.getRequestDispatcher("settings.jsp").forward(request, response);
		
	}
	
}
