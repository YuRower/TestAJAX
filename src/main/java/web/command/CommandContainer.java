package web.command;

import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;



public class CommandContainer {
	
	private static final Logger LOG = LogManager.getLogger(CommandContainer.class);
	
	private static Map<String, Command> commands = new TreeMap<String, Command>();
	
	static {
		// common commands
	
		commands.put("commandNotFound", new NoCommand());
		
		// user commands
		commands.put("registration", new RegistrationCommand());
		commands.put("redirectRegistration", new RedirectRegistrationCommand());
		commands.put("redirectRegistrationCompleted", new RedirectRegistrationCompletedCommand());
		
		LOG.debug("Command container was successfully initialized");
		LOG.trace("Number of commands --> " + commands.size());
	}

	
	public static Command get(String commandName) {
		if (commandName == null || !commands.containsKey(commandName)) {
			LOG.trace("Command not found, name --> " + commandName);
			return commands.get("commandNotFound"); 
		}
		
		return commands.get(commandName);
	}
	
}