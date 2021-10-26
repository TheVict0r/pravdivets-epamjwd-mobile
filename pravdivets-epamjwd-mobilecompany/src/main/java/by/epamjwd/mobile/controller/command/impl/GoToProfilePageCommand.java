package by.epamjwd.mobile.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.bean.Role;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.AttributeValue;
import by.epamjwd.mobile.controller.repository.PagePath;

public class GoToProfilePageCommand implements Command{

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.removeAttribute(AttributeName.PASSPORT);
		session.removeAttribute(AttributeName.PHONE);
		session.removeAttribute(AttributeName.PHONE_FORMAT);
		session.removeAttribute(AttributeName.SUBSCRIBER);
		session.removeAttribute(AttributeName.SUBSCRIBER_USER_ID);
		
		Role role = (Role) session.getAttribute(AttributeName.ROLE);
		
		if(role == null) {
			request.setAttribute(AttributeName.SESSION_TIME_OUT, AttributeValue.SESSION_TIME_OUT);
			return new RouteHelper(PagePath.LOGIN, RouteMethod.FORWARD);
		}
		
		RouteHelper result;
		switch (role) {
		case ADMIN:
			result = new RouteHelper(PagePath.ADMIN, RouteMethod.FORWARD);
			break;
		case CONSULTANT:
			result = new RouteHelper(PagePath.SUBSCRIBER_BASE, RouteMethod.FORWARD);
			break;
		case SUBSCRIBER:
			result = new RouteHelper(PagePath.SUBSCRIBER_LIST_REDIRECT, RouteMethod.REDIRECT);
			break;
		default:
			result = new RouteHelper(PagePath.ERROR, RouteMethod.FORWARD);
		}
		return result;
	}

}
