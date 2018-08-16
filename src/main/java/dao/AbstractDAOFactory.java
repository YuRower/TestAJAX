package dao;

import dao.mySql.MysqlDAOFactory;
import web.exception.DBException;

public abstract class AbstractDAOFactory {
	
	public enum FactoryTypes{
		MYSQL
	}

	public abstract UserDAO getUserDAO();


	public static AbstractDAOFactory getDAOFactory(FactoryTypes type) throws DBException {

		switch (type) {
		case MYSQL:
			return MysqlDAOFactory.getInstance();
		default:
			return null;
		}
	}

}