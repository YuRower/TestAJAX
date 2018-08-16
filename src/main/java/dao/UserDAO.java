package dao;

import entity.User;
import web.exception.DBException;

public interface UserDAO  {
	boolean isEmailInUse(String email) throws DBException;
	boolean newUserWithDefaultValues(User user) throws DBException;
	User findUserByEmail(String email) throws DBException;
}