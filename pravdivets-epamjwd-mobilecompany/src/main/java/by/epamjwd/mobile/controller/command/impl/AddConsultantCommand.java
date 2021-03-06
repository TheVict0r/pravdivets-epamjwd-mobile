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
import by.epamjwd.mobile.controller.command.helpers.ConsultantCommandHelper;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.AttributeValue;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.controller.repository.ParameterName;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.UserService;
import by.epamjwd.mobile.service.exception.ServiceException;
import by.epamjwd.mobile.service.validation.InputDataValidator;

public class AddConsultantCommand implements Command{
	private final static Logger LOGGER = LogManager.getLogger(AddSubscriberCommand.class);
	
	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		RouteHelper result = RouteHelper.ERROR;
		HttpSession session = request.getSession();
		
		String firstName  = request.getParameter(ParameterName.FIRST_NAME);
		String middleName = request.getParameter(ParameterName.MIDDLE_NAME);
		String lastName   = request.getParameter(ParameterName.LAST_NAME);
		String passport   = request.getParameter(ParameterName.PASSPORT);
		String email      = request.getParameter(ParameterName.EMAIL);
		String password1  = request.getParameter(ParameterName.PASSWORD1);
		String password2  = request.getParameter(ParameterName.PASSWORD2);
		
		if( firstName == null || firstName.isBlank()  || 
			 lastName == null || lastName.isBlank()   ||
			 passport == null || passport.isBlank()   ||
			    email == null || email.isBlank()      ||
			password1 == null || password1.isBlank()  ||
			password2 == null || password2.isBlank()) {
			session.setAttribute(AttributeName.WRONG_DATA, AttributeValue.WRONG_DATA);
			return new RouteHelper(PagePath.ADD_CONSULTANT_REDIRECT, RouteMethod.REDIRECT);
		}
		
		UserService userService = ServiceProvider.getInstance().getUserService();

		try {
			if (userService.isEmailBooked(email)) {
				return provideErrorMessage(session, AttributeValue.BOOKED_EMAIL, firstName, middleName, lastName,
						passport, password1, password2, email);
			}
		} catch (ServiceException e) {
			LOGGER.error("Error while verifying is email booked " + email, e);
			return RouteHelper.ERROR_500;
		}

		try {
			if (userService.isPassportBooked(passport)) {
				return provideErrorMessage(session, AttributeValue.BOOKED_PASSPORT, firstName, middleName, lastName,
						passport, password1, password2, email);
			}
		} catch (ServiceException e) {
			LOGGER.error("Error while verifying is passport booked " + passport, e);
			return RouteHelper.ERROR_500;
		}

		if (!password1.equals(password2)) {
			return provideErrorMessage(session, AttributeValue.MISSMATCHED_PASSWORDS, firstName, middleName, lastName,
					passport, password1, password2, email);
		}

		if (!InputDataValidator.isPassword(password1)) {
			return provideErrorMessage(session, AttributeValue.INCORRECT_PASSWORD, firstName, middleName, lastName,
					passport, password1, password2, email);
		}

		clearAttributes(session);
		
		try {
			long consultantID = userService.addUser(userService.buildConsultantUser(firstName, middleName, lastName, 
					password1, passport, email));
			Optional<User> consultantOptional = userService.findUserById(consultantID);
			result = ConsultantCommandHelper.handleConsultantOptional(consultantOptional, session, 
					AttributeName.WRONG_DATA, AttributeValue.WRONG_DATA);
		} catch (ServiceException e) {
			LOGGER.error("Error while adding a new consultant.", e);
			return RouteHelper.ERROR_500;
		}

		return result;
	}

	
	private RouteHelper provideErrorMessage(HttpSession session, String attributeValue, String firstName,
			String middleName, String lastName, String passport, String password1, String password2, String email) {
		session.setAttribute(AttributeName.ERROR, attributeValue);
		session.setAttribute(AttributeName.CONSULTATNT_FIRST_NAME, firstName);
		session.setAttribute(AttributeName.CONSULTATNT_MIDDLE_NAME, middleName);
		session.setAttribute(AttributeName.CONSULTATNT_LAST_NAME, lastName);
		session.setAttribute(AttributeName.PASSPORT, passport);
		session.setAttribute(AttributeName.PASSWORD1, password1);
		session.setAttribute(AttributeName.PASSWORD2, password2);
		session.setAttribute(AttributeName.EMAIL, email);
		return new RouteHelper(PagePath.ADD_CONSULTANT_REDIRECT, RouteMethod.REDIRECT);
	}

	private void clearAttributes(HttpSession session) {
		session.removeAttribute(AttributeName.ERROR);
		session.removeAttribute(AttributeName.CONSULTATNT_FIRST_NAME);
		session.removeAttribute(AttributeName.CONSULTATNT_MIDDLE_NAME);
		session.removeAttribute(AttributeName.CONSULTATNT_LAST_NAME);
		session.removeAttribute(AttributeName.PASSPORT);
		session.removeAttribute(AttributeName.PASSWORD1);
		session.removeAttribute(AttributeName.PASSWORD2);
		session.removeAttribute(AttributeName.EMAIL);
	}
	
	
}
