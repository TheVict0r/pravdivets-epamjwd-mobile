package by.epamjwd.mobile.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.PagePath;

public class LogoutCommand implements Command {

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.removeAttribute(AttributeName.FIRST_NAME);
		session.removeAttribute(AttributeName.LAST_NAME);
		session.removeAttribute(AttributeName.USER_ID);
		session.invalidate();
		RouteHelper result = null;
		result = new RouteHelper(PagePath.LOGIN, RouteMethod.FORWARD);
		return result;
	}

}