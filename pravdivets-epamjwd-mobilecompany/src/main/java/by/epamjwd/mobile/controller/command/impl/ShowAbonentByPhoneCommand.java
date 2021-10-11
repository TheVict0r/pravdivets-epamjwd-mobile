package by.epamjwd.mobile.controller.command.impl;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Abonent;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.controller.repository.ParameterName;
import by.epamjwd.mobile.service.AbonentService;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.exception.ServiceException;
import by.epamjwd.mobile.util.PhoneNumberFormatter;

public class ShowAbonentByPhoneCommand implements Command {
	
	private final static Logger LOGGER = LogManager.getLogger(ShowAbonentByPhoneCommand.class);

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {

		ServiceProvider provider = ServiceProvider.getInstance();
		AbonentService abonentService = provider.getAbonentService();

		Integer phoneNumber = Integer.parseInt(request.getParameter(ParameterName.PHONE_NUMBER));
		PhoneNumberFormatter numberFormatter = new PhoneNumberFormatter();

		RouteHelper result = null;
		try {
			Abonent abonent = abonentService.findAbonentByPhoneNumber(phoneNumber);
			request.setAttribute(AttributeName.ABONENT, abonent);
			String phoneNumberFormat = numberFormatter.formatPhomeNumber(phoneNumber);
			request.setAttribute(AttributeName.PHONE_NUMBER, phoneNumberFormat);
			result = new RouteHelper(PagePath.ABONENT, RouteMethod.FORWARD);
		} catch (ServiceException e) {
			LOGGER.error("Unable to obtain data for phone number " + phoneNumber, e);
			result = new RouteHelper(PagePath.ERROR_404, RouteMethod.FORWARD);
		}
		return result;
	}

}