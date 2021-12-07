package by.epamjwd.mobile.controller.command.impl;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.controller.repository.ParameterName;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.UserService;
import by.epamjwd.mobile.service.exception.ServiceException;

public class EditPersonalDataCommand implements Command{
	private final static Logger LOGGER = LogManager.getLogger(EditPersonalDataCommand.class);

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceProvider provider = ServiceProvider.getInstance();
		UserService userService = provider.getUserService();
		
		HttpSession session = request.getSession();
		session.removeAttribute(AttributeName.ACTIVATE_EDIT);
		User user = (User)session.getAttribute(AttributeName.SUBSCRIBER_USER);
		long userID = user.getId();
		
		String newFirstName = request.getParameter(ParameterName.SUBSCRIBER_USER_FIRST_NAME);
		String newMiddleName = request.getParameter(ParameterName.SUBSCRIBER_USER_MIDDLE_NAME);
		String newLastName = request.getParameter(ParameterName.SUBSCRIBER_USER_LAST_NAME);
		String newPassport = request.getParameter(ParameterName.PASSPORT);
		String newEmail = request.getParameter(ParameterName.EMAIL);
		
		user.setFirstName(newFirstName);
		user.setMiddleName(newMiddleName);
		user.setLastName(newLastName);
		user.setPassport(newPassport);
		user.setEmail(newEmail);
		
		try {
			userService.updateUser(user);
		} catch (ServiceException e) {
			LOGGER.error("Error during updating user data", e);
			return RouteHelper.ERROR_500;
		}
		
		try {
			Optional<User> userOptional = userService.findUserById(userID);
			if(userOptional.isPresent()) {
				User updatedUser = userOptional.get();
				session.setAttribute(AttributeName.SUBSCRIBER_USER, updatedUser);
			}
		} catch (ServiceException e) {
			LOGGER.error("Error retrieving updated user", e);
			return  RouteHelper.ERROR_500;
		}
		
		return new RouteHelper(PagePath.SUBSCRIBER_REDIRECT, RouteMethod.REDIRECT);
	}

}
