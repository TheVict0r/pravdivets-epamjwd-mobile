package by.epamjwd.mobile.dao.impl;

import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.Article;
import by.epamjwd.mobile.dao.AbstractDao;
import by.epamjwd.mobile.dao.ArticleDAO;
import by.epamjwd.mobile.dao.SQLParametersHelper;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.dao.mapper.RowMapperFactory;
import by.epamjwd.mobile.dao.repository.DBColumnName;
import by.epamjwd.mobile.dao.repository.DBTableName;

/**
 * Class provides the SQL-database operations with news articles
 */
public class SQLArticleDAOImpl extends AbstractDao<Article> implements ArticleDAO {
	public final static String COMMA = ", ";
	public final static String QUESTION_MARK = "=?, ";

	private static final String ADD_NEW_ARTICLE = "INSERT INTO " + DBTableName.NEWS + " (" + DBColumnName.NEWS_DATE
			+ COMMA + DBColumnName.NEWS_TITLE + COMMA + DBColumnName.NEWS_INTRO + COMMA + DBColumnName.NEWS_TEXT
			+ ") VALUES (?, ?, ?, ?)";

	private static final String GET_ARTICLE_BY_TITLE = "SELECT * FROM " + DBTableName.NEWS + " WHERE "
			+ DBColumnName.NEWS_TITLE + "= ?";

	public SQLArticleDAOImpl() {
		super(RowMapperFactory.getInstance().getNewsRowMapper(), DBTableName.NEWS);
	}

	/**
	 * Provides all news articles as a list in descending order (from the most
	 * recent to the earliest).
	 * 
	 * @return list of all news articles from from the most recent to the earliest.
	 * 
	 * @throws DaoException if SQLException occurs
	 */
	@Override
	public List<Article> getAllArticles() throws DaoException {
		return findALLDescending();
	}

	/**
	 * Provides news article retrieved by it's ID.
	 * 
	 * @throws DaoException if SQLException occurs
	 */
	@Override
	public Optional<Article> getArticleByID(long id) throws DaoException {
		return findById(id);
	}

	/**
	 * Retrieves news article by it's title
	 * 
	 * @return news article as an Optional value
	 * 
	 * @throws DaoException if SQLException occurs
	 */
	@Override
	public Optional<Article> getArticleByTitle(String title) throws DaoException {
		return executeQueryForSingleResult(GET_ARTICLE_BY_TITLE, title);
	}

	/**
	 * Adds news article to the database.
	 * 
	 * @return new article ID
	 * 
	 * @throws DaoException if SQLException occurs
	 */
	@Override
	public long addArticle(Article article) throws DaoException {
		Object[] params = SQLParametersHelper.provideNewArticleParameters(article);
		long articleID = executeInsertQuery(ADD_NEW_ARTICLE, params);
		return articleID;
	}

}
