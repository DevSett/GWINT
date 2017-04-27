import org.apache.log4j.Logger;

import java.awt.print.PrinterException;

/**
 * Created by killsett on 20.04.17.
 */
public class mainTest {
    public static void main(String[] args) {
        Logger log = Logger.getLogger(mainTest.class);
        log.info("Информация",new Exception());
        log.error("Ошибка",new PrinterException());
    }
}
