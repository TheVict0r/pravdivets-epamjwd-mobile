package by.epamjwd.mobile.controller.command.helpers;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Plan;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.AttributeValue;
import by.epamjwd.mobile.service.PlanService;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.exception.ServiceException;

public class PlanCommandHelper {

	private PlanCommandHelper() {
	}

	/**
	 * Method for handling the tariff plan by it's ID.
	 * 
	 * Retrieves by ID tariff plan from the data storage and sets it to the session 
	 * 
	 * @param session - http-session
	 * 
	 * @param planID - the ID of tariff plan
	 * 
	 * @param pagePath - path to page for presenting the result to the client
	 * 
	 * @param logger - logger to log an errors in the case they occur 
	 * 
	 * @return - RouteHelper containing path to page and route method
	 */
	public static RouteHelper handlePlanByID(HttpSession session, long planID, String pagePath, RouteMethod routeMethod, Logger logger) {
		RouteHelper result = RouteHelper.ERROR;
		PlanService planService = ServiceProvider.getInstance().getPlanService();
		try {
			Optional<Plan> planOptional = planService.findPlanByID(planID);
			if (planOptional.isPresent()) {
				Plan plan = planOptional.get();
				session.setAttribute(AttributeName.PLAN, plan);
				result = new RouteHelper(pagePath, routeMethod);
			} else {
				session.setAttribute(AttributeName.ERROR, AttributeValue.NO_PLAN);
				session.setAttribute(AttributeName.PLAN_ID, planID);
				result = RouteHelper.ERROR_404;
			}
		} catch (ServiceException e) {
			logger.error("Unable to obtain full tariff plan data. ", e);
			result = RouteHelper.ERROR_500;
		}
		return result;
	}

}
