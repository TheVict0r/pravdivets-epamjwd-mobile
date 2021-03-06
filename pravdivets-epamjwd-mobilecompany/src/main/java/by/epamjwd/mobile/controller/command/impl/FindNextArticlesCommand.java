package by.epamjwd.mobile.controller.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Article;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.command.NumericParser;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.AttributeValue;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.repository.IndexRepository;
import by.epamjwd.mobile.repository.ListDirection;
import by.epamjwd.mobile.service.ArticleService;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.exception.ServiceException;

public class FindNextArticlesCommand implements Command {

	private final static Logger LOGGER = LogManager.getLogger(FindAllArticlesCommand.class);

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		ArticleService articleService = ServiceProvider.getInstance().getArticleService();
		HttpSession session = request.getSession();

		ListDirection previousDirection = (ListDirection) session.getAttribute(AttributeName.DIRECTION);
		int currentIdx = NumericParser.parseUnsignedIntValue(session.getAttribute(AttributeName.CURRENT_IDX));

		session.removeAttribute(AttributeName.NO_PREVIOUS_NEWS);

		try {

			int toIndex = articleService.calculateToIndex(currentIdx, IndexRepository.STEP, ListDirection.TO_BEGINNING,
					previousDirection);

			int fromIndex = articleService.calculateFromIndex(toIndex, IndexRepository.STEP, ListDirection.TO_BEGINNING,
					previousDirection);

			List<Article> newsBatch = articleService.buildArticlesBatch(fromIndex, toIndex);
			session.setAttribute(AttributeName.ALL_ARTICLES, newsBatch);
			session.setAttribute(AttributeName.CURRENT_IDX, fromIndex);
			session.setAttribute(AttributeName.DIRECTION, ListDirection.TO_BEGINNING);

			if (articleService.isPreviousIndexZero(toIndex, IndexRepository.STEP)) {
				// if ((toIndex - IndexRepository.STEP) == IndexRepository.ZERO_INDEX) {
				session.setAttribute(AttributeName.NO_NEXT_NEWS, AttributeValue.TRUE);
				session.setAttribute(AttributeName.CURRENT_IDX, fromIndex);
			}

			return new RouteHelper(PagePath.ALL_NEWS_REDIRECT, RouteMethod.REDIRECT);

		} catch (ServiceException e) {
			LOGGER.error("Unable to obtain news list. ", e);
			return RouteHelper.ERROR_500;
		}

	}

}
