package by.epamjwd.mobile.controller.command.impl;

import java.util.NoSuchElementException;

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

public class AbonentForStuffCommand implements Command {

	private final static Logger LOGGER = LogManager.getLogger(RouteHelper.class);

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {

		ServiceProvider provider = ServiceProvider.getInstance();
		AbonentService abonentService = provider.getAbonentService();

		int phoneNumber = Integer.parseInt(request.getParameter(ParameterName.PHONE_NUMBER));
		RouteHelper result = null;
		try {
			Abonent abonent = abonentService.getAbonentByPhoneNumber(phoneNumber).get();
			request.setAttribute(AttributeName.ABONENT, abonent);
			result = new RouteHelper(PagePath.ABONENT_FOR_STUFF, RouteMethod.FORWARD);
		} catch (ServiceException| NoSuchElementException e) {
			LOGGER.error("Unable to obtain data for phone number " + phoneNumber, e);
			result = new RouteHelper(PagePath.ERROR_404, RouteMethod.FORWARD);
		}
		return result;
	}
}