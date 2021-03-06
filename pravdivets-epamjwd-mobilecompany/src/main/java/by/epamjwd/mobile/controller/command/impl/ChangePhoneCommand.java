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
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.AttributeValue;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.SubscriberService;
import by.epamjwd.mobile.service.exception.ServiceException;
import by.epamjwd.mobile.util.PhoneFormatter;
import by.epamjwd.mobile.util.PhoneGenerator;

public class ChangePhoneCommand implements Command{
	private final static Logger LOGGER = LogManager.getLogger(EditPersonalDataCommand.class);
	
	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.removeAttribute(AttributeName.ACTIVATE_EDIT);
		session.removeAttribute(AttributeName.NEW_PHONE_FORMAT);

		Subscriber subscriber = (Subscriber) session.getAttribute(AttributeName.SUBSCRIBER);

		if (subscriber == null) {
			session.setAttribute(AttributeName.WRONG_DATA, AttributeValue.WRONG_DATA);
			return new RouteHelper(PagePath.SUBSCRIBER_REDIRECT, RouteMethod.REDIRECT);
		}

		String newPhone;
		try {
			newPhone = PhoneGenerator.getInstance().provideFreePhone();
			session.setAttribute(AttributeName.NEW_PHONE, newPhone);
		} catch (ServiceException e) {
			LOGGER.error("Error during new phone number generation pricess", e);
			return RouteHelper.ERROR_500;
		}

		long subscriberID = subscriber.getId();
		subscriber.setPhone(newPhone);

		SubscriberService subscriberService = ServiceProvider.getInstance().getSubscriberService();
		try {
			subscriberService.updateSubscriber(subscriber);
		} catch (ServiceException e) {
			LOGGER.error("Error during updating subscriber data", e);
			return RouteHelper.ERROR_500;
		}

		try {
			Optional<Subscriber> updatedSubscriberOptional = subscriberService.findSubscriberById(subscriberID);
			if (updatedSubscriberOptional.isPresent()) {
				Subscriber updatedSubscriber = updatedSubscriberOptional.get();
				session.setAttribute(AttributeName.SUBSCRIBER, updatedSubscriber);
				String newPhoneFromDatabase = updatedSubscriber.getPhone();
				String newPhoneFormat = PhoneFormatter.formatPhone(newPhoneFromDatabase);
				session.setAttribute(AttributeName.PHONE_FORMAT, newPhoneFormat);
			}
		} catch (ServiceException e) {
			LOGGER.error("Error retrieving updated subscriber", e);
			return RouteHelper.ERROR_500;
		}

		return new RouteHelper(PagePath.SUBSCRIBER_REDIRECT, RouteMethod.REDIRECT);
	}

}
