package web.command;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import web.mail.MailProperty;
import web.mail.MailThread;

public class RegistrationCommand extends Command {

	private static final Logger LOG = LogManager.getLogger(RegistrationCommand.class);

	@Override
	public RequestProcessorInfo execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, ApplicationException {
		LOG.debug("Command starts");

		AbstractDAOFactory mysqlFactory = AbstractDAOFactory.getDAOFactory(FactoryTypes.MYSQL);
		UserDAO userDao = mysqlFactory.getUserDAO();

		String email = request.getParameter("email");
		LOG.trace("Request parameter: email --> " + email);
		String message = request.getParameter("message");
		System.out.println(message);
		
		String password = request.getParameter("password");
		if (password == null || password.trim().isEmpty()) {// || !password.matches(RegularExpressions.PASSWORD)) {
			System.out.println(password);

			throw new ApplicationException("Invalid password");
		}
		password = PasswordHasher.getHash(password);

		String name = request.getParameter("name");
		LOG.trace("Request parameter: name --> " + name);
		System.out.println("name" + name);
		String surname = request.getParameter("surname");
		LOG.trace("Request parameter: surname --> " + surname);

		if (name == null || surname == null || name.trim().isEmpty() || surname.trim().isEmpty()) {
			throw new ApplicationException("Invalid name or/and surname");
		}

		// random string that will be used for verification purposes
		 String emailVerification = UUID.randomUUID().toString();
		User user = new User(name, surname, email, password);
		LOG.trace("Created user --> " + user);

		String to = "automybankepm@gmail.com";
		String from = email;
		String subject = "Order Confirmation";
		// user.getFirstName()
		String body = message;
		MailThread mail = new MailThread(to, from, subject, body, MailProperty.getInstance().getProperties());
		mail.start();

		MailSender sender = new MailSender();
		sender.composeMessage(email,request);
		userDao.newUserWithDefaultValues(user);
		LOG.trace("Saved user --> " + user);

		LOG.debug("Command finished");
		return new RequestProcessorInfo(ProcessorMode.REDIRECT, Path.COMMAND_REDIRECT_REGISTRATION_COMPLETED);

	}

}