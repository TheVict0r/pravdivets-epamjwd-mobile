package by.epamjwd.mobile.service.validation;

import java.util.Date;
import java.util.regex.Pattern;

import by.epamjwd.mobile.bean.Article;
import by.epamjwd.mobile.bean.Plan;
import by.epamjwd.mobile.bean.Role;
import by.epamjwd.mobile.bean.Service;
import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.bean.SubscriberStatus;
import by.epamjwd.mobile.bean.User;

public class InputDataValidator {

	private InputDataValidator() {
	}
	
	public static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
	public static final String PHONE_NUMBER_REGEX = "^(25|29|33|44|55)[0-9]{7}$";
	public static final String NAME_REGEX = "^([А-Я]{1}[а-яё-]+|[A-Z]{1}[a-z-]+)$";
	public static final String PASSPORT_REGEX = "^[A-Z]{2}[0-9]{7}$";
	
	/*
	 * The password must be at least 8 characters long, 
	 * contain uppercase and lowercase letters, and at least 1 number. 
	 * Any other characters are not allowed.
	 */
	public static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
	public static final    int DELAY_TIME = 1;

	
	public static boolean isUserValid(User user) {
		if (user == null) {
            return false;
        }
		long id = user.getId();
		String firstName = user.getFirstName();
		String middleName = user.getMiddleName();
		String lastName = user.getLastName();
		String passport = user.getPassport();
		String email = user.getEmail();
		Role role = user.getRole();
		
		return 
				 id >= 0 &&
				 firstName != null && isName(firstName) && 
				 middleName != null && ((isName(middleName) || middleName.isBlank())) &&
				 lastName != null && isName(lastName) &&
				 passport != null && isPassport(passport) &&
				 email != null && isEmail(email) &&
				 role != null;
	}

	
	public static boolean isSubscriberValid(Subscriber subscriber) {
		if (subscriber == null) {
            return false;
        }
		long id = subscriber.getId();
		Date contractDate = subscriber.getContractDate(); 
		String phone = subscriber.getPhone();
		Date statusDate = subscriber.getStatusDate();
		SubscriberStatus status = subscriber.getStatus();
		long planId = subscriber.getPlanId();
		long userId = subscriber.getUserId();

		Date currentDelayed = new Date((new Date()).getTime() + DELAY_TIME);
		
		return 
				id >= 0 &&
				contractDate != null && contractDate.before(currentDelayed) && 
			    phone != null && !phone.isBlank() && isPhone(phone) &&
				statusDate != null && statusDate.before(currentDelayed) && 
				status != null &&
				planId > 0 &&
				userId >= 0;
	}

	
	public static boolean isPlanValid(Plan plan) {
		if (plan == null) {
            return false;
        }
		long id = plan.getId();
		String name = plan.getName();
		String description = plan.getDescription();
		int regularPayment = plan.getRegularPayment();
		int upfrontPayment = plan.getUpfrontPayment();
		int priceWithinNetwork = plan.getPriceWithinNetwork();
		int priceOtherNetworks = plan.getPriceOtherNetworks();
		int priceAbroad = plan.getPriceAbroad();
		int priceVideocall = plan.getPriceVideocall();
		int priceSMS = plan.getPriceSMS();
		int priceMMS = plan.getPriceMMS();
		int priceInternet = plan.getPriceInternet();
		
		return 
				id >= 0 &&
				name != null && !name.isBlank() &&
				description != null && !description.isBlank() &&
				regularPayment >= 0	&&
				upfrontPayment >= 0 &&
				priceWithinNetwork >=0 &&
				priceOtherNetworks >=0 &&
				priceAbroad >=0 &&
				priceVideocall >=0 &&
				priceSMS >=0 &&
				priceMMS >=0 &&
				priceInternet >=0;
	}

	
	public static boolean isServiceValid(Service service) {
		if (service == null) {
            return false;
        }
		long id = service.getId();
		String name = service.getName();
		String description = service.getDescription();
		int tariff = service.getTariff();

		return 
				id >= 0 &&
				name != null && !name.isBlank() &&
				description != null && !description.isBlank() &&
				tariff >= 0;
	}

	
	public static boolean isArticleValid(Article article) {
		if (article == null) {
            return false;
        }
		long id = article.getId();
		Date date = article.getDate();
		String title = article.getTitle();
		String intro = article.getIntro();
		String text = article.getText();

		return 
				id >= 0 &&
				date != null &&
			   title != null && !title.isBlank() &&
				intro != null && !intro.isBlank() &&
				text != null && !text.isBlank();
	}


	public static boolean isEmail(String anyString) {
		return isMatchesPattern(anyString, EMAIL_REGEX);
	}

	public static boolean isPhone(String anyString) {
		return isMatchesPattern(anyString, PHONE_NUMBER_REGEX);
	}

	public static boolean isName(String anyString) {
		return isMatchesPattern(anyString, NAME_REGEX);
	}
	
	public static boolean isPassport(String passport) {
		return isMatchesPattern(passport, PASSPORT_REGEX);
		
	}

	public static boolean isPassword(String password) {
		return isMatchesPattern(password, PASSWORD_REGEX);
	}
	
	private static boolean isMatchesPattern(String anyString, String regex) {
		return Pattern.matches(regex, anyString);
	}

}
