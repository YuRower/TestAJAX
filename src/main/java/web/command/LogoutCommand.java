package web.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import web.Path;
import web.RequestProcessorInfo;
import web.RequestProcessorInfo.ProcessorMode;


public class LogoutCommand extends Command {
	
	private static final Logger LOG = LogManager.getLogger(LogoutCommand.class);

	@Override
	public RequestProcessorInfo execute(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("Command starts");
		
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}

		LOG.debug("Command finished");
		return new RequestProcessorInfo(ProcessorMode.REDIRECT,Path.PAGE_LOGIN);
	}

}