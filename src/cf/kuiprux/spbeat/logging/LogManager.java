package cf.kuiprux.spbeat.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

//Logger를 관리하고 수집된 로그를 파일로 출력
public class LogManager {
	
	public static final String LOG_DIRECTORY = "logs";
	
	private Logger logger;

	public LogManager(String name) {
		this.logger = Logger.getLogger(name);
	}
	
	public Logger getLogger() {
		return logger;
	}

	public void log(Level level, String msg, Throwable thrown) {
		getLogger().log(level, msg, thrown);
	}

}
