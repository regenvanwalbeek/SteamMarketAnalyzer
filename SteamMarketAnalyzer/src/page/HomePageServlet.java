package page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class HomePageServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 404 if not home page
		if (!request.getRequestURI().equals("/")){
			request.getRequestDispatcher("404.jsp").forward(request, response);
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
		
		request.getRequestDispatcher("home.jsp").forward(request, response);
		
	}
}
