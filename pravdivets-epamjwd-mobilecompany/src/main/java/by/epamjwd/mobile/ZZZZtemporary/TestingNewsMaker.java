package by.epamjwd.mobile.ZZZZtemporary;

import java.util.Optional;

import by.epamjwd.mobile.bean.Article;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.dao.impl.SQLNewsDAOImpl;

//import java.util.List;
//
//import by.epamtc.mobile.entity.NewsArticle;
//import by.epamtc.mobile.service.NewsMaker;

public class TestingNewsMaker {

	public static final String SELECT_FROM_NEWS = "SELECT * FROM NEWS";

	public static void main(String[] args) throws DaoException {
		//NewsMaker newsmaker = new NewsMaker();
		//List<NewsArticle> newsArticles = newsmaker.selectNewsSetFromDatabase(SELECT_FROM_NEWS);
		//System.out.println(newsArticles.get(newsArticles.size()-1));
//		System.out.println();
//		System.out.println(newsArticles.get(newsArticles.size()-2));
//		System.out.println();
//		System.out.println(newsArticles.get(newsArticles.size()-3));
		
		SQLNewsDAOImpl dao = new SQLNewsDAOImpl();
		Optional<Article> article = dao.getArticleByID(10);
		
		System.out.println(article);
		
	}

}
