package by.epamjwd.mobile.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.command.NumericParser;
import by.epamjwd.mobile.controller.command.helpers.ServiceCommandHelper;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.AttributeValue;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.controller.repository.ParameterName;

public class FindServiceByIdAdminCommand implements Command {

	private final static Logger LOGGER = LogManager.getLogger(FindServiceByIdAdminCommand.class);

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		long id = NumericParser.parseUnsignedLongValue(request.getParameter(ParameterName.ID));
		if (id == NumericParser.INVALID_VALUE) {
			session.setAttribute(AttributeName.WRONG_DATA, AttributeValue.WRONG_DATA);
			return RouteHelper.ERROR_404;
		}

		return ServiceCommandHelper.handleServiceByID(session, id, PagePath.SERVICE_ADMIN_REDIRECT,
				RouteMethod.REDIRECT, LOGGER);

	}

}
