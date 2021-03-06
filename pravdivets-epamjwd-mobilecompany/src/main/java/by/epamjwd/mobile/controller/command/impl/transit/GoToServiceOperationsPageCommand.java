package by.epamjwd.mobile.controller.command.impl.transit;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Service;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.command.SessionCleaner;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.ServiceService;
import by.epamjwd.mobile.service.exception.ServiceException;

public class GoToServiceOperationsPageCommand implements Command {

	private final static Logger LOGGER = LogManager.getLogger(GoToServiceOperationsPageCommand.class);
	
	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		SessionCleaner.getInstance().removeUnusedAttributes(request);
		ServiceService serviceService = ServiceProvider.getInstance().getServiceService();
		RouteHelper result = RouteHelper.ERROR;
		try {
			List<Service> serviceList = serviceService.findAllServices();
			request.setAttribute(AttributeName.ALL_SERVICES, serviceList);
			result = new RouteHelper(PagePath.SERVICE_OPERATIONS, RouteMethod.FORWARD);
		} catch (ServiceException e) {
			LOGGER.error("Unable to obtain service list. ", e);
			result = RouteHelper.ERROR_500;
		}

		return result;
	}
}
