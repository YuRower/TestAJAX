package web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import web.Path;
import web.RequestProcessorInfo;
import web.RequestProcessorInfo.ProcessorMode;
import web.exception.ApplicationException;

public class RedirectRegistrationCompletedCommand extends Command {

	private static final Logger LOG = LogManager.getLogger(RedirectRegistrationCompletedCommand.class);

	@Override
	public RequestProcessorInfo execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, ApplicationException {
		LOG.debug("Command starts");

		LOG.trace("Redirecting to registration completed page");

		LOG.debug("Command finished");
		return new RequestProcessorInfo(ProcessorMode.FORWARD, Path.PAGE_REGISTRATION_COMPLETED);
	}

}