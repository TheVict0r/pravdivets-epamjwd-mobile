package by.epamjwd.mobile.controller.command.impl;

import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Service;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.controller.repository.ParameterName;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.ServiceService;
import by.epamjwd.mobile.service.exception.ServiceException;

public class ShowFullServiceCommand implements Command{

	private final static Logger LOGGER = LogManager.getLogger(ShowFullServiceCommand.class);

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceProvider provider = ServiceProvider.getInstance();
		ServiceService serviceService = provider.getServiceService();

		int id = Integer.parseInt(request.getParameter(ParameterName.ID));
		RouteHelper result = null;
		try {
			Service service = serviceService.findServiceByID(id).get();
			request.setAttribute(AttributeName.SERVICE, service);
			result = new RouteHelper(PagePath.SERVICE, RouteMethod.FORWARD);
		} catch (ServiceException | NoSuchElementException e) {
			LOGGER.error("Unable to obtain full service data for ID " + id, e);
			result = new RouteHelper(PagePath.ERROR_404, RouteMethod.FORWARD);
		}
		return result;
	}

}