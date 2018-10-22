package web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import web.RequestProcessorInfo.ProcessorMode;
import web.command.Command;
import web.command.CommandContainer;
import web.exception.ApplicationException;

@WebServlet("/Controller")

public class Controller extends HttpServlet {

	private static final long serialVersionUID = -2914049413872333511L;

	private static final Logger LOG = LogManager.getLogger(Controller.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		System.out.println("Controller starts");

		String commandName = request.getParameter("command");
		System.out.println("Request parameter: command --> " + commandName);

		Command command = CommandContainer.get(commandName);
		System.out.println("Obtained command --> " + command);

		RequestProcessorInfo requestProcessorInfo = new RequestProcessorInfo(ProcessorMode.FORWARD,
				Path.PAGE_REGISTRATION);

		try {
			requestProcessorInfo = command.execute(request, response);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

		System.out.println("Forward address --> " + requestProcessorInfo.getPath());
		System.out.println("Controller finished, now go to forward address --> " + requestProcessorInfo.getPath());

		
	}
}