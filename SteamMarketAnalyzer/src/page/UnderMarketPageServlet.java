package page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.Authenticator;

public class UnderMarketPageServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		if (!Authenticator.isAuthenticated()){
			response.sendRedirect("/");
			return;
		}
		
		request.getRequestDispatcher("undermarket.jsp").forward(request, response);
		
	}
}
