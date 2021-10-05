package by.epamjwd.mobile.dao;

import java.util.Optional;

import by.epamjwd.mobile.bean.Abonent;
import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.dao.exception.DaoException;

public interface AbonentDAO extends UserDAO{

	public Optional<Abonent> getAbonentByPhoneNumber(int phoneNumber) throws DaoException;

	
}
