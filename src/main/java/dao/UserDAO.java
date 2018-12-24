package dao;

import entity.User;
import web.exception.DBException;

public interface UserDAO  {
	User findUserByEmail(String email) throws DBException;

	boolean newUserWithDefaultValues(User user) throws DBException;
}