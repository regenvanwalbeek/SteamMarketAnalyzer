package auth;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

/**
 * Validates the user.
 * 
 * @author Regen Van Walbeek
 */
public class Authenticator {
	
	/**
	 * Checks if the user is valid.
	 * @param user
	 * @return
	 */
	public static boolean isAuthenticated() {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
		if (user == null){
			return false;
		}
		
		String email = user.getEmail();
		if (email.equals("regenvanwalbeek@gmail.com")
				|| email.equals("jesse.roose@gmail.com")) {
			return true;
		}
		return false;
	}

}
