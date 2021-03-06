package by.epamjwd.mobile.controller.command.helpers;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Service;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.AttributeValue;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.ServiceService;
import by.epamjwd.mobile.service.exception.ServiceException;

public class ServiceCommandHelper {
	private ServiceCommandHelper() {
	}

	/**
	 * Method for handling the service by it's ID.
	 * 
	 * Retrieves by ID service from the data storage and sets it to the session
	 * 
	 * @param session   - http-session
	 * 
	 * @param serviceID - the ID of service
	 * 
	 * @param pagePath  - path to page for presenting the result to the client
	 * 
	 * @param logger    - logger to log an errors in the case they occur
	 * 
	 * @return - RouteHelper containing path to page and route method
	 */
	public static RouteHelper handleServiceByID(HttpSession session, long serviceID, String pagePath,
			RouteMethod routeMethod, Logger logger) {
		ServiceService serviceService = ServiceProvider.getInstance().getServiceService();
		RouteHelper result = RouteHelper.ERROR;
		try {
			Optional<Service> serviceOptional = serviceService.findServiceByID(serviceID);
			if (serviceOptional.isPresent()) {
				Service service = serviceOptional.get();
				session.setAttribute(AttributeName.SERVICE, service);
				result = new RouteHelper(pagePath, routeMethod);
			} else {
				session.setAttribute(AttributeName.ERROR, AttributeValue.NO_SERVICE);
				session.setAttribute(AttributeName.SERVICE_ID, serviceID);
				result = RouteHelper.ERROR_404;
			}
		} catch (ServiceException e) {
			logger.error("Unable to obtain full service data for ID " + serviceID, e);
			result = RouteHelper.ERROR_500;
		}
		return result;

	}

}
