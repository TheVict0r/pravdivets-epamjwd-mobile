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

public class FindConsultantByPassportCommand implements Command {
	private final static Logger LOGGER = LogManager.getLogger(FindConsultantByPassportCommand.class);

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		String passport = request.getParameter(ParameterName.PASSPORT);
		UserService userService = ServiceProvider.getInstance().getUserService();
		HttpSession session = request.getSession();
		
		if(passport == null || passport.isBlank()) {
			session.setAttribute(AttributeName.WRONG_DATA, AttributeValue.WRONG_DATA);
			return new RouteHelper(PagePath.CONSULTANT_OPERATIONS_REDIRECT, RouteMethod.REDIRECT);
		}
		
		RouteHelper result = RouteHelper.ERROR;
		
		try {
			Optional<User> consultantOptional = userService.findUserByPassport(passport);
			result = ConsultantCommandHelper.handleConsultantOptional(consultantOptional, 
					session, AttributeName.PASSPORT, passport);
		} catch (ServiceException e) {
			LOGGER.error("Unable to obtain consultant data for passport number " + passport, e);
			result = RouteHelper.ERROR_500;
		}
		return result;
	}

}