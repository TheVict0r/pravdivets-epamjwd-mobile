package by.epamjwd.mobile.controller.command.impl;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.command.helpers.SubscriberCommandHelper;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.AttributeValue;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.controller.repository.ParameterName;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.SubscriberService;
import by.epamjwd.mobile.service.exception.ServiceException;

public class FindSubscriberByPhoneCommand implements Command {

	private final static Logger LOGGER = LogManager.getLogger(FindSubscriberByPhoneCommand.class);

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		SubscriberService subscriberService = ServiceProvider.getInstance().getSubscriberService();
		String phone = request.getParameter(ParameterName.PHONE);
		HttpSession session = request.getSession();

		if (phone == null || phone.isBlank()) {
			session.setAttribute(AttributeName.WRONG_DATA, AttributeValue.WRONG_DATA);
			return new RouteHelper(PagePath.SUBSCRIBER_OPERATIONS_REDIRECT, RouteMethod.REDIRECT);
		}

		RouteHelper result = RouteHelper.ERROR;
		try {
			Optional<Subscriber> subscriberOptional = subscriberService.findSubscriberByPhone(phone);

			if (subscriberOptional.isPresent()) {
				Subscriber subscriber = subscriberOptional.get();
				result = SubscriberCommandHelper.handleSubscriber(request, subscriber);
			} else {
				request.setAttribute(AttributeName.ERROR, AttributeValue.WRONG_PHONE);
				request.setAttribute(AttributeName.PHONE, phone);
				result = new RouteHelper(PagePath.SUBSCRIBER_OPERATIONS, RouteMethod.FORWARD);
			}
		} catch (ServiceException e) {
			LOGGER.error("Unable to obtain data for phone number " + phone, e);
			result = RouteHelper.ERROR_500;
		}
		return result;
	}

}
