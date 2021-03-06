package by.epamjwd.mobile.service.validation;

import org.junit.Assert;
import org.junit.Test;

import by.epamjwd.mobile.bean.Role;
import by.epamjwd.mobile.bean.User;

public class IsUserValidTest {

	private final User goodUser1 = new User(1L, "", "John", " ", "Smith", "AB1234567", 
			"goodUser1@gmail.com", Role.SUBSCRIBER);
	
	private final User goodUser2 = new User(0, "", "Иван", "Иванович", "Петров", "CD8912345", 
			"goodUser2@gmail.com", Role.ADMIN);
	
	private final User goodUser3 = new User(100001L, "", "Пётр", "Иванович", "Иванов", "DV5896541", 
			"goodUser3@gmail.com", Role.CONSULTANT);
	
	private final User badUser1 = new User(-1L, "", "Андрей", "Андреевич", "Андреев", "DV5896541", 
			"badUser1@gmail.com", Role.SUBSCRIBER);
	
	private final User badUser2 = new User(10L, "", " ", "Васильевич", "Васильев", "SF4588526", 
			"badUser2@gmail.com", Role.SUBSCRIBER); 
	
	private final User badUser3 = new User(0, "", "Фёдор", "Михайлович", " ", "TA8523697", 
			"badUser3@gmail.com", Role.SUBSCRIBER); 
	
	private final User badUser4 = new User(10L, "", "Василий", "Васильевич", "Васильев", "SF458852", 
			"badUser4@gmail.com", Role.SUBSCRIBER); // short passport number
	
	private final User badUser5 = new User(10L, "", "Василий", "Васильевич", "Васильев", "SF4588527", 
			"badUser5Agmail.com", Role.SUBSCRIBER); //bad e-mail
	
	private final User badUser6 = new User(0, "", "Александр", "Сергеевич", "Пушкин", "PC4567892", 
			"canon@mail.ru", null); 
	
	private final static User badUser7 = new User();
	
	private final User badUser8 = new User(0, "", null, "Сергеевич", "Пушкин", "PC4567892", 
			"canon@mail.ru", Role.SUBSCRIBER); 
	
	private final User badUser9 = new User(0, "", "Александр", null, "Пушкин", "PC4567892", 
			"canon@mail.ru", Role.SUBSCRIBER); 
	
	private final User badUser10 = new User(0, "", "Александр", "Сергеевич", null, "PC4567892", 
			"canon@mail.ru", Role.SUBSCRIBER); 
	
	private final User badUser11 = new User(0, "", "Александр", "Сергеевич", "Пушкин", null, 
			"canon@mail.ru", Role.SUBSCRIBER); 
	
	private final User badUser12 = new User(0, "", "Александр", "Сергеевич", "Пушкин", "PC4567892", 
			null, Role.SUBSCRIBER); 
	
	private final User badUser13 = new User(0, "", "Александр", "Сергеевич", "Пушкин", " ", 
			"canon@mail.ru", Role.SUBSCRIBER); 
	
	private final User badUser14 = new User(0, "", "Александр", "Сергеевич", "Пушкин", "PC4567892", 
			" ", Role.SUBSCRIBER); 
	
	@Test
	public void goodUser1Test() {
		Assert.assertTrue(InputDataValidator.isUserValid(goodUser1));
	}
	
	@Test
	public void goodUser2Test() {
		Assert.assertTrue(InputDataValidator.isUserValid(goodUser2));
	}
	
	@Test
	public void goodUser3Test() {
		Assert.assertTrue(InputDataValidator.isUserValid(goodUser3));
	}
	
	@Test
	public void badUser1Test() {
		Assert.assertFalse(InputDataValidator.isUserValid(badUser1));
	}
	
	@Test
	public void badUser2Test() {
		Assert.assertFalse(InputDataValidator.isUserValid(badUser2));
	}
	
	@Test
	public void badUser3Test() {
		Assert.assertFalse(InputDataValidator.isUserValid(badUser3));
	}
	
	@Test
	public void badUser4Test() {
		Assert.assertFalse(InputDataValidator.isUserValid(badUser4));
	}
	
	@Test
	public void badUser5Test() {
		Assert.assertFalse(InputDataValidator.isUserValid(badUser5));
	}
	
	@Test
	public void badUser6Test() {
		Assert.assertFalse(InputDataValidator.isUserValid(badUser6));
	}

	@Test
	public void badUser7Test() {
		Assert.assertFalse(InputDataValidator.isUserValid(badUser7));
	}
	
	@Test
	public void badUser8Test() {
		Assert.assertFalse(InputDataValidator.isUserValid(badUser8));
	}
	
	@Test
	public void badUser9Test() {
		Assert.assertFalse(InputDataValidator.isUserValid(badUser9));
	}
	
	@Test
	public void badUser10Test() {
		Assert.assertFalse(InputDataValidator.isUserValid(badUser10));
	}
	
	@Test
	public void badUser11Test() {
		Assert.assertFalse(InputDataValidator.isUserValid(badUser11));
	}
	
	@Test
	public void badUser12Test() {
		Assert.assertFalse(InputDataValidator.isUserValid(badUser12));
	}
	
	@Test
	public void badUser13Test() {
		Assert.assertFalse(InputDataValidator.isUserValid(badUser13));
	}
	
	@Test
	public void badUser14Test() {
		Assert.assertFalse(InputDataValidator.isUserValid(badUser14));
	}
	

}
