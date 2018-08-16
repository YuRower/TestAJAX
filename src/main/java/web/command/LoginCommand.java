package web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.AbstractDAOFactory;
import dao.AbstractDAOFactory.FactoryTypes;
import dao.UserDAO;
import entity.User;
import web.Path;
import web.RequestProcessorInfo;
import web.RequestProcessorInfo.ProcessorMode;
import web.Util.security.PasswordHasher;
import web.exception.ApplicationException;

public class LoginCommand extends Command {

	private static final Logger LOG = LogManager.getLogger(LoginCommand.class);

	@Override
	public RequestProcessorInfo execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, ApplicationException {
		LOG.debug("Command starts");

		HttpSession session = request.getSession();

		AbstractDAOFactory mysqlFactory = AbstractDAOFactory.getDAOFactory(FactoryTypes.MYSQL);
		UserDAO userDao = mysqlFactory.getUserDAO();

		String email = request.getParameter("email");
		System.out.println("Request parameter: email --> " + email);

		String password = request.getParameter("password");
		if (email == null || password == null || email.trim().isEmpty() || password.trim().isEmpty()) {
			throw new ApplicationException("Email/password cannot be empty");
		}

		User user = userDao.findUserByEmail(email);
		System.out.println("Found in DB: user --> " + user);

		if (user == null || !PasswordHasher.checkPassword(password, user.getPassword())) {
			throw new ApplicationException("Cannot find user with such login/password");
		}

		String forward;
		forward = Path.PAGE_REGISTRATION;
		session.setAttribute("user", user);
		System.out.println("Set the session attribute: user --> " + user);

		LOG.debug("Command finished");
		return new RequestProcessorInfo(ProcessorMode.REDIRECT, forward);
	}

}