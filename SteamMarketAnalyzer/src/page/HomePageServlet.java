package page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.Authenticator;

public class HomePageServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		if (!Authenticator.isAuthenticated()){
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		
		request.getRequestDispatcher("home.jsp").forward(request, response);
		
	}
}
