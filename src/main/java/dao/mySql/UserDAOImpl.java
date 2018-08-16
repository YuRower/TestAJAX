package dao.mySql;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.UserDAO;
import entity.User;
import web.exception.DBException;
import web.exception.Messages;


public class UserDAOImpl implements UserDAO {

	private static final Logger LOG = LogManager.getLogger(UserDAOImpl.class);

	private MysqlDAOFactory factory;

	public UserDAOImpl(MysqlDAOFactory factory) {
		this.factory = factory;
	}

	// Users table attributes
	public static final String USER_ID = "user_id";
	public static final String USER_NAME = "name";
	public static final String USER_SURNAME = "surname";
	public static final String USER_EMAIL = "email";
	public static final String USER_PASSWORD = "password";
	
	
	// SQL queries
	private static final String SQL_FIND_USER_BY_EMAIL = "SELECT * FROM users WHERE email=?";
	private static final String SQL_USER_WITH_EMAIL_EXISTS = "SELECT EXISTS(SELECT * FROM Users WHERE email = ?)";
	private static final String SQL_INSERT_USER_SHORT_VARIANT = "INSERT INTO users VALUES (DEFAULT, ?, ?, ?, ?)";

	
	@Override
	public boolean newUserWithDefaultValues(User user) throws DBException {
		boolean result = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = factory.getConnection();
			pstmt = con.prepareStatement(SQL_INSERT_USER_SHORT_VARIANT, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getSurname());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getPassword());
			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					user.setId(rs.getInt(1));
					result = true;
				}
			}
			con.commit();
		} catch (SQLException ex) {
			factory.rollback(con);
			LOG.error(Messages.ERR_CANNOT_INSERT_USER, ex);
			throw new DBException(Messages.ERR_CANNOT_INSERT_USER, ex);
		} finally {
			factory.close(con, pstmt, rs);
		}
		return result;
	}



	@Override
	public boolean isEmailInUse(String email) throws DBException {
		boolean result = true;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = factory.getConnection();
			pstmt = con.prepareStatement(SQL_USER_WITH_EMAIL_EXISTS);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			rs.next();
			result = rs.getBoolean(1);
			con.commit();
		} catch (SQLException ex) {
			factory.rollback(con);
		} finally {
			factory.close(con, pstmt, rs);
		}
		return result;
	}
	
	@Override
	public User findUserByEmail(String email) throws DBException  {
		User user = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = factory.getConnection();
			pstmt = con.prepareStatement(SQL_FIND_USER_BY_EMAIL);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = extractUser(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			factory.rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, ex);
		} finally {
			factory.close(con, pstmt, rs);
		}
		return user;
}
	private User extractUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt(USER_ID));
		user.setName(rs.getString(USER_NAME));
		user.setSurname(rs.getString(USER_SURNAME));
		user.setEmail(rs.getString(USER_EMAIL));
		user.setPassword(rs.getString(USER_PASSWORD));
	
		return user;
	}

}

