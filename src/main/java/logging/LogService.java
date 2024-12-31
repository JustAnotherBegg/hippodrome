package logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogService {

    /*
    Так как для всех записей лога у нас одинаковая логика, решил вынести просто в отдельный класс.
    Возможно имеет смысл для каждого класса создать свой логгер.
     */
    public static Logger logger = LoggerFactory.getLogger(LogService.class);

}
