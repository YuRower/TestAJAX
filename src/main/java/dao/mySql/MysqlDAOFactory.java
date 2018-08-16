package dao.mySql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.AbstractDAOFactory;
import dao.UserDAO;
import web.exception.DBException;
import web.exception.Messages;


public class MysqlDAOFactory extends AbstractDAOFactory {

	private static final Logger LOG = LogManager.getLogger(MysqlDAOFactory.class);
	
	private static MysqlDAOFactory instance;
	/**
	 * Singletone pattern implementation
	 * @return instance of this class
	 * @throws DBException if initialization failed.
	 */
	public static synchronized MysqlDAOFactory getInstance() throws DBException {
		if (instance == null) {
			instance = new MysqlDAOFactory();
		}
		return instance;
	}
	
	private DataSource ds;
	
	/**
	 * Private constructor that configures datasource.
	 * 
	 * @throws DBException
	 */
	private MysqlDAOFactory() throws DBException {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/web");
			LOG.trace("Data source ==> " + ds);
		} catch (NamingException ex) {
			LOG.error(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
		}
	}
	
	/**
	 * Returns a DB connection from the Pool of Connections. Before using this
	 * method you must configure the Date Source and the Connections Pool in
	 * your WEB_APP_ROOT/META-INF/context.xml file.
	 * 
	 * @return DB connection.
	 */
	public Connection getConnection() throws DBException {
		Connection con = null;
		try {
			con = ds.getConnection();
		} catch (SQLException ex) {
			LOG.error(Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex);
		}
		return con;
	}
	
	/**
	 * Closes a connection.
	 * 
	 * @param con
	 *            Connection to be closed.
	 */
	public void close(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException ex) {
				LOG.error(Messages.ERR_CANNOT_CLOSE_CONNECTION, ex);
			}
		}
	}

	/**
	 * Closes a statement object.
	 * 
	 * @param stmt
	 *            Statement to be closed.
	 */
	public void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException ex) {
				LOG.error(Messages.ERR_CANNOT_CLOSE_STATEMENT, ex);
			}
		}
	}

	/**
	 * Closes a result set object.
	 * 
	 * @param rs
	 *            Result set to be closed.
	 */
	public void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {
				LOG.error(Messages.ERR_CANNOT_CLOSE_RESULTSET, ex);
			}
		}
	}

	/**
	 * Closes resources.
	 * 
	 * @param con
	 *            Connection to be closed.
	 * 
	 * @param stmt
	 *            Statement to be closed.
	 *
	 * @param rs
	 *            Result set to be closed.
	 * 
	 */
	public void close(Connection con, Statement stmt, ResultSet rs) {
		close(rs);
		close(stmt);
		close(con);
	}

	/**
	 * Rollbacks a connection.
	 * 
	 * @param con
	 *            Connection to be rollbacked.
	 */
	public void rollback(Connection con) {
		if (con != null) {
			try {
				con.rollback();
			} catch (SQLException ex) {
				LOG.error("Cannot rollback transaction", ex);
			}
		}
	}
	
	
	@Override
	public UserDAO getUserDAO()  {
		return new UserDAOImpl(instance);
	}


}